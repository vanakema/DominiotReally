package test;

import main.GameContext;
import main.PlayerDeck;
import main.cards.Card;

import org.junit.Test;

import junit.framework.TestCase;

public class MilitiaCardTest extends TestCase {

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
    
    Card militia = Card.makeCard(Card.CARD_NAME_MILITIA);
    militia.performAction(env.context);

    // This was originally 3+1, that was an error in the understanding of the card's implementation
    assertEquals(5, env.player.getPlayerDeck().getHand().size());
    assertEquals(3, env.opponent.getPlayerDeck().getHand().size());
  }

}
