package main;


public class ChapelCard extends ActionCard {

  protected ChapelCard() {
    super(Card.CARD_NAME_CHAPEL, "Trash up to 4 cards from your hand.", 2);

  }

  @Override
  public void addAdditionalActions(GameContext context) {
    // No Additional Actions
  }

  @Override
  public void addAdditionalBuys(GameContext context) {
    // No Additional Buys
  }

  @Override
  public void addAdditionalCoins(GameContext context) {
    // No Additional Coins
  }

  @Override
  public void performAction(GameContext context) {
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    for (int trashesRemaining = 4; trashesRemaining > 0; trashesRemaining--) {
      int index = context.decideCardInHand("Trash a card from your hand?", true);
      if (index != GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED) {
        deck.trashCardAtIndex(index);
      } else {
        break;
      }
    }
  }

}
