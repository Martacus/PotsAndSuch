package com.mart.docheio.common.blocks.patterns;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class PotTallPattern {

    public enum BOTTOM implements StringRepresentable {
        TRANSPARENT(0, "transparent", ""),
        EDGES(1, "edges", "Edges"),
        BLOCKS(2, "blocks", "Blocks"),
        CHEVRON(3, "chevron", "Chevron"),
        FULL_STRIPES(4, "full_stripes", "Full Stripes"),
        STRIPED(5, "striped", "Striped");


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

        public static PotTallPattern.BOTTOM getById(int i) {
            for (PotTallPattern.BOTTOM e : values()) {
                if (i == e.getId()) {
                    return e;
                }
            }
            return PotTallPattern.BOTTOM.TRANSPARENT;
        }
    }
}