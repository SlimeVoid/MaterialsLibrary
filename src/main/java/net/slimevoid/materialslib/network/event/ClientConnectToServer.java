package net.slimevoid.materialslib.network.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.slimevoid.materialslib.core.lib.PacketLib;

/**
 * Created by Allen on 7/20/2014.
 */
public class ClientConnectToServer {

    @SubscribeEvent
    public void PlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event){
        PacketLib.sendMaterialList(event.player);
    }
}
