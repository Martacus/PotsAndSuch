package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;

public class PotPlanterPattern {

    public enum UPPER implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        NECK_BLOCKS(1, "neck_blocks", "Blocks"),
        NECK_PEEK(2, "neck_peek", "Peek"),
        SUSPICIOUS(3, "neck_suspicious", "Suspicius"),
        RIM_BLOCKS(5, "rim_blocks", "Rim Blocks"),
        RIM_EYES(4, "rim_eyes", "Rim Eyes");


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

        public static PotPlanterPattern.UPPER getById(int i) {
            for (PotPlanterPattern.UPPER e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotPlanterPattern.UPPER.TRANSPARENT;
        }
    }

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        BLOCKS(3, "blocks", "Blocks"),
        STRIPES(2, "stripes", "Stripes"),
        WIGGLE(4, "wiggle", "Wiggle"),
        WAVE(1, "wave", "Wave"),
        HIDE(5, "hide", "Hide");


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

        public static PotPlanterPattern.BOTTOM getById(int i) {
            for (PotPlanterPattern.BOTTOM e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotPlanterPattern.BOTTOM.TRANSPARENT;
        }
    }
}
