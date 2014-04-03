package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import main.Card;
import main.PlayerDeck;

import org.junit.Test;

public class PlayerDeckTest {

  @Test
  public void testSize() {
    PlayerDeck deck1 = new PlayerDeck();

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
  public void testNotEnoughCards() {
    PlayerDeck deck1 = new PlayerDeck();
    List<Card> hand = deck1.drawNum(3);
    assertEquals(3, hand.size());
    hand = deck1.drawNum(11);
    assertEquals(10, hand.size());
  }

  @Test
  public void testShuffle() {
    PlayerDeck deck = new PlayerDeck();
    List<Card> hand = deck.drawNum(10);
    hand = deck.drawNum(5);

    assertEquals(5, hand.size());
  }
  
  @Test
  public void testGethand() {
    
  }

}