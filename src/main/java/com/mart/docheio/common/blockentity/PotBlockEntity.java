package com.mart.docheio.common.blockentity;

import com.mart.docheio.common.registry.entity.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PotBlockEntity extends BlockEntity {

    public PotBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.POT.get(), pPos, pBlockState);
    }

    public void tick(){

    }
}
