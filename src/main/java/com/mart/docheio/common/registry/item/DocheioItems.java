package com.mart.docheio.common.registry.item;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.registry.blocks.DocheioBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DocheioItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotsMod.POTSMOD);

    public static final RegistryObject<Item> POT = ITEMS.register("pot", () -> new BlockItem(DocheioBlocks.POT.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_AMPHORA = ITEMS.register("pot_amphora", () -> new BlockItem(DocheioBlocks.POT_AMPHORA.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_FLOWER = ITEMS.register("pot_flower", () -> new BlockItem(DocheioBlocks.POT_FLOWER.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_JUG = ITEMS.register("pot_jug", () -> new BlockItem(DocheioBlocks.POT_JUG.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_JUG_LARGE = ITEMS.register("pot_jug_large", () -> new BlockItem(DocheioBlocks.POT_JUG_LARGE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_PITCHER = ITEMS.register("pot_pitcher", () -> new BlockItem(DocheioBlocks.POT_PITCHER.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_PLANTER = ITEMS.register("pot_planter", () -> new BlockItem(DocheioBlocks.POT_PLANTER.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_PLANTER_SMALL = ITEMS.register("pot_planter_small", () -> new BlockItem(DocheioBlocks.POT_PLANTER_SMALL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_SMALL = ITEMS.register("pot_small", () -> new BlockItem(DocheioBlocks.POT_SMALL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_TALL = ITEMS.register("pot_tall", () -> new BlockItem(DocheioBlocks.POT_TALL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_VASE = ITEMS.register("pot_vase", () -> new BlockItem(DocheioBlocks.POT_VASE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> POT_VASE_LARGE = ITEMS.register("pot_vase_large", () -> new BlockItem(DocheioBlocks.POT_VASE_LARGE.get(), new Item.Properties().stacksTo(1)));

}
