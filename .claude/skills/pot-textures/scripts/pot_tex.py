"""Pot texture pipeline helper for the docheio mod. Usage documented in ../SKILL.md.

Subcommands:
    check <shape>
        Audit a pot shape: template model UVs pixel-exact, texture variable
        naming, texture file sizes, palette conformance and edge-bleed risks
        in the _normal texture.

    pad --size WxH <png> [<png> ...]
        Grow PNG canvases to WxH with transparent right/bottom padding.
        Art stays at the same pixel coordinates (anchored top-left).

    fix-model --size WxH [--old-size WxH] <template.json> [...]
        Normalize a Blockbench-exported template: set texture_size, restore
        the "all" texture variable, and snap every UV to an exact pixel
        boundary of the WxH canvas. Pass --old-size when the UVs were
        exported against a smaller canvas that was since padded to WxH
        (pixels are assumed to have stayed in place, anchored top-left).
"""
import argparse
import importlib.util
import json
import os
import sys

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
REPO = os.path.abspath(os.path.join(SCRIPT_DIR, "..", "..", "..", ".."))
ASSETS = os.path.join(REPO, "src", "main", "resources", "assets", "docheio")
TEMPLATES = os.path.join(ASSETS, "models", "block", "templates")
BLOCK_TEX = os.path.join(ASSETS, "textures", "block")
CHANGER = os.path.join(ASSETS, "textures", "texture_changer", "changer.py")

SNAP_WARN_PIXELS = 0.35  # a snap larger than this means UVs and art truly disagree


def load_changer():
    """Import changer.py so the color palettes have a single source of truth."""
    spec = importlib.util.spec_from_file_location("changer", CHANGER)
    mod = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(mod)
    return mod


def parse_size(text):
    w, h = text.lower().split("x")
    return int(w), int(h)


def open_png(path):
    from PIL import Image
    with open(path, "rb") as f:
        data = f.read()
    import io
    return Image.open(io.BytesIO(data)).convert("RGBA")


# ---------------------------------------------------------------- check

def find_templates(shape):
    names = [shape + suffix + ".json" for suffix in ("", "_lower", "_upper")]
    return [os.path.join(TEMPLATES, n) for n in names
            if os.path.isfile(os.path.join(TEMPLATES, n))]


def check_template(path, problems, warnings):
    with open(path, encoding="utf-8") as f:
        model = json.load(f)
    rel = os.path.relpath(path, REPO)
    w, h = model.get("texture_size", [16, 16])

    tex_keys = set(model.get("textures", {}))
    if "all" not in tex_keys:
        problems.append(f"{rel}: textures block has keys {sorted(tex_keys)} but must "
                        f"declare the variable 'all' (datagen color models and pattern "
                        f"models override 'all'; run fix-model)")
    extra = tex_keys - {"all", "particle", "0"}
    if extra:
        warnings.append(f"{rel}: extra texture slots {sorted(extra)} - intentional "
                        f"overlays (e.g. a shiny/pattern layer); their faces are "
                        f"skipped by the canvas and bleed checks")

    face_rects = []  # (label, x0, y0, x1, y1) in pixels for the bleed audit
    for idx, elem in enumerate(model.get("elements", [])):
        ename = f"[{idx}]{elem.get('name', '?')}"
        for fname, face in elem.get("faces", {}).items():
            label = f"{os.path.basename(path)} {ename}/{fname}"
            ref = face.get("texture")
            if ref == "#0":
                problems.append(f"{rel}: {ename}/{fname} references '#0' (Blockbench "
                                f"export artifact) instead of '#all' (run fix-model)")
            elif ref != "#all":
                # A deliberate extra slot: its UVs are relative to a different
                # texture, so the canvas/bleed checks below don't apply to it.
                continue
            uv = face.get("uv")
            if not uv:
                continue
            for i, v in enumerate(uv):
                scale = (w if i % 2 == 0 else h) / 16.0
                px = v * scale
                if abs(px - round(px)) > 1e-3:
                    problems.append(f"{rel}: {ename}/{fname} uv[{i}]={v} is {px:.4f}px "
                                    f"on the {w}x{h} canvas - not pixel-exact (run fix-model)")
            x0, x1 = sorted((uv[0], uv[2]))
            y0, y1 = sorted((uv[1], uv[3]))
            face_rects.append((label,
                               int(round(x0 * w / 16)), int(round(y0 * h / 16)),
                               int(round(x1 * w / 16)), int(round(y1 * h / 16))))
    return (w, h), face_rects


