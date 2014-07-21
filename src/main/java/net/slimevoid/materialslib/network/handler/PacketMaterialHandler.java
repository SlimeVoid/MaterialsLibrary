package net.slimevoid.materialslib.network.handler;

import net.slimevoid.materialslib.network.packet.PacketMaterialList;
import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.library.network.handlers.SubPacketHandler;

public class PacketMaterialHandler extends SubPacketHandler {

	@Override
	protected PacketUpdate createNewPacket() {
		return new PacketMaterialList();
	}

}
