package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

  private static final List<Card> mockCards = Arrays.asList(new Card[] {
      Card.makeCard(Card.CARD_NAME_FESTIVAL), Card.makeCard(Card.CARD_NAME_LABORATORY),
      Card.makeCard(Card.CARD_NAME_MARKET), Card.makeCard(Card.CARD_NAME_SMITHY),
      Card.makeCard(Card.CARD_NAME_VILLAGE), Card.makeCard(Card.CARD_NAME_WOODCUTTER)});

  private SupplyDeck supplyDeck = new SupplyDeck(mockCards);
  private List<Player> players = new ArrayList<Player>();

  private TurnController currentTurn;
  
  public GameController() {
    players.add(new Player("Player 1"));
    players.add(new Player("Player 2"));
    
    currentTurn = new TurnController(players.get(0));
  }

  public TurnController getCurrentTurn() {
    return this.currentTurn;
  }
  
  public SupplyDeck getSupplyDeck() {
    return this.supplyDeck;
  }
  
  public void endCurrentTurn() {
    int currentPlayerIndex = players.indexOf(currentTurn.getPlayer());
    int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
    
    currentTurn = new TurnController(players.get(nextPlayerIndex));
  }
  
}
