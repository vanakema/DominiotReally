package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;

import org.junit.Before;
import org.junit.Test;

public class CouncilRoomCardTest {
  Player player;
  Player opponent;
  TurnController turnController;
  SupplyDeck supplyDeck;
  GameContext context;

  @Before
  public void setUp() throws Exception {
    player = new Player("Test Player");
    opponent = new Player("Test Opponent");
    List<Card> cards = Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
        Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
        Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
        Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    supplyDeck = new SupplyDeck(cards);

    turnController = new TurnController(player, opponent, supplyDeck, null);
    context = new GameContext(turnController);
  }

  @Test
  public void testActionsToCurrentPlayer() {
    int oldHandSize = turnController.getPlayer().getPlayerDeck().getHand().size();
    int oldNumBuys = context.getBuyCount();
    
    Card councilRoom = Card.makeCard(Card.CARD_NAME_COUNCILROOM);
    councilRoom.performAction(context);
    
    int newHandSize = turnController.getPlayer().getPlayerDeck().getHand().size();
    int newNumBuys = context.getBuyCount();
    
    assertEquals(oldHandSize + 4, newHandSize);
    assertEquals(oldNumBuys + 1, newNumBuys);
  }
  
  @Test
  public void testActionsToOpponent() {
    int oldHandSize = turnController.getOpponent().getPlayerDeck().getHand().size();
    
    Card councilRoom = Card.makeCard(Card.CARD_NAME_COUNCILROOM);
    councilRoom.performAction(context);
    
    int newHandSize = turnController.getOpponent().getPlayerDeck().getHand().size();
    
    assertEquals(oldHandSize +1, newHandSize);
  }

}