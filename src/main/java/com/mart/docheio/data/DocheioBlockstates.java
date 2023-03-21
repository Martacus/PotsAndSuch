package com.mart.docheio.data;

import com.mart.docheio.PotsMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class DocheioBlockstates extends BlockStateProvider {

    public DocheioBlockstates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PotsMod.POTSMOD, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    @Nonnull
    @Override
    public String getName() {
        return "Docheio BlockStates";
    }


}
