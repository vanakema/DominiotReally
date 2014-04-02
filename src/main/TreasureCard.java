package main;

public class TreasureCard extends Card {

	int value;

	private TreasureCard(String name, String description, int cost, int value) {
		super(name, description, cost);

		this.value = value;
	}

	public static TreasureCard makeCopper() {
		return new TreasureCard(CARD_NAME_COPPER, "Treasure", 0, 1);
	}

	public static TreasureCard makeSilver() {
		return new TreasureCard(CARD_NAME_SILVER, "Treasure", 3, 2);
	}

	public static TreasureCard makeGold() {
		return new TreasureCard(CARD_NAME_GOLD, "Treasure", 6, 3);
	}

	public int getValue() {
		return this.value;
	}

}
