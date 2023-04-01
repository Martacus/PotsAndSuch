package com.mart.docheio.common.blocks;

import com.mart.docheio.common.blockentity.PotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PotEntityBlock<T extends PotBlockEntity> extends PotBlock implements EntityBlock {

    protected Supplier<BlockEntityType<T>> blockEntityType = null;
    protected BlockEntityTicker<T> ticker = null;

    public PotEntityBlock(Properties pProperties, VoxelShape shape) {
        super(pProperties, shape);
    }

    public PotEntityBlock<T> setBlockEntity(Supplier<BlockEntityType<T>> type) {
        this.blockEntityType = type;
        this.ticker = (l, p, s, t) -> t.tick();
        return this;
    }

    @Override
    public <Y extends BlockEntity> BlockEntityTicker<Y> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<Y> type) {
        return (BlockEntityTicker<Y>) ticker;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return hasTileEntity(pState) ? blockEntityType.get().create(pPos, pState) : null;
    }

    public boolean hasTileEntity(BlockState state) {
        return this.blockEntityType != null;
    }

}
