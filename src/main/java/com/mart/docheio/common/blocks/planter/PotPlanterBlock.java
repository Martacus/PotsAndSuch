package com.mart.docheio.common.blocks.planter;

import com.mart.docheio.common.blocks.PotBlock;
import com.mart.docheio.common.blocks.patterns.PotPlanterPattern;
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

public class PotPlanterBlock  extends PotBlock {

    public static final EnumProperty<PotPlanterPattern.UPPER> TOP_PATTERN = DocheioProperties.POT_PLANTER_UPPER_PATTERN;
    public static final EnumProperty<PotPlanterPattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_PLANTER_BOTTOM_PATTERN; 

    public PotPlanterBlock(Properties pProperties, VoxelShape shape) {
        super(pProperties, shape);
        registerDefaultState(this.getStateDefinition().any()
                .setValue(TOP_PATTERN, PotPlanterPattern.UPPER.TRANSPARENT)
                .setValue(BOTTOM_PATTERN, PotPlanterPattern.BOTTOM.TRANSPARENT) );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TOP_PATTERN, BOTTOM_PATTERN);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pHand == InteractionHand.MAIN_HAND){
            if(pPlayer.isCrouching()){
                PotPlanterPattern.BOTTOM p = pState.getValue(BOTTOM_PATTERN);
                pLevel.setBlockAndUpdate(pPos, pState.setValue(BOTTOM_PATTERN, PotPlanterPattern.BOTTOM.getById(p.getId() + 1)));
            } else {
                PotPlanterPattern.UPPER p = pState.getValue(TOP_PATTERN);
                pLevel.setBlockAndUpdate(pPos, pState.setValue(TOP_PATTERN, PotPlanterPattern.UPPER.getById(p.getId() + 1) ));
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}