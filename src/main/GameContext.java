package main;

/**
 * The game context holds the current state of the game. It undergoes a
 * lifecycle enforced by the TurnController whereby the current context is
 * cloned, mutated, validated, and if valid becomes the new current state.
 */
class GameContext {
	private int treasureCount;

	/**
	 * Creates a new GameContext with all fields default initialized for the
	 * first turn of a new game.
	 */
	GameContext() {
	}

	/**
	 * Clone constructor. Creates a new GameContext that is a duplicate of the
	 * argument.
	 */
	GameContext(GameContext context) {
	}

	public int getActionCount() {
		return 0;
	} // Default 1

	public void adjustActionCountByDelta(int delta) {
	}

	public int getTreasureCount() {
		return 0;
	} // Default 0

	public void adjustTreasureCountByDelta(int delta) {
		this.treasureCount += delta;
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

	/**
	 * Returns whether this GameContext is valid to be commited.
	 */
	public boolean isValid() {
		return false;
	}
}
