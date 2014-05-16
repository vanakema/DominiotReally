package main.cards;

import main.GameContext;

public class FeastCard extends ActionCard {

  public FeastCard() {
    super(Card.CARD_NAME_FEAST, 0);
  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);

    // TODO: Do we correctly handle this card when played as the second action of Throne Room?
    context.setLumpSumTreasureCount(5);
    context.setShouldTrashCurrentCard();
  }

}
