package com.mart.docheio.common.event;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.registry.item.DocheioItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = PotsMod.POTSMOD, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabsEvent {

    @SubscribeEvent
    public static void setCTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(PotsMod.POTSMOD, "pots"),
                builder -> builder.title(Component.literal("Docheio"))
                        .icon(() -> new ItemStack(Items.FLOWER_POT))
                        .displayItems((enabledFlags, output) -> {
                            output.accept(DocheioItems.POT.get());
                            output.accept(DocheioItems.POT_AMPHORA.get());
                            output.accept(DocheioItems.POT_FLOWER.get());
                            output.accept(DocheioItems.POT_JUG.get());
                            output.accept(DocheioItems.POT_JUG_LARGE.get());
                            output.accept(DocheioItems.POT_PITCHER.get());
                            output.accept(DocheioItems.POT_PLANTER.get());
                            output.accept(DocheioItems.POT_PLANTER_SMALL.get());
                            output.accept(DocheioItems.POT_SMALL.get());
                            output.accept(DocheioItems.POT_TALL.get());
                            output.accept(DocheioItems.POT_VASE.get());
                            output.accept(DocheioItems.POT_VASE_LARGE.get());
                        })
                        .build());
    }
}