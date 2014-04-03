package main;

public class TurnController {

  private Player player;
  private GameContext currentContext;

  public TurnController(Player player) {
    this.player = player;
    this.currentContext = new GameContext();
  }

  public Player getPlayer() {
    return this.player;
  }

  public GameContext getCurrentContext() {
    return this.currentContext;
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
