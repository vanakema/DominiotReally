package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import main.Card;
import main.GameContext;
import main.ChapelCard;
import main.Player;
import main.PlayerDeck;
import main.SupplyDeck;
import main.TurnController;

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
    controller = new TurnController(player, supplyDeck, null);
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
  public void testDescription() {
    assertEquals("Trash up to 4 cards from your hand.", card.getDescription());
  }

  @Test
  public void testPerformAction() {
    

  }



}
