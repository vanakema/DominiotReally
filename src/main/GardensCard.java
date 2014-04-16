package main;

import java.util.List;

public class GardensCard extends ActionCard {

  protected GardensCard() {
    super(Card.CARD_NAME_GARDENS,
        "Worth 1 Victory for every 10 cards in your deck (rounded down).", 4);
  }

}
