package main.cards;

import main.GameContext;
import main.GameContext.DecisionDelegate;

public class ThroneRoomCard extends ActionCard {

  public ThroneRoomCard() {
    super(Card.CARD_NAME_THRONE_ROOM, "Choose an Action card in your hand. Play it twice.", 4);
  }
  
  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    int index = context.decideCardInHand("What card do you want to play twice?", true);
    if (index != GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED) {
      context.getTurnController().forcePlayingCardAtIndex(index);
      context.getTurnController().forcePlayingCardAtIndex(index);
      context.getPlayer().getPlayerDeck().discardCardInHandAtIndex(index);
    }
    
  }

}
