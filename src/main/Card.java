package main;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class Card {

	private String name;
	private String description;
	private int cost;

	protected Card(String name, String description, int cost) {
		this.name = name;
		this.description = description;
		this.cost = cost;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public int getCost() {
		return this.cost;
	}

	public static final String CARD_NAME_FESTIVAL = "Festival";
	public static final String CARD_NAME_LABORATORY = "Laboratory";
	public static final String CARD_NAME_MARKET = "Market";
	public static final String CARD_NAME_SMITHY = "Smithy";
	public static final String CARD_NAME_VILLAGE = "Village";
	public static final String CARD_NAME_WOODCUTTER = "Woodcutter";

	public static final String CARD_NAME_ESTATE = "Estate";
	public static final String CARD_NAME_DUCHY = "Duchy";
	public static final String CARD_NAME_PROVINCE = "Province";

	public static final String CARD_NAME_COPPER = "Copper";
	public static final String CARD_NAME_SILVER = "Silver";
	public static final String CARD_NAME_GOLD = "Gold";

	static private final Map<String, Card> cards = new HashMap<String, Card>();
	static {
		cards.put(CARD_NAME_FESTIVAL, BoringActionCard.makeFestival());
		cards.put(CARD_NAME_LABORATORY, BoringActionCard.makeLaboratory());
		cards.put(CARD_NAME_MARKET, BoringActionCard.makeMarket());
		cards.put(CARD_NAME_SMITHY, BoringActionCard.makeSmithy());
		cards.put(CARD_NAME_VILLAGE, BoringActionCard.makeVillage());
		cards.put(CARD_NAME_WOODCUTTER, BoringActionCard.makeWoodcutter());

		cards.put(CARD_NAME_ESTATE, VictoryCard.makeEstate());
		cards.put(CARD_NAME_DUCHY, VictoryCard.makeDuchy());
		cards.put(CARD_NAME_PROVINCE, VictoryCard.makeProvince());

		cards.put(CARD_NAME_COPPER, TreasureCard.makeCopper());
		cards.put(CARD_NAME_SILVER, TreasureCard.makeSilver());
		cards.put(CARD_NAME_GOLD, TreasureCard.makeGold());
	}

	public static final Card makeCard(String name) {
		if (cards.containsKey(name)){
			return cards.get(name);
		}else{
			throw new NoSuchElementException();
		}
		
		
	}
}
