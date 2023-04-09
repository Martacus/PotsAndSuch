package com.mart.docheio.common.registry.blocks;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.*;
import com.mart.docheio.common.blocks.amphora.PotAmphoraBlock;
import com.mart.docheio.common.blocks.amphora.PotAmphoraComponentBlock;
import com.mart.docheio.common.blocks.flower.PotFlowerBlock;
import com.mart.docheio.common.blocks.jug.PotJugBlock;
import com.mart.docheio.common.blocks.jug_large.PotJugComponentBlock;
import com.mart.docheio.common.blocks.jug_large.PotJugLargeBlock;
import com.mart.docheio.common.blocks.pot.PotPotBlock;
import com.mart.docheio.common.registry.entity.BlockEntityRegistry;
import com.mart.docheio.common.util.PotColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import team.lodestar.lodestone.systems.block.LodestoneBlockProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotsMod.DOCHEIO);
    public static final LodestoneBlockProperties POT_PROPERTIES = new LodestoneBlockProperties(Material.DECORATION).instabreak().noOcclusion().setCutoutRenderType();

    public static final VoxelShape SHAPE_POT = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D);
    public static final VoxelShape SHAPE_AMPHORA_LOWER = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 29.0D, 15.0D);
    public static final VoxelShape SHAPE_AMPHORA_UPPER = Block.box(1.0D, -16.0D, 1.0D, 15.0D, 13.0D, 15.0D);
    public static final VoxelShape SHAPE_FLOWER = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    public static final VoxelShape SHAPE_JUG = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D);
    public static final VoxelShape SHAPE_JUG_LARGE = Block.box(2.0D, 0.0D, 3.0D, 14.0D, 19.0D, 13.0D);
    public static final VoxelShape SHAPE_JUG_LARGE_UPPER = Block.box(2.0D, -16.0D, 3.0D, 14.0D, 3.0D, 13.0D);
    public static final VoxelShape SHAPE_PITCHER = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 15.0D, 13.0D);
    public static final VoxelShape SHAPE_PLANTER = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    public static final VoxelShape SHAPE_PLANTER_SMALL = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D);
    public static final VoxelShape SHAPE_SMALL = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
    public static final VoxelShape SHAPE_TALL = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);
    public static final VoxelShape SHAPE_VASE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    public static final VoxelShape SHAPE_VASE_LARGE_LOWER  = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 28.0D, 13.0D);
    public static final VoxelShape SHAPE_VASE_LARGE_UPPER = Block.box(3.0D, -16.0D, 3.0D, 13.0D, 12.0D, 13.0D);

    public static final HashMap<PotColor, RegistryObject<Block>> POT_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_AMPHORA_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_AMPHORA_TOP_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_FLOWER_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_JUG_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_JUG_LARGE_MAP = new HashMap<>();
    public static final HashMap<PotColor, RegistryObject<Block>> POT_JUG_LARGE_TOP_MAP = new HashMap<>();
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
            POT_MAP.put(p, BLOCKS.register("pot_" + p.getSerializedName(),
                    () -> new PotPotBlock(POT_PROPERTIES, SHAPE_POT)));
            POT_AMPHORA_MAP.put(p, BLOCKS.register("pot_amphora_" + p.getSerializedName(),
                    () -> new PotAmphoraBlock(POT_PROPERTIES, p, SHAPE_AMPHORA_LOWER).setBlockEntity(BlockEntityRegistry.POT_AMPHORA)));
            POT_AMPHORA_TOP_MAP.put(p, BLOCKS.register("pot_amphora_component_" + p.getSerializedName(),
                    () -> new PotAmphoraComponentBlock(POT_PROPERTIES, SHAPE_AMPHORA_UPPER)));
            POT_FLOWER_MAP.put(p, BLOCKS.register("pot_flower_" + p.getSerializedName(),
                    () -> new PotFlowerBlock(POT_PROPERTIES, SHAPE_FLOWER)));
            POT_JUG_MAP.put(p, BLOCKS.register("pot_jug_" + p.getSerializedName(),
                    () -> new PotJugBlock(POT_PROPERTIES, SHAPE_JUG)));
            POT_JUG_LARGE_MAP.put(p, BLOCKS.register("pot_jug_large_" + p.getSerializedName(),
                    () -> new PotJugLargeBlock(POT_PROPERTIES, p, SHAPE_JUG_LARGE).setBlockEntity(BlockEntityRegistry.POT_JUG_LARGE)));
            POT_JUG_LARGE_TOP_MAP.put(p, BLOCKS.register("pot_jug_large_component_" + p.getSerializedName(),
                    () -> new PotJugComponentBlock(POT_PROPERTIES, SHAPE_JUG_LARGE_UPPER)));
            POT_PITCHER_MAP.put(p, BLOCKS.register("pot_pitcher_" + p.getSerializedName(),
                    () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_PITCHER)));
            POT_PLANTER_MAP.put(p, BLOCKS.register("pot_planter_" + p.getSerializedName(),
                    () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_PLANTER)));
            POT_PLANTER_SMALL_MAP.put(p, BLOCKS.register("pot_planter_small_" + p.getSerializedName(),
                    () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_PLANTER_SMALL)));
            POT_SMALL_MAP.put(p, BLOCKS.register("pot_small_" + p.getSerializedName(),
                    () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_SMALL)));
            POT_TALL_MAP.put(p, BLOCKS.register("pot_tall_" + p.getSerializedName(),
                    () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_TALL)));
            POT_VASE_MAP.put(p, BLOCKS.register("pot_vase_" + p.getSerializedName(),
                    () -> new PotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_VASE)));
            POT_VASE_LARGE_MAP.put(p, BLOCKS.register("pot_vase_large_" + p.getSerializedName(),
                    () -> new TwoTallPotBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), SHAPE_VASE_LARGE_LOWER, SHAPE_VASE_LARGE_UPPER)));
        });
    }

    @SafeVarargs
    public static Block[] allPotsOfType(HashMap<PotColor, RegistryObject<Block>>... potRegistry) {
        List<Block> pots = new ArrayList<>();
        Arrays.stream(potRegistry).toList().forEach(registry -> {
            registry.forEach((d, k) -> {
                pots.add(k.get());
            });
        });
        Block[] array = new Block[pots.size()];
        pots.toArray(array);
        return array;

    }
}
