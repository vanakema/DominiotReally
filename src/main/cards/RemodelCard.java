package main.cards;

import main.GameContext;
import main.PlayerDeck;

public class RemodelCard extends ActionCard {

  public RemodelCard() {
    super(Card.CARD_NAME_REMODEL, "Trash a card from your hand. Gain a card costing up to 2 Gold more than the trashed card.", 0);
  }
  
  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    int index = context.decideCardInHand("Trash a card from your hand and recieve one worth its cost +2", false);
    
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    Card card = deck.getHand().get(index);
    deck.trashCardInHandAtIndex(index);
    
    context.setLumpSumTreasureCount(card.getCost() + 2);
  }
  
}
