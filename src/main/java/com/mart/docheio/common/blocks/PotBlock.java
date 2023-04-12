package com.mart.docheio.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;


public class PotBlock extends LodestoneEntityBlock {

    protected final VoxelShape SHAPE;

    public PotBlock(Properties pProperties, VoxelShape shape) {
        super(pProperties);
        this.SHAPE = shape;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }


}
