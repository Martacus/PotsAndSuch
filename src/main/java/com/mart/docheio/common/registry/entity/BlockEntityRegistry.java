package com.mart.docheio.common.registry.entity;

import com.mart.docheio.PotsMod;
import com.mart.docheio.client.render.blockentity.PotEntityRenderer;
import com.mart.docheio.common.blockentity.PotAmphoraEntity;
import com.mart.docheio.common.blockentity.PotBlockEntity;
import com.mart.docheio.common.blocks.PotBlock;
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

    public static final RegistryObject<BlockEntityType<PotBlockEntity>> POT = BLOCK_ENTITY_TYPES.register("pot", () -> BlockEntityType.Builder.of(PotBlockEntity::new, allPotsOfType(BlockRegistry.POT_MAP)).build(null));
    public static final RegistryObject<BlockEntityType<PotAmphoraEntity>> POT_AMPHORA = BLOCK_ENTITY_TYPES.register("pot_amphora", () -> BlockEntityType.Builder.of(PotAmphoraEntity::new, allPotsOfType(BlockRegistry.POT_AMPHORA_MAP)).build(null));

    @Mod.EventBusSubscriber(modid = PotsMod.DOCHEIO, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientOnly {
        @SubscribeEvent
        public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
            //event.registerBlockEntityRenderer(POT.get(), PotEntityRenderer::new);
        }
    }
}
