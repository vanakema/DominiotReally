package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

  private static final List<Card> mockCards = Arrays.asList(new Card[] {
      Card.makeCard(Card.CARD_NAME_FESTIVAL), Card.makeCard(Card.CARD_NAME_LABORATORY),
      Card.makeCard(Card.CARD_NAME_MARKET), Card.makeCard(Card.CARD_NAME_SMITHY),
      Card.makeCard(Card.CARD_NAME_VILLAGE), Card.makeCard(Card.CARD_NAME_WOODCUTTER),
      Card.makeCard(Card.CARD_NAME_CHANCELLOR), Card.makeCard(Card.CARD_NAME_WORKSHOP)});

  private SupplyDeck supplyDeck = new SupplyDeck(mockCards);
  private List<Player> players = new ArrayList<Player>();

  private TurnController currentTurn;
  private GameContext.DecisionDelegate decisionDelegate;

  public GameController(GameContext.DecisionDelegate decisionDelegate) {
    this.players.add(new Player("Player 1"));
    this.players.add(new Player("Player 2"));

    this.decisionDelegate = decisionDelegate;    
    this.currentTurn = new TurnController(players.get(0), players.get(1), supplyDeck, this.decisionDelegate);
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
    int opponentPlayerIndex = (currentPlayerIndex + 2) % players.size();

    currentTurn = new TurnController(players.get(nextPlayerIndex), players.get(opponentPlayerIndex), supplyDeck, this.decisionDelegate);
  }
  
}
