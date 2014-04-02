/**
 * 
 */
package main;

/**
 * @author vanakema
 * 
 */
public abstract class ActionCard extends Card {

	protected ActionCard(String name, String description, int cost) {
		super(name, description, cost);
	}

	public int additionalActions = 0;
	public int additionalBuys = 0;
	public int additionalCoins = 0;
	public int numCardsDraw = 0;

	public abstract void addAdditionalActions(GameContext context);

	public abstract void addAdditionalBuys(GameContext context);

	public abstract void addAdditionalCoins(GameContext context);

	public abstract void drawNumOfCards();

}
