package com.mart.docheio.common.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PotColorEnumProperty extends EnumProperty<PotColor> {

    private final ImmutableSet<String> values;
    private final Map<String, PotColor> types = Maps.newHashMap();

    protected PotColorEnumProperty(String pName, Class<PotColor> pClazz, Collection<PotColor> pValues) {
        super(pName, pClazz, pValues);
        this.values = ImmutableSet.copyOf(pValues.stream().map(PotColor::getSerializedName).collect(Collectors.toList()));

        for (PotColor color : PotColor.values()) {
            this.types.put(color.getSerializedName(), color);
        }
    }

    @Override
    public Optional<PotColor> getValue(String pValue) {
        return super.getValue(pValue);
    }

    @Override
    public Collection<PotColor> getPossibleValues() {
        return this.types.values();
    }

}
