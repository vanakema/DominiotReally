package main;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class Card implements Cloneable {

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
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Card))
      return false;
    
    return ((Card)o).name.equals(this.name);
  }

  public abstract void performAction(GameContext context);

  public static final String CARD_NAME_FESTIVAL = "Festival";
  public static final String CARD_NAME_LABORATORY = "Laboratory";
  public static final String CARD_NAME_MARKET = "Market";
  public static final String CARD_NAME_SMITHY = "Smithy";
  public static final String CARD_NAME_VILLAGE = "Village";
  public static final String CARD_NAME_WOODCUTTER = "Woodcutter";
  public static final String CARD_NAME_MINE = "Mine";
  public static final String CARD_NAME_CHAPEL = "Chapel";
  public static final String CARD_NAME_CELLAR = "Cellar";
  public static final String CARD_NAME_MONEYLENDER = "Moneylender";
  public static final String CARD_NAME_CHANCELLOR = "Chancellor";

  public static final String CARD_NAME_ESTATE = "Estate";
  public static final String CARD_NAME_DUCHY = "Duchy";
  public static final String CARD_NAME_PROVINCE = "Province";
  public static final String CARD_NAME_CURSE = "Curse";

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
    cards.put(CARD_NAME_MINE, new MineCard());
    cards.put(CARD_NAME_CHAPEL, new ChapelCard());
    cards.put(CARD_NAME_CELLAR, new CellarCard());
    cards.put(CARD_NAME_MONEYLENDER, new MoneylenderCard());
    cards.put(CARD_NAME_CHANCELLOR, new ChancellorCard());

    cards.put(CARD_NAME_ESTATE, VictoryCard.makeEstate());
    cards.put(CARD_NAME_DUCHY, VictoryCard.makeDuchy());
    cards.put(CARD_NAME_PROVINCE, VictoryCard.makeProvince());
    cards.put(CARD_NAME_CURSE, VictoryCard.makeCurse());

    cards.put(CARD_NAME_COPPER, TreasureCard.makeCopper());
    cards.put(CARD_NAME_SILVER, TreasureCard.makeSilver());
    cards.put(CARD_NAME_GOLD, TreasureCard.makeGold());
  }

  public static final Card makeCard(String name) {
    if (cards.containsKey(name)) {
      return makeCard(cards.get(name));
    } else {
      throw new NoSuchElementException();
    }
  }
  
  public static final Card makeCard(Card card) {
    try {
      return (Card) card.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("Someone removed Clonable from Card", e);
    }
  }
}
