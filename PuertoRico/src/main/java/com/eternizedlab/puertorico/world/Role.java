package com.eternizedlab.puertorico.world;


public abstract class Role {
	private boolean used;
	private int bonusGold;
	private Player privileger;

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	/**
	 * Perform action towards a player.
	 * 
	 * @param player
	 * @return true if the player will no longer take action later.
	 */
	public abstract boolean takeAction(Player player, PlayerInput playerInput);

	public int getBonusGold() {
		return bonusGold;
	}

	public void increaseBonusGold() {
		bonusGold++;
	}

	public void clearBonusGold() {
		bonusGold = 0;
	}

	public void postGlobalAction() {
	}

	public Player getPrivileger() {
		return privileger;
	}

	public void setPrivileger(Player privileger) {
		this.privileger = privileger;
	}

}
