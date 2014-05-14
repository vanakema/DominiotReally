package main.cards;

import main.GameContext;

public class MoatCard extends ActionCard {

  public MoatCard() {
    super(
        Card.CARD_NAME_MOAT,
        "When another player plays an Attack card, you may reveal this from your hand. If you do, you are unaffected by that Attack.",
        2);
    this.numCardsDraw = 2;
  }

}
