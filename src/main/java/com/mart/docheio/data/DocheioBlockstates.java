package com.mart.docheio.data;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.*;
import com.mart.docheio.common.blocks.amphora.PotAmphoraBlock;
import com.mart.docheio.common.blocks.amphora.PotAmphoraComponentBlock;
import com.mart.docheio.common.blocks.flower.PotFlowerBlock;
import com.mart.docheio.common.blocks.patterns.PotAmphoraPattern;
import com.mart.docheio.common.blocks.patterns.PotFlowerPattern;
import com.mart.docheio.common.blocks.patterns.PotPattern;
import com.mart.docheio.common.blocks.pot.PotPotBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
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
        takeAll(blocks, b -> b.get() instanceof PotAmphoraBlock).forEach(this::potAmphoraPatternBlock);
        takeAll(blocks, b -> b.get() instanceof PotAmphoraComponentBlock).forEach(this::potAmphoraComponentBlock);
        takeAll(blocks, b -> b.get() instanceof PotFlowerBlock).forEach(this::potFlowerPatternBlock);
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

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name , docheioPath("block/templates/" + templateName)).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).addModel();

        for(PotPattern.BOTTOM bottomPattern : PotPattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName();
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot/pot_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_lower")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(PotPotBlock.BOTTOM_PATTERN, bottomPattern);
        }

        for(PotPattern.TOP topPattern : PotPattern.TOP.values()){
            String type_upper = topPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot/pot_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_upper_" + type_upper, docheioPath("block/templates/" + templateName + "_upper")).renderType("cutout_mipped").texture("pattern_2", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(PotPotBlock.TOP_PATTERN, topPattern);
        }
    }

    public void potAmphoraPatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name, docheioPath("block/templates/" + templateName + "_base")).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(PotAmphoraBlock.FACING, Direction.NORTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(PotAmphoraBlock.FACING, Direction.EAST);
        builder.part().modelFile(baseModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(PotAmphoraBlock.FACING, Direction.SOUTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(PotAmphoraBlock.FACING, Direction.WEST);

        for(PotAmphoraPattern.BOTTOM bottomPattern : PotAmphoraPattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName(); 
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_amphora/pot_amphora_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_lower")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(PotAmphoraBlock.BOTTOM_PATTERN, bottomPattern);
        }

        for(PotAmphoraPattern.MIDDLE middlePattern : PotAmphoraPattern.MIDDLE.values()){
            String type_middle = middlePattern.getSerializedName();
            ResourceLocation middle_texture = type_middle.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_amphora/pot_amphora_pattern_" + type_middle);
            ModelFile middlePatternModel = models().withExistingParent(templateName + "_middle_" + type_middle , docheioPath("block/templates/" + templateName + "_middle")).renderType("cutout_mipped").texture("pattern_2", middle_texture);
            builder.part().modelFile(middlePatternModel).addModel().condition(PotAmphoraBlock.MIDDLE_PATTERN, middlePattern);
        }

        for(PotAmphoraPattern.TOP upperPattern : PotAmphoraPattern.TOP.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_amphora/pot_amphora_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_middle_top_" + type_upper , docheioPath("block/templates/" + templateName + "_middle_top")).renderType("cutout_mipped").texture("pattern_3", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(PotAmphoraBlock.TOP_PATTERN, upperPattern);
        }
    }

    public void potAmphoraComponentBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = docheioPath("block/" + name.replace("_component", ""));
        final String templateName = replaceAll(replaceAll(name, colors), List.of("_component"));

        ModelFile baseModel = models().withExistingParent(name, docheioPath("block/templates/" + templateName + "_component"))
                .texture("main", side).texture("particle", side);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(PotAmphoraBlock.FACING, Direction.NORTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(PotAmphoraBlock.FACING, Direction.EAST);
        builder.part().modelFile(baseModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(PotAmphoraBlock.FACING, Direction.SOUTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(PotAmphoraBlock.FACING, Direction.WEST);

        for(PotAmphoraPattern.TOP upperPattern : PotAmphoraPattern.TOP.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_amphora/pot_amphora_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_upper_" + type_upper , docheioPath("block/templates/" + templateName + "_upper")).renderType("cutout_mipped").texture("pattern_3", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(PotAmphoraComponentBlock.TOP_PATTERN, upperPattern);
        }
    }

    public void potFlowerPatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name , docheioPath("block/templates/" + templateName)).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).addModel();

        for(PotFlowerPattern.BOTTOM bottomPattern : PotFlowerPattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName();
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_flower/pot_flower_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_pattern")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(DocheioProperties.POT_FLOWER_BOTTOM_PATTERN, bottomPattern);
        }
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
