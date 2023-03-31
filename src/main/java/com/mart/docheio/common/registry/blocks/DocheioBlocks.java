package com.mart.docheio.common.registry.blocks;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.PotBlock;
import com.mart.docheio.common.blocks.PotteryWheelBlock;
import com.mart.docheio.common.blocks.TallPotBlock;
import com.mart.docheio.common.util.PotColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.HashMap;

public class DocheioBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotsMod.DOCHEIO);

    public static final VoxelShape SHAPE_POT = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D);
    public static final VoxelShape SHAPE_AMPHORA = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 28.0D, 13.0D);
    public static final VoxelShape SHAPE_FLOWER = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_JUG = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_JUG_LARGE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_PITCHER = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_PLANTER = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_PLANTER_SMALL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_SMALL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_TALL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_VASE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_VASE_LARGE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public static final HashMap<PotColor, RegistryObject<Block>> POT_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_AMPHORA_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_FLOWER_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_JUG_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_JUG_LARGE_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_PITCHER_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_PLANTER_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_PLANTER_SMALL_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_SMALL_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_TALL_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_VASE_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_VASE_LARGE_MAP = new HashMap<>();
    public static final RegistryObject<Block> POTTERY_WHEEL = BLOCKS.register("pottery_wheel", () -> new PotteryWheelBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static void createPots(){
        Arrays.stream(PotColor.values()).toList().forEach(p -> {
            POT_MAP.put(p, BLOCKS.register("pot_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_AMPHORA_MAP.put(p, BLOCKS.register("pot_amphora_" + p.getSerializedName(), () -> new TallPotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion())));
            POT_FLOWER_MAP.put(p, BLOCKS.register("pot_flower_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_JUG_MAP.put(p, BLOCKS.register("pot_jug_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_JUG_LARGE_MAP.put(p, BLOCKS.register("pot_jug_large_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_PITCHER_MAP.put(p, BLOCKS.register("pot_pitcher_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_PLANTER_MAP.put(p, BLOCKS.register("pot_planter_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_PLANTER_SMALL_MAP.put(p, BLOCKS.register("pot_planter_small_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_SMALL_MAP.put(p, BLOCKS.register("pot_small_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_TALL_MAP.put(p, BLOCKS.register("pot_tall_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_VASE_MAP.put(p, BLOCKS.register("pot_vase_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
            POT_VASE_LARGE_MAP.put(p, BLOCKS.register("pot_vase_large_" + p.getSerializedName(), () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_POT)));
        });
    }
}
