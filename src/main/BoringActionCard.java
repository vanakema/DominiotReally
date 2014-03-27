package main;

public class BoringActionCard extends ActionCard {

	public BoringActionCard(String name, String description, int cost,
			int numActions, int numBuys, int numCoins, int numCardsDraw) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.additionalActions = numActions;
		this.additionalBuys = numBuys;
		this.additionalCoins = numCoins;
		this.numCardsDraw = numCardsDraw;

	}

	public static BoringActionCard makeFestival() {
		return new BoringActionCard("Festival",
				"Action: +2 Actions, +1 Buy, +2 Coins", 5, 2, 1, 2, 0);
	}

	public static BoringActionCard makeLaboratory() {
		return new BoringActionCard("Laboratory",
				"Action: +2 Cards, +1 Action", 5, 1, 0, 0, 2);
	}

	public static BoringActionCard makeMarket() {
		return new BoringActionCard("Market",
				"Action: +1 Card, +1 Action, +1 Buy, +1 Coin", 5, 1, 1, 1, 1);
	}

	public static BoringActionCard makeSmithy() {
		return new BoringActionCard("Smithy", "Action: +3 Cards", 4, 0, 0, 4, 3);
	}

	public static BoringActionCard makeVillage() {
		return new BoringActionCard("Village", "Action: +1 Card, +2 Actions",
				3, 2, 0, 0, 1);
	}

	public static BoringActionCard makeWoodcutter() {
		return new BoringActionCard("Woodcutter", "Action: +1 Buy, +2 Coins",
				3, 0, 1, 2, 0);
	}

	@Override
	public void addAdditionalActions(GameContext context) {
		context.adjustActionCountByDelta(context.getActionCount());

	}

	@Override
	public void addAdditionalBuys(GameContext context) {
		context.adjustActionCountByDelta(this.additionalActions);
	}

	@Override
	public void addAdditionalCoins(GameContext context) {
		context.adjustTreasureCountByDelta(cost);

	}

	@Override
	public void drawNumOfCards() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

}
