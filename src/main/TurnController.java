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
    return false;
  }

}
