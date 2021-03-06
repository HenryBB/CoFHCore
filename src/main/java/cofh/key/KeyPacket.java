package cofh.key;

import cofh.CoFHCore;
import cofh.network.CoFHPacket;
import cofh.network.PacketHandler;

import net.minecraft.entity.player.EntityPlayer;

public class KeyPacket extends CoFHPacket {

	public static void initialize() {

		PacketHandler.instance.registerPacket(KeyPacket.class);
	}

	@Override
	public void handlePacket(EntityPlayer player, boolean isServer) {

		String bindUUID = getString();
		if (CoFHKey.serverBinds.containsKey(bindUUID)) {
			CoFHKey.serverBinds.get(bindUUID).keyPressServer(player);
		} else {
			CoFHCore.log.error("Invalid Key Packet! Unregistered Server Key! UUID: " + bindUUID);
		}
	}

	public void sendKeyPacket(String uuid) {

		addString(uuid);
		PacketHandler.sendToServer(this);
	}
}
