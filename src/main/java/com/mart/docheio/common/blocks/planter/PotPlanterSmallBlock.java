package com.mart.docheio.common.blocks.planter;

import com.mart.docheio.common.blocks.PotBlock;
import com.mart.docheio.common.blocks.patterns.PotPlanterSmallPattern;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PotPlanterSmallBlock extends PotBlock {
    public static final EnumProperty<PotPlanterSmallPattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_PLANTER_SMALL_BOTTOM_PATTERN;

    public PotPlanterSmallBlock(BlockBehaviour.Properties pProperties, VoxelShape shape) {
        super(pProperties, shape);
        registerDefaultState(this.getStateDefinition().any().setValue(BOTTOM_PATTERN, PotPlanterSmallPattern.BOTTOM.TRANSPARENT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BOTTOM_PATTERN);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pHand == InteractionHand.MAIN_HAND){
            PotPlanterSmallPattern.BOTTOM p = pState.getValue(BOTTOM_PATTERN);
            pLevel.setBlockAndUpdate(pPos, pState.setValue(BOTTOM_PATTERN, PotPlanterSmallPattern.BOTTOM.getById(p.getId() + 1)));
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
