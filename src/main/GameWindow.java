package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import main.SupplyDeck.CardTuple;

public class GameWindow implements GamePanel.Delegate {

  public static void main(String[] args) {
    new GameWindow();
  }

  private static final String APPLICATION_NAME = "Dominion";

  private JFrame applicationFrame;
  private GamePanel panel;
  private GameController game;

  public GameWindow() {
    panel = new GamePanel(this);
    panel.addActionLine("Game Started!");
    
    game = new GameController();

    applicationFrame = new JFrame(APPLICATION_NAME);
    applicationFrame.setContentPane(panel);
    applicationFrame.setBounds(0, 0, 600, 600);
    applicationFrame.setVisible(true);

    updateUI();
  }

  private List<String> namesForCardTuples(List<CardTuple> tuples) {
    List<String> names = new ArrayList<String>();
    for (CardTuple tuple : tuples)
      names.add(tuple.userDescription());
    
    return names;
  }
  
  private void updateUI() {
    applicationFrame.setTitle(APPLICATION_NAME + ": " + game.getCurrentTurn().getPlayer().getName());
    
    SupplyDeck supplyDeck = game.getSupplyDeck();
    panel.setActionCardsInSupply(namesForCardTuples(supplyDeck.getActionCardRoster()));
    panel.setResourceCardsInSupply(namesForCardTuples(supplyDeck.getResourceCardRoster()));
    
    List<String> handCardRoster = new ArrayList<String>();
    List<Card> handCards = game.getCurrentTurn().getPlayer().getDeck().getHand();
    for (Card card : handCards)
      handCardRoster.add(card.getName());
    panel.setCardsInHand(handCardRoster);
    
    GameContext context = game.getCurrentTurn().getCurrentContext();
    panel.setNumberOfActions(context.getActionCount());
    panel.setNumberOfBuys(context.getBuyCount());
    panel.setNumberOfCoins(context.getTreasureCount());
  }

  @Override
  public void userSelectedActionSupplyCardAtIndex(int index) {
    panel.addActionLine("Bought action card at index " + index);

    updateUI();
  }

  @Override
  public void userSelectedResourceSupplyCardAtIndex(int index) {
    panel.addActionLine("Bought resource card at index " + index);
    
    updateUI();
  }

  @Override
  public void userSelectedCardInHandAtIndex(int index) {
    panel.addActionLine("Play card " + index);
    
    updateUI();
  }

  @Override
  public void userClickedEndTurnButton() {
    panel.addActionLine(game.getCurrentTurn().getPlayer().getName() + " ended their turn.");
    panel.addActionLine("");
    
    game.endCurrentTurn();
    
    updateUI();
  }
}
