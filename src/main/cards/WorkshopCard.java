package main.cards;

import main.GameContext;

public class WorkshopCard extends ActionCard {

  protected WorkshopCard() {
    super(Card.CARD_NAME_WORKSHOP, 3);
  }
  
  @Override
  public void addAdditionalCoins(GameContext context) {
    super.addAdditionalCoins(context);
    
    context.setLumpSumTreasureCount(4);
  }

}
