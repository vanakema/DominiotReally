package test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.Card;
import main.GameContext;
import main.MineCard;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import junit.framework.TestCase;

public class MineCardTest extends TestCase {
  Player player;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  MineCard card = new MineCard();

  @Override
  protected void setUp() {
    player = new Player("Test Player");

    List<Card> cards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
            Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
            Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
            Card.makeCard(Card.CARD_NAME_WOODCUTTER)});

    supplyDeck = new SupplyDeck(cards);
    controller = new TurnController(player, supplyDeck, null);
    context = new GameContext(controller);
  }

  @Test
  public void testName() {
    assertEquals("Mine", card.getName());
  }

  @Test
  public void testCost() {
    assertEquals(5, card.getCost());
  }

  @Test
  public void testDescription() {
    assertEquals(
        "Trash a Treasure card from your hand. Gain a Treasure card costing up to $3 more; put it into your hand.",
        card.getDescription());
  }
  
  

}
