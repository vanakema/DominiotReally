package main.cards;


public class BoringActionCard extends ActionCard {

  public BoringActionCard(String name, int cost, int numActions, int numBuys, int numCoins,
      int numCardsDraw) {
    super(name, cost);

    this.additionalActions = numActions;
    this.additionalBuys = numBuys;
    this.additionalCoins = numCoins;
    this.numCardsDraw = numCardsDraw;

  }

  protected static BoringActionCard makeFestival() {
    return new BoringActionCard(CARD_NAME_FESTIVAL, 5, 2, 1, 2, 0);
  }

  protected static BoringActionCard makeLaboratory() {
    return new BoringActionCard(CARD_NAME_LABORATORY, 5, 1, 0, 0, 2);
  }

  protected static BoringActionCard makeMarket() {
    return new BoringActionCard(CARD_NAME_MARKET, 5, 1, 1, 1, 1);
  }

  protected static BoringActionCard makeSmithy() {
    return new BoringActionCard(CARD_NAME_SMITHY, 4, 0, 0, 0, 3);
  }

  protected static BoringActionCard makeVillage() {
    return new BoringActionCard(CARD_NAME_VILLAGE, 3, 2, 0, 0, 1);
  }

  protected static BoringActionCard makeWoodcutter() {
    return new BoringActionCard(CARD_NAME_WOODCUTTER, 3, 0, 1, 2, 0);
  }

}
