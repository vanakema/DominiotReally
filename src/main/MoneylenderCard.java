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
    // **************************************
    // NEED REVISED -- PRE-CHOICE LOGIC BUILD
    // **************************************
    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    for(int i = 0; i< deck.hand.size(); i++){
      if(deck.hand.get(i).getName() == Card.CARD_NAME_COPPER ){
        hasCopper = true;
        deck.trashCardAtIndex(i);
        break;
      }
    }

  }

}
