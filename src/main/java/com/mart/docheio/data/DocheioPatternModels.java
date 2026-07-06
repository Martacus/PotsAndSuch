package com.mart.docheio.data;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.util.PotPatterns;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

import static com.mart.docheio.PotsMod.docheioPath;

/**
 * Generates the pattern overlay models that {@code PotEntityRenderer} draws on
 * top of a pot's base body. One model per authored pattern texture per shape:
 * each inherits the shape's template (sharing the base body's exact geometry and
 * UV layout) and swaps in the pattern texture. Output lands in
 * {@code models/block/pattern/}, where {@code PotClientModelEvents} discovers and
 * registers every entry for baking — so a new pattern is just a texture plus a
 * one-line addition to {@link PotPatterns}.
 */
public class DocheioPatternModels extends BlockModelProvider {

    public DocheioPatternModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PotsMod.DOCHEIO, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (Map.Entry<String, List<String>> shape : PotPatterns.BY_SHAPE.entrySet()) {
            for (String pattern : shape.getValue()) {
                String model = PotPatterns.modelName(shape.getKey(), pattern);
                // Full "block/pattern/..." path: BlockModelProvider only prepends its
                // "block/" folder when the name has no slash, and PotClientModelEvents /
                // the overlay ids both resolve models under block/pattern/.
                withExistingParent("block/pattern/" + model, docheioPath("block/templates/" + shape.getKey()))
                        .texture("all", PotPatterns.texture(shape.getKey(), pattern))
                        .texture("particle", PotPatterns.texture(shape.getKey(), pattern))
                        .renderType("minecraft:cutout");
            }
        }
    }

    @Nonnull
    @Override
    public String getName() {
        return "Docheio Pattern Models";
    }
}
