package main.cards;


public class BoringActionCard extends ActionCard {

  public BoringActionCard(String name, String description, int cost, int numActions, int numBuys,
      int numCoins, int numCardsDraw) {
    super(name, description, cost);

    this.additionalActions = numActions;
    this.additionalBuys = numBuys;
    this.additionalCoins = numCoins;
    this.numCardsDraw = numCardsDraw;

  }

  protected static BoringActionCard makeFestival() {
    return new BoringActionCard(CARD_NAME_FESTIVAL, "Action: +2 Actions, +1 Buy, +2 Coins", 5, 2,
        1, 2, 0);
  }

  protected static BoringActionCard makeLaboratory() {
    return new BoringActionCard(CARD_NAME_LABORATORY, "Action: +2 Cards, +1 Action", 5, 1, 0, 0, 2);
  }

  protected static BoringActionCard makeMarket() {
    return new BoringActionCard(CARD_NAME_MARKET, "Action: +1 Card, +1 Action, +1 Buy, +1 Coin", 5,
        1, 1, 1, 1);
  }

  protected static BoringActionCard makeSmithy() {
    return new BoringActionCard(CARD_NAME_SMITHY, "Action: +3 Cards", 4, 0, 0, 4, 3);
  }

  protected static BoringActionCard makeVillage() {
    return new BoringActionCard(CARD_NAME_VILLAGE, "Action: +1 Card, +2 Actions", 3, 2, 0, 0, 1);
  }

  protected static BoringActionCard makeWoodcutter() {
    return new BoringActionCard(CARD_NAME_WOODCUTTER, "Action: +1 Buy, +2 Coins", 3, 0, 1, 2, 0);
  }

}
