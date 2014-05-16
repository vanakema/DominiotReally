package main.cards;

import main.GameContext;

public class SpyCard extends ActionCard {

  protected SpyCard() {
    super(Card.CARD_NAME_SPY, 4);

    this.additionalActions = 1;
    this.numCardsDraw = 1;
  }

  public void performAction(GameContext context) {
    super.performAction(context);
    boolean discardOwnOrNah = context.decideCardInOwnDeck("Do you wish to discard this card?");

    if (discardOwnOrNah) {
      context.getPlayer().getPlayerDeck().discardCardInDeckAtIndex(0);
    }

    if (context.shouldPerformMaliciousActions()) {
      boolean discardTheirsOrNah =
          context.decideCardInOpponentDeck("Do you wish to trash this card?");

      if (discardTheirsOrNah) {
        context.getTurnController().getOpponent().getPlayerDeck().discardCardInDeckAtIndex(0);
      }
    }

  }

}
