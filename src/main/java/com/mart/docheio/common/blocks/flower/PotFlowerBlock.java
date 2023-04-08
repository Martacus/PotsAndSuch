package com.mart.docheio.common.blocks.flower;

import com.mart.docheio.common.blocks.PotBlock;
import com.mart.docheio.common.blocks.patterns.PotAmphoraPattern;
import com.mart.docheio.common.blocks.patterns.PotFlowerPattern;
import com.mart.docheio.common.blocks.patterns.PotPattern;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PotFlowerBlock extends PotBlock {

    public static final EnumProperty<PotFlowerPattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_FLOWER_BOTTOM_PATTERN;

    public PotFlowerBlock(Properties pProperties, VoxelShape shape) {
        super(pProperties, shape);
        registerDefaultState(this.getStateDefinition().any().setValue(BOTTOM_PATTERN, PotFlowerPattern.BOTTOM.TRANSPARENT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BOTTOM_PATTERN);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pHand == InteractionHand.MAIN_HAND){
            PotFlowerPattern.BOTTOM p = pState.getValue(BOTTOM_PATTERN);
            pLevel.setBlockAndUpdate(pPos, pState.setValue(BOTTOM_PATTERN, PotFlowerPattern.BOTTOM.getById(p.getId() + 1)));
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
