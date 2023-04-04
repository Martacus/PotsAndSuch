package com.mart.docheio.client.render.blockentity;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.blockentity.PotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class PotEntityRenderer implements BlockEntityRenderer<PotBlockEntity> {

    public static final ResourceLocation VIGNETTE = PotsMod.docheioPath("textures/block/patterns/pot_small/pattern_1.png");

    public PotEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PotBlockEntity pBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(pBlockEntity.getBlockState());
        for (BakedQuad quad : model.getQuads(pBlockEntity.getBlockState(), null, pBlockEntity.getLevel().random)) {
            // Check if this is the quad you want to modify.
            // You can use quad.getSprite() to get the texture of the quad.
            // If this is the quad you want to modify, create a new BakedQuad with the modified texture.
            // Otherwise, use the existing quad.
            //BakedQuad modifiedQuad = /* create a new BakedQuad with the modified texture */;

            // Replace the existing quad with the modified quad in the BakedModel.
            //model.getQuads(state, null, blockEntity.getWorld().random).remove(quad);
            //model.getQuads(state, null, blockEntity.getWorld().random).add(modifiedQuad);
            System.out.println(quad.getSprite());
            System.out.println(quad.getSprite().getName());
        }
    }

    public void renderQuad(PoseStack poseStack, int pPackedLight) {
        float height = 9/16f;
        float width = 13/16f;
        VertexConsumer textureConsumer = RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderTypeRegistry.TRANSPARENT_TEXTURE.applyAndCache(VIGNETTE));
        Vector3f[] positions = new Vector3f[]{new Vector3f(13/16f, 0, 0),new Vector3f(0, 0, 0), new Vector3f(0, height, 0), new Vector3f(width, height, 0)};
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setLight(pPackedLight).setPosColorTexLightmapDefaultFormat();

        poseStack.pushPose();
        poseStack.translate(0f, 0, (3/16f) - 0.0001f);
        builder.renderQuad(textureConsumer, poseStack, positions, 1f);
        builder.setPosColorLightmapDefaultFormat();
        poseStack.popPose();
    }
}
