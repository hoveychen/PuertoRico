package com.eternizedlab.puertorico.world.role;

import com.eternizedlab.puertorico.world.Player;
import com.eternizedlab.puertorico.world.PlayerInput;
import com.eternizedlab.puertorico.world.Role;

public class Prospector extends Role {

	@Override
	public boolean takeAction(Player player, PlayerInput playerInput) {
		if (player == getPrivileger()) {
			player.takeGold(1);
			clearBonusGold();
			setPrivileger(null);
		}
		return true;
	}

}
