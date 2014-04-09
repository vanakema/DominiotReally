package main;

import java.util.List;


public class ChapelCard extends ActionCard {
  
  protected ChapelCard(String name, String description, int cost) {
    super(name, description, cost);
    
  }

  @Override
  public void addAdditionalActions(GameContext context) {
    //No Additional Actions
  }

  @Override
  public void addAdditionalBuys(GameContext context) {
    //No Additional Buys
  }

  @Override
  public void addAdditionalCoins(GameContext context) {
    //No Additional Coins
  }

  @Override
  public void performAction(GameContext context) {
    // TODO Auto-generated method stub
    List<Card> hand = context.getPlayer().getPlayerDeck().hand;
  }

}
