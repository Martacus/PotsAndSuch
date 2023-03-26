package com.mart.docheio.common.blocks;

import com.mart.docheio.common.util.PotColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class PotBlock extends Block {

    public static final EnumProperty<PotColor> COLOR = EnumProperty.create("color", PotColor.class);

    public PotBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(COLOR));
    }

}
