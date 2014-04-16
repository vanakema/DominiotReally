package main;

import java.util.List;



public class GardensCard extends Card {

  protected GardensCard() {
    super(Card.CARD_NAME_GARDENS,
        "Worth 1 Victory for every 10 cards in your deck (rounded down).", 4,
        Card.CARD_TYPE_VICTORYCARD);
    
  }

  @Override
  public void performAction(GameContext context) {
    // No action performed. Logic performed after game 
    //in PlayerDeck's countVictoryPoints method.

  }



}
