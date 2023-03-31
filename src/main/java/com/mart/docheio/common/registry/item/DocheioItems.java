package com.mart.docheio.common.registry.item;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.PotBlock;
import com.mart.docheio.common.blocks.PotteryWheelBlock;
import com.mart.docheio.common.registry.blocks.DocheioBlocks;
import com.mart.docheio.common.util.PotColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.HashMap;

public class DocheioItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotsMod.DOCHEIO);
    public static final HashMap<PotColor, RegistryObject<Item>> POT_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_AMPHORA_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_FLOWER_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_JUG_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_JUG_LARGE_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_PITCHER_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_PLANTER_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_PLANTER_SMALL_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_SMALL_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_TALL_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_VASE_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Item>> POT_VASE_LARGE_MAP = new HashMap<>();
    public static final RegistryObject<Item> POTTERY_WHEEL = ITEMS.register("pottery_wheel", () -> new BlockItem(DocheioBlocks.POTTERY_WHEEL.get(), new Item.Properties().stacksTo(1)));

    public static void createPots(){
        Arrays.stream(PotColor.values()).toList().forEach(p -> {
            POT_MAP.put(p, ITEMS.register("pot_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_AMPHORA_MAP.put(p, ITEMS.register("pot_amphora_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_AMPHORA_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_FLOWER_MAP.put(p, ITEMS.register("pot_flower_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_FLOWER_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_JUG_MAP.put(p, ITEMS.register("pot_jug_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_JUG_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_JUG_LARGE_MAP.put(p, ITEMS.register("pot_jug_large_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_JUG_LARGE_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_PITCHER_MAP.put(p, ITEMS.register("pot_pitcher_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_PITCHER_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_PLANTER_MAP.put(p, ITEMS.register("pot_planter_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_PLANTER_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_PLANTER_SMALL_MAP.put(p, ITEMS.register("pot_planter_small_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_PLANTER_SMALL_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_SMALL_MAP.put(p, ITEMS.register("pot_small_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_SMALL_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_TALL_MAP.put(p, ITEMS.register("pot_tall_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_TALL_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_VASE_MAP.put(p, ITEMS.register("pot_vase_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_VASE_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
            POT_VASE_LARGE_MAP.put(p, ITEMS.register("pot_vase_large_" + p.getSerializedName(), () -> new BlockItem(DocheioBlocks.POT_VASE_LARGE_MAP.get(p).get(), new Item.Properties().stacksTo(1))));
        });
    }
}
