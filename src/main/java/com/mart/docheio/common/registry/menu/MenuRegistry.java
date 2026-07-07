package com.mart.docheio.common.registry.menu;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.menu.PotteryWheelMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuRegistry {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, PotsMod.DOCHEIO);

    public static final RegistryObject<MenuType<PotteryWheelMenu>> POTTERY_WHEEL =
            MENU_TYPES.register("pottery_wheel",
                    () -> new MenuType<>(PotteryWheelMenu::new, FeatureFlags.VANILLA_SET));
}
