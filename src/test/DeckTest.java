package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import main.Card;
import main.PlayerDeck;

import org.junit.Test;

public class DeckTest {

  @Test
  public void sizeTest() {
    PlayerDeck deck1 = makeDeck();

    assertEquals(10, deck1.getSize());

    List<Card> hand = deck1.drawNum(5);

    assertEquals(5, deck1.getSize());

    hand = deck1.drawNum(5);

    assertEquals(0, deck1.getSize());

    assertEquals(5, hand.size());

    hand = deck1.drawNum(7);

    assertEquals(7, hand.size());

    hand = deck1.drawNum(2);
    assertEquals(2, hand.size());
  }

  @Test
  public void notEnoughCardsTest() {
    PlayerDeck deck1 = makeDeck();
    List<Card> hand = deck1.drawNum(3);
    assertEquals(3, hand.size());
    hand = deck1.drawNum(11);
    assertEquals(10, hand.size());
  }

  @Test
  public void ShuffleTest() {
    PlayerDeck deck = makeDeck();
    List<Card> hand = deck.drawNum(10);
    hand = deck.drawNum(5);

    assertEquals(5, hand.size());
  }

  public PlayerDeck makeDeck() {
    PlayerDeck deck1 = new PlayerDeck();

    Card newCard1 = Card.makeCard(Card.CARD_NAME_COPPER);
    Card newCard2 = Card.makeCard(Card.CARD_NAME_COPPER);
    Card newCard3 = Card.makeCard(Card.CARD_NAME_COPPER);
    Card newCard4 = Card.makeCard(Card.CARD_NAME_SMITHY);
    Card newCard5 = Card.makeCard(Card.CARD_NAME_SMITHY);
    Card newCard6 = Card.makeCard(Card.CARD_NAME_FESTIVAL);
    Card newCard7 = Card.makeCard(Card.CARD_NAME_LABORATORY);
    Card newCard8 = Card.makeCard(Card.CARD_NAME_PROVINCE);
    Card newCard9 = Card.makeCard(Card.CARD_NAME_GOLD);
    Card newCard10 = Card.makeCard(Card.CARD_NAME_ESTATE);

    deck1.addCard(newCard1);
    deck1.addCard(newCard2);
    deck1.addCard(newCard3);
    deck1.addCard(newCard4);
    deck1.addCard(newCard5);
    deck1.addCard(newCard6);
    deck1.addCard(newCard7);
    deck1.addCard(newCard8);
    deck1.addCard(newCard9);
    deck1.addCard(newCard10);

    return deck1;
  }


}
