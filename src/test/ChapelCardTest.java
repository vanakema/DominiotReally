package test;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;
import main.cards.ChapelCard;

import org.junit.Test;

public class ChapelCardTest extends TestCase {

  Player player;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  ChapelCard card = new ChapelCard();

  @Override
  protected void setUp() {
    player = new Player("Test Player");
    
    List<Card> cards = Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
    Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
    Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
    Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    
    supplyDeck = new SupplyDeck(cards);
    controller = new TurnController(player, null, supplyDeck, null);
    context = new GameContext(controller);
  }

  @Test
  public void testName() {
    assertEquals("Chapel", card.getName());
  }

  @Test
  public void testCost() {
    assertEquals(2, card.getCost());
  }

  @Test
  public void testPerformAction() {
    context.setDecisionDelegate(new TestDecisionDelegate() {
      int count =0;
      @Override
      public int decideCardInHand(GameContext context, String question, boolean canIgnore) {
        if(count == 4) {
          
          return GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED;
        }
        else {
          count++;
          return 0;
        }
      }
    });
    context.getPlayer().getPlayerDeck().drawNumAndDiscardOldHand(5);
    List<Card> hand = context.getPlayer().getPlayerDeck().getHand();
    int handSize = hand.size();
    assertEquals(handSize,5);
    
    Card chapel = Card.makeCard(Card.CARD_NAME_CHAPEL);
    chapel.performAction(context);
    
    assertEquals(1,hand.size());

  }



}
