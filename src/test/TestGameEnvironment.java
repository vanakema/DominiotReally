package test;

import java.util.Arrays;
import java.util.List;

import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;

public class TestGameEnvironment {

  Player player;
  Player opponent;
  TurnController turnController;
  SupplyDeck supplyDeck;
  GameContext context;
  
  public TestGameEnvironment() {
    player = new Player("Test Player");
    opponent = new Player("Test Opponent");
    List<Card> cards = Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
        Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
        Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
        Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    supplyDeck = new SupplyDeck(cards);
  
    turnController = new TurnController(player, opponent, supplyDeck, null);
    context = new GameContext(turnController);
  }
  
}
