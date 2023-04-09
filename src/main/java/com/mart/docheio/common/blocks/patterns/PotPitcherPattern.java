package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class PotPitcherPattern {
    
    public enum UPPER implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        NECK_LABEL(1, "neck_label", "Label"),
        NECK_SPECKS(2, "neck_specks", "Rim Specks"),
        STRIPES(3, "neck_stripes", "Stripes"),
        RIM_EYES(4, "rim_eyes", "Rim Eyes"),
        RIM_SPECKS(5, "rim_specks", "Rim Specks");

    
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
    
        public static PotPitcherPattern.UPPER getById(int i){
            for(PotPitcherPattern.UPPER e : values()){
                if(i == e.getId()){
                    return e;
                }
            }
            return PotPitcherPattern.UPPER.TRANSPARENT;
        }
    }

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        LABEL(1, "label", "Label"),
        STRIPES(2, "stripes", "Stripes"),
        BLOCKS(3, "blocks", "Blocks"),
        WIGGLE(4, "wiggle", "Wiggle"),
        SPECKS(5, "specks", "Specks");


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

        public static PotPitcherPattern.BOTTOM getById(int i){
            for(PotPitcherPattern.BOTTOM e : values()){
                if(i == e.getId()){
                    return e;
                }
            }
            return PotPitcherPattern.BOTTOM.TRANSPARENT;
        }
    }
}
