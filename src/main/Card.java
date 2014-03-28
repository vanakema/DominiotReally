package main;

import java.util.HashMap;
import java.util.Map;

public abstract class Card {
	String name;
	String description;
	int cost;
	
	public abstract String getName();
	public abstract String getDescription();
	public abstract int getCost();
	
	public static final String CARD_NAME_FESTIVAL = "Festival";
	public static final String CARD_NAME_LABORATORY = "Laboratory";
	public static final String CARD_NAME_MARKET = "Market";
	public static final String CARD_NAME_SMITHY = "Smithy";
	public static final String CARD_NAME_VILLAGE = "Village";
	public static final String CARD_NAME_WOODCUTTER = "Woodcutter";
	
	static private final Map<String, Card> cards = new HashMap<String, Card>();
	static {
		cards.put(CARD_NAME_FESTIVAL, BoringActionCard.makeFestival());
		cards.put(CARD_NAME_LABORATORY, BoringActionCard.makeLaboratory());
		cards.put(CARD_NAME_MARKET, BoringActionCard.makeMarket());
		cards.put(CARD_NAME_SMITHY, BoringActionCard.makeSmithy());
		cards.put(CARD_NAME_VILLAGE, BoringActionCard.makeVillage());
		cards.put(CARD_NAME_WOODCUTTER, BoringActionCard.makeWoodcutter());
	}
	
	public static final Card makeCard(String name) {
		if (cards.containsKey(name))
			return cards.get(name);
		
		return null;
	}
}
