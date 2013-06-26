package com.eternizedlab.puertorico.world;

public class Building {
	public enum BuildingType {
		SMALL_MARKET(1, 1),
		HACIENDA(1, 2),
		CONSTRUCTION_HUT(1, 2),
		SMALL_WAREHOUSE(1, 3),
		HOSPICE(2, 4),
		OFFICE(2, 5),
		LARGE_MARKET(2, 5),
		LARGE_WAREHOUSE(2, 6),
		FACTORY(3, 7),
		UNIVERSITY(3, 8),
		HARBOR(3, 8),
		WHARF(3, 9),
		GUILD_HALL(4, 10, 2),
		RESIDENCE(4, 10, 2),
		FORTRESS(4, 10, 2),
		CUSTOM_HOUSE(4, 10, 2),
		CITY_HALL(4, 10, 2);

		private final int size;
		private final int price;
		private final int value;
		private final int circleNum;

		BuildingType(int value, int price, int size, int circleNum) {
			this.price = price;
			this.value = value;
			this.size = size;
			this.circleNum = circleNum;
		}

		BuildingType(int value, int price) {
			this(value, price, 1);
		}

		BuildingType(int value, int price, int size) {
			this(value, price, size, 1);
		}

		public int getSize() {
			return size;
		}

		public int getPrice() {
			return price;
		}

		public int getLevel() {
			return value;
		}
		
		public int getCircleNum() {
			return circleNum;
		}
	}

	private BuildingType type;
	private int assignedWorkerNum;

	public BuildingType getType() {
		return type;
	}

	public void setType(BuildingType type) {
		this.type = type;
	}

	public int getAssignedWorkerNum() {
		return assignedWorkerNum;
	}

	public void setAssignedWorkerNum(int assignedWorkerNum) {
		this.assignedWorkerNum = assignedWorkerNum;
	}

}
