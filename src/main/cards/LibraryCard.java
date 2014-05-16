package main.cards;

import java.util.List;

import main.GameContext;
import main.PlayerDeck;

public class LibraryCard extends ActionCard {

  protected LibraryCard() {
    super(Card.CARD_NAME_LIBRARY, 5);
  }


  @Override
  public void performAction(GameContext context) {
    super.performAction(context);

    PlayerDeck deck = context.getPlayer().getPlayerDeck();
    List<Card> currentHand = context.getPlayer().getPlayerDeck().getHand();


    while (currentHand.size() < 7) {
      Card currentCard = deck.getDrawDeck().get(0);
      if (currentCard instanceof ActionCard) {
        if (context.decideBoolean("Would you like to add " + currentCard.getName()
            + " to your hand?")) {
          deck.drawNum(1);
        } else {
          deck.discardCardInDeckAtIndex(0);
        }
      } else {
        deck.drawNum(1);
      }

    }
  }
}
