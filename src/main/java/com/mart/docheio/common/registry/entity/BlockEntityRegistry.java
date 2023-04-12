package com.mart.docheio.common.registry.entity;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blockentity.PotAmphoraEntity;
import com.mart.docheio.common.blockentity.PotItemBlockEntity;
import com.mart.docheio.common.blockentity.PotJugLargeEntity;
import com.mart.docheio.common.blockentity.PotVaseLargeEntity;
import com.mart.docheio.common.registry.blocks.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.mart.docheio.common.registry.blocks.BlockRegistry.allPotsOfType;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PotsMod.DOCHEIO);

    public static final RegistryObject<BlockEntityType<PotItemBlockEntity>> POT = BLOCK_ENTITY_TYPES.register("pot", () -> BlockEntityType.Builder.of(PotItemBlockEntity::new, allPotsOfType(BlockRegistry.POT_MAP)).build(null));
    public static final RegistryObject<BlockEntityType<PotAmphoraEntity>> POT_AMPHORA = BLOCK_ENTITY_TYPES.register("pot_amphora", () -> BlockEntityType.Builder.of(PotAmphoraEntity::new, allPotsOfType(BlockRegistry.POT_AMPHORA_MAP)).build(null));
    public static final RegistryObject<BlockEntityType<PotJugLargeEntity>> POT_JUG_LARGE = BLOCK_ENTITY_TYPES.register("pot_jug_large", () -> BlockEntityType.Builder.of(PotJugLargeEntity::new, allPotsOfType(BlockRegistry.POT_JUG_LARGE_MAP)).build(null));
    public static final RegistryObject<BlockEntityType<PotVaseLargeEntity>> POT_VASE_LARGE = BLOCK_ENTITY_TYPES.register("pot_vase_large", () -> BlockEntityType.Builder.of(PotVaseLargeEntity::new, allPotsOfType(BlockRegistry.POT_VASE_LARGE_MAP)).build(null));

    @Mod.EventBusSubscriber(modid = PotsMod.DOCHEIO, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientOnly {
        @SubscribeEvent
        public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
            //event.registerBlockEntityRenderer(POT.get(), PotEntityRenderer::new);
        }
    }
}
