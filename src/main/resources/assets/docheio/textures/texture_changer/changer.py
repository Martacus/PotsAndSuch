from PIL import Image
import os

# Get the current working directory
cwd = os.getcwd()

# Define the image filename
filename = "block\\pot.png"

# Construct the full path to the image file
image_path = os.path.join(cwd, filename)

# Open the image


# Define the colors to be replaced and the new colors to be used
old_colors = [(165, 93, 67, 255), (155, 89, 65, 255), (145, 84, 62, 255), (128, 75, 56, 255), (107, 68, 54, 255), (90, 60, 49, 255), (78, 55, 46, 255)]  # Red, green, and blue

colors = {
    "clay": [
      (175, 185, 214, 255), 
      (170, 177, 200, 255), 
      (172, 174, 189, 255), 
      (159, 164, 174, 255),
      (142, 150, 165, 255),
      (132, 137, 149, 255),
      (122, 126, 134, 255)
    ],
    "red": [
        (163, 74, 60, 255),
        (157, 69, 55, 255),
        (149, 65, 52, 255),
        (137, 59, 46, 255),
        (123, 53, 42, 255),
        (109, 48, 38, 255),
        (98, 43, 36, 255)
    ],
    "black": [
        (67, 44, 34, 255),
        (61, 39, 31, 255),
        (56, 35, 27, 255),
        (48, 29, 22, 255),
        (37, 22, 16, 255),
        (28, 17, 12, 255),
        (20, 12, 9, 255)
    ],
    "gray": [
        (73, 55, 44, 255),
        (66, 50, 41, 255),
        (64, 47, 39, 255),
        (57, 41, 34, 255),
        (48, 35, 29, 255),
        (42, 30, 25, 255),
        (36, 25, 22, 255)
    ],
    "brown": [
        (95, 64, 47, 255),
        (89, 59, 42, 255),
        (84, 55, 38, 255),
        (76, 49, 34, 255),
        (67, 42, 31, 255),
        (56, 35, 26, 255),
        (45, 27, 21, 255)
    ],
    "light_gray": [
        (154, 127, 114, 255),
        (148, 120, 109, 255),
        (143, 114, 103, 255),
        (133, 105, 96, 255),
        (118, 94, 87, 255),
        (101, 83, 78, 255),
        (91, 67, 64, 255)
    ]
}

# Iterate over the pixels in the image and replace the old colors with the new colors
for s in colors:
  image = Image.open(image_path)
  pixels = image.load()
  for i in range(image.size[0]):
    for j in range(image.size[1]):
        if pixels[i, j] in old_colors:
            index = old_colors.index(pixels[i, j])
            pixels[i, j] = colors[s][index]
  image_path_2 = os.path.join(cwd, "block\\pot_" + s + ".png")
  image.save(image_path_2)
    




