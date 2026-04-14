package com.stalemated.soulmaster;

import com.stalemated.soulmaster.config.Config;
import com.stalemated.soulmaster.effects.Effects;
import net.fabricmc.api.ModInitializer;
import net.tinyconfig.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Soulmaster implements ModInitializer {
	public static final String MOD_ID = "soulmaster";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ConfigManager<Config> config = new ConfigManager<>
            ("soulmaster", new Config())
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		config.refresh();

		Effects.register();
		config.save();
		LOGGER.info("Soulmaster initialized!");
	}
}