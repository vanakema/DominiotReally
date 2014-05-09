package test;

import junit.framework.TestCase;
import main.PlayerDeck;
import main.PlayerDeck.PlayerDeckType;
import main.cards.Card;

import org.junit.Test;

public class AdventurerCardTest extends TestCase {

  TestGameEnvironment env;

  @Override
  public void setUp() {
    env = new TestGameEnvironment();
  }

  @Test
  public void testForTwoTreasureCardsAtTopOfDrawDeck() {
    Card card = Card.makeCard(Card.CARD_NAME_ADVENTURER);

    PlayerDeck deck = env.player.getPlayerDeck();
    for (int i = 0; i < 10; i++) {
      deck.addCard(Card.makeCard(Card.CARD_NAME_CHAPEL));
      deck.discardHand();
      deck.getHand();
    }

    String beforeDraw = deck.toString(PlayerDeckType.DRAW);
    String beforeHand = deck.toString(PlayerDeckType.HAND);
    String beforeDiscard = deck.toString(PlayerDeckType.DISCARD);
    int beforeCardCount = beforeDraw.split(" ").length + beforeHand.split(" ").length + beforeDiscard.split(" ").length;

    card.performAction(env.context);

    String afterDraw = deck.toString(PlayerDeckType.DRAW);
    String afterHand = deck.toString(PlayerDeckType.HAND);
    String afterDiscard = deck.toString(PlayerDeckType.DISCARD);
    int afterCardCount = afterDraw.split(" ").length + afterHand.split(" ").length + afterDiscard.split(" ").length;

    // Draw deck should begin with two treasure cards.
    assertTrue(afterDraw.startsWith(Card.CARD_NAME_COPPER + " " + Card.CARD_NAME_COPPER));
    assertEquals(beforeHand, afterHand); // Hand should not be changed
    assertEquals(beforeCardCount, afterCardCount); // Nothing lost, nothing gained
  }

}
