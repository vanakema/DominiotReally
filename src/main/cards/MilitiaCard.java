package main.cards;

import main.GameContext;
import main.PlayerDeck;

public class MilitiaCard extends ActionCard {
  
  protected MilitiaCard() {
    super(Card.CARD_NAME_MILITIA, "Each other player discards down to 3 cards in his hand.", 2);
    
    this.additionalCoins = 2;
  }
  
  static final int NUMBER_OF_CARDS_TO_DISCARD_TO = 3;

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    
    PlayerDeck playerDeck = context.getPlayer().getPlayerDeck();
    
    // Add one extra to account for `this` card
    while (playerDeck.getHand().size() > NUMBER_OF_CARDS_TO_DISCARD_TO + 1) {
      int index = context.decideCardInHand("Discard down to three cards", false);
      
      // Haha, very funny, but you can't discard this one
      if (playerDeck.getHand().get(index) == this)
        continue;
      
      playerDeck.discardCardInHandAtIndex(index);
    }
    
    PlayerDeck opponentDeck = context.getTurnController().getOpponent().getPlayerDeck();
    while (opponentDeck.getHand().size() > NUMBER_OF_CARDS_TO_DISCARD_TO) {
      int index = context.decideCardInOpponentHand("Discard down to three cards", false);
      opponentDeck.discardCardInHandAtIndex(index);
    }
    
  }

}
