package com.mart.docheio.common.blocks.pot;

import com.mart.docheio.common.blockentity.PotItemBlockEntity;
import com.mart.docheio.common.blocks.patterns.PotPattern;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

public class PotPotBlock extends LodestoneEntityBlock<PotItemBlockEntity> {

    public static final EnumProperty<PotPattern.UPPER> TOP_PATTERN = DocheioProperties.POT_UPPER_PATTERN;
    public static final EnumProperty<PotPattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_BOTTOM_PATTERN;
    protected final VoxelShape SHAPE;
    public PotPotBlock(Properties pProperties, VoxelShape shape) {
        super(pProperties);
        this.SHAPE = shape;
        registerDefaultState(this.getStateDefinition().any().setValue(TOP_PATTERN, PotPattern.UPPER.TRANSPARENT).setValue(BOTTOM_PATTERN, PotPattern.BOTTOM.TRANSPARENT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TOP_PATTERN, BOTTOM_PATTERN);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

}
