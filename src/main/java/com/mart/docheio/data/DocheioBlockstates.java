package com.mart.docheio.data;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.*;
import com.mart.docheio.common.blocks.amphora.PotAmphoraBlock;
import com.mart.docheio.common.blocks.amphora.PotAmphoraComponentBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

import java.util.*;

import static com.mart.docheio.PotsMod.docheioPath;
import static com.mart.docheio.common.registry.blocks.BlockRegistry.BLOCKS;
import static com.mart.docheio.common.util.Util.takeAll;

public class DocheioBlockstates extends BlockStateProvider {

    public DocheioBlockstates(DataGenerator output, ExistingFileHelper exFileHelper) {
        super(output, PotsMod.DOCHEIO, exFileHelper);

    }

    @Override
    protected void registerStatesAndModels() {
        Set<RegistryObject<Block>> blocks = new HashSet<>(BLOCKS.getEntries());
        takeAll(blocks, b -> b.get() instanceof PotPotBlock).forEach(this::potPatternBlock);
        takeAll(blocks, b -> b.get() instanceof PotAmphoraBlock).forEach(this::potAmphoraBlock);
        takeAll(blocks, b -> b.get() instanceof PotAmphoraComponentBlock).forEach(this::potAmphoraComponentBlock);
        takeAll(blocks, b -> b.get() instanceof PotBlock).forEach(this::potBlock);
        takeAll(blocks, b -> b.get() instanceof TwoTallPotBlock).forEach(this::tallPotBlock);
        takeAll(blocks, b -> b.get() instanceof PotteryWheelBlock).forEach(this::multipleSideBlock);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Docheio BlockStates";
    }

    public void potBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile pot = models().withExistingParent(name, docheioPath("block/templates/" + templateName))
                .texture("main", side).texture("particle", side);
        getVariantBuilder(blockRegistryObject.get()).partialState().setModels(ConfiguredModel.builder().modelFile(pot).build());
    }

    public void potPatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        getVariantBuilder(blockRegistryObject.get()).forAllStates(m -> {
            String type_top = m.getValue(PotPotBlock.TOP_PATTERN).getSerializedName();
            String type_bottom = m.getValue(PotPotBlock.BOTTOM_PATTERN).getSerializedName();

            ResourceLocation top = type_top.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot/pot_pattern_" + type_top);
            ResourceLocation bottom = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot/pot_pattern_" + type_bottom);

            ModelFile pot = models().withExistingParent(name + "_" + type_top  + "_" + type_bottom, docheioPath("block/templates/" + templateName)).renderType("cutout_mipped")
            .texture("main", side).texture("particle", side)
                    .texture("pattern_1", bottom)
                    .texture("pattern_2", top);

            return ConfiguredModel.builder().modelFile(pot).build();
        });
    }

    public void potAmphoraBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> {
            Direction rotation = s.getValue(PotAmphoraBlock.FACING);
            ModelFile model = models().withExistingParent(name, docheioPath("block/templates/" + templateName + "_lower"))
                    .texture("main", side).texture("particle", side);
            return ConfiguredModel.builder().modelFile(model).rotationY((int) rotation.toYRot()).build();
        });
    }

    public void potAmphoraComponentBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = docheioPath("block/" + name.replace("_component", ""));
        final String templateName = replaceAll(replaceAll(name, colors), List.of("_component"));

        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> {
            Direction rotation = s.getValue(PotAmphoraBlock.FACING);
            ModelFile model = models().withExistingParent(name, docheioPath("block/templates/" + templateName + "_upper"))
                    .texture("main", side).texture("particle", side);
            return ConfiguredModel.builder().modelFile(model).rotationY((int) rotation.toYRot()).build();
        });
    }

    public void tallPotBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> {
            String type = s.getValue(TwoTallPotBlock.HALF).getSerializedName();
            ModelFile model = models().withExistingParent(name + "_" + type, docheioPath("block/templates/" + templateName + "_" + type))
                    .texture("main", side).texture("particle", side);
            return ConfiguredModel.builder().modelFile(model).build();
        });
    }

    public void multipleSideBlock(RegistryObject<Block> blockRegistryObject){
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ModelFile pot = models().withExistingParent(name, new ResourceLocation("minecraft", "block/cube"))
                .texture("down", docheioPath("block/" + name + "/" + name + "_bottom"))
                .texture("up", docheioPath("block/" + name + "/" + name + "_top"))
                .texture("east", docheioPath("block/" + name + "/" + name + "_east"))
                .texture("west", docheioPath("block/" + name + "/" + name + "_west"))
                .texture("south", docheioPath("block/" + name + "/" + name + "_south"))
                .texture("north", docheioPath("block/" + name + "/" + name + "_north"))
                .texture("particle", docheioPath("block/" + name + "/" + name + "_top"))
                ;
        getVariantBuilder(blockRegistryObject.get()).partialState().setModels(ConfiguredModel.builder().modelFile(pot).build());
    }

    public static List<String> colors = List.of("_normal", "_black", "_blue", "_brown", "_cyan", "_clay", "_gray",
            "_green", "_light", "_lime", "_magenta", "_orange", "_pink", "_purple", "_red", "_white", "_yellow");

    public static String replaceAll(String origin, List<String> collection){
        String templateName = "" + origin;
        for(String s : collection){
            templateName = templateName.replace(s, "");
        }
        return templateName;
    }

}
