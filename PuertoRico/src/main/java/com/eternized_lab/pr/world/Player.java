package com.eternizedlab.puertorico.world;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.eternizedlab.puertorico.world.Tile.TileType;

import android.util.Log;

public class Player {
	private final static String TAG = World.class.getSimpleName();
	protected int gold;
	protected int vp;
	protected List<Building> buildings;
	protected List<Tile> tiles;
	protected Map<Good, Integer> goods;
	private boolean actionCompleted;
	private int colonist;
	private int seq;
	private int totalBuildingSize;
	private int totalBuildingEmptyCircleNum;
	private PlayerInput playerInput;

	public Player(int gold, TileType initTile) {
		this.gold = gold;
		tiles = new LinkedList<Tile>();
		tiles.add(new Tile(initTile));
		buildings = new LinkedList<Building>();
		goods = new HashMap<Good, Integer>();
	}
	
	/**
	 * Take action of a particular role. Any interactive action will be limited
	 * in this method.
	 * 
	 * @param role
	 * @return true if the player have completed the role action, i.e. no more
	 *         action to take later.
	 */
	public boolean takeTurn(Role role) {
		assert(getPlayerInput() != null);
		return role.takeAction(this, getPlayerInput());
	}

	public Role chooseRole() {
		assert (getPlayerInput() != null);
		return getPlayerInput().chooseRole();
	}

	public int getTotalBuildingSize() {
		return totalBuildingSize;
	}

	public int getTotalBuildingEmptyCircleNum() {
		return totalBuildingEmptyCircleNum;
	}

	public boolean isBuildingFull() {
		return getTotalBuildingSize() >= 12;
	}

	public boolean isTileFull() {
		return tiles.size() >= 12;
	}

	public boolean isActionCompleted() {
		return actionCompleted;
	}

	public void setActionCompleted(boolean actionCompleted) {
		this.actionCompleted = actionCompleted;
	}

	public void takeGold(int amount) {
		gold += amount;
	}

	public void giveGold(int amount) {
		assert (gold >= amount);
		gold -= amount;
	}

	public int getGold() {
		return gold;
	}

	public int getColonist() {
		return colonist;
	}

	public void addColonist(int amount) {
		colonist += amount;
	}

	public Building getBuildingOrNull(Class<Building> cls) {
		for (Building building : buildings) {
			if (building.getClass() == cls) {
				return building;
			}
		}
		return null;
	}

	public int getOnworkTile(Class<Tile> cls) {
		int counter = 0;
		for (Tile tile : tiles) {
			if (tile.getClass() == cls && tile.isOnwork()) {
				counter++;
			}
		}
		return counter;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void build(Building building) {
		for (Building b : buildings) {
			if (b.getType() == building.getType()) {
				Log.e(TAG, "Error while building duplicated building."
						+ building.getType().toString());
			}
		}
		totalBuildingSize += building.getType().getSize();
	}

	public PlayerInput getPlayerInput() {
		return playerInput;
	}

	public void setPlayerInput(PlayerInput playerInput) {
		this.playerInput = playerInput;
	}
}
