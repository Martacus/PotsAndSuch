package com.mart.docheio.common.util;

import net.minecraft.util.StringRepresentable;

public enum PotColor implements StringRepresentable {
    WHITE(0, "white"),
    ORANGE(1, "orange"),
    MAGENTA(2, "magenta"),
    LIGHT_BLUE(3, "light_blue"),
    YELLOW(4, "yellow"),
    LIME(5, "lime"),
    PINK(6, "pink"),
    GRAY(7, "gray"),
    LIGHT_GRAY(8, "light_gray"),
    CYAN(9, "cyan"),
    PURPLE(10, "purple"),
    BLUE(11, "blue"),
    BROWN(12, "brown"),
    GREEN(13, "green"),
    RED(14, "red"),
    BLACK(15, "black"),
    NORMAL(16, "normal"),
    CLAY(17, "clay");

    private final int id;
    private final String name;

    PotColor(int pId, String name) {
        this.id = pId;
        this.name = name;
    }


    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getId() {
        return id;
    }
}
