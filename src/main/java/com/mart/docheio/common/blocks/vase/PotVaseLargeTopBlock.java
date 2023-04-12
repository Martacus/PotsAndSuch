package com.mart.docheio.common.blocks.vase;

import com.mart.docheio.common.blocks.patterns.PotVaseLargePattern;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import team.lodestar.lodestone.systems.multiblock.MultiblockComponentBlock;

public class PotVaseLargeTopBlock extends MultiblockComponentBlock {

    public static final EnumProperty<PotVaseLargePattern.UPPER> TOP_PATTERN = DocheioProperties.POT_VASE_LARGE_UPPER_PATTERN;
    public static final EnumProperty<PotVaseLargePattern.MIDDLE> MIDDLE_PATTERN = DocheioProperties.POT_VASE_LARGE_MIDDLE_PATTERN;

    protected final VoxelShape SHAPE;

    public PotVaseLargeTopBlock(Properties properties, VoxelShape shape) {
        super(properties);
        this.SHAPE = shape;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(TOP_PATTERN, PotVaseLargePattern.UPPER.TRANSPARENT)
                .setValue(MIDDLE_PATTERN, PotVaseLargePattern.MIDDLE.TRANSPARENT)
        );
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TOP_PATTERN, MIDDLE_PATTERN);
    }

}
