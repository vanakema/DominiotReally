package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;
import main.cards.SpyCard;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class BureaucratCardTest extends TestCase {
  Player player;
  Player secondPlayer;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  Card card = Card.makeCard(Card.CARD_NAME_BUREAUCRAT);

  @Override
  protected void setUp() {
    player = new Player("Test Player");
    secondPlayer = new Player("Test Player2");

    List<Card> cards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
            Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
            Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
            Card.makeCard(Card.CARD_NAME_WOODCUTTER)});

    supplyDeck = new SupplyDeck(cards);
    controller = new TurnController(player, secondPlayer, supplyDeck, null);
    context = new GameContext(controller);
  }

  @Test
  public void testActionsOnPlayer() {
    context.setDecisionDelegate(new TestDecisionDelegate());
    card.performAction(context);
    assertEquals(Card.makeCard(Card.CARD_NAME_SILVER).getName(), context.getTurnController().getPlayer()
        .getPlayerDeck().getDrawDeck().get(0).getName());
    
  }

  @Test
  public void testActionsOnOpponent() {
    while (true) {
      if (context.getTurnController().getOpponent().getPlayerDeck().getHand()
          .contains(Card.makeCard(Card.CARD_NAME_COPPER))) {
        int oldHandSize =
            context.getTurnController().getOpponent().getPlayerDeck().getHand().size();
        int oldDrawDeckSize =
            context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().size();
        card.performAction(context);
        assertEquals(oldHandSize - 1, context.getTurnController().getOpponent().getPlayerDeck()
            .getHand().size());
        assertEquals(oldDrawDeckSize + 1, context.getTurnController().getOpponent().getPlayerDeck()
            .getDrawDeck().size());
        break;
      } else {
        context.getTurnController().getOpponent().getPlayerDeck().drawNumAndDiscardOldHand(5);
      }
    }
  }

}
