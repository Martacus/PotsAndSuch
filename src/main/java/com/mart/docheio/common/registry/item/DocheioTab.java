package com.mart.docheio.common.registry.item;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.util.PotColor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class DocheioTab extends CreativeModeTab
{
    public static final DocheioTab INSTANCE = new DocheioTab();

    public DocheioTab() {
        super(PotsMod.DOCHEIO);
    }


    @Override
    public ItemStack makeIcon() {
        return new ItemStack(DocheioItems.POT_MAP.get(PotColor.NORMAL).get());
    }
}
