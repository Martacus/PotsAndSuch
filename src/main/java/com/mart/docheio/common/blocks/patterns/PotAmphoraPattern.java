package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class PotAmphoraPattern {

    public enum UPPER implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        CHEVRON(1, "chevron", "Chevron"),
        NECK_EYES(2, "neck_eyes", "Eyes"),
        NECK_HIDE(3, "neck_hide", "Hide"),
        NECK_STITCH(4, "neck_stitch", "Stitch"),
        NECK_STRIPES(5, "neck_stripes", "Stripes");

        private final int id;
        private final String name;
        private final String representName;

        @Override
        public @NotNull String getSerializedName() {
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

    public enum MIDDLE implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        EYES(1, "eyes", "Eyes"),
        HIDE(2, "hide", "Hide"),
        STRIPE(3, "stripe", "Stripe"),
        WIGGLE(4, "wiggle", "Wiggle"),
        ZAG(5, "zag", "Zag");

        private final int id;
        private final String name;
        private final String representName;

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }

        MIDDLE(int id, String name, String representName) {
            this.id = id;
            this.name = name;
            this.representName = representName;
        }

        public int getId() {
            return id;
        }

        public static MIDDLE getById(int i) {
            for (MIDDLE e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return MIDDLE.TRANSPARENT;
        }
    }

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        LOW_EYES(1, "low_eyes", "Eyes"),
        LOW_STRIPES(2, "low_stripes", "Stripes"),
        LOW_WIGGLE(3, "low_wiggle", "Wiggle"),
        LOW_ZAG(4, "low_zag", "Zag"),
        STITCH(5, "stitch", "Stitch");


        private final int id;
        private final String name;
        private final String representName;

        @Override
        public @NotNull String getSerializedName() {
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
