package test;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import main.Card;
import main.Player;
import main.SupplyDeck;
import main.TurnController;

import org.junit.Test;

public class TurnControllerTest extends TestCase {

  Player player;
  SupplyDeck supplyDeck;
  TurnController turnController;

  @Override
  protected void setUp() {
    player = new Player("Test Player");
    
    List<Card> cards = Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
        Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
        Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
        Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    supplyDeck = new SupplyDeck(cards);

    turnController = new TurnController(player, supplyDeck);
  }
  
  @Test
  public void testGetPlayer() {
    assertEquals(player, turnController.getPlayer());
  }
}
