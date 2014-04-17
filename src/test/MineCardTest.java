package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.GameContext.DecisionDelegate;
import main.PlayerDeck.PlayerDeckType;
import main.cards.Card;

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

    turnController = new TurnController(player, null, supplyDeck, null);
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
      
      @Override
      public boolean decideBoolean(GameContext context, String question) {
        return false;
      }
    });
    context.getPlayer().getPlayerDeck().addCard(Card.makeCard(Card.CARD_NAME_COPPER), PlayerDeckType.HAND);
    List<Card> hand = context.getPlayer().getPlayerDeck().getHand();
    Card oldFirstInDeck = context.getPlayer().getPlayerDeck().getDrawDeck().get(0);
    int handSize = hand.size();
    assertEquals(handSize, 1);
    
    Card mine = Card.makeCard(Card.CARD_NAME_MINE);
    mine.performAction(context);
    assertEquals(handSize, hand.size());
    assertFalse(oldFirstInDeck.equals(hand.get(0)));
    assertEquals(Card.makeCard(Card.CARD_NAME_SILVER), hand.get(0));
  }

}
