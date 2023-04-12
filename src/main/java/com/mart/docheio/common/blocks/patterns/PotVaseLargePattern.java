package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class PotVaseLargePattern {

    public enum UPPER implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        NECK_CHEVRON(1, "neck_chevron", "Chevron"),
        NECK_EYE(2, "neck_eye", "Eyes"),
        HIDE(3, "hide", "Hide"),
        NECK_SPECKS(4, "neck_specks", "Stitch"),
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

        public static PotVaseLargePattern.UPPER getById(int i) {
            for (PotVaseLargePattern.UPPER e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotVaseLargePattern.UPPER.TRANSPARENT;
        }
    }

    public enum MIDDLE implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        EYE(1, "eye", "Eye"),
        BLOCKS(2, "blocks", "Blocks"),
        CHECKERS(3, "checkers", "Checkers"),
        PRAY(4, "pray", "Pray"),
        SQUINT(5, "squint", "Squint");

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

        public static PotVaseLargePattern.MIDDLE getById(int i) {
            for (PotVaseLargePattern.MIDDLE e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotVaseLargePattern.MIDDLE.TRANSPARENT;
        }
    }

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        CHEVRON(1, "chevron", "Chevron"),
        LOW_BLOCKS(2, "low_blocks", "Blocks"),
        LOW_CHECKERS(3, "low_checkers", "Checkers"),
        STRIPES(4, "stripes", "Stripes"),
        WIGGLE(5, "wiggle", "Wiggle");


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

        public static PotVaseLargePattern.BOTTOM getById(int i) {
            for (PotVaseLargePattern.BOTTOM e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotVaseLargePattern.BOTTOM.TRANSPARENT;
        }
    }
}
