package com.mart.docheio.data;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.*;
import com.mart.docheio.common.blocks.amphora.PotAmphoraBlock;
import com.mart.docheio.common.blocks.amphora.PotAmphoraComponentBlock;
import com.mart.docheio.common.blocks.flower.PotFlowerBlock;
import com.mart.docheio.common.blocks.jug.PotJugBlock;
import com.mart.docheio.common.blocks.jug_large.PotJugComponentBlock;
import com.mart.docheio.common.blocks.jug_large.PotJugLargeBlock;
import com.mart.docheio.common.blocks.patterns.*;
import com.mart.docheio.common.blocks.pitcher.PotPitcherBlock;
import com.mart.docheio.common.blocks.planter.PotPlanterBlock;
import com.mart.docheio.common.blocks.planter.PotPlanterSmallBlock;
import com.mart.docheio.common.blocks.pot.PotPotBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
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
        takeAll(blocks, b -> b.get() instanceof PotJugBlock).forEach(this::potJugPatternBlock);
        takeAll(blocks, b -> b.get() instanceof PotJugLargeBlock).forEach(this::potJugLargePatternBlock);
        takeAll(blocks, b -> b.get() instanceof PotJugComponentBlock).forEach(this::potJugLargeComponentBlock);
        takeAll(blocks, b -> b.get() instanceof PotPitcherBlock).forEach(this::potPitcherPatternBlock);
        takeAll(blocks, b -> b.get() instanceof PotPlanterBlock).forEach(this::potPlanterPatternBlock);
        takeAll(blocks, b -> b.get() instanceof PotPlanterSmallBlock).forEach(this::potPlanterSmallPatternBlock);
        //takeAll(blocks, b -> b.get() instanceof PotBlock).forEach(this::potBlock);
        //takeAll(blocks, b -> b.get() instanceof TwoTallPotBlock).forEach(this::tallPotBlock);
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
            builder.part().modelFile(lowerPatternModel).addModel().condition(DocheioProperties.POT_BOTTOM_PATTERN, bottomPattern);
        }

        for(PotPattern.UPPER upperPattern : PotPattern.UPPER.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot/pot_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_upper_" + type_upper, docheioPath("block/templates/" + templateName + "_upper")).renderType("cutout_mipped").texture("pattern_2", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(DocheioProperties.POT_UPPER_PATTERN, upperPattern);
        }
    }

    public void potAmphoraPatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name, docheioPath("block/templates/" + templateName + "_base")).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.EAST);
        builder.part().modelFile(baseModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.SOUTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.WEST);

        for(PotAmphoraPattern.BOTTOM bottomPattern : PotAmphoraPattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName(); 
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_amphora/pot_amphora_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_lower")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(DocheioProperties.POT_AMPHORA_BOTTOM_PATTERN, bottomPattern);
        }

        for(PotAmphoraPattern.MIDDLE middlePattern : PotAmphoraPattern.MIDDLE.values()){
            String type_middle = middlePattern.getSerializedName();
            ResourceLocation middle_texture = type_middle.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_amphora/pot_amphora_pattern_" + type_middle);
            ModelFile middlePatternModel = models().withExistingParent(templateName + "_middle_" + type_middle , docheioPath("block/templates/" + templateName + "_middle")).renderType("cutout_mipped").texture("pattern_2", middle_texture);
            builder.part().modelFile(middlePatternModel).addModel().condition(DocheioProperties.POT_AMPHORA_MIDDLE_PATTERN, middlePattern);
        }

        for(PotAmphoraPattern.UPPER upperPattern : PotAmphoraPattern.UPPER.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_amphora/pot_amphora_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_middle_top_" + type_upper , docheioPath("block/templates/" + templateName + "_middle_top")).renderType("cutout_mipped").texture("pattern_3", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(DocheioProperties.POT_AMPHORA_UPPER_PATTERN, upperPattern);
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

        for(PotAmphoraPattern.UPPER upperPattern : PotAmphoraPattern.UPPER.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_amphora/pot_amphora_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_upper_" + type_upper , docheioPath("block/templates/" + templateName + "_upper")).renderType("cutout_mipped").texture("pattern_3", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(DocheioProperties.POT_AMPHORA_UPPER_PATTERN, upperPattern);
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

    public void potJugPatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name , docheioPath("block/templates/" + templateName)).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.EAST);
        builder.part().modelFile(baseModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.SOUTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.WEST);

        for(PotJugPattern.BOTTOM bottomPattern : PotJugPattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName();
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_jug/pot_jug_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_lower")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(DocheioProperties.POT_JUG_BOTTOM_PATTERN, bottomPattern);
        }

        for(PotJugPattern.UPPER upperPattern : PotJugPattern.UPPER.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_jug/pot_jug_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_upper_" + type_upper, docheioPath("block/templates/" + templateName + "_upper")).renderType("cutout_mipped").texture("pattern_2", upper_texture);
            builder.part().modelFile(upperPatternModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.NORTH).condition(DocheioProperties.POT_JUG_UPPER_PATTERN, upperPattern);
            builder.part().modelFile(upperPatternModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.EAST).condition(DocheioProperties.POT_JUG_UPPER_PATTERN, upperPattern);
            builder.part().modelFile(upperPatternModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.SOUTH).condition(DocheioProperties.POT_JUG_UPPER_PATTERN, upperPattern);
            builder.part().modelFile(upperPatternModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.WEST).condition(DocheioProperties.POT_JUG_UPPER_PATTERN, upperPattern);
        }
    }

    public void potJugLargePatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name, docheioPath("block/templates/" + templateName + "_base")).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.EAST);
        builder.part().modelFile(baseModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.SOUTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.WEST);

        for(PotJugLargePattern.BOTTOM bottomPattern : PotJugLargePattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName();
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_jug_large/pot_jug_large_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_lower")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(DocheioProperties.POT_JUG_LARGE_BOTTOM_PATTERN, bottomPattern);
        }

        for(PotJugLargePattern.MIDDLE middlePattern : PotJugLargePattern.MIDDLE.values()){
            String type_middle = middlePattern.getSerializedName();
            ResourceLocation middle_texture = type_middle.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_jug_large/pot_jug_large_pattern_" + type_middle);
            ModelFile middlePatternModel = models().withExistingParent(templateName + "_middle_" + type_middle , docheioPath("block/templates/" + templateName + "_middle")).renderType("cutout_mipped").texture("pattern_2", middle_texture);
            builder.part().modelFile(middlePatternModel).addModel().condition(DocheioProperties.POT_JUG_LARGE_MIDDLE_PATTERN, middlePattern);
        }

        for(PotJugLargePattern.UPPER upperPattern : PotJugLargePattern.UPPER.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_jug_large/pot_jug_large_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_middle_top_" + type_upper , docheioPath("block/templates/" + templateName + "_middle_top")).renderType("cutout_mipped").texture("pattern_3", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(DocheioProperties.POT_JUG_LARGE_TOP_PATTERN, upperPattern);
        }
    }

    public void potJugLargeComponentBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = docheioPath("block/" + name.replace("_component", ""));
        final String templateName = replaceAll(replaceAll(name, colors), List.of("_component"));

        ModelFile baseModel = models().withExistingParent(name, docheioPath("block/templates/" + templateName + "_top"))
                .texture("main", side).texture("particle", side);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.EAST);
        builder.part().modelFile(baseModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.SOUTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.WEST);

        for(PotJugLargePattern.UPPER upperPattern : PotJugLargePattern.UPPER.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_jug_large/pot_jug_large_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_upper_" + type_upper , docheioPath("block/templates/" + templateName + "_upper")).renderType("cutout_mipped").texture("pattern_3", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(DocheioProperties.POT_JUG_LARGE_TOP_PATTERN, upperPattern);
        }
    }

    public void potPitcherPatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name , docheioPath("block/templates/" + templateName)).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.EAST);
        builder.part().modelFile(baseModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.SOUTH);
        builder.part().modelFile(baseModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.WEST);

        for(PotPitcherPattern.BOTTOM bottomPattern : PotPitcherPattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName();
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_pitcher/pot_pitcher_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_lower")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(DocheioProperties.POT_PITCHER_BOTTOM_PATTERN, bottomPattern);
        }

        for(PotPitcherPattern.UPPER upperPattern : PotPitcherPattern.UPPER.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_pitcher/pot_pitcher_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_upper_" + type_upper, docheioPath("block/templates/" + templateName + "_upper")).renderType("cutout_mipped").texture("pattern_2", upper_texture);
            builder.part().modelFile(upperPatternModel).rotationY((int) Direction.NORTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.NORTH).condition(DocheioProperties.POT_PITCHER_UPPER_PATTERN, upperPattern);
            builder.part().modelFile(upperPatternModel).rotationY((int) Direction.EAST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.EAST).condition(DocheioProperties.POT_PITCHER_UPPER_PATTERN, upperPattern);
            builder.part().modelFile(upperPatternModel).rotationY((int) Direction.SOUTH.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.SOUTH).condition(DocheioProperties.POT_PITCHER_UPPER_PATTERN, upperPattern);
            builder.part().modelFile(upperPatternModel).rotationY((int) Direction.WEST.toYRot()).addModel().condition(HorizontalDirectionalBlock.FACING, Direction.WEST).condition(DocheioProperties.POT_PITCHER_UPPER_PATTERN, upperPattern);
        }
    }

    public void potPlanterPatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name , docheioPath("block/templates/" + templateName)).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).addModel();

        for(PotPlanterPattern.BOTTOM bottomPattern : PotPlanterPattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName();
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_planter/pot_planter_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_lower")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(DocheioProperties.POT_PLANTER_BOTTOM_PATTERN, bottomPattern);
        }

        for(PotPlanterPattern.UPPER upperPattern : PotPlanterPattern.UPPER.values()){
            String type_upper = upperPattern.getSerializedName();
            ResourceLocation upper_texture = type_upper.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_planter/pot_planter_pattern_" + type_upper);
            ModelFile upperPatternModel = models().withExistingParent(templateName + "_upper_" + type_upper, docheioPath("block/templates/" + templateName + "_upper")).renderType("cutout_mipped").texture("pattern_2", upper_texture);
            builder.part().modelFile(upperPatternModel).addModel().condition(DocheioProperties.POT_PLANTER_UPPER_PATTERN, upperPattern);
        }
    }

    public void potPlanterSmallPatternBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation textureLocation = docheioPath("block/" + name);
        final String templateName = replaceAll(name, colors);

        ModelFile baseModel = models().withExistingParent(name , docheioPath("block/templates/" + templateName)).renderType("cutout_mipped")
                .texture("main", textureLocation).texture("particle", textureLocation);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(blockRegistryObject.get());
        builder.part().modelFile(baseModel).addModel();

        for(PotPlanterSmallPattern.BOTTOM bottomPattern : PotPlanterSmallPattern.BOTTOM.values()){
            String type_bottom = bottomPattern.getSerializedName();
            ResourceLocation bottom_texture = type_bottom.equals("transparent") ? docheioPath("block/patterns/transparent") : docheioPath("block/patterns/pot_planter_small/pot_planter_small_pattern_" + type_bottom);
            ModelFile lowerPatternModel = models().withExistingParent(templateName + "_lower_" + type_bottom , docheioPath("block/templates/" + templateName + "_lower")).renderType("cutout_mipped").texture("pattern_1", bottom_texture);
            builder.part().modelFile(lowerPatternModel).addModel().condition(DocheioProperties.POT_PLANTER_SMALL_BOTTOM_PATTERN, bottomPattern);
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
