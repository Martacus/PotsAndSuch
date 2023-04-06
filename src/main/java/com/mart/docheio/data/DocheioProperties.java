package com.mart.docheio.data;

import com.mart.docheio.common.blocks.patterns.PotAmphoraPattern;
import com.mart.docheio.common.blocks.patterns.PotPattern;
import net.minecraft.core.FrontAndTop;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class DocheioProperties {

    public static final EnumProperty<PotPattern.TOP> POT_TOP_PATTERN = EnumProperty.create("pot_pattern_top", PotPattern.TOP.class);
    public static final EnumProperty<PotPattern.BOTTOM> POT_BOTTOM_PATTERN = EnumProperty.create("pot_pattern_bottom", PotPattern.BOTTOM.class);
    public static final EnumProperty<PotAmphoraPattern.TOP> POT_AMPHORA_TOP_PATTERN = EnumProperty.create("pot_amphora_pattern_top", PotAmphoraPattern.TOP.class);
    public static final EnumProperty<PotAmphoraPattern.MIDDLE> POT_AMPHORA_MIDDLE_PATTERN = EnumProperty.create("pot_amphora_pattern_middle", PotAmphoraPattern.MIDDLE.class);
    public static final EnumProperty<PotAmphoraPattern.BOTTOM> POT_AMPHORA_BOTTOM_PATTERN = EnumProperty.create("pot_amphora_pattern_bottom", PotAmphoraPattern.BOTTOM.class);

}
