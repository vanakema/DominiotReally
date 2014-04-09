package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Card;
import main.GameContext;
import main.GameController;
import main.SupplyDeck;
import main.SupplyDeck.CardTuple;

public class GameWindow implements GamePanel.Delegate, GameContext.DecisionDelegate {

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

    game = new GameController(this);

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
    applicationFrame
        .setTitle(APPLICATION_NAME + ": " + game.getCurrentTurn().getPlayer().getName());

    SupplyDeck supplyDeck = game.getSupplyDeck();
    panel.setActionCardsInSupply(namesForCardTuples(supplyDeck.getActionCardRoster()));
    panel.setResourceCardsInSupply(namesForCardTuples(supplyDeck.getResourceCardRoster()));

    List<String> handCardRoster = new ArrayList<String>();
    List<Card> handCards = game.getCurrentTurn().getPlayer().getPlayerDeck().getHand();
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
    if (game.getCurrentTurn().tryPurchaseActionCardAtIndex(index)) {
      CardTuple tuple = game.getSupplyDeck().getActionCardRoster().get(index);
      panel.addActionLine("Bought one " + tuple.getCard().getName() + ".");
    } else {
      panel.addActionLine("Cannot buy that card.");
    }

    updateUI();
  }

  @Override
  public void userSelectedResourceSupplyCardAtIndex(int index) {
    if (game.getCurrentTurn().tryPurchaseResourceCardAtIndex(index)) {
      CardTuple tuple = game.getSupplyDeck().getResourceCardRoster().get(index);
      panel.addActionLine("Bought one " + tuple.getCard().getName() + ".");
    } else {
      panel.addActionLine("Cannot buy that card.");
    }

    updateUI();
  }

  @Override
  public void userSelectedCardInHandAtIndex(int index) {
    if (game.getCurrentTurn().tryPlayingCardAtIndex(index)) {
      panel.addActionLine("Play card " + index);
    }
    
    updateUI();
  }

  @Override
  public void userClickedEndTurnButton() {
    panel.addActionLine(game.getCurrentTurn().getPlayer().getName() + " ended their turn.");
    panel.addActionLine("");

    game.endCurrentTurn();

    updateUI();
  }

  @Override
  public boolean getDecision(GameContext context, String question) {
    return (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, question, "Make Decision", JOptionPane.YES_NO_OPTION));
  }
}
