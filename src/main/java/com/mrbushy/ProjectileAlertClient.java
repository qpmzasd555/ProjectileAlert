package com.mrbushy;

import com.mrbushy.client.ProjectileHudOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ProjectileAlertClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new ProjectileHudOverlay());

    }
}
