package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;

public class WitchCardTest extends TestCase {
  
  Player player;
  Player opponent;
  TurnController turnController;
  GameContext context;
  
  @Override
  public void setUp() {
    player = new Player("Player");
    opponent = new Player("Opponent");
    
    SupplyDeck supplyDeck = new SupplyDeck(Arrays.asList(new Card[]{ Card.makeCard(Card.CARD_NAME_WITCH) }));
    turnController = new TurnController(player, opponent, supplyDeck, null);
    
    context = new GameContext(turnController);
  }
  
  public void testDoesCurseOpponent() {
    List<Card> existingCards = new ArrayList<>(opponent.getPlayerDeck().getDiscardDeck());
    
    Card witch = Card.makeCard(Card.CARD_NAME_WITCH);
    witch.performAction(context);
    
    List<Card> afterCards = new ArrayList<>(opponent.getPlayerDeck().getDiscardDeck());
    for (Card card : existingCards)
      afterCards.remove(card);
    
    assertEquals(1, afterCards.size());
    assertEquals(Card.makeCard(Card.CARD_NAME_CURSE), afterCards.get(0));
  }

}
