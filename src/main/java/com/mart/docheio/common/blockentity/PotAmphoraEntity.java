package com.mart.docheio.common.blockentity;

import com.mart.docheio.common.blocks.amphora.PotAmphoraBlock;
import com.mart.docheio.common.registry.blocks.BlockRegistry;
import com.mart.docheio.common.registry.entity.BlockEntityRegistry;
import com.mart.docheio.common.util.PotColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.systems.multiblock.MultiBlockCoreEntity;
import team.lodestar.lodestone.systems.multiblock.MultiBlockStructure;

import java.util.function.Supplier;

public class PotAmphoraEntity extends MultiBlockCoreEntity {

    public PotAmphoraEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.POT_AMPHORA.get(), getStructure(state), pos, state);
    }

    private static MultiBlockStructure getStructure(BlockState state) {
        PotAmphoraBlock block = (PotAmphoraBlock) state.getBlock();
        return MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, BlockRegistry.POT_AMPHORA_TOP_MAP.get(block.getColor()).get().defaultBlockState()));
    }

    @Override
    public void onBreak(@Nullable Player player) {
        super.onBreak(player);
        destroyMultiblock(player, level, worldPosition);
    }

    public static Supplier<MultiBlockStructure> getItemStructure(PotColor color) {
        return () -> MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0,
                BlockRegistry.POT_AMPHORA_TOP_MAP.get(color).get().defaultBlockState()));
    }


}
