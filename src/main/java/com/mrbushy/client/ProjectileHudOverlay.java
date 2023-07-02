package com.mrbushy.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mrbushy.ProjectileAlert;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ProjectileHudOverlay implements HudRenderCallback {
    public static final Identifier ALERT = new Identifier(ProjectileAlert.MOD_ID, "textures/alert_pix.png");
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width/2;
            y = height;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ALERT);
        DrawableHelper.drawTexture(matrixStack, x - 17, 10, 0, 0, 30, 30, 30, 30);
    }
}
