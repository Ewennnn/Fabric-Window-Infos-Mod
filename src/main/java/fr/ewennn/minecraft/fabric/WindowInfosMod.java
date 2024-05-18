package fr.ewennn.minecraft.fabric;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowInfosMod implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("Window Infos");

	@Override
	public void onInitialize() {
		LOGGER.info("Window Infos loaded !");
	}
}