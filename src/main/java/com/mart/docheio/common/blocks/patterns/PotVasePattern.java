package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;

public class PotVasePattern {

    public enum UPPER implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        NECK_STRIPE(1, "neck_stripe", "Stripe"),
        CHECKERS(2, "checkers", "Checkers"),
        EYES(3, "eyes", "Eyes"),
        EYES_INVERSE(3, "eyes_inverse", "Inverse Eyes"),
        RIM_SPECKS(4, "rim_specks", "Rim Specks");


        private final int id;
        private final String name;
        private final String representName;
        @Override
        public String getSerializedName() {
            return name;
        }

        UPPER(int id, String name, String representName){
            this.id = id;
            this.name = name;
            this.representName = representName;
        }

        public int getId() {
            return id;
        }

        public static PotVasePattern.UPPER getById(int i){
            for(PotVasePattern.UPPER e : values()){
                if(i == e.getId()){
                    return e;
                }
            }
            return PotVasePattern.UPPER.TRANSPARENT;
        }
    }

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        BLOCKS(3, "blocks", "Blocks"),
        STRIPES(2, "stripes", "Stripes"),
        SOUL(4, "soul", "Soul"),
        WIGGLE(1, "wiggle", "Wiggle"),
        ZAG(5, "zag", "Zag");


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

        public int getId() {
            return id;
        }

        public static PotVasePattern.BOTTOM getById(int i){
            for(PotVasePattern.BOTTOM e : values()){
                if(i == e.getId()){
                    return e;
                }
            }
            return PotVasePattern.BOTTOM.TRANSPARENT;
        }
    }
}
