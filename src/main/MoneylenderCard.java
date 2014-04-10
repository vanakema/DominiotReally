package main;

public class MoneylenderCard extends ActionCard {

  public MoneylenderCard() {
    super(Card.CARD_NAME_MONEYLENDER, "Trash a Copper from your hand. If you do, +$3.", 4);
  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    boolean hasCopper = false;
    for (int i = 0; i < deck.hand.size(); i++) {
      if (deck.hand.get(i).getName() == Card.CARD_NAME_COPPER) {
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
