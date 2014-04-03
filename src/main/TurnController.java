package main;

import java.util.List;

import main.SupplyDeck.CardTuple;

public class TurnController {

  private Player player;
  private SupplyDeck supplyDeck;
  private GameContext currentContext;

  public TurnController(Player player, SupplyDeck supplyDeck) {
    this.player = player;
    this.supplyDeck = supplyDeck;
    this.currentContext = new GameContext();
  }

  public Player getPlayer() {
    return this.player;
  }

  public GameContext getCurrentContext() {
    return this.currentContext;
  }
  
  private boolean canBuyCardAtIndexInRoster(int index, List<CardTuple> roster) {
    return this.getCurrentContext().getTreasureCount() >= roster.get(index).getCard().getCost();
  }
  
  private boolean tryPurchaseCardHelper(Card card) {
    if (card == null)
      return false;
    
    this.getCurrentContext().adjustTreasureCountByDelta(- card.getCost());
    player.getPlayerDeck().addCard(card);
    return true;
  }

  public boolean tryPurchaseActionCardAtIndex(int index) {
    if (!canBuyCardAtIndexInRoster(index, supplyDeck.getActionCardRoster()))
      return false;
    
    return tryPurchaseCardHelper(supplyDeck.buyActionCardAtIndex(index));
  }
  
  public boolean tryPurchaseResourceCardAtIndex(int index) {
    if (!canBuyCardAtIndexInRoster(index, supplyDeck.getResourceCardRoster()))
      return false;
    
    return tryPurchaseCardHelper(supplyDeck.buyResourceCardAtIndex(index));
  }
  
  /**
   * Check if there are still actions left, get card at `index` from player's hand, perform action
   * of card on context, decrement context values correctly.
   * 
   * @param index
   * @return did playing card succeed
   */
  public boolean tryPlayingCardAtIndex(int index) {
    if (this.currentContext.getActionCount() > 0) {
      Card selectedCard = this.player.getPlayerDeck().getHand().get(index);
      selectedCard.performAction(this.currentContext);
      this.currentContext.adjustActionCountByDelta(-1);
      return true;
    }

    else {
      return false;
    }
  }

}
