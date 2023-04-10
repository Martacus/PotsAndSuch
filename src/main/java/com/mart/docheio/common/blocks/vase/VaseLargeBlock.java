package com.mart.docheio.common.blocks.vase;

import com.mart.docheio.common.blockentity.PotVaseLargeEntity;
import com.mart.docheio.common.blocks.IPotBlock;
import com.mart.docheio.common.blocks.patterns.PotVaseLargePattern;
import com.mart.docheio.common.util.PotColor;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

public class VaseLargeBlock extends LodestoneEntityBlock<PotVaseLargeEntity> implements IPotBlock {

    public static final EnumProperty<PotVaseLargePattern.UPPER> TOP_PATTERN = DocheioProperties.POT_VASE_LARGE_UPPER_PATTERN;
    public static final EnumProperty<PotVaseLargePattern.MIDDLE> MIDDLE_PATTERN = DocheioProperties.POT_VASE_LARGE_MIDDLE_PATTERN;
    public static final EnumProperty<PotVaseLargePattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_VASE_LARGE_BOTTOM_PATTERN;
    private final PotColor color;
    protected final VoxelShape SHAPE;

    public VaseLargeBlock(Properties properties, PotColor color, VoxelShape shape) {
        super(properties);
        this.color = color;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(TOP_PATTERN, PotVaseLargePattern.UPPER.TRANSPARENT)
                .setValue(MIDDLE_PATTERN, PotVaseLargePattern.MIDDLE.TRANSPARENT)
                .setValue(BOTTOM_PATTERN, PotVaseLargePattern.BOTTOM.TRANSPARENT));
        this.SHAPE = shape;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TOP_PATTERN, MIDDLE_PATTERN, BOTTOM_PATTERN);
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
                PotVaseLargePattern.BOTTOM p = pState.getValue(BOTTOM_PATTERN);
                pLevel.setBlockAndUpdate(pPos, pState.setValue(BOTTOM_PATTERN, PotVaseLargePattern.BOTTOM.getById(p.getId() + 1)));
            } else {
                PotVaseLargePattern.MIDDLE p = pState.getValue(MIDDLE_PATTERN);
                pLevel.setBlockAndUpdate(pPos, pState.setValue(MIDDLE_PATTERN, PotVaseLargePattern.MIDDLE.getById(p.getId() + 1) ));
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
        pLevel.setBlockAndUpdate(pPos.above(), pLevel.getBlockState(pPos.above())
                .setValue(DocheioProperties.POT_VASE_LARGE_UPPER_PATTERN, pState.getValue(TOP_PATTERN))
                .setValue(DocheioProperties.POT_VASE_LARGE_MIDDLE_PATTERN, pState.getValue(MIDDLE_PATTERN))
        );
    }
}

