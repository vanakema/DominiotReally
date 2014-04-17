package main.cards;

import main.GameContext;
import main.PlayerDeck;
import main.GameContext.DecisionDelegate;

public class CellarCard extends ActionCard {



  protected CellarCard() {
    super(Card.CARD_NAME_CELLAR,
        "+1 Action. Discard any number of cards. 1+ Card per card discarded.", 2);
    this.additionalActions = 1;

  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    while (true) {
      int index = context.decideCardInHand("Choose a card to trash, and draw a new one after.", true);
      if (index != GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED) {
        deck.trashCardInHandAtIndex(index);
        deck.drawNum(1);
      } else {
        break;
      }
    }
  }

}
