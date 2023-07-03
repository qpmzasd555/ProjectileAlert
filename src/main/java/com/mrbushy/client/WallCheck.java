package com.mrbushy.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class WallCheck {
    public static boolean hasWallInBetween(Entity player, Entity entity) {
        Vec3d playerPos = player.getPos();
        Vec3d entityPos = entity.getPos();

        double distanceX = entityPos.x - playerPos.x;
        double distanceY = entityPos.y - playerPos.y;
        double distanceZ = entityPos.z - playerPos.z;

        double maxDistance = Math.max(Math.abs(distanceX), Math.abs(distanceZ));
        double stepX = distanceX / maxDistance;
        double stepY = distanceY / maxDistance;
        double stepZ = distanceZ / maxDistance;

        double x = playerPos.x;
        double y = playerPos.y;
        double z = playerPos.z;

        for (int i = 0; i < maxDistance; i++) {
            x += stepX;
            y += stepY;
            z += stepZ;

            BlockHitResult blockHitResult = player.world.raycast(new RaycastContext(new Vec3d(x, y, z), entityPos, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));

            if (blockHitResult.getType() != HitResult.Type.MISS) {
                return true; // Найдена стена между игроком и сущностью
            }
        }

        return false; // Стена не найдена
    }
}
