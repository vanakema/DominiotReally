package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.GameContext;
import main.GameController;
import main.SupplyDeck;
import main.SupplyDeck.CardTuple;
import main.cards.Card;

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
    if (game.getCurrentTurn() == null) {
      applicationFrame.setTitle(APPLICATION_NAME + ": GAME OVER: "
          + game.getWinningPlayer().getName() + " WINS!");
      applicationFrame.setEnabled(false);
      return;
    }

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
    panel.addActionLine(game.getCurrentTurn().getPlayer().getName() + " ended their turn.\n");
    game.endCurrentTurn();

    updateUI();
  }

  @Override
  public boolean decideBoolean(GameContext context, String question) {
    return (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(panel, question,
        "Make Decision", JOptionPane.YES_NO_OPTION));
  }

  @Override
  public int decideCardInHand(GameContext context, String question, boolean canIgnore) {
    List<String> cards = new ArrayList<String>();
    for (Card card : game.getCurrentTurn().getPlayer().getPlayerDeck().getHand())
      cards.add(String.format("%s $%d", card.getName(), card.getCost()));

    if (canIgnore)
      cards.add("Cancel");

    int result =
        JOptionPane.showOptionDialog(panel, question, "Select a Card from Hand",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, cards.toArray(), 0);
    return (canIgnore && result == cards.size() - 1) ? GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED
        : result;
  }
}
