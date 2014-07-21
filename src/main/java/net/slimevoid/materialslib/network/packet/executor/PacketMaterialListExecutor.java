package net.slimevoid.materialslib.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.slimevoid.materialslib.core.lib.ConfigurationLib;
import net.slimevoid.materialslib.network.packet.PacketMaterialList;
import net.slimevoid.library.IPacketExecutor;
import net.slimevoid.library.network.PacketUpdate;

public class PacketMaterialListExecutor implements IPacketExecutor {

	@Override
	public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
		if (packet instanceof PacketMaterialList) {
			PacketMaterialList packetList = (PacketMaterialList) packet;
			ConfigurationLib.setBaseBlockList(packetList.getMaterialList());
		}
	}

}
