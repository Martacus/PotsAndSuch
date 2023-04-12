package com.mart.docheio.client.render.blockentity;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blockentity.PotItemBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PotEntityRenderer implements BlockEntityRenderer<PotItemBlockEntity> {

    public static final ResourceLocation VIGNETTE = PotsMod.docheioPath("textures/block/patterns/pot_small/pattern_1.png");

    public PotEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PotItemBlockEntity pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

    }

}
