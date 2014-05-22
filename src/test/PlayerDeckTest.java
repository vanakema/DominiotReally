package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import main.PlayerDeck;
import main.PlayerDeck.PlayerDeckType;
import main.cards.Card;
import main.cards.ChapelCard;

import org.junit.Assert;
import org.junit.Test;

public class PlayerDeckTest {

  @Test
  public void testSize() {
    PlayerDeck deck1 = new PlayerDeck();

    assertEquals(10, deck1.getSize());

    List<Card> hand = deck1.drawNumAndDiscardOldHand(5);

    assertEquals(5, deck1.getSize());

    hand = deck1.drawNumAndDiscardOldHand(5);

    assertEquals(5, deck1.getSize());

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
    assertEquals(8, hand.size());

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
    for (int i = 0; i < 5; i++) {
      deck.discardCardInHandAtIndex(0);
    }
    assertEquals(5, hand.size());
    deck.drawNum(5);
    assertEquals(10, hand.size());
    for (int i = 0; i < 5; i++) {
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
    deck.addCard(Card.makeCard(Card.CARD_NAME_CHANCELLOR), PlayerDeckType.HAND);
    assertEquals(deck.getHand().get(deck.getHand().size() - 1),
        Card.makeCard(Card.CARD_NAME_CHANCELLOR));
    assertEquals(PlayerDeck.STANDARD_HAND_SIZE + 1, deck.getHand().size());
  }

  @Test
  public void testCountVictoryPoints() {
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10, deck.getSize());
    assertEquals(3, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_PROVINCE));
    assertEquals(9, deck.countVictoryPoints());

    deck.drawNumAndDiscardOldHand(9);
    assertEquals(9, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_DUCHY));
    assertEquals(12, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_COPPER));
    assertEquals(12, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_CURSE));
    assertEquals(11, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_GARDENS));
    assertEquals(12, deck.countVictoryPoints());

    for (int i = 0; i < 10; i++) {
      deck.addCard(Card.makeCard(Card.CARD_NAME_COPPER));
    }

    assertEquals(13, deck.countVictoryPoints());

    deck.addCard(Card.makeCard(Card.CARD_NAME_CURSE));
    assertEquals(12, deck.countVictoryPoints());



  }

  @Test
  public void testDiscardCardInHandAtIndex() {
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10, deck.getSize());

    List<Card> hand = deck.getHand();
    assertEquals(5, hand.size());

    deck.drawNum(deck.getSize());
    assertEquals(10, hand.size());

    ChapelCard chapel = new ChapelCard();
    deck.addCard(chapel);

    deck.drawNum(1);
    assertEquals(11, hand.size());

    deck.discardCardInHandAtIndex(hand.size() - 1);
    assertEquals(10, hand.size());
    assertEquals(false, hand.contains(chapel));
  }

  @Test
  public void testDiscardDrawPile() {
    PlayerDeck deck = new PlayerDeck();
    assertEquals(10, deck.getSize());

    deck.discardDrawPile();

    assertEquals(0, deck.getSize());
    assertEquals(10, deck.getDiscardDeck().size());
  }

  private Card anyCard() {
    return Card.makeCard(Card.CARD_NAME_CELLAR);
  }

  @Test
  public void testAddingCardToHand() {
    PlayerDeck deck = new PlayerDeck();
    int currentHandSize = deck.getHand().size();
    deck.addCard(anyCard(), PlayerDeckType.HAND);
    assertEquals(currentHandSize + 1, deck.getHand().size());
  }

  @Test
  public void testAddingCardToDrawDeck() {
    PlayerDeck deck = new PlayerDeck();
    int currentDeckSize = deck.getDrawDeck().size();
    deck.addCard(anyCard(), PlayerDeckType.DRAW);
    assertEquals(currentDeckSize + 1, deck.getDrawDeck().size());
  }

  @Test
  public void testAddingCardToDiscardDeck() {
    PlayerDeck deck = new PlayerDeck();
    int currentDiscardSize = deck.getDiscardDeck().size();
    deck.addCard(anyCard(), PlayerDeckType.DISCARD);
    assertEquals(currentDiscardSize + 1, deck.getDiscardDeck().size());
  }
  
  @Test
  public void testGettingDescriptionsForHand() {
    PlayerDeck deck = new PlayerDeck();
    List<String> descriptions = deck.getCardDescriptions(PlayerDeckType.HAND, Integer.MAX_VALUE);
    
    assertEquals(5, descriptions.size());
  }
  
  @Test
  public void testGettignDescriptionsForHandWithSmallerNumberOfCards() {
    PlayerDeck deck = new PlayerDeck();
    List<String> descriptions = deck.getCardDescriptions(PlayerDeckType.HAND, 1);
    
    assertEquals(1, descriptions.size());
  }
  
  @Test
  public void testDiscardCardInDeckAtIndex(){
    PlayerDeck deck = new PlayerDeck();
    
    assertEquals(10,deck.getSize());
    
    for(int i=0;i<10;i++){
      deck.trashCardInDeckAtIndex(0);
    }
    
    assertEquals(0,deck.getSize());
    
    boolean containsCellarStill = false;
    
    deck.addCard(Card.makeCard(Card.CARD_NAME_CELLAR), PlayerDeckType.DRAW);
    
    assertEquals(1,deck.getSize());
    
    for(int j=0; j<deck.getSize();j++){
      if(deck.getDrawDeck().get(j).getName() == Card.CARD_NAME_CELLAR){
        containsCellarStill = true;
      }
    }
    assertEquals(true, containsCellarStill);
    
  }

}
