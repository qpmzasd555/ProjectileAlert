package com.mrbushy.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mrbushy.ProjectileAlert;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;

public class ProjectileHudOverlay implements HudRenderCallback {
    public static final Identifier ALERT = new Identifier(ProjectileAlert.MOD_ID, "textures/alert.png");
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();

        double renderDistance = client.options.getViewDistance().getValue() * 16.0;
        if (client.player != null) {
            Box renderBox = new Box(
                    client.player.getPos().x - renderDistance,
                    client.player.getPos().y - renderDistance,
                    client.player.getPos().z - renderDistance,
                    client.player.getPos().x + renderDistance,
                    client.player.getPos().y + renderDistance,
                    client.player.getPos().z + renderDistance
            );

            for (ArrowEntity arrow : client.player.getWorld().getEntitiesByType(EntityType.ARROW, renderBox, EntityPredicates.VALID_ENTITY)) {

                if (!WallCheck.hasWallInBetween(client.player, arrow)) {

                    Vector3D rayOrigin = new Vector3D(arrow.getX(), arrow.getZ(), arrow.getY());
                    Vector3D rayDirection = new Vector3D(arrow.getVelocity().x, arrow.getVelocity().z, arrow.getVelocity().y);
                    Ray ray = new Ray(rayOrigin, rayDirection);

                    Vector3D parallelepipedMinPoint = new Vector3D(client.player.getPos().x - 0.4375, client.player.getPos().z - 0.4375, client.player.getPos().y);
                    Vector3D parallelepipedMaxPoint = new Vector3D(client.player.getPos().x + 0.4375, client.player.getPos().z + 0.4375, client.player.getPos().y + 1.875);
                    Parallelepiped parallelepiped = new Parallelepiped(parallelepipedMinPoint, parallelepipedMaxPoint);

                    boolean intersects = ray.intersectsParallelepiped(parallelepiped);
                    if (intersects) {
                        ProjectileAlert.DANGER_STATUS = true;
                    }
                }
            }

            Box anvilDetectionBox = new Box(client.player.getX() - 1, client.player.getY(), client.player.getZ() - 1, client.player.getX() + 1, client.player.getY() + 100, client.player.getZ() + 1);
            for (FallingBlockEntity entity : client.player.getWorld().getEntitiesByType(EntityType.FALLING_BLOCK, anvilDetectionBox, EntityPredicates.VALID_ENTITY)){
                if (entity.getBlockState().isOf(Blocks.ANVIL) || entity.getBlockState().isOf(Blocks.CHIPPED_ANVIL) || entity.getBlockState().isOf(Blocks.DAMAGED_ANVIL)){
                    ProjectileAlert.DANGER_STATUS = true;
                }
            }

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width/2;
            y = height;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ALERT);

        if (ProjectileAlert.DANGER_STATUS){
            DrawableHelper.drawTexture(matrixStack, x - 17, 10, 0, 0, 30, 30, 30, 30);
        }
        ProjectileAlert.DANGER_STATUS = false;
    }
}
