package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;

public class PotJugPattern {

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        BLOCKS(1, "blocks", "Blocks"),
        LABEL(2, "label", "Label"),
        ZAG(3, "zag", "Zag"),
        STRIPES(4, "stripes", "Stripes"),
        WIGGLE(5, "wiggle", "Wiggle");


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

        public static PotJugPattern.BOTTOM getById(int i) {
            for (PotJugPattern.BOTTOM e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotJugPattern.BOTTOM.TRANSPARENT;
        }
    }

    public enum UPPER implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        HANDLES(1, "handles", "Handles"),
        CHECKERS(2, "neck_checkers", "Checkers"),
        RIM_BLOCKS(3, "rim_blocks", "Rim Blocks"),
        STRIPES(4, "neck_stripe", "Stripes"),
        RIM_BOLD(5, "rim_bold", "Rim Bold");

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

        public static PotJugPattern.UPPER getById(int i) {
            for (PotJugPattern.UPPER e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotJugPattern.UPPER.TRANSPARENT;
        }
    }
}
