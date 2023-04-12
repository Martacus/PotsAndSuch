package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class PotSmallPattern {

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        EYES(1, "eyes", "Eyes"),
        BLOCKS(2, "blocks", "Blocks"),
        CHEVRON(3, "chevron", "Chevron"),
        SPECKS(4, "specks", "Specks"),
        STRIPED(5, "rim_striped", "Striped Rim");


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

        public static PotSmallPattern.BOTTOM getById(int i) {
            for (PotSmallPattern.BOTTOM e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotSmallPattern.BOTTOM.TRANSPARENT;
        }
    }
}
