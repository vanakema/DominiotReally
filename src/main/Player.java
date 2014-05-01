package main;

import java.util.List;

import main.cards.Card;

public class Player {

  private String name;
  private PlayerDeck playerDeck = new PlayerDeck();

  public Player(String name) {
    this.name = name;
  }
  
  public Player(String name, List<Card> playerDeck) {
    this.name = name;
    this.playerDeck = new PlayerDeck(playerDeck);
  }

  public String getName() {
    return this.name;
  }

  public PlayerDeck getPlayerDeck() {
    return this.playerDeck;
  }

}
