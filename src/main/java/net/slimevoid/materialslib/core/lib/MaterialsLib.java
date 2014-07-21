package net.slimevoid.materialslib.core.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.slimevoid.materialslib.api.IMaterialHandler;

import net.slimevoid.library.util.helpers.ItemHelper;

public class MaterialsLib {

	public static final int							minimumlength		= 50;
	private static ArrayList<ItemStack>				materials			= new ArrayList<ItemStack>();
	private static ArrayList<String>				descs				= new ArrayList<String>();
	private static ArrayList<IMaterialHandler>		materialHandlers	= new ArrayList<IMaterialHandler>();
	private static HashMap<List<Object>, Integer>	materialIndex		= new HashMap<List<Object>, Integer>();
	public static int								brickIndex			= 35;

	public static int getSize() {
		return materials.size();
	}

	public static void initMaterials() {
		descs.clear();
		materials.clear();
		addMaterial(Blocks.stone,
					"Stone");
		addMaterial(Blocks.grass,
					"Grass");
		addMaterial(Blocks.dirt,
					"Dirt");
		addMaterial(Blocks.cobblestone,
					"Cobblestone");
		addMaterial(Blocks.planks,
					"Oak Plank");
		addMaterial(Blocks.planks,
					1,
					"Spruce Plank");
		addMaterial(Blocks.planks,
					2,
					"Birch Plank");
		addMaterial(Blocks.planks,
					3,
					"Jungle Plank");
		addMaterial(Blocks.log,
					0,
					"Oak Wood");
		addMaterial(Blocks.log,
					1,
					"Spruce Wood");
		addMaterial(Blocks.log,
					2,
					"Birch Wood");
		addMaterial(Blocks.log,
					3,
					"Jungle Wood");
		addMaterial(Blocks.glass,
					"Glass");
		addMaterial(Blocks.lapis_block,
					"Lapis Lazuli");
		addMaterial(Blocks.sandstone,
					"Sandstone");
		addMaterial(Blocks.sandstone,
					1,
					"Chisled Sandstone");
		addMaterial(Blocks.sandstone,
					2,
					"Smooth Sandstone");
		for (int i = 0; i < 16; i++) {
			addMaterial(Blocks.wool,
						i,
						(new StringBuilder()).append(ItemHelper.correctName(ItemDye.field_150923_a[15 - i])).append(" Wool").toString());
		}
		addMaterial(Blocks.gold_block,
					"Gold");
		addMaterial(Blocks.iron_block,
					"Iron");
		addMaterial(Blocks.brick_block,
					"Brick");
		addMaterial(Blocks.bookshelf,
					"Bookshelf");
		addMaterial(Blocks.mossy_cobblestone,
					"Moss Stone");
		addMaterial(Blocks.obsidian,
					"Obsidian");
		addMaterial(Blocks.diamond_block,
					"Diamond");
		addMaterial(Blocks.snow,
					"Snow");
		addMaterial(Blocks.clay,
					"Clay");
		addMaterial(Blocks.pumpkin,
					"Pumpkin");
		addMaterial(Blocks.netherrack,
					"Netherrack");
		addMaterial(Blocks.soul_sand,
					"Soul Sand");
		addMaterial(Blocks.glowstone,
					"Glowstone");
		addMaterial(Blocks.stonebrick,
					0,
					"Stone Brick");
		addMaterial(Blocks.stonebrick,
					1,
					"Mossy Stone Brick");
		addMaterial(Blocks.stonebrick,
					2,
					"Cracked Stone Brick");
		addMaterial(Blocks.stonebrick,
					3,
					"Chisled Stone Brick");
		addMaterial(Blocks.melon_block,
					"Melon");
		addMaterial(Blocks.nether_brick,
					"Nether Brick");
		addMaterial(Blocks.end_stone,
					"End Stone");
		addMaterial(Blocks.emerald_block,
					"Emerald");
		addMaterial(Blocks.quartz_block,
					"Quartz");
		addMaterial(Blocks.quartz_block,
					1,
					"Chisled Quartz");
		addMaterial(Blocks.quartz_block,
					2,
					"Pillar Quartz");

	}

	public static void addMaterialHandler(IMaterialHandler handler) {
		for (int i = 0; i < materials.size(); i++) {
			if (materials.get(i) != null) {
				handler.addMaterialReference(i);
			}
		}

		materialHandlers.add(handler);
	}

	public static Integer getMaterial(ItemStack ist) {
		return (Integer) materialIndex.get(Arrays.asList(new Object[] {
				ist.getUnlocalizedName(),
				Integer.valueOf(ist.getItemDamage()) }));
	}

	public static void addMaterial(Block bl, String desc) {
		addMaterial(bl,
					0,
					desc);
	}

	public static void addMaterial(Block bl, int md, String desc) {
		ItemStack ist = new ItemStack(bl, 1, md);
		addMaterial(ist,
					desc);
	}

