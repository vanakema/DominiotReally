package test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.Card;
import main.ChapelCard;
import main.GameContext;
import main.MoneylenderCard;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import junit.framework.TestCase;

public class MoneylenderCardTest extends TestCase{
  
  Player player;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  MoneylenderCard card = new MoneylenderCard();

  @Override
  protected void setUp() {
    player = new Player("Test Player");
    
    List<Card> cards = Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
    Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
    Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
    Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    
    supplyDeck = new SupplyDeck(cards);
    controller = new TurnController(player, supplyDeck, null);
    context = new GameContext(controller);
  }
  
  @Test
  public void testName() {
    assertEquals("Moneylender", card.getName());
  }

  @Test
  public void testCost() {
    assertEquals(4, card.getCost());
  }

  @Test
  public void testDescription() {
    assertEquals("Trash a Copper from your hand. If you do, +$3.", card.getDescription());
  }
  

}
