package com.mart.docheio.data;

import com.mart.docheio.common.blocks.patterns.PotPattern;
import net.minecraft.core.FrontAndTop;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class DocheioProperties {

    public static final EnumProperty<PotPattern.TOP> POT_TOP_PATTERN = EnumProperty.create("pot_pattern_top", PotPattern.TOP.class);
    public static final EnumProperty<PotPattern.BOTTOM> POT_BOTTOM_PATTERN = EnumProperty.create("pot_pattern_bottom", PotPattern.BOTTOM.class);

}
