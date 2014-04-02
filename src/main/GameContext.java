package main;

/**
 * The game context holds the current state of the game. It undergoes a lifecycle enforced by the
 * TurnController whereby the current context is cloned, mutated, validated, and if valid becomes
 * the new current state.
 */
public class GameContext {
  private int treasureCount;
  private int actionCount;
  private int buyCount;

  /**
   * Creates a new GameContext with all fields default initialized for the first turn of a new game.
   */
  public GameContext() {
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
  }

  public int getActionCount() {
    return this.actionCount;
  } // Default 1

  public int getTreasureCount() {
    return this.treasureCount;
  } // Default 0

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



  // I'm tempted to expose all players, via something like
  // - Player opposingPlayer(); (sidenote: we assume two users, which is why
  // this isn't a list)
  // - Player thisPlayer();
  // but I want to provide strong guarantee that cards can't inadvertently
  // mutate a Player's state unsafely. We only guarantee that GameContext
  // is free to mutate, thus changing a player would be an error.
  //
  // We could totally do this by having an `isModifiable` flag on Player that
  // we set to `true` when processing a turn, and throw an exception if it
  // is violated, but that feels weird.
  //
  // Instead I think we should figure out what we may need from players and
  // expose it directly, but that will involve reading the cards to find out.
  // Offhand I'm thinking "As part of playing this card you must discard from
  // the top of your draw deck" or "trash this card after playing".

}
