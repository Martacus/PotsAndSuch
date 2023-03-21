package com.mart.docheio.common.registry.blocks;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.PotBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DocheioBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotsMod.POTSMOD);

    public static final RegistryObject<Block> POT = BLOCKS.register("pot", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_AMPHORA = BLOCKS.register("pot_amphora", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_FLOWER = BLOCKS.register("pot_flower", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_JUG = BLOCKS.register("pot_jug", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_JUG_LARGE = BLOCKS.register("pot_jug_large", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_PITCHER = BLOCKS.register("pot_pitcher", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_PLANTER = BLOCKS.register("pot_planter", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_PLANTER_SMALL = BLOCKS.register("pot_planter_small", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_SMALL = BLOCKS.register("pot_small", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_TALL = BLOCKS.register("pot_tall", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_VASE = BLOCKS.register("pot_vase", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POT_VASE_LARGE = BLOCKS.register("pot_vase_large", () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));


}
