package com.mart.docheio.common.blocks.amphora;

import com.mart.docheio.common.blockentity.PotAmphoraEntity;
import com.mart.docheio.common.blocks.IPotBlock;
import com.mart.docheio.common.blocks.TwoTallPotBlock;
import com.mart.docheio.common.blocks.patterns.PotAmphoraPattern;
import com.mart.docheio.common.blocks.patterns.PotPattern;
import com.mart.docheio.common.registry.entity.BlockEntityRegistry;
import com.mart.docheio.common.util.PotColor;
import com.mart.docheio.data.DocheioProperties;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

public class PotAmphoraBlock extends LodestoneEntityBlock<PotAmphoraEntity> implements IPotBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<PotAmphoraPattern.TOP> TOP_PATTERN = DocheioProperties.POT_AMPHORA_TOP_PATTERN;
    public static final EnumProperty<PotAmphoraPattern.MIDDLE> MIDDLE_PATTERN = DocheioProperties.POT_AMPHORA_MIDDLE_PATTERN;
    public static final EnumProperty<PotAmphoraPattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_AMPHORA_BOTTOM_PATTERN;
    private final PotColor color;

    public PotAmphoraBlock(Properties properties, PotColor color) {
        super(properties);
        this.color = color;
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH)
                .setValue(TOP_PATTERN, PotAmphoraPattern.TOP.NECK_HIDE)
                .setValue(MIDDLE_PATTERN, PotAmphoraPattern.MIDDLE.EYES)
                .setValue(BOTTOM_PATTERN, PotAmphoraPattern.BOTTOM.LOW_EYES));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING).add(TOP_PATTERN).add(MIDDLE_PATTERN).add(BOTTOM_PATTERN);
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
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
        pLevel.setBlockAndUpdate(pPos.above(), pLevel.getBlockState(pPos.above()).setValue(FACING, pState.getValue(FACING)));
    }
}
