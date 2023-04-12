package com.mart.docheio.common.blockentity;

import com.mart.docheio.common.blockentity.inventory.RandomRetrievalInventory;
import com.mart.docheio.common.registry.entity.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.systems.blockentity.ItemHolderBlockEntity;

public class PotItemBlockEntity extends ItemHolderBlockEntity {


    public PotItemBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.POT.get(), pPos, pBlockState);

        inventory = new RandomRetrievalInventory(16, 1);
    }

    public void tick() {

    }
}
