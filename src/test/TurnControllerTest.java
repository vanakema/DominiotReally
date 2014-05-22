package test;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;

import org.junit.Test;

public class TurnControllerTest extends TestCase {

  Player player;
  Player opponent;
  SupplyDeck supplyDeck;
  TurnController turnController;

  @Override
  protected void setUp() {
    List<Card> playerCards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER)});
    List<Card> opponentCards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER)});
    player = new Player("Test Player", playerCards);
    opponent = new Player("Test Opponent", opponentCards);


    List<Card> cards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
            Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
            Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
            Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    supplyDeck = new SupplyDeck(cards);

    turnController = new TurnController(player, opponent, supplyDeck, null);
  }

  // Make changes so it has a context to check for the true condition
  @Test
  public void testTryPurchaseActionCardAtIndex() {
    assertFalse(turnController.tryPurchaseActionCardAtIndex(0));
  }

  @Test
  public void testGetPlayer() {
    assertEquals(player, turnController.getPlayer());
  }

  @Test
  public void testGetOpponent() {
    assertEquals(opponent, turnController.getOpponent());
  }

  @Test
  public void testGetCurrentContext() {
    assertNotNull(turnController.getCurrentContext());
  }

  @Test
  public void testPlayingCardConsumesOneFromHand() {
    int beforeHandCount = player.getPlayerDeck().getHand().size();
    assertTrue(turnController.tryPlayingCardAtIndex(player.getPlayerDeck().getHand().size() - 1));
    assertEquals(beforeHandCount - 1, player.getPlayerDeck().getHand().size());
  }

  @Test
  public void testThatPlayingAndInvalidCardIndexIsHandled() {
    assertFalse(turnController.tryPlayingCardAtIndex(player.getPlayerDeck().getHand().size()));
  }

  @Test
  public void testThatPlayingACardWithNoActionsIsHandled() {
    List<Card> noActionCards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_MOAT),
            Card.makeCard(Card.CARD_NAME_MOAT), Card.makeCard(Card.CARD_NAME_MOAT),
            Card.makeCard(Card.CARD_NAME_MOAT), Card.makeCard(Card.CARD_NAME_MOAT),
            Card.makeCard(Card.CARD_NAME_MOAT), Card.makeCard(Card.CARD_NAME_MOAT),
            Card.makeCard(Card.CARD_NAME_MOAT), Card.makeCard(Card.CARD_NAME_MOAT),
            Card.makeCard(Card.CARD_NAME_MOAT)});
    Player playerSpecial = new Player("Test Player", noActionCards);
    Player opponentSpecial = new Player("Test Opponent", noActionCards);


    List<Card> cards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
            Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
            Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
            Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    SupplyDeck supplyDeckSpecial = new SupplyDeck(cards);

    TurnController turnControllerSpecial =
        new TurnController(playerSpecial, opponentSpecial, supplyDeckSpecial, null);
    while (turnControllerSpecial.getCurrentContext().getActionCount() > 0) {
      assertTrue(turnControllerSpecial.tryPlayingCardAtIndex(0));
    }

    assertFalse(turnControllerSpecial.tryPlayingCardAtIndex(0));
  }

  @Test
  public void testThatBuyingACardWorks() {
    // Purchase a copper because they are always $0
    assertTrue(turnController.tryPurchaseResourceCardAtIndex(0));
  }

  @Test
  public void testThatBuyingACardWithoutBuysFails() {
    while (turnController.getCurrentContext().getBuyCount() > 0) {
      assertTrue(turnController.tryPurchaseResourceCardAtIndex(0));
    }
    assertFalse(turnController.tryPurchaseResourceCardAtIndex(0));
  }
}
