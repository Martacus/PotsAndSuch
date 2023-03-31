package com.mart.docheio.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.*;

public class DocheioLootTables extends LootTableProvider {

    private final List<SubProviderEntry> tables = new ArrayList<>();

    public DocheioLootTables(PackOutput pOutput) {
        super(pOutput, Collections.emptySet(), Collections.emptyList());
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> tables, ValidationContext ctx) {
        tables.forEach((name, table) -> LootTables.validate(ctx, name, table));
    }

    @Override
    public List<SubProviderEntry> getTables() {

        //tables.add(new LootTableProvider.SubProviderEntry(PotBlockSubprovider::new, LootContextParamSets.BLOCK));

        return tables;
    }



}
