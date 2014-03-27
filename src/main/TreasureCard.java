package main;

public class TreasureCard extends Card {

	int value;
	
	private TreasureCard(String name, String description, int cost, int value) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.value = value;
	}
	
	public static TreasureCard makeCopper() {
		return new TreasureCard("Copper", "Treasure", 0, 1);
	}
	
	public static TreasureCard makeSilver() {
		return new TreasureCard("Silver", "Treasure", 3, 2);
	}
	
	public static TreasureCard makeGold() {
		return new TreasureCard("Gold", "Treasure", 6, 3);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public int getCost() {
		return this.cost;
	}
	
	public int getValue(){
		return this.value;
	}

}
