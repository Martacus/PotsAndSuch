package com.mart.docheio.common.blocks.amphora;

import com.mart.docheio.common.blockentity.PotAmphoraEntity;
import com.mart.docheio.common.blocks.IPotBlock;
import com.mart.docheio.common.blocks.TwoTallPotBlock;
import com.mart.docheio.common.registry.entity.BlockEntityRegistry;
import com.mart.docheio.common.util.PotColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

public class PotAmphoraBlock extends LodestoneEntityBlock<PotAmphoraEntity> implements IPotBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private final PotColor color;

    public PotAmphoraBlock(Properties properties, PotColor color) {
        super(properties);
        this.color = color;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public PotColor getColor() {
        return color;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
        pLevel.setBlockAndUpdate(pPos.above(), pLevel.getBlockState(pPos.above()).setValue(FACING, pState.getValue(FACING)));
    }
}
