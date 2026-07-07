package com.mart.docheio.client.event;

import com.mart.docheio.PotsMod;
import com.mart.docheio.client.screen.PotteryWheelScreen;
import com.mart.docheio.common.registry.menu.MenuRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = PotsMod.DOCHEIO, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotteryWheelClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() ->
                MenuScreens.register(MenuRegistry.POTTERY_WHEEL.get(), PotteryWheelScreen::new));
    }
}
