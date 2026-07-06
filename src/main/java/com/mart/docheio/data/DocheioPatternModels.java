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
        for (Map.Entry<String, List<String>> entry : PotPatterns.BY_SHAPE.entrySet()) {
            String shape = entry.getKey();
            for (String pattern : entry.getValue()) {
                if (PotPatterns.isMultiblock(shape)) {
                    // Two overlays per pattern: each half inherits its own template
                    // (_lower/_upper) but samples the same shared pattern texture.
                    for (String half : PotPatterns.HALVES) {
                        overlay(PotPatterns.halfModelName(shape, half, pattern),
                                "block/templates/" + shape + "_" + half,
                                PotPatterns.texture(shape, pattern));
                    }
                } else {
                    overlay(PotPatterns.modelName(shape, pattern),
                            "block/templates/" + shape,
                            PotPatterns.texture(shape, pattern));
                }
            }
        }
    }

    private void overlay(String model, String parentTemplate, net.minecraft.resources.ResourceLocation texture) {
        // Full "block/pattern/..." path: BlockModelProvider only prepends its "block/"
        // folder when the name has no slash, and PotClientModelEvents / the overlay ids
        // both resolve models under block/pattern/.
        withExistingParent("block/pattern/" + model, docheioPath(parentTemplate))
                .texture("all", texture)
                .texture("particle", texture)
                .renderType("minecraft:cutout");
    }

    @Nonnull
    @Override
    public String getName() {
        return "Docheio Pattern Models";
    }
}
