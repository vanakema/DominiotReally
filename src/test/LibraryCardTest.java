package test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import main.GameContext;
import main.Player;
import main.PlayerDeck;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;
import main.cards.LibraryCard;
import junit.framework.TestCase;

public class LibraryCardTest extends TestCase {
  Player player;
  Player secondPlayer;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  LibraryCard card = (LibraryCard) Card.makeCard(Card.CARD_NAME_LIBRARY);

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
  public void testName() {
    assertEquals("Library", card.getName());
  }

  @Test
  public void testCost() {
    assertEquals(5, card.getCost());
  }

  @Test
  public void testPerformAction() {
    context.setDecisionDelegate(new TestDecisionDelegate() {
      @Override
      public boolean decideBoolean(GameContext context, String question) {
        return true;
      }
    });

    assertEquals(10, this.player.getPlayerDeck().getSize());

    Iterator<Card> handIterator = this.player.getPlayerDeck().getHand().iterator();

    while (handIterator.hasNext()) {
      System.out.print(handIterator.next().getName() + " ");
    }
    
    this.player.getPlayerDeck().getDrawDeckForTestingOnly().add(0, Card.makeCard(Card.CARD_NAME_CHAPEL));
    this.player.getPlayerDeck().getDrawDeckForTestingOnly().add(0, Card.makeCard(Card.CARD_NAME_ADVENTURER));
    
    Card library = Card.makeCard(Card.CARD_NAME_LIBRARY);
    library.performAction(context);

    Iterator<Card> handIterator2 = this.player.getPlayerDeck().getHand().iterator();

    while (handIterator2.hasNext()) {
      System.out.print(handIterator2.next().getName() + " ");
    }
    assertEquals(7, this.player.getPlayerDeck().getHand().size());

  }



}
