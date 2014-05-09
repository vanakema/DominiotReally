package main.cards;

import main.GameContext;

/**
 * Reveal cards from your deck until you reveal 2 Treasure cards. Put those Treasure cards in your
 * hand and discard the other revealed cards.
 * 
 * If you have to shuffle in the middle, shuffle. Don't shuffle in the revealed cards as these cards
 * do not go to the Discard pile until you have finished revealing cards. If you run out of cards
 * after shuffling and still only have one Treasure, you get just that one Treasure.
 * 
 * @author axiixc
 * 
 */
public class AdventurerCard extends ActionCard {

  protected AdventurerCard() {
    super(Card.CARD_NAME_ADVENTURER, "", 6);
  }

  @Override
  public void performAction(GameContext context) {
    super.performAction(context);

    context.getPlayer().getPlayerDeck().adventurerCard_performActionHelper();
  }

}
