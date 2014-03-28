package main;

public class VictoryCard extends Card {

	int victoryPointValue;
	
	private VictoryCard(String name, String description, int cost, int value){
		super(name, description, cost);
		
		this.victoryPointValue = value;
	}
	
	public static VictoryCard makeEstate(){
		return new VictoryCard(CARD_NAME_ESTATE, "Victory", 2,1);
	}
	
	public static VictoryCard makeDuchy(){
		return new VictoryCard(CARD_NAME_DUCHY, "Victory", 5,3);
	}
	
	public static VictoryCard makeProvince(){
		return new VictoryCard(CARD_NAME_PROVINCE,"Victory",8,6);
	}
	
	public int getVictoryPointValue() {
		return this.victoryPointValue;
	}

	
}
