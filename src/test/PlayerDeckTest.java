package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import junit.framework.Assert;

import main.Card;
import main.ChapelCard;
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
    
    deck.drawNumAndDiscardOldHand(0);
    deck.drawNum(8);
    assertEquals(8,hand.size());
    
  }

  @Test
  public void testAddCard() {
    PlayerDeck deck = new PlayerDeck();
    deck.addCard(Card.makeCard(Card.CARD_NAME_GOLD));
    Assert.assertTrue(deck.getDiscard().contains(Card.makeCard(Card.CARD_NAME_GOLD)));
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
  
  @Test
  public void testDiscardCardInHandAtIndex(){
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10,deck.getSize());
    
    List<Card> hand = deck.getHand();
    assertEquals(5,hand.size());
    
    deck.drawNum(deck.getSize());
    assertEquals(10,hand.size());
    
    ChapelCard chapel = new ChapelCard();
    deck.addCard(chapel);
    
    deck.drawNum(1);
    assertEquals(11,hand.size());
    
    deck.discardCardInHandAtIndex(hand.size()-1);
    assertEquals(10,hand.size());
    assertEquals(false, hand.contains(chapel));
  }
  
  @Test
  public void testDiscardDrawPile(){
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10,deck.getSize());
    
    deck.discardDrawPile();
    
    assertEquals(0,deck.getSize());
    assertEquals(10,deck.getDiscardDeck().size());
  }
  
  @Test
  public void testInsertCardIntoHand(){
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10,deck.getSize());
    
    ChapelCard chapel = new ChapelCard();
    deck.insertCardIntoHand(chapel);
    assertEquals(1,deck.getHand().size());
    
    
  }

}
