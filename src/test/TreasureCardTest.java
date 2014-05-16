package test;

import static org.junit.Assert.assertEquals;
import main.cards.TreasureCard;

import org.junit.Test;

/**
 * Tests all the various methods of the TreasureCard's
 * 
 * @author vanakema. Created Mar 19, 2014.
 */
public class TreasureCardTest {
  TreasureCard c1 = TreasureCard.makeCopper();
  TreasureCard s1 = TreasureCard.makeSilver();
  TreasureCard g1 = TreasureCard.makeGold();

  @Test
  public void nameTest() {
    assertEquals("Copper", c1.getName());
    assertEquals("Silver", s1.getName());
    assertEquals("Gold", g1.getName());
  }

  @Test
  public void costTest() {
    assertEquals(0, c1.getCost());
    assertEquals(3, s1.getCost());
    assertEquals(6, g1.getCost());
  }

  @Test
  public void descriptionTest() {
    assertEquals("Treasure: +1 Coin", c1.getDescription());
    assertEquals("Treasure: +2 Coin", s1.getDescription());
    assertEquals("Treasure: +3 Coin", g1.getDescription());
  }

  @Test
  public void valueTest() {
    assertEquals(1, c1.getValue());
    assertEquals(2, s1.getValue());
    assertEquals(3, g1.getValue());
  }

}
