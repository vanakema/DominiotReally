package test;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;

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

    turnController = new TurnController(player, null, supplyDeck, null);
  }
  
  @Test
  public void testGetPlayer() {
    assertEquals(player, turnController.getPlayer());
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
    while (turnController.getCurrentContext().getActionCount() > 0) {
      assertTrue(turnController.tryPlayingCardAtIndex(0));
    }
    
    assertFalse(turnController.tryPlayingCardAtIndex(0));
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
