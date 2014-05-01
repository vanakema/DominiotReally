package test;

import java.util.Arrays;
import java.util.List;

import main.GameContext;
import main.Player;
import main.SupplyDeck;
import main.TurnController;
import main.cards.Card;
import main.cards.SpyCard;
import junit.framework.TestCase;

public class SpyTest extends TestCase{

  Player player;
  List<Card> hand;
  TurnController controller;
  SupplyDeck supplyDeck;
  GameContext context;
  SpyCard card = (SpyCard) Card.makeCard(Card.CARD_NAME_SPY);
  
  @Override
  protected void setUp() {
    player = new Player("Test Player");
    
    List<Card> cards = Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
    Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
    Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
    Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    
    supplyDeck = new SupplyDeck(cards);
    controller = new TurnController(player, null, supplyDeck, null);
    context = new GameContext(controller);
  }
}
