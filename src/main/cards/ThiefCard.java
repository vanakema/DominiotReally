package main.cards;

import main.GameContext;

public class ThiefCard extends ActionCard {

  public ThiefCard() {
    super(
        Card.CARD_NAME_THIEF,
        "Each other player reveals the top 2 cards of his deck. If they revealed any Treasure cards, they trash one of them that you choose. You may gain any or all of these trashed cards. They discard the other revealed cards.",
        0);
  }

  public void performAction(GameContext context) {
    super.performAction(context);
    Card card0 = context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().get(0);
    Card card1 = context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().get(1);
    if (card0.getType() == Card.CARD_TYPE_TREASURECARD
        || card1.getType() == Card.CARD_TYPE_TREASURECARD) {
      boolean choseATreasureCard = false;
      while (choseATreasureCard != true) {
        int indexToTrash = context.decideCardInOpponentDeck("Choose a TREASURE card to trash", 2);
        if (context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck()
            .get(indexToTrash).getType() == Card.CARD_TYPE_TREASURECARD) {
          context.getTurnController().getOpponent().getPlayerDeck()
              .trashCardInDeckAtIndex(indexToTrash);
          choseATreasureCard = true;
        }
      }
    }
  }

}
