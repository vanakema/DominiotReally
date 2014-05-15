package main.cards;

import main.GameContext;

public class BureaucratCard extends ActionCard {

  protected BureaucratCard() {
    super(
        Card.CARD_NAME_BUREAUCRAT,
        "Gain a Silver card, put it on top of your deck. Each other player reveals a Victory card and puts it on top of his deck. (or reveals a hand with no Victory cards).",
        4);
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
