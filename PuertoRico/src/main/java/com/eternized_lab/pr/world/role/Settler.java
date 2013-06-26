package com.eternizedlab.puertorico.world.role;

import java.util.List;
import java.util.Map;

import com.eternizedlab.puertorico.world.ColonistShip;
import com.eternizedlab.puertorico.world.Player;
import com.eternizedlab.puertorico.world.PlayerInput;
import com.eternizedlab.puertorico.world.Role;
import com.eternizedlab.puertorico.world.Tile;
import com.eternizedlab.puertorico.world.TileLord;

public class Settler extends Role {

	private List<Player> players;
	private TileLord tileLord;
	
	public Settler(Map<Tile, Integer> worldTiles, TileLord tileLord) {
		this.tileLord = tileLord;
		this.players = players;
	}
	
	public interface MayorActionable {
		void onAdjustColonists();
	}

	@Override
	public boolean takeAction(Player player, PlayerInput playerInput) {
		if (player == getPrivileger()) {
			
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
