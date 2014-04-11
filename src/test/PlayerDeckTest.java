package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import junit.framework.Assert;

import main.Card;
import main.PlayerDeck;

import org.junit.Test;

public class PlayerDeckTest {

  @Test
  public void testSize() {
    PlayerDeck deck1 = new PlayerDeck();

    assertEquals(10, deck1.getSize());

    List<Card> hand = deck1.drawNumAndDiscardOldHand(5);

    assertEquals(5, deck1.getSize());

    hand = deck1.drawNumAndDiscardOldHand(5);

    assertEquals(0, deck1.getSize());

    assertEquals(5, hand.size());

    hand = deck1.drawNumAndDiscardOldHand(7);

    assertEquals(7, hand.size());

    hand = deck1.drawNumAndDiscardOldHand(2);
    assertEquals(2, hand.size());
  }

  @Test
  public void testNotEnoughCards() {
    PlayerDeck deck1 = new PlayerDeck();
    List<Card> hand = deck1.drawNumAndDiscardOldHand(3);
    assertEquals(3, hand.size());
    hand = deck1.drawNumAndDiscardOldHand(11);
    assertEquals(10, hand.size());
  }

  @Test
  public void testShuffle() {
    PlayerDeck deck = new PlayerDeck();
    List<Card> hand = deck.drawNumAndDiscardOldHand(10);
    hand = deck.drawNumAndDiscardOldHand(5);

    assertEquals(5, hand.size());
  }

  // TODO: Modify test to check for an unequal hand after trash logic is added
  @Test
  public void testGetHand() {
    PlayerDeck deck = new PlayerDeck();
    List<Card> hand = deck.getHand();
    assertEquals(5, hand.size());
  }

  @Test
  public void testAddCard() {
    PlayerDeck deck = new PlayerDeck();
    deck.addCard(Card.makeCard(Card.CARD_NAME_GOLD));
    Assert.assertTrue(deck.getDiscard().contains(Card.makeCard(Card.CARD_NAME_GOLD)));
  }
  
  @Test
  public void testJustDrawNum() {
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10, deck.getDrawDeck().size());
    List<Card> hand = deck.drawNum(10);
    assertEquals(10, hand.size());
    for(int i = 0; i < 5; i++) {
      deck.discardCardInHandAtIndex(0);
    }
    assertEquals(5, hand.size());
    deck.drawNum(5);
    assertEquals(10, hand.size());
    for(int i = 0; i < 5; i++) {
      deck.discardCardInHandAtIndex(0);
    }
    assertEquals(5, hand.size());
    deck.drawNum(3);
    assertEquals(8, hand.size());
    
  }
  
  @Test
  public void testDiscardDrawDeck() {
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10, deck.getDrawDeck().size());
    deck.discardDrawPile();
    assertEquals(0, deck.getDrawDeck().size());
  }
  
  @Test
  public void testInsertCardIntoHand() {
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10, deck.getDrawDeck().size());
    deck.drawNumAndDiscardOldHand(PlayerDeck.STANDARD_HAND_SIZE);
    assertEquals(PlayerDeck.STANDARD_HAND_SIZE, deck.getHand().size());
    deck.insertCardIntoHand(Card.makeCard(Card.CARD_NAME_CHANCELLOR));
    assertEquals(deck.getHand().get(deck.getHand().size()-1), Card.makeCard(Card.CARD_NAME_CHANCELLOR));
    assertEquals(PlayerDeck.STANDARD_HAND_SIZE + 1, deck.getHand().size());
  }

  @Test
  public void testCountVictoryPoints() {
    PlayerDeck deck = new PlayerDeck();
    assertEquals(3, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_PROVINCE));
    assertEquals(9, deck.countVictoryPoints());

    List<Card> hand = deck.drawNumAndDiscardOldHand(9);
    assertEquals(9, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_DUCHY));
    assertEquals(12, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_COPPER));
    assertEquals(12, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_CURSE));
    assertEquals(11, deck.countVictoryPoints());
  }

}
