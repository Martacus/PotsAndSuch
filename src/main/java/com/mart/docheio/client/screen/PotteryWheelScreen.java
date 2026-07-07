package com.mart.docheio.client.screen;

import com.mart.docheio.PotsMod;
import com.mart.docheio.common.menu.PotteryWheelMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class PotteryWheelScreen extends AbstractContainerScreen<PotteryWheelMenu> {

    private static final ResourceLocation TEXTURE =
            PotsMod.docheioPath("textures/gui/pottery_wheel.png");

    public PotteryWheelScreen(PotteryWheelMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        // Background occupies 0,0 -> 175,175 of the texture.
        this.imageWidth = 176;
        this.imageHeight = 176;
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
        // Only the title for now; there is no player inventory to label.
        this.font.draw(poseStack, this.title, this.titleLabelX, this.titleLabelY, 0x404040);
    }
}
