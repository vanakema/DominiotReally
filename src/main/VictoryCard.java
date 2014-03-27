package main;

public class VictoryCard extends Card {

	int victoryPointValue;
	
	private VictoryCard(String name, String description, int cost, int value){
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.victoryPointValue = value;
	}
	
	public static VictoryCard makeEstate(){
		return new VictoryCard("Estate", "Victory", 2,1);
	}
	
	public static VictoryCard makeDuchy(){
		return new VictoryCard("Duchy", "Victory", 5,3);
	}
	
	public static VictoryCard makeProvince(){
		return new VictoryCard("Province","Victory",8,6);
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
	
	public int getVictoryPointValue() {
		return this.victoryPointValue;
	}

	
}
