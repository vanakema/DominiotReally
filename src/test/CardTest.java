package test;

import static org.junit.Assert.*;

import main.Card;

import org.junit.Test;

public class CardTest {

  @Test
  public void testMakeInvalidCard() {
    Card badCard = Card.makeCard("Dutcher");
    assertEquals(null, badCard);
  }

}
