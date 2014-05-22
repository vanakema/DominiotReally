package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import main.GameContext;
import main.Player;
import main.PlayerDeck;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;

import org.junit.Before;
import org.junit.Test;


public class ThiefCardTest {
  Player player;
  Player opponent;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  Card card = Card.makeCard(Card.CARD_NAME_THIEF);

  @Before
  public void setUp() throws Exception {
    List<Card> playerDeckCards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_GOLD),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_GOLD),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_GOLD)});
    List<Card> opponentDeckCards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_COPPER),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_GOLD),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_GOLD),
            Card.makeCard(Card.CARD_NAME_COPPER), Card.makeCard(Card.CARD_NAME_GOLD)});

    player = new Player("Test Player", playerDeckCards);
    opponent = new Player("Test Opponent", opponentDeckCards);

    List<Card> cards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
            Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
            Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
            Card.makeCard(Card.CARD_NAME_WOODCUTTER)});

    supplyDeck = new SupplyDeck(cards);
    controller = new TurnController(player, opponent, supplyDeck, null);
    context = new GameContext(controller);
  }

  @Test
  public void testName() {
    assertEquals(Card.CARD_NAME_THIEF, card.getName());
  }

   @Test
   public void testCost() {
   assertEquals(4, card.getCost());
   }

  @Test
  public void testPerformActionPlayer() {
    context.setDecisionDelegate(new TestDecisionDelegate() {
      @Override
      public int decideCardInDeck(GameContext context, PlayerDeck deck, String question,
          int numberOfCards) {
        return 1;
      }

      @Override
      public boolean decideBoolean(GameContext context, String question) {
        return true;
      }
    });

    int oldSizeOfPlayerDiscardPile = context.getPlayer().getPlayerDeck().getDiscardDeck().size();
    int oldSizeOfOpponentDrawDeck =
        context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().size();

    card.performAction(context);

    int newSizeOfPlayerDiscardPile = context.getPlayer().getPlayerDeck().getDiscardDeck().size();
    int newSizeOfOpponentHand =
        context.getTurnController().getOpponent().getPlayerDeck().getDrawDeck().size();

    assertEquals(oldSizeOfPlayerDiscardPile + 1, newSizeOfPlayerDiscardPile);

  }


}
