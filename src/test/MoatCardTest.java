package test;

import java.util.Arrays;
import java.util.List;

import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;

import org.junit.Test;

import junit.framework.TestCase;

public class MoatCardTest extends TestCase {
  Player player;
  TurnController turnController;
  SupplyDeck supplyDeck;
  GameContext context;

  @Override
  protected void setUp() {
    player = new Player("Test Player");
    List<Card> cards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
            Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
            Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
            Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    supplyDeck = new SupplyDeck(cards);

    turnController = new TurnController(player, null, supplyDeck, null);
    context = new GameContext(turnController);
  }

  @Test
    public void testMoat(){    
      List<Card> deck = player.getPlayerDeck().getDrawDeckForTestingOnly();
      
      List<Card> hand = player.getPlayerDeck().getHand();
      
      assertEquals(5,hand.size());
      assertEquals(5,deck.size());
      Card moat = Card.makeCard(Card.CARD_NAME_MOAT);
      moat.performAction(context);
      
      assertEquals(7,hand.size());
      assertEquals(3, deck.size());
      
    }
}
