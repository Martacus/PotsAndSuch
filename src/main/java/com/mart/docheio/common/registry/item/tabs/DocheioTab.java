package com.mart.docheio.common.registry.item.tabs;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DocheioTab extends CreativeModeTab
{
    public static final DocheioTab INSTANCE = new DocheioTab();

    public DocheioTab() {
        super(new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 9).icon(() -> new ItemStack(Items.FLOWER_POT)));
    }


}

