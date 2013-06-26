package com.eternizedlab.puertorico.world.role;

import java.util.List;

import com.eternizedlab.puertorico.world.ColonistShip;
import com.eternizedlab.puertorico.world.Player;
import com.eternizedlab.puertorico.world.PlayerInput;
import com.eternizedlab.puertorico.world.Role;

public class Mayor extends Role {

	private ColonistShip colonistShip;
	private List<Player> players;
	
	public Mayor(ColonistShip colonistShip, List<Player> players) {
		this.colonistShip = colonistShip;
		this.players = players;
	}
	
	public interface MayorActionable {
		void onAdjustColonists();
	}

	@Override
	public boolean takeAction(Player player, PlayerInput playerInput) {
		if (player == getPrivileger()) {
			player.addColonist(colonistShip.popColonistFromSupply());
			setPrivileger(null);
		}
		int playerNum = players.size();
		player.addColonist(colonistShip.getColonistInShip() / playerNum);
		if (colonistShip.getColonistInShip() % playerNum >= player.getSeq()) {
			player.addColonist(1);
		}
		playerInput.onAdjustColonists();
		return true;
	}

	@Override
	public void postGlobalAction() {
		int emptyCircleNum = 0;
		for (int i = players.size() -1; i>= 0; i--) {
			emptyCircleNum += players.get(i).getTotalBuildingEmptyCircleNum();
		}
		colonistShip.clearAndSupply(emptyCircleNum, players.size());
	};

}
