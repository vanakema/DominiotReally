package main;

import java.util.List;

import main.SupplyDeck.CardTuple;

public class TurnController {

  private Player player;
  private SupplyDeck supplyDeck;
  private GameContext currentContext;

  public TurnController(Player player, SupplyDeck supplyDeck,
      GameContext.DecisionDelegate decisionDelegate) {
    this.player = player;
    this.supplyDeck = supplyDeck;

    this.currentContext = new GameContext(this);
    this.currentContext.setDecisionDelegate(decisionDelegate);
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

    this.getCurrentContext().adjustTreasureCountByDelta(-card.getCost());
    this.getCurrentContext().invalidateLumpSumTreasure();
    
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

  public boolean tryForceInsertResourceCardIntoHand(int index) {
    Card cardToBuy = supplyDeck.buyResourceCardAtIndex(index);
    if (cardToBuy == null) {
      return false;
    } else {
      this.player.getPlayerDeck().getHand().add(cardToBuy);
      return true;
    }
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
      GameContext context = new GameContext(this.currentContext);
      Card selectedCard = this.player.getPlayerDeck().getHand().get(index);
      selectedCard.performAction(context);
      context.adjustActionCountByDelta(-1);
      
      if (context.getShouldTrashCurrentCard())
        this.player.getPlayerDeck().trashCardInHandAtIndex(index);
      else
        this.player.getPlayerDeck().discardCardInHandAtIndex(index);
      
      this.currentContext = context;
      return true;
    }

    else {
      return false;
    }
  }

  /**
   * Does no checks if you can play the card, and it does not decrement any action counters. This is
   * for special action cards where this does not matter. Does not remove the card either
   * 
   * @param index
   * @return did playing card succeed
   */
  public void forcePlayingCardAtIndex(int index) {
    Card selectedCard = this.player.getPlayerDeck().getHand().get(index);
    selectedCard.performAction(this.currentContext);
  }

}
