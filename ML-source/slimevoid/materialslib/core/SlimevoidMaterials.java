package slimevoid.materialslib.core;

import slimevoid.materialslib.lib.ConfigurationLib;
import slimevoid.materialslib.lib.CoreLib;
import slimevoid.materialslib.lib.ReInitMatsCommand;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(
		modid = CoreLib.MOD_ID,
		name = CoreLib.MOD_NAME,
		version = CoreLib.MOD_VERSION,
		dependencies = CoreLib.MOD_DEPENDENCIES)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SlimevoidMaterials {

	@EventHandler
	public static void MaterialsPreInit(FMLPreInitializationEvent event) {
		ConfigurationLib.CommonConfig(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public static void MaterialsPostInit(FMLPostInitializationEvent event) {
		ConfigurationLib.reInitMaterials();
	}

	@EventHandler
	public void registerCommand(FMLServerStartingEvent event) {
		// 1.6.2 Only command
		event.registerServerCommand(new ReInitMatsCommand());
	}
}
