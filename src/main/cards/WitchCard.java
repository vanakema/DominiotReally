package main.cards;

import main.GameContext;

public class WitchCard extends ActionCard {

  public WitchCard() {
    super(Card.CARD_NAME_WITCH, "+2 Cards, Each other player gains a Curse card.", 5);
    this.numCardsDraw = 2;
  }
  
  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    context.curseOpponent();
  }

}
