package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.cards.Card;
import main.cards.VictoryCard;

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
    int victoryPoints = 0;
    int gardenCardCounter = 0;
    // loops through current Deck
    for (int i = 0; i < this.deck.size(); i++) {
      if (this.deck.get(i).getName() == "Gardens") {
        gardenCardCounter++;
      }
      if (this.deck.get(i) instanceof VictoryCard) {
        VictoryCard card = (VictoryCard) this.deck.get(i);
        victoryPoints += card.getVictoryPointValue();
      }
    }
    // loops through current discardDeck
    for (int j = 0; j < this.discardDeck.size(); j++) {
      if (this.discardDeck.get(j).getName() == "Gardens") {
        gardenCardCounter++;
      }
      if (this.discardDeck.get(j) instanceof VictoryCard) {
        VictoryCard card = (VictoryCard) this.discardDeck.get(j);
        victoryPoints += card.getVictoryPointValue();
      }
    }
    // loops through current hand
    for (int k = 0; k < this.hand.size(); k++) {
      if (this.hand.get(k).getName() == "Gardens") {
        gardenCardCounter++;
      }
      if (this.hand.get(k) instanceof VictoryCard) {
        VictoryCard card = (VictoryCard) this.hand.get(k);
        victoryPoints += card.getVictoryPointValue();
      }
    }
    // gets size of every part of Deck
    int deckSize = this.deck.size() + this.hand.size() + this.discardDeck.size();
    // does the floor calculation for number of victory points player should get from gardensCard
    int victoryPointsFromGardensCard = (int) Math.floor(deckSize / 10);
    // adds victoryPoints gained from gardens card multiplied by number of gardensCard to
    // victoryPoints
    victoryPoints += (victoryPointsFromGardensCard * gardenCardCounter);

    return victoryPoints;
  }

  public int getSize() {
    return this.deck.size();
  }

  boolean once;

  public List<Card> getHand() {
    if (this.hand.size() == 0)

      this.drawNumAndDiscardOldHand(PlayerDeck.STANDARD_HAND_SIZE);

    // return Collections.unmodifiableList(this.hand);
    return this.hand;
  }

  public List<Card> getDiscardDeck() {
    return Collections.unmodifiableList(this.discardDeck);
  }

  public List<Card> getDrawDeck() {
    return Collections.unmodifiableList(this.deck);
  }

  // Can be revised later to add in random order
  public void addCard(Card card) {
    addCard(card, PlayerDeckType.DISCARD);
  }

  public List<Card> drawNumAndDiscardOldHand(int numToDraw) throws IndexOutOfBoundsException {
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

  public List<Card> drawNum(int numToDraw) {
    if (this.deck.size() - numToDraw >= 0) {
      for (int i = 0; i < numToDraw; i++) {
        this.hand.add(this.deck.remove(0));
      }
    } else {
      int remainingToDraw = numToDraw - this.deck.size();
      this.hand.addAll(this.deck);
      this.deck.clear();
      shuffleDeck();
      if (this.deck.size() > remainingToDraw) {
        for (int i = 0; i < remainingToDraw; i++) {
          this.hand.add(this.deck.remove(0));
        }
      } else {
        this.hand.addAll(this.deck);
        this.deck.clear();
      }
    }

    return this.hand;
  }

  private void shuffleDeck() {
    Collections.shuffle(this.discardDeck);
    this.deck.addAll(this.discardDeck);
    this.discardDeck.clear();
  }

  public List<Card> getDiscard() {
    return this.discardDeck;
  }

  public void trashCardInHandAtIndex(int index) {
    this.hand.remove(index);
  }
  
  public void trashCardInDeckAtIndex(int index){
    this.deck.remove(index);
  }

  public void discardCardInHandAtIndex(int index) {
    this.discardDeck.add(this.hand.remove(index));
  }

  public void discardDrawPile() {
    int drawPileSize = this.deck.size();
    for (int i = 0; i < drawPileSize; i++) {
      this.discardDeck.add(this.deck.remove(0));
    }
  }
  
  public void discardHand() {
    this.discardDeck.addAll(this.hand);
    this.hand.clear();
  }

  public void addCard(Card cardToInsert, PlayerDeckType type) {
    switch (type) {
      case HAND:
        this.hand.add(cardToInsert);
        break;
      case DISCARD:
        this.discardDeck.add(cardToInsert);
        break;
      case DRAW:
        this.deck.add(cardToInsert);
        break;
    }
  }

  public enum PlayerDeckType {
    HAND, DISCARD, DRAW
  }

}
