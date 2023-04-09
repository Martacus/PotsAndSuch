package com.mart.docheio.common.blocks.jug_large;

import com.mart.docheio.common.blocks.patterns.PotJugLargePattern;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.systems.multiblock.MultiblockComponentBlock;

public class PotJugComponentBlock extends MultiblockComponentBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<PotJugLargePattern.UPPER> TOP_PATTERN = DocheioProperties.POT_JUG_LARGE_TOP_PATTERN;

    protected final VoxelShape SHAPE;

    public PotJugComponentBlock(Properties properties, VoxelShape shape) {
        super(properties);
        this.SHAPE = shape;
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH)
                .setValue(TOP_PATTERN, PotJugLargePattern.UPPER.TRANSPARENT));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING).add(TOP_PATTERN);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
}