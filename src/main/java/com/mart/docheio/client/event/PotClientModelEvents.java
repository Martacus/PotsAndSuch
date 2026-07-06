package com.mart.docheio.client.event;

import com.mart.docheio.PotsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Overlay pattern models live under models/block/pattern/ and are not referenced
 * by any blockstate, so they must be registered for baking explicitly. We discover
 * them by listing resources instead of maintaining a hand-written list, so adding a
 * new pattern is just dropping in a texture + generating its model.
 */
@Mod.EventBusSubscriber(modid = PotsMod.DOCHEIO, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotClientModelEvents {

    private static final String MODEL_DIR = "models/block/pattern";

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        Minecraft.getInstance().getResourceManager()
                .listResources(MODEL_DIR, rl -> rl.getNamespace().equals(PotsMod.DOCHEIO)
                        && rl.getPath().endsWith(".json"))
                .keySet()
                .forEach(rl -> {
                    // assets/docheio/models/block/pattern/foo.json -> docheio:block/pattern/foo
                    String path = rl.getPath();
                    path = path.substring("models/".length(), path.length() - ".json".length());
                    event.register(new ResourceLocation(rl.getNamespace(), path));
                });
    }
}
