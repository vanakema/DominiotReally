package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import main.SupplyDeck.CardTuple;
import main.cards.Card;

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
    this.currentTurn =
        new TurnController(players.get(0), players.get(1), supplyDeck, this.decisionDelegate);
  }

  public TurnController getCurrentTurn() {
    return this.currentTurn;
  }

  public SupplyDeck getSupplyDeck() {
    return this.supplyDeck;
  }

  public void endCurrentTurn() {
    if (!isActiveGame()) {
      currentTurn = null;
      return;
    }
    
    currentTurn.getPlayer().getPlayerDeck().discardHand();
    
    int currentPlayerIndex = players.indexOf(currentTurn.getPlayer());
    int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
    int opponentPlayerIndex = (currentPlayerIndex + 2) % players.size();

    currentTurn =
        new TurnController(players.get(nextPlayerIndex), players.get(opponentPlayerIndex),
            supplyDeck, this.decisionDelegate);
  }

  private static final Card ENDGAME_CARD = Card.makeCard(Card.CARD_NAME_PROVINCE);

  public boolean isActiveGame() {
    int numberOfEmptyPiles = 0;
    
    List<CardTuple> resourceCards = this.supplyDeck.getResourceCardRoster();
    for (CardTuple tuple : resourceCards) {
      if (ENDGAME_CARD.equals(tuple.getCard()) && tuple.getSupply() == 0) {
        return false;
      }
      
      if (tuple.getSupply() == 0) {
        numberOfEmptyPiles++;
      }
    }
    
    List<CardTuple> actionCards = this.supplyDeck.getActionCardRoster();
    for (CardTuple tuple : actionCards) {
      if (tuple.getSupply() == 0) {
        numberOfEmptyPiles++;
      }
    }
    
    return (numberOfEmptyPiles < 3);
  }
  
  public Player getWinningPlayer() {
    // Calculate the scores of all players
    List<Integer> scores = new ArrayList<>();
    for (int idx = 0; idx < players.size(); ++idx)
      scores.add(players.get(idx).getPlayerDeck().countVictoryPoints());
    
    // If the max two scores are equal, we have a tie, return null
    List<Integer> scores2 = new ArrayList<>(scores);
    Collections.sort(scores2);
    if (scores.size() >= 2 && scores2.get(0) == scores2.get(1))
      return null;
    
    // Otherwise find the index of the max score and return that player
    int maxPlayerIndex = 0;
    int maxPlayerScore = scores.get(0);
    for (int idx = 1; idx < players.size(); ++idx) {
      if (scores.get(idx) > maxPlayerScore) {
        maxPlayerIndex = idx;
        maxPlayerScore = scores.get(idx);
      }
    }
    
    return players.get(maxPlayerIndex);
  }
  
  public Player getPlayer(int index) {
    return this.players.get(index);
  }
}
