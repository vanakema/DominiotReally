/**
 * 
 */
package main.cards;

import main.GameContext;

/**
 * @author vanakema
 * 
 */
public abstract class ActionCard extends Card {

  protected ActionCard(String name, String description, int cost) {
    super(name, description, cost, Card.CARD_TYPE_ACTIONCARD);
  }

  protected int additionalActions = 0;
  protected int additionalBuys = 0;
  protected int additionalCoins = 0;
  protected int numCardsDraw = 0;

  public void addAdditionalActions(GameContext context) {
    context.adjustActionCountByDelta(this.additionalActions);
  }

  public void addAdditionalBuys(GameContext context) {
    context.adjustBuyCountByDelta(this.additionalBuys);
  }

  public void addAdditionalCoins(GameContext context) {
    context.adjustTreasureCountByDelta(this.additionalCoins);

  }
  
  public void drawAdditionalCards(GameContext context) {
    context.getPlayer().getPlayerDeck().drawNum(numCardsDraw);
  }

  @Override
  public void performAction(GameContext context) {
    addAdditionalActions(context);
    addAdditionalBuys(context);
    addAdditionalCoins(context);
    drawAdditionalCards(context);
  }

}
