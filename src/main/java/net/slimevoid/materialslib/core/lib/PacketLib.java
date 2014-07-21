package net.slimevoid.materialslib.core.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.slimevoid.library.network.handlers.PacketPipeline;
import net.slimevoid.materialslib.network.handler.PacketMaterialHandler;
import net.slimevoid.materialslib.network.packet.PacketMaterialList;
import net.slimevoid.materialslib.network.packet.executor.PacketMaterialListExecutor;
import net.slimevoid.library.network.PacketIds;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketLib {
    public static PacketPipeline handler               = new PacketPipeline();

	public static void sendMaterialList(EntityPlayer player) {
		PacketMaterialList packet = new PacketMaterialList(ConfigurationLib.getBaseBlockList(false));
		System.out.println("Sending Mats List");
		packet.setCommand(CommandLib.SEND_MATERIALS);
        if (player instanceof EntityPlayerMP)
                handler.sendToPlayer(packet,(EntityPlayerMP)player);
	}

	public static void sendAllMaterialList() {
		PacketMaterialList packet = new PacketMaterialList(ConfigurationLib.getBaseBlockList(false));
		System.out.println("Sending updated Mats List");
		packet.setCommand(CommandLib.SEND_MATERIALS);
        handler.broadcastPacket(packet);
	}

	@SideOnly(Side.CLIENT)
	public static void registerClientPacketHandlers() {
		PacketMaterialHandler materialHandler = new PacketMaterialHandler();
		materialHandler.registerClientExecutor(	CommandLib.SEND_MATERIALS,
												new PacketMaterialListExecutor());

        handler.registerPacketHandler(	PacketIds.LOGIN,
													materialHandler);
	}

	public static void registerPacketHandlers() {

	}


}
