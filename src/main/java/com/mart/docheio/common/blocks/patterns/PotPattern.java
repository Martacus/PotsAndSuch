package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;

public class PotPattern {

    public enum UPPER implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        EYES(1, "eyes", "Eyes"),
        CHECKERS(2, "checkers", "Checkers"),
        RIM_BLOCKS(3, "rim_blocks", "Rim Blocks"),
        INVERSE_EYES(4, "inverse_eyes", "Inverse Eyes"),
        RIM_SPECKS(5, "rim_specks", "Rim Specks");

        private final int id;
        private final String name;
        private final String representName;

        @Override
        public String getSerializedName() {
            return name;
        }

        UPPER(int id, String name, String representName) {
            this.id = id;
            this.name = name;
            this.representName = representName;
        }

        public int getId() {
            return id;
        }

        public static UPPER getById(int i) {
            for (UPPER e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return UPPER.TRANSPARENT;
        }
    }

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        WIGGLE(1, "wiggle", "Wiggle"),
        BOLD(2, "bold", "Bold"),
        ZAG(3, "zag", "Zag"),
        LINES(4, "linespeck", "Line Speck"),
        BLOCKS(5, "blocks", "Blocks");


        private final int id;
        private final String name;
        private final String representName;

        @Override
        public String getSerializedName() {
            return name;
        }

        BOTTOM(int id, String name, String representName) {
            this.id = id;
            this.name = name;
            this.representName = representName;
        }

        public int getId() {
            return id;
        }

        public static BOTTOM getById(int i) {
            for (BOTTOM e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return BOTTOM.TRANSPARENT;
        }
    }

}
