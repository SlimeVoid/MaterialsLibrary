package net.slimevoid.materialslib.core.lib;

import java.io.File;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationLib {
	private static Configuration configuration;

	private static String[]			baseBlockIdsNDmgs;

	public static void CommonConfig(File suggestedConfigurationFile) {
		configuration = new Configuration(suggestedConfigurationFile);
		configuration.load();

		baseBlockIdsNDmgs = configuration.get(	Configuration.CATEGORY_GENERAL,
												"BaseBlockList",
												new String[] { "44-Smooth Stone" },
												"Data to generate custom Blocks with the format BaseBlockID<_DMG-Friendly Prefix>. "
														+ "\nexample 35_14-Red Wool will create a slope, slanted corner, and oblic slope blocks"
														+ "\nwith the texture based on the blockid 35 with damage 14, Damage is optional if 0"
														+ "\ndisplay names will use the Friendly prefix given if non is specified then a name"
														+ "\nwill be assigned based on the firendly name of the base block recomended to give a"
														+ "\nFriendly Prefix").getStringList();

		configuration.save();
	}

	public static String[] getBaseBlockList(boolean getLatest) {
		if (getLatest) {
			configuration.load();

			baseBlockIdsNDmgs = configuration.get(	Configuration.CATEGORY_GENERAL,
													"BaseBlockList",
													new String[] {}).getStringList();

			configuration.save();
		}
		return baseBlockIdsNDmgs;
	}

	public static void setBaseBlockList(String[] materialList) {
		baseBlockIdsNDmgs = materialList;
		reInitMaterials();
	}

	public static void reInitMaterials() {
		initMaterials();
		try {
			FMLClientHandler.instance().getClient().refreshResources();
		} catch (Exception ex) {
		}
	}

	private static void initMaterials() {
		MaterialsLib.initMaterials();
		for (String custommats : baseBlockIdsNDmgs) {
			String blockName =custommats.split("-")[0].split("_")[0];
			Integer blockDMG = custommats.split("-")[0].split("_").length == 1 ? 0 : Integer.parseInt(custommats.split("-")[0].split("_")[1]);
			MaterialsLib.addMaterial(Block.getBlockFromName(blockName),
                    blockDMG,
                    custommats.split("-").length == 1 ? Item.getItemFromBlock(Block.getBlockFromName(blockName)).getItemStackDisplayName(new ItemStack(Item.getItemFromBlock(Block.getBlockFromName(blockName)), 1, blockDMG)) : custommats.split("-")[1]);

		}
	}

}
