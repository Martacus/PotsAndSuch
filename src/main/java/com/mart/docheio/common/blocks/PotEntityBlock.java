package com.mart.docheio.common.blocks;

import com.mart.docheio.common.blockentity.PotBlockEntity;
import com.mart.docheio.common.util.PotPatterns;
import net.minecraft.core.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        // The base body + pattern overlays are all drawn by PotEntityRenderer, so the
        // chunk renderer must not draw this block itself (otherwise it z-fights the BER).
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    // TODO(debug): temporary way to prove the pattern pipeline renders. Right-click
    // cycles this shape's patterns onto their natural slots; sneak-right-click with an
    // empty hand clears all patterns. Replace with the real (pottery wheel / apply-item)
    // flow.
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (!(level.getBlockEntity(pos) instanceof PotBlockEntity pot)) {
            return InteractionResult.PASS;
        }
        if (player.isShiftKeyDown() && player.getItemInHand(hand).isEmpty()) {
            for (PotBlockEntity.PatternSlot slot : PotBlockEntity.PatternSlot.values()) {
                pot.setPattern(slot, null);
            }
            return InteractionResult.CONSUME;
        }
        String shape = PotPatterns.shapeOf(ForgeRegistries.BLOCKS.getKey(state.getBlock()).getPath());
        List<String> patterns = PotPatterns.forShape(shape);
        if (patterns.isEmpty()) {
            return InteractionResult.PASS;
        }
        String pattern = patterns.get(Math.floorMod(debugIndex++, patterns.size()));
        String modelName = PotPatterns.modelName(shape, pattern);
        pot.setPattern(PotBlockEntity.PatternSlot.fromPatternName(modelName),
                PotPatterns.modelId(shape, pattern));
        return InteractionResult.CONSUME;
    }

    private int debugIndex = 0;

}
