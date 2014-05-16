package main.cards;

import main.GameContext;
import main.PlayerDeck;

public class MoneylenderCard extends ActionCard {

  protected MoneylenderCard() {
    super(Card.CARD_NAME_MONEYLENDER, 4);
  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    boolean hasCopper = false;
    for (int i = 0; i < deck.getHand().size(); i++) {
      if (deck.getHand().get(i).getName() == Card.CARD_NAME_COPPER) {
        hasCopper = true;
        deck.trashCardInHandAtIndex(i);
        break;
      }
    }
    if (hasCopper) {
      context.adjustTreasureCountByDelta(3);
    }

  }

}
