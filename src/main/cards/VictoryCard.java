package main.cards;

import main.GameContext;

public class VictoryCard extends Card {

  int victoryPointValue;

  private VictoryCard(String name, int cost, int value) {
    super(name, cost, Card.CARD_TYPE_VICTORYCARD);

    this.victoryPointValue = value;
  }

  public static VictoryCard makeEstate() {
    return new VictoryCard(CARD_NAME_ESTATE, 2, 1);
  }

  public static VictoryCard makeDuchy() {
    return new VictoryCard(CARD_NAME_DUCHY, 5, 3);
  }

  public static VictoryCard makeProvince() {
    return new VictoryCard(CARD_NAME_PROVINCE, 8, 6);
  }
  
  public static VictoryCard makeCurse() {
    return new VictoryCard(CARD_NAME_CURSE, 0, -1);
  }

  public int getVictoryPointValue() {
    return this.victoryPointValue;
  }

  @Override
  public void performAction(GameContext context) {
    // Does nothing because it doesn't affect anything.
  }

}
