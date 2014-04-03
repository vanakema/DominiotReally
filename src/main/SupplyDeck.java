package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class tracks a collection of cards representing the unowned game objects. Cards are `bought'
 * from this deck by players and added to their deck, at which time they are removed from the supply
 * deck.
 */
public class SupplyDeck {
  public class CardTuple {
    private Card card;
    private int supply;

    CardTuple(Card card, int supply) {
      this.card = card;
      this.supply = supply;
    }

    public String userDescription() {
      return String.format("%s (%s)", this.card.getName(), this.supply);
    }

    public Card getCard() {
      return this.card;
    }

    public int getSupply() {
      return this.supply;
    }

    private void decrementSupply() {
      if (this.supply > 0)
        this.supply--;
    }
  }

  private List<CardTuple> actionCards = new ArrayList<CardTuple>();
  private List<CardTuple> resourceCards = new ArrayList<CardTuple>();

  private static final int DEFAULT_ACTION_CARD_COUNT = 10;
  private static final String[] DEFAULT_RESOURCE_CARD_NAMES = new String[] {Card.CARD_NAME_COPPER,
      Card.CARD_NAME_SILVER, Card.CARD_NAME_GOLD, Card.CARD_NAME_ESTATE, Card.CARD_NAME_DUCHY,
      Card.CARD_NAME_PROVINCE, Card.CARD_NAME_CURSE};
  private static final Integer[] DEFAULT_RESOURCE_CARD_COUNT = new Integer[] {60, 40, 30, 24, 12,
      12, 30};

  /**
   * Create a new SupplyDeck with the specified action cards. Victory and Treasure cards will be
   * automatically populated according to game rules.
   * 
   * `actionCards' should be a list of exactly 10 cards. Any other size will result in a
   * IllegalArgumentException.
   */
  public SupplyDeck(List<Card> actionCards) {
    for (Card card : actionCards)
      this.actionCards.add(new CardTuple(card, DEFAULT_ACTION_CARD_COUNT));

    for (int idx = 0; idx < DEFAULT_RESOURCE_CARD_NAMES.length; ++idx) {
      String cardName = DEFAULT_RESOURCE_CARD_NAMES[idx];
      Integer cardCount = DEFAULT_RESOURCE_CARD_COUNT[idx];
      this.resourceCards.add(new CardTuple(Card.makeCard(cardName), cardCount));
    }
  }

  /**
   * Returns a mapping of available cards, and the number remaining for purchase of each, (may be
   * zero). This map is immutable.
   */
  public List<CardTuple> getActionCardRoster() {
    return Collections.unmodifiableList(this.actionCards);
  }

  public Card buyActionCardAtIndex(int index) {
    CardTuple tuple = this.actionCards.get(index);
    tuple.decrementSupply();
    return tuple.getCard();
  }

  public List<CardTuple> getResourceCardRoster() {
    return Collections.unmodifiableList(this.resourceCards);
  }

  public Card buyResourceCardAtIndex(int index) {
    CardTuple tuple = this.resourceCards.get(index);
    tuple.decrementSupply();
    return tuple.getCard();
  }
}
