package main.cards;

import main.GameContext;

public class CouncilRoomCard extends ActionCard {

  protected CouncilRoomCard() {
    super(Card.CARD_NAME_COUNCILROOM, 5);
    this.numCardsDraw = 4;
    this.additionalBuys = 1;
  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);

    context.opponentDrawNumCards(1);
  }

}
