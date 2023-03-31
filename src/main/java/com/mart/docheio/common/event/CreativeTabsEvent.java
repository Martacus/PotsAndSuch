package com.mart.docheio.common.event;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.registry.item.DocheioItems;
import com.mart.docheio.data.DocheioLootTables;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = PotsMod.DOCHEIO, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabsEvent {

    @SubscribeEvent
    public static void setCTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(PotsMod.DOCHEIO, "pots"),
                builder -> builder.title(Component.literal("Docheio"))
                        .icon(() -> new ItemStack(Items.FLOWER_POT))
                        .displayItems((enabledFlags, output) -> {
                            DocheioItems.POT_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_AMPHORA_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_FLOWER_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_JUG_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_JUG_LARGE_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_PITCHER_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_PLANTER_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_PLANTER_SMALL_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_SMALL_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_TALL_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_VASE_MAP.forEach((p, k) -> output.accept(k.get()));
                            DocheioItems.POT_VASE_LARGE_MAP.forEach((p, k) -> output.accept(k.get()));
                            output.accept(DocheioItems.POTTERY_WHEEL.get());
                        })
                        .build());
    }

}