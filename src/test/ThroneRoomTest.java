package test;

import main.GameContext;
import main.PlayerDeck;
import main.PlayerDeck.PlayerDeckType;
import main.cards.Card;

import org.junit.Test;

import junit.framework.TestCase;

public class ThroneRoomTest extends TestCase {

  TestGameEnvironment env;

  @Override
  public void setUp() {
    env = new TestGameEnvironment();
  }

  @Test
  public void testPerformAction() {
    env.context.setDecisionDelegate(new TestDecisionDelegate() {
      @Override
      public int decideCardInHand(GameContext context, PlayerDeck deck, String question,
          boolean canIgnore) {
        return 0;
      }
    });

    final Integer[] callCount = new Integer[] {0};
    env.player.getPlayerDeck().addCard(new Card("", "", 0, "") {
      @Override
      public void performAction(GameContext context) {
        callCount[0]++;
      }
    }, PlayerDeckType.HAND);
    
    Card.makeCard(Card.CARD_NAME_THRONE_ROOM).performAction(env.context);
    
    assertEquals(2, (int) callCount[0]);
  }

}
