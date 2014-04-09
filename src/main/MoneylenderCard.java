package main;

public class MoneylenderCard extends ActionCard {

  private boolean hasCopper;
  protected MoneylenderCard() {
    super(Card.CARD_NAME_MONEYLENDER, "Trash a Copper from your hand. If you do, +$3.", 4);
    hasCopper = false;
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
    if(hasCopper){
      context.adjustBuyCountByDelta(3);
    }

  }

  @Override
  public void performAction(GameContext context) {
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    

  }

}
