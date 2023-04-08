package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class PotFlowerPattern {
    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        EYES(1, "eyes", "Eyes"),
        LABEL(2, "label", "Label"),
        RIM(3, "rim", "Wiggle"),
        SPECKS(4, "specks", "Specks"),
        STRIPED(5, "striped", "Striped");


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

        public static PotFlowerPattern.BOTTOM getById(int i){
            for(PotFlowerPattern.BOTTOM e : values()){
                if(i == e.getId()){
                    return e;
                }
            }
            return PotFlowerPattern.BOTTOM.TRANSPARENT;
        }
    }
}
