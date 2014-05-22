package test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.GameContext;
import main.Player;
import main.PlayerDeck;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;
import main.cards.SpyCard;
import junit.framework.TestCase;

public class SpyCardTest extends TestCase {

  Player player;
  Player secondPlayer;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  SpyCard card = (SpyCard) Card.makeCard(Card.CARD_NAME_SPY);

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
    assertEquals("Spy", card.getName());
  }

  @Test
  public void testCost() {
    assertEquals(4, card.getCost());
  }

  @Test
  public void testPerformAction() {
    context.setDecisionDelegate(new TestDecisionDelegate() {
      @Override
      public boolean decideCardInDeck(GameContext context, PlayerDeck deck, String question) {
        return true;
      }

    });

    int oldPlayerDeckSize = this.player.getPlayerDeck().getSize();
    int oldOpponentDeckSize = this.secondPlayer.getPlayerDeck().getSize();
    

    Card spy = Card.makeCard(Card.CARD_NAME_SPY);
    spy.performAction(context);

    assertEquals(oldOpponentDeckSize - 1,this.secondPlayer.getPlayerDeck().getSize() + this.secondPlayer.getPlayerDeck().getHand().size());

  }


}
