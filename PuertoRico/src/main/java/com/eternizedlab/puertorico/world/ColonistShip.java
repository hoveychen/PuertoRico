package com.eternizedlab.puertorico.world;

public class ColonistShip {
	private int colonistInShip;
	private int colonistSupply;
	private boolean notEnoughSupply;

	public ColonistShip(int colonistSupply) {
		this.colonistSupply = colonistSupply;
		notEnoughSupply = false;
	}

	public boolean isNotEnoughSupply() {
		return notEnoughSupply;
	}

	public int getColonistInShip() {
		return colonistInShip;
	}

	public int getColonistSupply() {
		return colonistSupply;
	}

	public int popColonistFromSupply() {
		// TODO(chenyuheng): Go through the rule and find out whether or not we
		// should get colonist from ship if supply is empty.
		if (colonistSupply > 0) {
			colonistSupply--;
			return 1;
		} else {
			return 0;
		}
	}

	public void clearAndSupply(int emptyCircleNum, int playerNum) {
		int supplyNum = Math.max(emptyCircleNum, playerNum);
		if (colonistSupply >= supplyNum) {
			colonistInShip = supplyNum;
			colonistSupply -= supplyNum;
		} else {
			colonistInShip = colonistSupply;
			notEnoughSupply = true;
		}
	}
}
