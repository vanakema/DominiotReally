package main.cards;

import main.GameContext;

public class ThiefCard extends ActionCard {

  public ThiefCard() {
    super(Card.CARD_NAME_THIEF, 4);
  }

  public void performAction(GameContext context) {
    super.performAction(context);

    if (context.shouldPerformMaliciousActions()) {
      Card card0 = null;
      Card card1 = null;
      if (context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().size() > 0) {
        card0 = context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().get(0);
      } else {
        context.getTurnController().getOpponent().getPlayerDeck().shuffleDeck();
        card0 = context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().get(0);
      }
      if (context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().size() > 0) {
        card1 = context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().get(1);
      }
      else {
        context.getTurnController().getOpponent().getPlayerDeck().shuffleDeck();
        card1 = context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().get(0);
      }
      if (card0.getType() == Card.CARD_TYPE_TREASURECARD
          || card1.getType() == Card.CARD_TYPE_TREASURECARD) {
        boolean choseATreasureCard = false;
        while (choseATreasureCard != true) {
          int indexToTrash = context.decideCardInOpponentDeck("Choose a TREASURE card to trash", 2);
          Card cardToTrash =
              context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck()
                  .get(indexToTrash);
          if (cardToTrash.getType() == Card.CARD_TYPE_TREASURECARD) {
            boolean keep = context.decideBoolean("Do you want to keep that card?");
            if (keep) {
              context.getTurnController().getPlayer().getPlayerDeck().addCard(cardToTrash);
            }
            context.getTurnController().getOpponent().getPlayerDeck()
                .trashCardInDeckAtIndex(indexToTrash);
            context.getTurnController().getOpponent().getPlayerDeck().discardCardInDeckAtIndex(0);
            choseATreasureCard = true;
          }
        }
      }
    }
  }
}
