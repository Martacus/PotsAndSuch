package com.mart.docheio.data;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blocks.PotBlock;
import com.mart.docheio.common.blocks.PotPotBlock;
import com.mart.docheio.common.blocks.TallPotBlock;
import com.mart.docheio.common.util.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.mart.docheio.PotsMod.docheioPath;
import static com.mart.docheio.common.registry.item.DocheioItems.ITEMS;
import static com.mart.docheio.common.util.Util.takeAll;
import static com.mart.docheio.data.DocheioBlockstates.colors;
import static com.mart.docheio.data.DocheioBlockstates.replaceAll;

public class DocheioItemsData extends ItemModelProvider {

    public DocheioItemsData(DataGenerator output, ExistingFileHelper existingFileHelper) {
        super(output, PotsMod.DOCHEIO, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<RegistryObject<Item>> items = new HashSet<>(ITEMS.getEntries());
        Collection<RegistryObject<Item>> blockItems = new ArrayList<>(takeAll(items, i -> i.get() instanceof BlockItem));

        takeAll(blockItems, i -> ((BlockItem) i.get()).getBlock() instanceof PotPotBlock).forEach(this::blockPatternItem);
        takeAll(blockItems, i -> ((BlockItem) i.get()).getBlock() instanceof PotBlock).forEach(this::tallBlockItem);
        takeAll(blockItems, i -> ((BlockItem) i.get()).getBlock() instanceof TallPotBlock).forEach(this::tallBlockItem);
        blockItems.forEach(this::blockItem);
    }

    private void blockItem(RegistryObject<Item> i) {
        String name = ForgeRegistries.ITEMS.getKey(i.get()).getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(PotsMod.docheioPath("block/" + name)));

    }

    private void blockPatternItem(RegistryObject<Item> i) {
        String name = ForgeRegistries.ITEMS.getKey(i.get()).getPath();
        ResourceLocation blank = docheioPath("block/patterns/transparent");
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(PotsMod.docheioPath("block/" + name)))
                .texture("pattern_1", blank)
                .texture("pattern_2", blank);
    }


    private void tallBlockItem(RegistryObject<Item> i) {
        String name = ForgeRegistries.ITEMS.getKey(i.get()).getPath();
        final String templateName = replaceAll(name, colors);

        ResourceLocation texture = docheioPath("block/" + name);
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(PotsMod.docheioPath("block/templates/" + templateName)))
                .texture("main", texture)
                .texture("particle", texture)
        ;

    }

}