def cmd_check(args):
    shape = args.shape
    problems, warnings, notes = [], [], []
    templates = find_templates(shape)
    if not templates:
        print(f"ERROR: no template model {shape}.json (or _lower/_upper) in {TEMPLATES}")
        return 1

    canvas = None
    all_rects = []
    for t in templates:
        size, rects = check_template(t, problems, warnings)
        all_rects.extend(rects)
        if canvas and size != canvas:
            problems.append(f"{os.path.basename(t)} texture_size {size} differs from "
                            f"other parts {canvas}")
        canvas = canvas or size
    w, h = canvas
    notes.append(f"templates: {', '.join(os.path.basename(t) for t in templates)} "
                 f"(canvas {w}x{h})")

    try:
        changer = load_changer()
    except Exception as e:  # most likely Pillow missing
        warnings.append(f"could not load changer.py ({e}); skipping texture checks - "
                        f"try 'pip install pillow'")
        changer = None

    if changer:
        colors = list(changer.COLORS) + ["normal"]
        for color in colors:
            p = os.path.join(BLOCK_TEX, f"{shape}_{color}.png")
            if not os.path.isfile(p):
                problems.append(f"missing texture {shape}_{color}.png")
                continue
            img = open_png(p)
            if img.size != (w, h):
                problems.append(f"{shape}_{color}.png is {img.size[0]}x{img.size[1]}, "
                                f"model expects {w}x{h} (run pad, or fix-model --old-size)")

        # Pattern overlay models (models/block/pattern/*.json) that inherit this
        # shape's template share its UV layout, so their textures must match the
        # canvas. Textures used by a template's own extra slots (e.g. pot_small's
        # shiny layer) have independent UVs and are deliberately not checked.
        pattern_model_dir = os.path.join(ASSETS, "models", "block", "pattern")
        overlay_count = 0
        if os.path.isdir(pattern_model_dir):
            for name in sorted(os.listdir(pattern_model_dir)):
                if not name.endswith(".json"):
                    continue
                with open(os.path.join(pattern_model_dir, name), encoding="utf-8") as f:
                    pm = json.load(f)
                if pm.get("parent", "").split("/templates/")[-1] != shape:
                    continue
                overlay_count += 1
                for ref in pm.get("textures", {}).values():
                    tex_rel = ref.split(":", 1)[-1] + ".png"
                    tex_path = os.path.join(ASSETS, "textures", *tex_rel.split("/"))
                    if not os.path.isfile(tex_path):
                        problems.append(f"pattern model {name} references missing "
                                        f"texture {ref}")
                        continue
                    img = open_png(tex_path)
                    if img.size != (w, h):
                        problems.append(f"pattern texture {ref} is "
                                        f"{img.size[0]}x{img.size[1]}, but the {shape} "
                                        f"canvas is {w}x{h}")
        notes.append(f"pattern overlay models inheriting {shape}: {overlay_count}")

        normal = os.path.join(BLOCK_TEX, f"{shape}_normal.png")
        if os.path.isfile(normal):
            img = open_png(normal)
            px = img.load()
            off_palette = set()
            if img.size == (w, h):
                for y in range(h):
                    for x in range(w):
                        p = px[x, y]
                        if p[3] > 0 and p not in changer.OLD_COLORS:
                            off_palette.add(p)
                for label, x0, y0, x1, y1 in all_rects:
                    holes = sum(1 for x in range(x0, min(x1, w))
                                for y in range(y0, min(y1, h))
                                if (x in (x0, x1 - 1) or y in (y0, y1 - 1))
                                and px[x, y][3] == 0)
                    if holes:
                        warnings.append(f"{shape}_normal.png: {label} has {holes} "
                                        f"transparent pixels on its border - bleed/seam "
                                        f"risk unless that face is hidden by other geometry")
            if off_palette:
                problems.append(f"{shape}_normal.png has {len(off_palette)} opaque colors "
                                f"outside the base palette (changer.py would leave them "
                                f"uncolored): {sorted(off_palette)[:5]}...")

    for n in notes:
        print("NOTE:", n)
    for wmsg in warnings:
        print("WARN:", wmsg)
    for p in problems:
        print("PROBLEM:", p)
    if not problems:
        print(f"OK: {shape} passed all hard checks "
              f"({len(warnings)} warning(s) to eyeball)")
    return 1 if problems else 0


