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
  
}
