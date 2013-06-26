package com.eternizedlab.puertorico.world;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.eternizedlab.puertorico.world.Tile.TileType;
import com.eternizedlab.puertorico.world.role.Mayor;
import com.eternizedlab.puertorico.world.role.Prospector;

import android.util.Log;

public class World {
	private final static String TAG = World.class.getSimpleName();
	private final static int ROUND_LIMIT = 100;
	private List<Role> roles;
	private int vp;
	private ColonistShip colonistShip;
	private TradingHouse tradingHouse;
	private List<CargoShip> cargoShips;
	private List<Tile> availTiles;
	private TileLord tileLord;
	private Map<Building, Integer> building;
	private Map<Good, Integer> goods;
	private List<Player> players;
	private int round;

	public World() {
	}

	private void initPlayer(int playerNum) {
		// TODO: Adjust by rule.
		assert (playerNum >= 3 && playerNum <= 5);
		players = new LinkedList<Player>();
		switch (playerNum) {
		case 3:
			players.add(new Player(2, TileType.INDIGO));
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			Log.e(TAG, "Invalid player number when initializing world.");
		}
	}

	private void initResource(int playerNum) {
		tileLord = new TileLord();
	}

	private void initRole(int playerNum) {
		roles = new LinkedList<Role>();
		roles.add(new Mayor(colonistShip, players));
		roles.add(new Prospector());
		if (playerNum >= 5) {
			// One additional prospector for 5 player game.
			roles.add(new Prospector());
		}
	}

	public void initWorld(int playerNum) {
		initPlayer(playerNum);
		initResource(playerNum);
		initRole(playerNum);
	}

	public int getPlayerNum() {
		return players.size();
	}

	public void rockTheRoad(int playerNum) {

		initWorld(playerNum);
		for (round = 0; round < ROUND_LIMIT; round++) {
			for (int seq = 0; seq < getPlayerNum(); seq++) {
				// the current seat of player to choose role.
				int currentSeat = (seq + round) % players.size();
				Player roleChooser = players.get(currentSeat);
				Role role = roleChooser.chooseRole();
				role.setPrivileger(roleChooser);
				roleChooser.takeGold(role.getBonusGold());
				role.clearBonusGold();

				for (int i = 0; i < getPlayerNum(); ++i) {
					Player player = players.get(i);
					player.setSeq((getPlayerNum() - currentSeat + i)
							% getPlayerNum());
					player.setActionCompleted(false);
				}
				int actionPendingNum = getPlayerNum();
				int seatToAction = currentSeat; // Start from the role chooser
				// Loop until all players have taken action.
				while (actionPendingNum > 0) {
					Player player = players.get(seatToAction);
					if (!player.isActionCompleted() && player.takeTurn(role)) {
						actionPendingNum--;
						player.setActionCompleted(true);
					}
					seatToAction = (seatToAction + 1) % players.size();
				}
				role.setUsed(true);
			}
			for (Role role : roles) {
				if (!role.isUsed()) {
					role.increaseBonusGold();
					role.setUsed(false);
				}
			}
			if (isGameEnded()) {
				break;
			}
		}
	}

	private boolean isGameEnded() {
		return false;
	}

	public int getCurrentRound() {
		return round;
	}

	public Player getGovernorPlayer() {
		return players.get(round % players.size());
	}

	public Player getPlayer(int seat) {
		assert (seat >= 0 && seat < players.size());
		return players.get(seat);
	}

	public boolean isGameEnding() {
		if (vp <= 0)
			return true;
		if (getColonistShip().isNotEnoughSupply())
			return true;
		for (Player player : players) {
			if (player.isTileFull() || player.isBuildingFull()) {
				return true;
			}
		}
		return false;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public ColonistShip getColonistShip() {
		return colonistShip;
	}
}
