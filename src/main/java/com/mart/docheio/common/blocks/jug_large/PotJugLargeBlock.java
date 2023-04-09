package com.mart.docheio.common.blocks.jug_large;

import com.mart.docheio.common.blockentity.PotJugLargeEntity;
import com.mart.docheio.common.blocks.IPotBlock;
import com.mart.docheio.common.blocks.patterns.PotJugLargePattern;
import com.mart.docheio.common.util.PotColor;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

public class PotJugLargeBlock  extends LodestoneEntityBlock<PotJugLargeEntity> implements IPotBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<PotJugLargePattern.UPPER> UPPER_PATTERN = DocheioProperties.POT_JUG_LARGE_TOP_PATTERN;
    public static final EnumProperty<PotJugLargePattern.MIDDLE> MIDDLE_PATTERN = DocheioProperties.POT_JUG_LARGE_MIDDLE_PATTERN;
    public static final EnumProperty<PotJugLargePattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_JUG_LARGE_BOTTOM_PATTERN;
    private final PotColor color;
    protected final VoxelShape SHAPE;

    public PotJugLargeBlock(BlockBehaviour.Properties properties, PotColor color, VoxelShape shape) {
        super(properties);
        this.color = color;
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH)
                .setValue(UPPER_PATTERN, PotJugLargePattern.UPPER.TRANSPARENT)
                .setValue(MIDDLE_PATTERN, PotJugLargePattern.MIDDLE.TRANSPARENT)
                .setValue(BOTTOM_PATTERN, PotJugLargePattern.BOTTOM.TRANSPARENT));
        this.SHAPE = shape;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING).add(UPPER_PATTERN).add(MIDDLE_PATTERN).add(BOTTOM_PATTERN);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public PotColor getColor() {
        return color;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pHand == InteractionHand.MAIN_HAND){
            if(pPlayer.isCrouching()){
                PotJugLargePattern.UPPER p = pState.getValue(UPPER_PATTERN);
                pLevel.setBlockAndUpdate(pPos, pState.setValue(UPPER_PATTERN, PotJugLargePattern.UPPER.getById(p.getId() + 1)));
            } else {
                PotJugLargePattern.MIDDLE p = pState.getValue(MIDDLE_PATTERN);
                pLevel.setBlockAndUpdate(pPos, pState.setValue(MIDDLE_PATTERN, PotJugLargePattern.MIDDLE.getById(p.getId() + 1) ));
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
        pLevel.setBlockAndUpdate(pPos.above(), pLevel.getBlockState(pPos.above()).setValue(FACING, pState.getValue(FACING)).setValue(DocheioProperties.POT_JUG_LARGE_TOP_PATTERN, pState.getValue(UPPER_PATTERN)));
    }
}
