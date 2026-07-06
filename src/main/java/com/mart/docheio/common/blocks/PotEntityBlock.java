package com.mart.docheio.common.blocks;

import com.mart.docheio.common.blockentity.PotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
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
    // cycles a pattern onto its natural slot; sneak-right-click with an empty hand
    // clears all patterns. Replace with the real (pottery wheel / apply-item) flow.
    private static final ResourceLocation[] DEBUG_PATTERNS = {
            new ResourceLocation("docheio", "block/pattern/pot_pattern_blocks"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_bold"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_checkers"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_eyes"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_inverse_eyes"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_linespeck"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_rim_blocks"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_rim_specks"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_wiggle"),
            new ResourceLocation("docheio", "block/pattern/pot_pattern_zag"),
    };

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
        ResourceLocation next = DEBUG_PATTERNS[debugIndex++ % DEBUG_PATTERNS.length];
        String name = next.getPath();
        pot.setPattern(PotBlockEntity.PatternSlot.fromPatternName(name), next);
        return InteractionResult.CONSUME;
    }

    private int debugIndex = 0;

}
