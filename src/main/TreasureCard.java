package main;

public class TreasureCard extends Card {

	int value;
	
	private TreasureCard(String name, String description, int cost, int value) {
		super(name, description, cost);
		
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
	
	public int getValue(){
		return this.value;
	}

}
