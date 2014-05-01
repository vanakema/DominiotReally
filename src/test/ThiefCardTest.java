package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import main.GameContext;
import main.Player;
import main.PlayerDeck;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;

import org.junit.Before;
import org.junit.Test;


public class ThiefCardTest {
  Player player;
  Player opponent;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  Card card = Card.makeCard(Card.CARD_NAME_THIEF);

  @Before
  public void setUp() throws Exception {
    List<Card> playerDeckCards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_GOLD)});

    player = new Player("Test Player", playerDeckCards);
    opponent = new Player("Test Opponent");

    List<Card> cards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
            Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
            Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
            Card.makeCard(Card.CARD_NAME_WOODCUTTER)});

    supplyDeck = new SupplyDeck(cards);
    controller = new TurnController(player, null, supplyDeck, null);
    context = new GameContext(controller);
  }

  @Test
  public void testName() {
    assertEquals(Card.CARD_NAME_THIEF, card.getName());
  }

  // TODO: Uncomment after you change the code so the price is the real price, not just 0

  // @Test
  // public void testCost() {
  // assertEquals(4, card.getCost());
  // }

  @Test
  public void testDescription() {
    assertEquals(
        "Each other player reveals the top 2 cards of his deck. If they revealed any Treasure cards, they trash one of them that you choose. You may gain any or all of these trashed cards. They discard the other revealed cards.",
        card.getDescription());
  }

  @Test
  public void testPerformActionOnPlayer() {
    context.setDecisionDelegate(new TestDecisionDelegate() {
      int count = 0;

      @Override
      public int decideCardInDeck(GameContext context, PlayerDeck deck, String question,
          int numberOfCards) {
        return 1;
      }
    });
    int size = 

  }


}
