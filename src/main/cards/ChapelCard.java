package main.cards;

import main.GameContext;
import main.PlayerDeck;


public class ChapelCard extends ActionCard {

  public ChapelCard() {
    super(Card.CARD_NAME_CHAPEL, "Trash up to 4 cards from your hand.", 2);

  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    for (int trashesRemaining = 4; trashesRemaining > 0; trashesRemaining--) {
      int index = context.decideCardInHand("Trash a card from your hand?", true);
      if (index != GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED) {
        deck.trashCardInHandAtIndex(index);
      } else {
        break;
      }
    }
  }

}
