package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class PotJugLargePattern {

    public enum UPPER implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        HANDLES(1, "handles", "Handles"),
        NECK_CHECKERS(2, "neck_checkers", "Checkers"),
        RIM_BLOCKS(3, "rim_blocks", "Rim Blocks"),
        RIM_SPECKS(4, "rim_specks", "Rim Specks"),
        NECK_STRIPES(5, "neck_stripe", "Stripes");

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

        public static PotJugLargePattern.UPPER getById(int i) {
            for (PotJugLargePattern.UPPER e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotJugLargePattern.UPPER.TRANSPARENT;
        }
    }

    public enum MIDDLE implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        LABEL(1, "label", "Label"),
        SPECKS(2, "specks", "Specks"),
        EYE(3, "eye", "Eyes"),
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

        public static PotJugLargePattern.MIDDLE getById(int i) {
            for (PotJugLargePattern.MIDDLE e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotJugLargePattern.MIDDLE.TRANSPARENT;
        }
    }

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        LOW_CHECKERS(1, "low_checkers", "Checkers"),
        LOW_LABEL(2, "low_label", "Label"),
        LOW_WIGGLE(3, "low_wiggle", "Wiggle"),
        STRIPES(4, "stripes", "Stripes"),
        BLOCKS(5, "blocks", "Blocks");


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

        public static PotJugLargePattern.BOTTOM getById(int i) {
            for (PotJugLargePattern.BOTTOM e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotJugLargePattern.BOTTOM.TRANSPARENT;
        }
    }

}
