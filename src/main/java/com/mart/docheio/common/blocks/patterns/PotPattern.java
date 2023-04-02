package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;

public class PotPattern {

    public enum TOP implements StringRepresentable {
        WIGGLE(0, "wiggle", "Wiggle"),
        BOLD(0, "bold", "Bold"),
        ZAG(0, "zag", "Zag"),
        LINES(0, "linespeck", "Line Speck"),
        BLOCKS(0, "blocks", "Blocks");

        private final int id;
        private final String name;
        private final String representName;
        @Override
        public String getSerializedName() {
            return name;
        }

        TOP(int id, String name, String representName){
            this.id = id;
            this.name = name;
            this.representName = representName;
        }


    }

    public enum BOTTOM implements StringRepresentable {
        EYES(0, "eyes", "Eyes"),
        CHECKERS(0, "checkers", "Checkers"),
        RIM_BLOCKS(0, "rim_blocks", "Rim Blocks"),
        INVERSE_EYES(0, "inverse_eyes", "Inverse Eyes"),
        RIM_SPECKS(0, "rim_specks", "Rim Specks");


        private final int id;
        private final String name;
        private final String representName;
        @Override
        public String getSerializedName() {
            return name;
        }

        BOTTOM(int id, String name, String representName){
            this.id = id;
            this.name = name;
            this.representName = representName;
        }
    }

}
