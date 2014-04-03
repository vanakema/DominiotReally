package test;

import static org.junit.Assert.*;

import main.ActionCard;
import main.BoringActionCard;
import main.Card;
import main.GameContext;

import org.junit.Test;

public class GameContextTest {

  @Test
  public void testCloneConstructor() {
    GameContext context1 = new GameContext();
    context1.adjustActionCountByDelta(3);
    context1.adjustBuyCountByDelta(5);
    context1.adjustTreasureCountByDelta(500);

    GameContext context2 = new GameContext(context1);
    assertEquals(context1.getActionCount(), context2.getActionCount());
    assertEquals(context1.getBuyCount(), context2.getBuyCount());
    assertEquals(context1.getTreasureCount(), context2.getTreasureCount());

  }

  @Test
  public void testIntegrationWithBoringCards_addAdditionalActions() {
    GameContext context = new GameContext();
    Card lab = Card.makeCard(Card.CARD_NAME_LABORATORY);
    lab.performAction(context);
    assertEquals(2, context.getActionCount());
  }

  @Test
  public void testIntegrationWithBoringCards_addAdditionalBuys() {
    GameContext context = new GameContext();
    Card fest = Card.makeCard(Card.CARD_NAME_FESTIVAL);
    fest.performAction(context);
    assertEquals(2, context.getBuyCount());
  }

  @Test
  public void testIntegrationWithBoringCards_addAdditionalCoins() {
    GameContext context = new GameContext();
    Card woodCutter = Card.makeCard(Card.CARD_NAME_WOODCUTTER);
    woodCutter.performAction(context);
    assertEquals(2, context.getTreasureCount());
  }

  @Test
  public void testIntegrationWithTreasureCards() {
    GameContext context = new GameContext();
    Card copper = Card.makeCard(Card.CARD_NAME_COPPER);
    Card silver = Card.makeCard(Card.CARD_NAME_SILVER);
    Card gold = Card.makeCard(Card.CARD_NAME_GOLD);

    copper.performAction(context);
    assertEquals(1, context.getTreasureCount());
    silver.performAction(context);
    assertEquals(3, context.getTreasureCount());
    gold.performAction(context);
    assertEquals(6, context.getTreasureCount());
  }

}
