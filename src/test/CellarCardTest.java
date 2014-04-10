package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import main.Card;
import main.GameContext;
import main.GameContext.DecisionDelegate;
import main.Player;
import main.SupplyDeck;
import main.TurnController;

import org.junit.Test;

public class CellarCardTest extends TestCase {
  Player player;
  TurnController turnController;
  SupplyDeck supplyDeck;
  GameContext context;

  @Override
  protected void setUp() {
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
  public void testFirstCardTrash() {
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
    context.getPlayer().getPlayerDeck().drawNumAndDiscardOldHand(5);
    List<Card> hand = context.getPlayer().getPlayerDeck().getHand();
    Card oldFirstInDeck = context.getPlayer().getPlayerDeck().getDrawDeck().get(0);
    int handSize = hand.size();
    assertEquals(handSize, 5);
    
    Card cellar = Card.makeCard(Card.CARD_NAME_CELLAR);
    cellar.performAction(context);
    
    assertEquals(handSize, hand.size());
    assertEquals(oldFirstInDeck, hand.get(4));
  }

}
