package com.mrbushy;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectileAlert implements ModInitializer {
	public static final String MOD_ID = "projectile-alert";
    public static final Logger LOGGER = LoggerFactory.getLogger("projectile-alert");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}