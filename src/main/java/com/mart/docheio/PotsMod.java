package com.mart.docheio;

import com.mart.docheio.common.registry.blocks.BlockRegistry;
import com.mart.docheio.common.registry.item.DocheioItems;
import com.mart.docheio.data.DocheioBlockstates;
import com.mart.docheio.data.DocheioItemsData;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mart.docheio.common.registry.blocks.BlockRegistry.BLOCKS;
import static com.mart.docheio.common.registry.entity.BlockEntityRegistry.BLOCK_ENTITY_TYPES;
import static com.mart.docheio.common.registry.item.DocheioItems.ITEMS;

@Mod(PotsMod.DOCHEIO)
public class PotsMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String DOCHEIO = "docheio";

    public PotsMod(){
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.createPots();
        DocheioItems.createPots();

        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        BLOCK_ENTITY_TYPES.register(modBus);

        modBus.addListener(DataOnly::gatherData);
    }

    public static ResourceLocation docheioPath(String path) {
        return new ResourceLocation(DOCHEIO, path);
    }

    public static class DataOnly {
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            generator.addProvider(true, new DocheioBlockstates(generator, event.getExistingFileHelper()));
            generator.addProvider(true, new DocheioItemsData(generator, event.getExistingFileHelper()));
            //generator.addProvider(true, new DocheioLootTables(generator));

            //BlockTagsProvider provider = new MalumBlockTags(generator, event.getExistingFileHelper());
            //generator.addProvider(new MalumItemModels(generator, event.getExistingFileHelper()));
            //generator.addProvider(new MalumLang(generator));
        }
    }
}
