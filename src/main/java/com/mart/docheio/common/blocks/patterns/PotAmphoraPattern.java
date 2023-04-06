package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class PotAmphoraPattern {

    public enum TOP implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        CHEVRON(1, "chevron", "Chevron"),
        NECK_EYES(1, "neck_eyes", "Eyes"),
        NECK_HIDE(1, "nech_hide", "Hide"),
        NECK_STITCH(1, "neck_stitch", "Stitch"),
        NECK_STRIPES(1, "neck_stripes", "Stripes");

        private final int id;
        private final String name;
        private final String representName;

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }

        TOP(int id, String name, String representName){
            this.id = id;
            this.name = name;
            this.representName = representName;
        }

        public int getId() {
            return id;
        }

        public static TOP getById(int i){
            for(TOP e : values()){
                if(i == e.getId()){
                    return e;
                }
            }
            return TOP.TRANSPARENT;
        }
    }

    public enum MIDDLE implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        EYES(1, "eyes", "Eyes"),
        HIDE(2, "hide", "Hide"),
        STRIPE(2, "stripe", "Stripe"),
        WIGGLE(2, "wiggle", "Wiggle"),
        ZAG(2, "zag", "Zag");

        private final int id;
        private final String name;
        private final String representName;

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }

        MIDDLE(int id, String name, String representName){
            this.id = id;
            this.name = name;
            this.representName = representName;
        }

        public int getId() {
            return id;
        }

        public static MIDDLE getById(int i){
            for(MIDDLE e : values()){
                if(i == e.getId()){
                    return e;
                }
            }
            return MIDDLE.TRANSPARENT;
        }
    }

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        LOW_EYES(1, "low_eyes", "Eyes"),
        LOW_STRIPES(1, "low_stripes", "Stripes"),
        LOW_WIGGLE(1, "low_wiggle", "Wiggle"),
        LOW_ZAG(1, "wiggle", "Wiggle"),
        STITCH(1, "wiggle", "Wiggle");


        private final int id;
        private final String name;
        private final String representName;
        @Override
        public @NotNull String getSerializedName() {
            return name;
        }

        BOTTOM(int id, String name, String representName){
            this.id = id;
            this.name = name;
            this.representName = representName;
        }

        public int getId() {
            return id;
        }

        public static BOTTOM getById(int i){
            for(BOTTOM e : values()){
                if(i == e.getId()){
                    return e;
                }
            }
            return BOTTOM.TRANSPARENT;
        }
    }
}
