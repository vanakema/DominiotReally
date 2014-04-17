package main.cards;

import main.GameContext;

public class WorkshopCard extends ActionCard {

  protected WorkshopCard() {
    super(Card.CARD_NAME_WORKSHOP, "Gain a card costing up to 4.", 0);
  }
  
  @Override
  public void addAdditionalCoins(GameContext context) {
    super.addAdditionalCoins(context);
    
    context.setLumpSumTreasureCount(4);
  }

}
