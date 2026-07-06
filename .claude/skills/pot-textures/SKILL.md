---
name: pot-textures
description: Pot texture pipeline for this mod (docheio) — audit and fix a pot shape's model UVs and texture canvases, then regenerate all 17 color variants from the hand-painted _normal texture using the palette-swap script. Use this whenever the user mentions pot textures, color variants, recoloring, texture seams, gaps or bleeding between model parts, see-through pixels at part boundaries, resizing or padding textures, pixel-exact UVs, changer.py, or getting another pot shape (small, tall, amphora, jug, jug_large, pitcher, planter, planter_small, vase, vase_large, flower) ready — even if they only describe a visual glitch like "I can see through the block" or "the sides look shifted".
compatibility: Requires Python 3 with Pillow (pip install pillow).
---

# Pot texture pipeline

Each pot shape in this mod has one template model, one hand-painted base texture
(`<shape>_normal.png`), 17 recolored variants generated from it, and optional
pattern overlay textures. This skill covers auditing a shape, fixing the two
classes of rendering bugs it can have, and regenerating the color variants.

## Why the bugs happen (read this before fixing anything)

**See-through seams between model parts** have two independent causes; fixing
only one is not enough:

1. **Fractional UVs.** Model UVs run 0–16 across the texture regardless of its
   pixel size. On an odd-sized canvas (the old textures were 48×29), pixel
   boundaries land on repeating decimals, Blockbench rounds them to 5 decimals
   on export, and nearest-neighbor sampling then reads the texel *next to* the
   intended one along face edges. The pots render via `RenderType.cutout()`, so
   a transparent neighbor texel becomes a discarded (invisible) pixel row.
   Fix: power-of-two canvas (64×32 for the basic pot) + pixel-exact UVs.
2. **Unfilled texture borders.** Even with exact UVs, if the texels just inside
   a face's UV rectangle are transparent, edges show holes — the same gutter/
   bleed problem as game-dev tilemaps. Fix: the art inside each face rectangle
   must be painted out to its border (this is hand work on `_normal` only; the
   generated colors inherit it).

**Wrong texture on colored pots / pattern overlays** happens when a template is
re-exported from Blockbench: it renames the texture variable `all` to `0` and
hardcodes a real texture path. The datagen color models
([DocheioBlockstates.java](../../src/main/java/com/mart/docheio/data/DocheioBlockstates.java))
and the pattern models (`models/block/pattern/*.json`) override the variable
`all`, so the template must declare `"all": "blank"` and every face must use
`"texture": "#all"`. **Always run `fix-model` after any Blockbench re-export.**

## Key paths (relative to repo root)

- Template models: `src/main/resources/assets/docheio/models/block/templates/<shape>.json`
  (multi-block shapes like `pot_amphora` and `pot_vase_large` also have
  `_lower`/`_upper` parts — fix all of them)
- Textures: `src/main/resources/assets/docheio/textures/block/<shape>_<color>.png`
- Pattern overlays: `src/main/resources/assets/docheio/textures/block/patterns/<shape>/*.png`
- Recolor script (single source of truth for the palettes):
  `src/main/resources/assets/docheio/textures/texture_changer/changer.py`
- Helper script for everything below: `scripts/pot_tex.py` (next to this file)

## Process for one shape

Run every command from the repo root. On Windows, `python` may resolve to the
Microsoft Store stub in some shells — if you see "Python was not found", use the
full interpreter path from `(Get-Command python).Source` in PowerShell. Always
run scripts as files; do not pipe Python code via heredocs on Windows.

1. **Audit first**: `python .claude/skills/pot-textures/scripts/pot_tex.py check <shape>`
   (e.g. `check pot_small`). It reports canvas size, non-pixel-exact UVs, wrong
   texture variables, wrong-sized textures, off-palette colors, and transparent
   face borders. If everything passes, stop — don't fix what isn't broken.

2. **Pick the target canvas** if the current one is odd-sized: the smallest
   power-of-two-per-axis canvas that fits the art (48×29 → 64×32). Then pad all
   of the shape's textures (17 colors + normal + its patterns folder):
   `python ... pot_tex.py pad --size 64x32 src/main/resources/assets/docheio/textures/block/<shape>_*.png src/main/resources/assets/docheio/textures/block/patterns/<shape>/*.png`
   Padding is transparent right/bottom margin; the art does not move, so the
   old UV pixel positions stay valid.

3. **Fix the template(s)**:
   `python ... pot_tex.py fix-model --size 64x32 --old-size 48x29 src/main/resources/assets/docheio/models/block/templates/<shape>.json`
   `--old-size` rescales UVs that were exported against the pre-padding canvas;
   omit it when the export already targeted the new canvas and only needs
   snapping + texture-variable repair. Heed any "snapped >0.35px" warnings —
   they mean the UV and the art genuinely disagree, so dump the texture's
   alpha map (Pillow) and compare against the face rectangles before trusting
   the result.

4. **Hand-fill the art (user's job)**: ask the user to fill in
   `<shape>_normal.png` so each face rectangle is painted to its borders, using
   only the 7-shade base palette (`OLD_COLORS` in changer.py). Re-run `check`
   afterwards: it flags off-palette colors (which the recolorer would skip) and
   remaining transparent border pixels. Border warnings on faces that are fully
   covered by neighboring geometry (e.g. a neck's top/bottom hidden inside the
   rim/body) are safe to ignore; interior holes (like the opening ring on an
   "up" face) are intentional and not flagged.

5. **Regenerate the 17 colors**:
   `python src/main/resources/assets/docheio/textures/texture_changer/changer.py src/main/resources/assets/docheio/textures/block/<shape>_normal.png`
   Output names derive from the base name (`pot_small_normal.png` →
   `pot_small_black.png`, …). It prints a warning listing any opaque colors it
   couldn't map — a clean run prints only "wrote ..." lines. Pattern textures
   are *not* recolored; they are cutout overlays whose transparent edges show
   the base pot through, which is correct.

6. **Verify**: `check <shape>` must print `OK`. Then have the user reload
   in-game (F3+T) and look at the seams between body/neck/rim from up close.

## Judgment calls the scripts can't make

- If `check` reports texture sizes that don't match the model canvas *and* the
  UVs are already pixel-exact for the model's `texture_size`, the textures were
  probably never padded — pad them (step 2) and skip `--old-size` in step 3.
- If the art was moved or redrawn rather than padded, `--old-size` math is
  invalid. Dump the alpha map and derive face rectangles from where the art
  actually is, then edit UVs to match (multiples of 16/width and 16/height).
- Some templates have deliberate extra texture slots besides `all` (e.g.
  `pot_small.json` has a `"1"` slot for a shiny/pattern overlay element). The
  scripts leave those faces alone — only the Blockbench artifact `#0` gets
  rewritten — but if a re-export renamed such a slot, restore it by hand from
  git history rather than pointing it at `#all`.
- Never resample/stretch the art. Every operation here preserves pixels
  exactly; anything else ruins pixel art and breaks the palette match.