# ---------------------------------------------------------------- pad

def cmd_pad(args):
    from PIL import Image
    w, h = parse_size(args.size)
    status = 0
    for path in args.files:
        img = open_png(path)
        if img.size == (w, h):
            print(f"skip (already {w}x{h}): {path}")
            continue
        if img.size[0] > w or img.size[1] > h:
            print(f"ERROR: {path} is {img.size[0]}x{img.size[1]}, larger than target")
            status = 1
            continue
        out = Image.new("RGBA", (w, h), (0, 0, 0, 0))
        out.paste(img, (0, 0))
        out.save(path)
        print(f"padded {img.size[0]}x{img.size[1]} -> {w}x{h}: {path}")
    return status


# ---------------------------------------------------------------- fix-model

def cmd_fix_model(args):
    w, h = parse_size(args.size)
    scale_u = scale_v = 1.0
    if args.old_size:
        ow, oh = parse_size(args.old_size)
        scale_u, scale_v = ow / w, oh / h

    for path in args.files:
        with open(path, encoding="utf-8") as f:
            model = json.load(f)
        model["texture_size"] = [w, h]
        textures = model.get("textures", {})
        textures.pop("0", None)       # Blockbench export artifact
        textures.pop("particle", None)  # datagen sets particle per color model
        textures["all"] = "blank"
        model["textures"] = textures
        for elem in model.get("elements", []):
            for fname, face in elem.get("faces", {}).items():
                if face.get("texture") == "#0":
                    face["texture"] = "#all"
                if face.get("texture") != "#all":
                    continue  # deliberate extra slot: UVs belong to another texture
                uv = face.get("uv")
                if not uv:
                    continue
                snapped = []
                for i, v in enumerate(uv):
                    v *= scale_u if i % 2 == 0 else scale_v
                    per_px = 16.0 / (w if i % 2 == 0 else h)
                    px = v / per_px
                    if abs(px - round(px)) > SNAP_WARN_PIXELS:
                        print(f"WARN: {os.path.basename(path)} "
                              f"{elem.get('name','?')}/{fname} uv[{i}] was "
                              f"{abs(px - round(px)):.2f}px off a pixel boundary - "
                              f"snapped, but verify the art location")
                    snapped.append(round(round(px) * per_px, 5))
                face["uv"] = snapped
        with open(path, "w", encoding="utf-8", newline="\n") as f:
            json.dump(model, f, indent="\t")
            f.write("\n")
        print(f"fixed: {path}")
    return 0


# ---------------------------------------------------------------- main

def main():
    ap = argparse.ArgumentParser(description=__doc__,
                                 formatter_class=argparse.RawDescriptionHelpFormatter)
    sub = ap.add_subparsers(dest="cmd", required=True)

    p = sub.add_parser("check")
    p.add_argument("shape", help="shape prefix, e.g. pot, pot_small, pot_amphora")
    p.set_defaults(fn=cmd_check)

    p = sub.add_parser("pad")
    p.add_argument("--size", required=True, help="target canvas, e.g. 64x32")
    p.add_argument("files", nargs="+")
    p.set_defaults(fn=cmd_pad)

    p = sub.add_parser("fix-model")
    p.add_argument("--size", required=True, help="canvas the textures actually have")
    p.add_argument("--old-size", help="canvas the UVs were exported against, if different")
    p.add_argument("files", nargs="+")
    p.set_defaults(fn=cmd_fix_model)

    args = ap.parse_args()
    sys.exit(args.fn(args))


if __name__ == "__main__":
    main()
