package slimevoid.materialslib.network.handler;

import slimevoid.materialslib.network.packet.PacketMaterialList;
import com.slimevoid.library.network.PacketUpdate;
import com.slimevoid.library.network.handlers.SubPacketHandler;

public class PacketMaterialHandler extends SubPacketHandler {

	@Override
	protected PacketUpdate createNewPacket() {
		return new PacketMaterialList();
	}

}
