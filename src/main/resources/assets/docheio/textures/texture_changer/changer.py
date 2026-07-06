"""Regenerates the 17 colored variants of a pot texture from its base (clay/normal) texture.

Usage:
    python changer.py <path-to-base-texture> [more base textures...]

Example:
    python changer.py ../block/pot_normal.png
        -> writes pot_black.png, pot_blue.png, ... next to pot_normal.png

The base texture must be painted with the 7-shade terracotta palette below
(OLD_COLORS). Every variant is produced by swapping that palette for the
matching 7-shade palette in COLORS; pixels outside the palette are left as-is.
Output name: '<base>_normal.png' -> '<base>_<color>.png' (a trailing '_normal'
is stripped; otherwise the color is appended to the stem).
"""

import sys
import os
from PIL import Image

OLD_COLORS = [
    (165, 93, 67, 255),
    (155, 89, 65, 255),
    (145, 84, 62, 255),
    (128, 75, 56, 255),
    (107, 68, 54, 255),
    (90, 60, 49, 255),
    (78, 55, 46, 255),
]

COLORS = {
    "clay": [
        (175, 185, 214, 255),
        (170, 177, 200, 255),
        (172, 174, 189, 255),
        (159, 164, 174, 255),
        (142, 150, 165, 255),
        (132, 137, 149, 255),
        (122, 126, 134, 255),
    ],
    "black": [
        (67, 44, 34, 255),
        (61, 39, 31, 255),
        (56, 35, 27, 255),
        (48, 29, 22, 255),
        (37, 22, 16, 255),
        (28, 17, 12, 255),
        (20, 12, 9, 255),
    ],
    "gray": [
        (73, 55, 44, 255),
        (66, 50, 41, 255),
        (64, 47, 39, 255),
        (57, 41, 34, 255),
        (48, 35, 29, 255),
        (42, 30, 25, 255),
        (36, 25, 22, 255),
    ],
    "brown": [
        (95, 64, 47, 255),
        (89, 59, 42, 255),
        (84, 55, 38, 255),
        (76, 49, 34, 255),
        (67, 42, 31, 255),
        (56, 35, 26, 255),
        (45, 27, 21, 255),
    ],
    "light_gray": [
        (154, 127, 114, 255),
        (148, 120, 109, 255),
        (143, 114, 103, 255),
        (133, 105, 96, 255),
        (118, 94, 87, 255),
        (101, 83, 78, 255),
        (91, 67, 64, 255),
    ],
    "white": [
        (232, 202, 183, 255),
        (225, 194, 178, 255),
        (219, 186, 171, 255),
        (207, 175, 159, 255),
        (188, 157, 143, 255),
        (171, 142, 130, 255),
        (160, 133, 123, 255),
    ],
    "cyan": [
        (109, 116, 121, 255),
        (103, 109, 112, 255),
        (96, 102, 104, 255),
        (86, 91, 92, 255),
        (75, 80, 81, 255),
        (65, 69, 70, 255),
        (56, 58, 59, 255),
    ],
    "light_blue": [
        (131, 126, 157, 255),
        (126, 121, 150, 255),
        (121, 116, 146, 255),
        (112, 107, 137, 255),
        (99, 95, 118, 255),
        (86, 82, 101, 255),
        (78, 73, 90, 255),
    ],
    "blue": [
        (92, 77, 112, 255),
        (86, 72, 105, 255),
        (82, 68, 101, 255),
        (75, 61, 93, 255),
        (65, 53, 80, 255),
        (57, 48, 71, 255),
        (48, 39, 59, 255),
    ],
    "purple": [
        (132, 76, 94, 255),
        (125, 72, 92, 255),
        (118, 70, 87, 255),
        (104, 63, 75, 255),
        (90, 56, 64, 255),
        (81, 50, 58, 255),
        (70, 43, 50, 255),
    ],
    "magenta": [
        (171, 106, 121, 255),
        (164, 99, 114, 255),
        (157, 94, 111, 255),
        (143, 84, 105, 255),
        (132, 79, 94, 255),
        (115, 71, 85, 255),
        (98, 62, 73, 255),
    ],
    "pink": [
        (182, 97, 98, 255),
        (175, 92, 93, 255),
        (168, 86, 87, 255),
        (164, 77, 78, 255),
        (140, 70, 71, 255),
        (123, 63, 62, 255),
        (109, 58, 57, 255),
    ],
    "red": [
        (163, 74, 60, 255),
        (157, 69, 55, 255),
        (149, 65, 52, 255),
        (137, 59, 46, 255),
        (123, 53, 42, 255),
        (109, 48, 38, 255),
        (98, 43, 36, 255),
    ],
    "orange": [
        (182, 105, 55, 255),
        (180, 98, 52, 255),
        (171, 94, 50, 255),
        (157, 86, 43, 255),
        (143, 75, 39, 255),
        (129, 69, 42, 255),
        (123, 60, 36, 255),
    ],
    "yellow": [
        (196, 144, 51, 255),
        (189, 137, 46, 255),
        (182, 132, 44, 255),
        (168, 121, 38, 255),
        (151, 107, 39, 255),
        (132, 94, 37, 255),
        (120, 84, 29, 255),
    ],
    "lime": [
        (129, 140, 67, 255),
        (119, 132, 61, 255),
        (113, 126, 58, 255),
        (100, 115, 50, 255),
        (88, 101, 44, 255),
        (76, 87, 40, 255),
        (62, 70, 33, 255),
    ],
    "green": [
        (100, 104, 60, 255),
        (96, 100, 55, 255),
        (91, 95, 52, 255),
        (79, 83, 44, 255),
        (69, 73, 40, 255),
        (62, 64, 36, 255),
        (51, 53, 30, 255),
    ],
}


def generate_variants(base_path):
    directory = os.path.dirname(base_path)
    stem = os.path.splitext(os.path.basename(base_path))[0]
    prefix = stem[: -len("_normal")] if stem.endswith("_normal") else stem

    base = Image.open(base_path).convert("RGBA")
    unmatched = {p for p in base.getdata() if p[3] > 0 and p not in OLD_COLORS}
    if unmatched:
        print(f"WARNING: {base_path} has {len(unmatched)} opaque colors outside the base palette; they will be kept as-is:")
        for p in sorted(unmatched):
            print(f"    {p}")

    for name, palette in COLORS.items():
        mapping = dict(zip(OLD_COLORS, palette))
        variant = base.copy()
        pixels = variant.load()
        for x in range(variant.width):
            for y in range(variant.height):
                pixels[x, y] = mapping.get(pixels[x, y], pixels[x, y])
        out_path = os.path.join(directory, f"{prefix}_{name}.png")
        variant.save(out_path)
        print(f"wrote {out_path}")


if __name__ == "__main__":
    if len(sys.argv) < 2:
        sys.exit(__doc__)
    for arg in sys.argv[1:]:
        generate_variants(arg)
