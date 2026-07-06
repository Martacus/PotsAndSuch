package com.mart.docheio.client.render.blockentity;

import com.mart.docheio.common.blockentity.PotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;

/**
 * Renders the whole pot: the coloured base body plus any pattern overlays. Doing the
 * base here (rather than as normal chunk terrain) keeps the base and the pattern
 * overlays on a single transform/draw path with identical geometry, so the later-drawn
 * pattern wins the depth test cleanly instead of z-fighting the base. The block reports
 * {@code ENTITYBLOCK_ANIMATED} so the chunk renderer does not also draw it.
 */
public class PotEntityRenderer implements BlockEntityRenderer<PotBlockEntity> {

    private final BlockRenderDispatcher blockRenderer;

    public PotEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(PotBlockEntity pot, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState state = pot.getBlockState();
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.cutout());

        // Base body (the per-colour block model for this state).
        drawModel(blockRenderer.getBlockModel(state), state, poseStack, consumer, packedLight);

        // Each occupied slot's pattern overlay, sharing the base geometry & UV layout.
        for (ResourceLocation modelId : pot.getPatterns().values()) {
            BakedModel overlay = Minecraft.getInstance().getModelManager().getModel(modelId);
            drawModel(overlay, state, poseStack, consumer, packedLight);
        }
    }

    private void drawModel(BakedModel model, BlockState state, PoseStack poseStack,
                           VertexConsumer consumer, int packedLight) {
        blockRenderer.getModelRenderer().renderModel(
                poseStack.last(), consumer, state, model,
                1.0F, 1.0F, 1.0F,
                packedLight, OverlayTexture.NO_OVERLAY,
                ModelData.EMPTY, RenderType.cutout());
    }
}
