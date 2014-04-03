package main;

public class Player {

  private String name;
  private PlayerDeck deck = new PlayerDeck();
  
  public Player(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public PlayerDeck getDeck() {
    return this.deck;
  }
  
}
