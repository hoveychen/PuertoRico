package com.eternizedlab.puertorico.world;

public class Tile {
	private boolean hasColonist;
	public enum TileType {
		COFFEE,
		TOBACCO,
		CORN,
		SUGAR,
		INDIGO,
		QUARRY,
		FOREST,
	}
	
	private TileType tileType;
	
	public Tile(TileType tileType) {
		setTileType(tileType);
	}

	public void unassignColonist() {
		hasColonist = false;
	}
	
	public void assignColonist() {
		hasColonist = true;
	}
	
	public boolean isOnwork() {
		return hasColonist;
	}

	public TileType getTileType() {
		return tileType;
	}

	private void setTileType(TileType tileType) {
		this.tileType = tileType;
	}
	
	
}
