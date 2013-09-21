package slimevoid.materialslib.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import slimevoid.materialslib.lib.ConfigurationLib;
import slimevoid.materialslib.network.packet.PacketMaterialList;
import slimevoidlib.IPacketExecutor;
import slimevoidlib.network.PacketUpdate;

public class PacketMaterialListExecutor implements IPacketExecutor {

	@Override
	public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
		if (packet instanceof PacketMaterialList) {
			PacketMaterialList packetList = (PacketMaterialList) packet;
			ConfigurationLib.setBaseBlockList(packetList.getMaterialList());
		}
	}

}
