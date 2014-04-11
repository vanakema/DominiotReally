package test;


import java.util.Arrays;
import java.util.List;

import main.Card;
import main.GameContext;
import main.GameContext.DecisionDelegate;
import main.Player;
import main.SupplyDeck;
import main.TurnController;

import org.junit.Before;
import org.junit.Test;

public class MineCardTest {
  Player player;
  TurnController turnController;
  SupplyDeck supplyDeck;
  GameContext context;

  @Before
  public void setUp() throws Exception {
    player = new Player("Test Player");
    List<Card> cards = Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
        Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
        Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
        Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    supplyDeck = new SupplyDeck(cards);

    turnController = new TurnController(player, supplyDeck, null);
    context = new GameContext(turnController);
  }

  @Test
  public void testMineCopper() {
    context.setDecisionDelegate(new DecisionDelegate() {
      boolean remaining = false;
      @Override
      public int decideCardInHand(GameContext context, String question, boolean canIgnore) {
        if(remaining == true) {
          return GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED;
        }
        else {
          remaining = true;
          return 0;
        }
      }
    }
  }
}
      