	private static void addMaterial(ItemStack ist, String desc) {
		materials.add(ist);
		if (desc == null) {
			desc = ist.getDisplayName();
		}
		descs.add(desc);
		materialIndex.put(	Arrays.asList(new Object[] {
									ist.getUnlocalizedName(),
									Integer.valueOf(ist.getItemDamage()) }),
							Integer.valueOf(materials.size()));
		IMaterialHandler imh;
		for (Iterator i$ = materialHandlers.iterator(); i$.hasNext(); imh.addMaterialReference(materials.size())) {
			imh = (IMaterialHandler) i$.next();
		}
	}

	private static int damageToMaterialData(int dmg) {
		// 524288, 262144, 131072, 65536
		// 32768, 16384, 8192, 4096
		// 2048, 1024, 512, 256
		// 128, 64, 32, 16
		// 8, 4, 2, 1
		int hd = dmg >> 12; // (Skips the first 8 bits)
		// System.out.println("Damage to hd: " + hd);
		int cn = dmg & 0xfff; // 0xfff = 4095 (Retrieves the first 12 bits)
		// System.out.println("Damage to cn: " + cn);
		switch (hd) {
		case 0: // '\0'
			cn |= 0x10000; // 0001 0000 0000 xxxx xxxx
			break;

		case 16: // '\020'
			cn |= 0x20100; // 0010 0000 0001 xxxx xxxx
			break;

		case 17: // '\021'
			cn |= 0x40200; // 0100 0000 0010 xxxx xxxx
			break;

		case 24: // '\030'
			cn |= 0x110300;
			break;

		case 25: // '\031'
			cn |= 0x120400;
			break;

		case 26: // '\032'
			cn |= 0x140500;
			break;

		case 27: // '\033'
			cn |= 0x30600;
			break;

		case 28: // '\034'
			cn |= 0x50700;
			break;

		case 29: // '\035'
			cn |= 0x60800;
			break;

		case 30: // '\036'
			cn |= 0x70900;
			break;

		case 31: // '\037'
			cn |= 0x130a00;
			break;

		case 32: // ' '
			cn |= 0x150b00;
			break;

		case 33: // '!'
			cn |= 0x160c00;
			break;

		case 34: // '"'
			cn |= 0x170d00;
			break;

		case 18: // '\022'
			cn |= 0x2010000;
			break;

		case 19: // '\023'
			cn |= 0x2020100;
			break;

		case 20: // '\024'
			cn |= 0x2040200;
			break;

		case 35: // '#'
			cn |= 0x2030300;
			break;

		case 36: // '$'
			cn |= 0x2050400;
			break;

		case 37: // '%'
			cn |= 0x2060500;
			break;

		case 38: // '&'
			cn |= 0x2070600;
			break;

		case 21: // '\025'
			cn |= 0x1010000;
			break;

		case 22: // '\026'
			cn |= 0x1020100;
			break;

		case 23: // '\027'
			cn |= 0x1040200;
			break;

		case 39: // '\''
			cn |= 0x1030300;
			break;

		case 40: // '('
			cn |= 0x1050400;
			break;

		case 41: // ')'
			cn |= 0x1060500;
			break;

		case 42: // '*'
			cn |= 0x1070600;
			break;

		case 43: // '+'
			cn |= 0x3020000;
			break;

		case 44: // ','
			cn |= 0x3040100;
			break;

		case 45: // '-'
			cn |= 0x3060200;
			break;
		}
		return cn;
	}

	public static IIcon getIconForSide(int n, int side) {
		n = n % materials.size();
		ItemStack ist = getItemStack(n);
		if (ist == null) {
			return Blocks.stone.getIcon(	side,
										0);
		}
		return Block.getBlockFromName(ist.getUnlocalizedName()).getIcon(side,
													ist.getItemDamage());

	}

	public static int damageToMaterialValue(int dmg) {
		return damageToMaterialData(dmg) & 0xffff; // = 65535
	}

	public static ItemStack getItemStack(int n) {
		n = n % materials.size();
		return materials.get(n);
	}

	public static Block getBlock(int n) {
		n = n % materials.size();
		ItemStack ist = materials.get(n);
		return Block.getBlockFromName(ist.getUnlocalizedName());
	}

	public static int getBlockDmg(int n) {
		n = n % materials.size();
		ItemStack ist = materials.get(n);
		return ist.getItemDamage();
	}

	public static String getName(int n) {
		n = n % materials.size();
		ItemStack bl = getItemStack(n);
		String name = getBlock(n).getUnlocalizedName();
		if (name.endsWith(".name")) {
			name = name.substring(	0,
									name.length() - 6);
		}
		if (getBlockDmg(n) > 0) {
			// add damage to name for better localization
			name += "." + getBlockDmg(n);
		}
		return name;
	}

	public static String getDesc(int n) {
		n = n % materials.size();
		return descs.get(n);
	}
}
