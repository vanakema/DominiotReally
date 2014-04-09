package main;

public class CellarCard extends ActionCard {



  protected CellarCard() {
    super(Card.CARD_NAME_CELLAR,
        "+1 Action. Discard any number of cards. 1+ Card per card discarded.", 2);

  }

  @Override
  public void addAdditionalActions(GameContext context) {
    context.adjustActionCountByDelta(1);

  }

  @Override
  public void addAdditionalBuys(GameContext context) {
    // No additional buys
  }

  @Override
  public void addAdditionalCoins(GameContext context) {
    // No additional Coins
  }

  @Override
  public void performAction(GameContext context) {
    // **************************************
    // NEED REVISED -- PRE-CHOICE LOGIC BUILD
    // **************************************
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    deck.discardCardAtIndex(0);
    deck.drawNum(1);
  }

}
