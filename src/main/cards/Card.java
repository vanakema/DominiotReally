package main.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import main.GameContext;

public abstract class Card implements Cloneable {

  private String name;
  private int cost;
  private String type;
  private static final ResourceBundle bundle = ResourceBundle.getBundle("CardDescriptions");

  protected Card(String name, int cost, String type) {
    this.name = name;
    this.cost = cost;
    this.type = type;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    try {
      return bundle.getString(this.name.replaceAll(" ", ""));
    } catch (MissingResourceException ex) {
      System.err.println("Could not find localized string for key \"" + this.name + "\"");
      return "<Unlocalized_Description(" + this.name + ")>";
    }
  }

  public int getCost() {
    return this.cost;
  }
  
  public String getType() {
    return this.type;
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Card))
      return false;
    
    return ((Card)o).name.equals(this.name);
  }

  public abstract void performAction(GameContext context);
  
  public static final String CARD_TYPE_ACTIONCARD = "ActionCard";
  public static final String CARD_TYPE_TREASURECARD = "TreasureCard";
  public static final String CARD_TYPE_VICTORYCARD = "VictoryCard";

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
  public static final String CARD_NAME_THRONE_ROOM = "Throne Room";
  public static final String CARD_NAME_WORKSHOP = "Workshop";
  public static final String CARD_NAME_REMODEL = "Remodel";
  public static final String CARD_NAME_FEAST = "Feast";
  public static final String CARD_NAME_GARDENS = "Gardens";
  public static final String CARD_NAME_WITCH = "Witch";
  public static final String CARD_NAME_COUNCILROOM = "Council Room";
  public static final String CARD_NAME_SPY = "Spy";
  public static final String CARD_NAME_THIEF = "Thief";
  public static final String CARD_NAME_MILITIA = "Militia";
  public static final String CARD_NAME_BUREAUCRAT = "Bureaucrat";
  public static final String CARD_NAME_ADVENTURER = "Adventurer";
  public static final String CARD_NAME_MOAT = "Moat";
  public static final String CARD_NAME_LIBRARY = "Library";

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
    cards.put(CARD_NAME_THRONE_ROOM, new ThroneRoomCard());
    cards.put(CARD_NAME_WORKSHOP, new WorkshopCard());
    cards.put(CARD_NAME_REMODEL, new RemodelCard());
    cards.put(CARD_NAME_FEAST, new FeastCard());
    cards.put(CARD_NAME_GARDENS, new GardensCard());
    cards.put(CARD_NAME_WITCH, new WitchCard());
    cards.put(CARD_NAME_SPY, new SpyCard());
    cards.put(CARD_NAME_COUNCILROOM, new CouncilRoomCard());
    cards.put(CARD_NAME_THIEF, new ThiefCard());
    cards.put(CARD_NAME_MILITIA, new MilitiaCard());
    cards.put(CARD_NAME_BUREAUCRAT, new BureaucratCard());
    cards.put(CARD_NAME_ADVENTURER, new AdventurerCard());
    cards.put(CARD_NAME_LIBRARY, new LibraryCard());
    cards.put(CARD_NAME_MOAT, new MoatCard());

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
  
  public static final List<Card> allCards(String ofType) {
    List<Card> result = new ArrayList<>();
    for (Entry<String, Card> entry : cards.entrySet())
      if (entry.getValue().getType() == ofType)
        result.add(makeCard(entry.getValue()));
    
    return result;
  }
}
