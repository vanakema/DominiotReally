package main;

public class MineCard extends ActionCard {

  protected MineCard() {
    super(Card.CARD_NAME_MINE, "Trash a Treasure card from your hand. Gain a Treasure card costing up to 3 Coins more; put it into your hand.", 5);
  }

  @Override
  public void addAdditionalActions(GameContext context) {

  }

  @Override
  public void addAdditionalBuys(GameContext context) {

  }

  @Override
  public void addAdditionalCoins(GameContext context) {

  }

  @Override
  public void performAction(GameContext context) {
    //TODO: Make so it takes an index, and 
    Card oldCard;
    int handSize = context.getPlayer().getPlayerDeck().getHand().size();
    for(int i = 0 ; i<handSize ; i++) {
      oldCard = context.getPlayer().getPlayerDeck().getHand().get(i);
      if(oldCard.getName().equals(CARD_NAME_COPPER)) {
        context.getPlayer().getPlayerDeck().trashCardAtIndex(i);
//        context.getPlayer().getPlayerDeck().insertCard(context)
      }
    }

  }

}
