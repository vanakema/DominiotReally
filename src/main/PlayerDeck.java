package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerDeck {

  List<Card> deck = new ArrayList<Card>();
  List<Card> discardDeck = new ArrayList<Card>();
  List<Card> hand = new ArrayList<Card>();

  public static final int STANDARD_HAND_SIZE = 5;

  public PlayerDeck() {
    for (int i = 0; i < 7; ++i)
      deck.add(Card.makeCard(Card.CARD_NAME_COPPER));
    for (int i = 0; i < 3; ++i)
      deck.add(Card.makeCard(Card.CARD_NAME_ESTATE));

    Collections.shuffle(deck);
  }

  /**
   * Iterates through all cards the deck contains and counts up all the victory points.
   * 
   * @return how many victory points are contained by this deck.
   */
  public int countVictoryPoints() {
    return 0;
  }

  public int getSize() {
    return this.deck.size();
  }

  public List<Card> getHand() {
    if (this.hand.size() == 0)

      this.drawNum(PlayerDeck.STANDARD_HAND_SIZE);

    return Collections.unmodifiableList(this.hand);
  }

  // Can be revised later to add in random order
  public void addCard(Card card) {
    this.discardDeck.add(card);
  }

  public List<Card> drawNum(int numToDraw) throws IndexOutOfBoundsException {
    int handSize = hand.size();// to avoid prevent the loop from
    // running to original hand.size()
    if (!hand.isEmpty()) { // to avoid dumping hand on first turn
      for (int i = 0; i < handSize; i++) {
        this.discardDeck.add(hand.remove(0));
      }
    }

    // makes sure there are enough cards to draw
    if (this.deck.size() - numToDraw >= 0) {
      for (int i = 0; i < numToDraw; i++) {
        this.hand.add(this.deck.remove(0));
      }

      return hand;
    } else {
      int remainingToDraw = numToDraw - this.deck.size();
      this.hand.addAll(this.deck);
      this.deck.clear();
      shuffleDeck();
      if (this.deck.size() > numToDraw) {
        for (int i = 0; i < remainingToDraw; i++) {
          this.hand.add(this.deck.remove(0));
        }
      } else {
        this.hand.addAll(this.deck);
        this.deck.clear();
      }
      // if not enough cards add more
      return this.hand;
    }
  }

  private void shuffleDeck() {
    Collections.shuffle(this.discardDeck);
    this.deck.addAll(this.discardDeck);
    this.discardDeck.clear();
  }

  public List<Card> getDiscard() {
    return this.discardDeck;
  }

}
