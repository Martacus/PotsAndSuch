package com.mart.docheio.common.blocks;

import com.mart.docheio.common.blockentity.PotBlockEntity;
import com.mart.docheio.common.blocks.patterns.PotPattern;
import com.mart.docheio.data.DocheioProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PotPotBlock extends PotEntityBlock<PotBlockEntity>{

    public static final EnumProperty<PotPattern.TOP> TOP_PATTERN = DocheioProperties.POT_TOP_PATTERN;
    public static final EnumProperty<PotPattern.BOTTOM> BOTTOM_PATTERN = DocheioProperties.POT_BOTTOM_PATTERN;

    public PotPotBlock(Properties pProperties, VoxelShape shape) {
        super(pProperties, shape);
        registerDefaultState(this.getStateDefinition().any().setValue(TOP_PATTERN, PotPattern.TOP.WIGGLE).setValue(BOTTOM_PATTERN, PotPattern.BOTTOM.INVERSE_EYES));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TOP_PATTERN, BOTTOM_PATTERN);
        //Minecraft.getInstance().getBlockRenderer().getBlockModel()
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        System.out.println(this.defaultBlockState());
        return super.getStateForPlacement(pContext);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        System.out.println(pState.getValue(TOP_PATTERN).getSerializedName());
        System.out.println(pState.getValue(BOTTOM_PATTERN).getSerializedName());
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
