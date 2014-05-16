package main.cards;

import main.GameContext;

public class BureaucratCard extends ActionCard {

  protected BureaucratCard() {
    super(Card.CARD_NAME_BUREAUCRAT, 4);
  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);

    if (context.shouldPerformMaliciousActions()) {
      context.getTurnController().tryForceInsertResourceCardIntoTopOfPlayerDrawDeck(1);
      boolean hadVictoryCard =
          context.getTurnController().tryToTakeAVictoryCardFromOpponentAndPutItOnTopOfHisDrawDeck();

      if (!hadVictoryCard) {
        context.decideCardInOpponentHand(
            "Here is your opponent's hand. You can't do anything with it", true);
      }
    }
  }

}
