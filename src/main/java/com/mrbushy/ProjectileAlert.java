package com.mrbushy;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectileAlert implements ModInitializer {
	// Just a comment, no worries <3
	public static final String MOD_ID = "projectile-alert";
    public static final Logger LOGGER = LoggerFactory.getLogger("projectile-alert");

	public static Boolean DANGER_STATUS = false;

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}
