package main.cards;

import main.GameContext;

public class ChancellorCard extends ActionCard {

  protected ChancellorCard() {
    super(Card.CARD_NAME_CHANCELLOR, 3);

    this.additionalCoins = 2;
  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);

    if (context.decideBoolean("Put your deck into the discard pile?")) {
      context.getPlayer().getPlayerDeck().discardDrawPile();
      System.out.println("Discarded some cards!");
    }
  }

}
