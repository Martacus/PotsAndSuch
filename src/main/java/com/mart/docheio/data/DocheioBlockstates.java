package com.mart.docheio.data;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.PotBlock;
import net.minecraft.data.PackOutput;
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
import java.util.function.Predicate;

import static com.mart.docheio.PotsMod.docheioPath;
import static com.mart.docheio.common.registry.blocks.DocheioBlocks.BLOCKS;

public class DocheioBlockstates extends BlockStateProvider {

    public DocheioBlockstates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PotsMod.DOCHEIO, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Set<RegistryObject<Block>> blocks = new HashSet<>(BLOCKS.getEntries());
        takeAll(blocks, b -> b.get() instanceof PotBlock).forEach(this::potBlock);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Docheio BlockStates";
    }

    public void potBlock(RegistryObject<Block> blockRegistryObject) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = docheioPath("block/" + name);
        ResourceLocation top = docheioPath("block/" + name);

        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> {
            String type = s.getValue(PotBlock.COLOR).getSerializedName();
            if(!type.equals("normal")){
                ModelFile pot = models().withExistingParent(name + "_" + type, docheioPath("block/templates/" + name)).texture("all", side + "_" + type);
                return ConfiguredModel.builder().modelFile(pot).build();
            } else {
                ModelFile pot = models().withExistingParent(name, docheioPath("block/templates/" + name)).texture("all", side);
                return ConfiguredModel.builder().modelFile(pot).build();
            }
        });
    }

    public static <T> Collection<T> takeAll(Collection<T> src, Predicate<T> pred) {
        List<T> ret = new ArrayList<>();

        Iterator<T> iter = src.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (pred.test(item)) {
                iter.remove();
                ret.add(item);
            }
        }

        if (ret.isEmpty()) {
            return Collections.emptyList();
        }
        return ret;
    }


}
