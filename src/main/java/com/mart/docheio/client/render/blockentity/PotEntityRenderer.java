package com.mart.docheio.client.render.blockentity;

import com.mart.docheio.common.blockentity.PotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class PotEntityRenderer implements BlockEntityRenderer<PotBlockEntity> {

    public PotEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PotBlockEntity pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        renderQuad(poseStack);
    }

    public void renderQuad(PoseStack poseStack) {
//        float height = 0.75f;
//        float width = 1.5f;
//        VertexConsumer textureConsumer = RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderTypeRegistry.TRANSPARENT_TEXTURE.applyAndCache(VIGNETTE));
//        Vector3f[] positions = new Vector3f[]{new Vector3f(-width, height, width), new Vector3f(width, height, width), new Vector3f(width, height, -width), new Vector3f(-width, height, -width)};
//        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setPosColorTexLightmapDefaultFormat();
//
//        poseStack.pushPose();
//        poseStack.translate(0.5f, 0.001f, 0.5f);
//        builder.renderQuad(textureConsumer, poseStack, positions, 1f);
//        builder.setPosColorLightmapDefaultFormat();
//        poseStack.popPose();
    }
}
