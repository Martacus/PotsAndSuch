package com.mart.docheio.common.blockentity;

import com.mart.docheio.common.registry.entity.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public class PotBlockEntity extends BlockEntity {

    /**
     * Which region of a pot a pattern paints onto. A pot may hold at most one
     * pattern per slot; which slots are meaningful depends on the pot's shape.
     */
    public enum PatternSlot {
        BODY, LOWER, NECK, RIM, HANDLES;

        public static PatternSlot fromPatternName(String name) {
            if (name.contains("_neck_")) return NECK;
            if (name.contains("_rim_")) return RIM;
            if (name.contains("_low_")) return LOWER;
            if (name.contains("_handles")) return HANDLES;
            return BODY;
        }
    }

    /** slot -> overlay model id (e.g. docheio:block/pattern/pot_amphora_pattern_chevron) */
    private final Map<PatternSlot, ResourceLocation> patterns = new EnumMap<>(PatternSlot.class);

    public PotBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.POT.get(), pPos, pBlockState);
    }

    public void tick() {
    }

    /** Immutable-ish view for the renderer. */
    public Map<PatternSlot, ResourceLocation> getPatterns() {
        return patterns;
    }

    /** Set (or clear, when modelId == null) the pattern occupying a slot, then sync to clients. */
    public void setPattern(PatternSlot slot, @Nullable ResourceLocation modelId) {
        if (modelId == null) {
            patterns.remove(slot);
        } else {
            patterns.put(slot, modelId);
        }
        setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    // ---- persistence ----

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        CompoundTag patternsTag = new CompoundTag();
        for (Map.Entry<PatternSlot, ResourceLocation> entry : patterns.entrySet()) {
            patternsTag.putString(entry.getKey().name(), entry.getValue().toString());
        }
        tag.put("Patterns", patternsTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        patterns.clear();
        CompoundTag patternsTag = tag.getCompound("Patterns");
        for (String key : patternsTag.getAllKeys()) {
            try {
                patterns.put(PatternSlot.valueOf(key), new ResourceLocation(patternsTag.getString(key)));
            } catch (IllegalArgumentException ignored) {
                // unknown slot from a newer/older version; skip
            }
        }
    }

    // ---- client sync ----

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<net.minecraft.network.protocol.game.ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
