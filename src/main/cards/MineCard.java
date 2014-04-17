package main.cards;

import main.GameContext;
import main.GameContext.DecisionDelegate;

public class MineCard extends ActionCard {

  public MineCard() {
    super(
        Card.CARD_NAME_MINE,
        "Trash a Treasure card from your hand. Gain a Treasure card costing up to $3 more; put it into your hand.",
        5);
  }

  @Override
  public void performAction(GameContext context) {
    //TODO: Testing on this
    super.performAction(context);
    int index = context.decideCardInHand("Choose a Treasure Card to trash", true);
    if (index != GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED) {
      Card cardToTrash = context.getPlayer().getPlayerDeck().getHand().get(index);
      if(cardToTrash.getName() == Card.CARD_NAME_COPPER) {
        if(context.getTurnController().tryForceInsertResourceCardIntoHand(1)) {
          context.getPlayer().getPlayerDeck().trashCardInHandAtIndex(index);
        }
      }
      else if(cardToTrash.getName() == Card.CARD_NAME_SILVER) {
        if(context.getTurnController().tryForceInsertResourceCardIntoHand(2)) {
          context.getPlayer().getPlayerDeck().trashCardInHandAtIndex(index);
        }
      }
    }
  }
}
