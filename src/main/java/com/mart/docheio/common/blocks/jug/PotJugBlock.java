package com.mart.docheio.common.blocks.jug;

import com.mart.docheio.common.blocks.PotBlock;
import com.mart.docheio.common.blocks.patterns.PotJugPattern;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PotJugBlock extends PotBlock {

    public static final EnumProperty<PotJugPattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_JUG_BOTTOM_PATTERN;
    public static final EnumProperty<PotJugPattern.UPPER> UPPER_PATTERN = DocheioProperties.POT_JUG_UPPER_PATTERN;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public PotJugBlock(Properties pProperties, VoxelShape shape) {
        super(pProperties, shape);
        registerDefaultState(this.getStateDefinition().any()
                .setValue(BOTTOM_PATTERN, PotJugPattern.BOTTOM.TRANSPARENT)
                .setValue(UPPER_PATTERN, PotJugPattern.UPPER.TRANSPARENT)
                .setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BOTTOM_PATTERN).add(UPPER_PATTERN).add(FACING);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND) {
            if (pPlayer.isCrouching()) {
                PotJugPattern.BOTTOM p = pState.getValue(BOTTOM_PATTERN);
                pLevel.setBlockAndUpdate(pPos, pState.setValue(BOTTOM_PATTERN, PotJugPattern.BOTTOM.getById(p.getId() + 1)));
            } else {
                PotJugPattern.UPPER p = pState.getValue(UPPER_PATTERN);
                pLevel.setBlockAndUpdate(pPos, pState.setValue(UPPER_PATTERN, PotJugPattern.UPPER.getById(p.getId() + 1)));
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
