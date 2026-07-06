package com.mart.docheio.common.util;

import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.mart.docheio.PotsMod.docheioPath;

/**
 * Registry of which decorative patterns each pot shape offers, plus the naming
 * convention that ties a (shape, pattern) pair to its overlay model and texture.
 *
 * <p>For a shape {@code S} and pattern {@code P} the authored texture lives at
 * {@code textures/block/patterns/S/S_pattern_P.png} and the generated overlay
 * model at {@code models/block/pattern/S_pattern_P.json}. The overlay inherits
 * the shape's template model, so it shares the base body's exact geometry and UV
 * layout — the renderer just draws it a second time on top.
 *
 * <p>The {@code rim_} / {@code neck_} / {@code low_} / {@code handles} fragments
 * in a pattern name select which {@link com.mart.docheio.common.blockentity.PotBlockEntity.PatternSlot}
 * it paints, so a pot can wear one pattern per region simultaneously.
 *
 * <p>The multi-block amphora and vase_large ({@link #MULTIBLOCK}) render as two
 * stacked halves that share one texture; their overlay models are generated as
 * {@code _lower}/{@code _upper} pairs (each half's template samples its own
 * region of the shared pattern texture), so at apply time both halves are set —
 * see {@link #halfModelId}.
 */
public final class PotPatterns {

    /** Shape base name (e.g. {@code pot_jug}) to its authored pattern short-names. */
    public static final Map<String, List<String>> BY_SHAPE = buildRegistry();

    private static Map<String, List<String>> buildRegistry() {
        Map<String, List<String>> m = new LinkedHashMap<>();
        m.put("pot", List.of("blocks", "bold", "checkers", "eyes", "inverse_eyes",
                "linespeck", "rim_blocks", "rim_specks", "wiggle", "zag"));
        m.put("pot_flower", List.of("eyes", "label", "rim", "specks", "striped"));
        m.put("pot_jug", List.of("blocks", "handles", "label", "neck_checkers",
                "neck_stripe", "rim_blocks", "rim_bold", "stripes", "wiggle", "zag"));
        m.put("pot_jug_large", List.of("blocks", "eye", "handles", "label",
                "low_checkers", "low_label", "low_wiggle", "neck_checkers",
                "neck_stripe", "rim_blocks", "rim_specks", "specks", "stripes",
                "wiggle", "zag"));
        m.put("pot_pitcher", List.of("blocks", "label", "neck_label", "neck_specks",
                "neck_stripes", "rim_eyes", "rim_specks", "specks", "stripes", "wiggle"));
        m.put("pot_planter", List.of("blocks", "hide", "neck_blocks", "neck_peek",
                "neck_suspicious", "rim_blocks", "rim_eyes", "stripes", "wave", "wiggle"));
        m.put("pot_planter_small", List.of("blocks", "chevron", "eyes", "hide", "striped"));
        m.put("pot_small", List.of("blocks", "chevron", "eyes", "rim_striped", "specks"));
        m.put("pot_tall", List.of("blocks", "chevron", "edges", "full_stripes", "striped"));
        m.put("pot_vase", List.of("blocks", "checkers", "eyes", "eyes_inverse",
                "neck_stripe", "rim_specks", "soul", "stripes", "wiggle", "zag"));
        m.put("pot_amphora", List.of("chevron", "eyes", "hide", "low_eyes",
                "low_stripes", "low_wiggle", "low_zag", "neck_eyes", "neck_hide",
                "neck_stitch", "neck_stripes", "stitch", "stripe", "wiggle", "zag"));
        m.put("pot_vase_large", List.of("blocks", "checkers", "chevron", "eye",
                "hide", "low_blocks", "low_checkers", "neck_chevron", "neck_eye",
                "neck_specks", "neck_stripes", "pray", "squint", "stripes", "wiggle"));
        return Collections.unmodifiableMap(m);
    }

    /** Shapes rendered as two stacked halves; their overlay models come in _lower/_upper pairs. */
    public static final Set<String> MULTIBLOCK = Set.of("pot_amphora", "pot_vase_large");

    /** The two half suffixes used in multi-block template + overlay model names. */
    public static final List<String> HALVES = List.of("lower", "upper");

    public static boolean isMultiblock(String shape) {
        return MULTIBLOCK.contains(shape);
    }

    public static List<String> forShape(String shape) {
        return BY_SHAPE.getOrDefault(shape, List.of());
    }

    /** e.g. {@code ("pot_jug","rim_blocks")} to {@code "pot_jug_pattern_rim_blocks"}. */
    public static String modelName(String shape, String pattern) {
        return shape + "_pattern_" + pattern;
    }

    /** Overlay model id, e.g. {@code docheio:block/pattern/pot_jug_pattern_rim_blocks}. */
    public static ResourceLocation modelId(String shape, String pattern) {
        return docheioPath("block/pattern/" + modelName(shape, pattern));
    }

    /** Pattern texture id, e.g. {@code docheio:block/patterns/pot_jug/pot_jug_pattern_rim_blocks}. */
    public static ResourceLocation texture(String shape, String pattern) {
        return docheioPath("block/patterns/" + shape + "/" + modelName(shape, pattern));
    }

    /** e.g. {@code ("pot_amphora","upper","neck_stitch")} to {@code "pot_amphora_upper_pattern_neck_stitch"}. */
    public static String halfModelName(String shape, String half, String pattern) {
        return shape + "_" + half + "_pattern_" + pattern;
    }

    /** Half-specific overlay model id for a multi-block shape (see {@link #MULTIBLOCK}). */
    public static ResourceLocation halfModelId(String shape, String half, String pattern) {
        return docheioPath("block/pattern/" + halfModelName(shape, half, pattern));
    }

    // --- color-suffix stripping ------------------------------------------------

    // Longest suffix first so "_light_gray" is matched before "_gray", etc.
    private static final List<String> COLOR_SUFFIXES = Arrays.stream(PotColor.values())
            .map(c -> "_" + c.getSerializedName())
            .sorted(Comparator.comparingInt(String::length).reversed())
            .toList();

    /** {@code "pot_jug_light_blue"} to {@code "pot_jug"}; an already-bare shape is unchanged. */
    public static String shapeOf(String blockName) {
        for (String suffix : COLOR_SUFFIXES) {
            if (blockName.endsWith(suffix)) {
                return blockName.substring(0, blockName.length() - suffix.length());
            }
        }
        return blockName;
    }

    private PotPatterns() {}
}
