package com.mart.docheio;

import com.mart.docheio.data.DocheioBlockstates;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mart.docheio.common.registry.blocks.DocheioBlocks.BLOCKS;
import static com.mart.docheio.common.registry.item.DocheioItems.ITEMS;

@Mod(PotsMod.POTSMOD)
public class PotsMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String POTSMOD = "docheio";

    public PotsMod(){
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modBus);
        ITEMS.register(modBus);

        modBus.addListener(DataOnly::gatherData);
    }

    public static class DataOnly {
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            //generator.addProvider(true, new DocheioBlockstates(generator.getPackOutput(), event.getExistingFileHelper()));
            //BlockTagsProvider provider = new MalumBlockTags(generator, event.getExistingFileHelper());
            //generator.addProvider(new MalumItemModels(generator, event.getExistingFileHelper()));
            //generator.addProvider(new MalumLang(generator));
        }
    }
}