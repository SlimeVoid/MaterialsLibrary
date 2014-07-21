package net.slimevoid.materialslib.core;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.slimevoid.materialslib.core.lib.ConfigurationLib;
import net.slimevoid.materialslib.core.lib.CoreLib;
import net.slimevoid.materialslib.core.lib.ReInitMatsCommand;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
		modid = CoreLib.MOD_ID,
		name = CoreLib.MOD_NAME,
		version = CoreLib.MOD_VERSION,
		dependencies = CoreLib.MOD_DEPENDENCIES)
public class SlimevoidMaterials {

    @SubscribeEvent
	public static void MaterialsPreInit(FMLPreInitializationEvent event) {
		ConfigurationLib.CommonConfig(event.getSuggestedConfigurationFile());
	}

    @SubscribeEvent
	public static void MaterialsPostInit(FMLPostInitializationEvent event) {
		ConfigurationLib.reInitMaterials();
	}

    @SubscribeEvent
	public void registerCommand(FMLServerStartingEvent event) {

		event.registerServerCommand(new ReInitMatsCommand());
	}
}
