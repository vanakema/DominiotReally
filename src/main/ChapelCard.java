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
    // **************************************
    // NEED REVISED -- PRE-CHOICE LOGIC BUILD
    // **************************************
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    deck.trashCardInHandAtIndex(0);
  }

}
