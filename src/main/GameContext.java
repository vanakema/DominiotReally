package main;

/**
 * The game context holds the current state of the game. It undergoes a lifecycle enforced by the
 * TurnController whereby the current context is cloned, mutated, validated, and if valid becomes
 * the new current state.
 */
public class GameContext {

  public static interface DecisionDelegate {
    public boolean getDecision(GameContext context, String question);
  }
  
  private int treasureCount;
  private int actionCount;
  private int buyCount;

  private TurnController turnController;
  private DecisionDelegate decisionDelegate;

  /**
   * Creates a new GameContext with all fields default initialized for the first turn of a new game.
   */
  public GameContext(TurnController turnController) {
    this();
    this.turnController = turnController;
  }

  public GameContext(){
    this.treasureCount = 0;
    this.actionCount = 1;
    this.buyCount = 1;
  }
  
  /**
   * Clone constructor. Creates a new GameContext that is a duplicate of the argument. For possible
   * undoing up a card, which may or may not be implemented.
   */
  public GameContext(GameContext context) {
    this.treasureCount = context.treasureCount;
    this.actionCount = context.actionCount;
    this.buyCount = context.buyCount;
    this.turnController = context.turnController;
    this.decisionDelegate = context.decisionDelegate;
  }

  public int getActionCount() {
    return this.actionCount;
  }

  public int getTreasureCount() {
    return this.treasureCount;
  }

  public int getBuyCount() {
    return this.buyCount;
  }

  public void adjustActionCountByDelta(int delta) {
    this.actionCount += delta;
  }

  public void adjustTreasureCountByDelta(int delta) {
    this.treasureCount += delta;
  }

  public void adjustBuyCountByDelta(int delta) {
    this.buyCount += delta;
  }
  
  public Player getPlayer() {
    return this.turnController.getPlayer();
  }

  public TurnController getTurnController() {
    return this.turnController;
  }
  
  public DecisionDelegate getDecisionDelegate() {
    return this.decisionDelegate;
  }
  
  public void setDecisionDelegate(DecisionDelegate decisionDelegate) {
    this.decisionDelegate = decisionDelegate;
  }
  
  public boolean getDecision(String question) {
    if (decisionDelegate == null)
      throw new RuntimeException("Attempting to make a decision without a decision delegate");
    
    return decisionDelegate.getDecision(this, question);
  }

}
