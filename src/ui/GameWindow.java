package ui;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.GameContext;
import main.GameController;
import main.PlayerDeck;
import main.PlayerDeck.PlayerDeckType;
import main.SupplyDeck;
import main.SupplyDeck.CardTuple;

public class GameWindow implements GamePanel.Delegate, GameContext.DecisionDelegate {
  
  private static final ResourceBundle uiBundle = ResourceBundle.getBundle("UI");
  
  private static final String GAME_STARTED_KEY = "GameStarted";
  private static final String GAME_OVER_KEY = "GameOver";
  private static final String CANNOT_BUY_CARD_KEY = "CannotBuyCard";
  private static final String BUY_CARD_KEY = "BuyCard";
  private static final String PLAYER_ENDED_TURN_KEY = "PlayerEndedTurn";
  private static final String PLAYER_PLAYED_CARD_KEY = "PlayerPlayedCard";
  private static final String PROMPT_SELECT_CARD_FROM_HAND_KEY = "PromptSelectCardFromHand";
  private static final String PROMPT_MAKE_DECISION_KEY = "MakeDecision";
  private static final String PROMPT_CANCEL_KEY = "PromptCancel";
  
  public static void main(String[] args) {
    new GameWindow();
  }

  private static final String APPLICATION_NAME = "Dominion";

  private JFrame applicationFrame;
  private GamePanel panel;
  private GameController game;

  public GameWindow() {
    panel = new GamePanel(this);
    panel.addActionLine(uiBundle.getString(GAME_STARTED_KEY));

    game = new GameController(this);

    applicationFrame = new JFrame(APPLICATION_NAME);
    applicationFrame.setContentPane(panel);
    applicationFrame.setBounds(0, 0, 600, 600);
    applicationFrame.setVisible(true);

    updateUI();
  }

  private void updateUI() {
    if (game.getCurrentTurn() == null) {
      applicationFrame.setTitle(MessageFormat.format(uiBundle.getString(GAME_OVER_KEY), APPLICATION_NAME, game.getWinningPlayer().getName()));
      applicationFrame.setEnabled(false);
      return;
    }

    applicationFrame
        .setTitle(APPLICATION_NAME + ": " + game.getCurrentTurn().getPlayer().getName());

    SupplyDeck supplyDeck = game.getSupplyDeck();
    panel.setActionCardsInSupply(supplyDeck.getActionCardRoster());
    panel.setResourceCardsInSupply(supplyDeck.getResourceCardRoster());
    panel.setCardsInHand(game.getCurrentTurn().getPlayer().getPlayerDeck().getHandWithoutRedrawing());

    GameContext context = game.getCurrentTurn().getCurrentContext();
    panel.setNumberOfActions(context.getActionCount());
    panel.setNumberOfBuys(context.getBuyCount());
    panel.setNumberOfCoins(context.getTreasureCount());
  }

  @Override
  public void userSelectedActionSupplyCardAtIndex(int index) {
    if (game.getCurrentTurn().tryPurchaseActionCardAtIndex(index)) {
      CardTuple tuple = game.getSupplyDeck().getActionCardRoster().get(index);
      panel.addActionLine(MessageFormat.format(uiBundle.getString(BUY_CARD_KEY), tuple.getCard().getName()));
    } else {
      panel.addActionLine(uiBundle.getString(CANNOT_BUY_CARD_KEY));
    }

    updateUI();
  }

  @Override
  public void userSelectedResourceSupplyCardAtIndex(int index) {
    if (game.getCurrentTurn().tryPurchaseResourceCardAtIndex(index)) {
      CardTuple tuple = game.getSupplyDeck().getResourceCardRoster().get(index);
      panel.addActionLine(MessageFormat.format(uiBundle.getString(BUY_CARD_KEY), tuple.getCard().getName()));
    } else {
      panel.addActionLine(uiBundle.getString(CANNOT_BUY_CARD_KEY));
    }

    updateUI();
  }

  @Override
  public void userSelectedCardInHandAtIndex(int index) {
    if (game.getCurrentTurn().tryPlayingCardAtIndex(index)) {
      panel.addActionLine(MessageFormat.format(uiBundle.getString(PLAYER_PLAYED_CARD_KEY), index));
    }

    updateUI();
  }

  @Override
  public void userClickedEndTurnButton() {
    String playerName = game.getCurrentTurn().getPlayer().getName();
    panel.addActionLine(MessageFormat.format(uiBundle.getString(PLAYER_ENDED_TURN_KEY), playerName));
    panel.addActionLine("");
    game.endCurrentTurn();

    updateUI();
  }

  @Override
  public boolean decideBoolean(GameContext context, String question) {
    return (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(panel, question,
        uiBundle.getString(PROMPT_MAKE_DECISION_KEY), JOptionPane.YES_NO_OPTION));
  }

  @Override
  public int decideCardInHand(GameContext context, PlayerDeck deck, String question,
      boolean canIgnore) {
    List<String> cards = deck.getCardDescriptions(PlayerDeckType.HAND, Integer.MAX_VALUE);
    if (canIgnore)
      cards.add(uiBundle.getString(PROMPT_CANCEL_KEY));

    int result =
        JOptionPane.showOptionDialog(panel, question, uiBundle.getString(PROMPT_SELECT_CARD_FROM_HAND_KEY),
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, cards.toArray(), 0);
    return (canIgnore && result == cards.size() - 1) ? GameContext.DecisionDelegate.CARD_IN_HAND_IGNORED
        : result;
  }

  @Override
  public boolean decideCardInDeck(GameContext context, PlayerDeck deck, String question) {
    List<String> cardDescriptions = deck.getCardDescriptions(PlayerDeckType.DRAW, 1);

    return (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(panel, question,
        cardDescriptions.get(0), JOptionPane.YES_NO_OPTION));
  }

  @Override
  public int decideCardInDeck(GameContext context, PlayerDeck deck, String question,
      int numberOfCards) {
    List<String> cardDescriptions = deck.getCardDescriptions(PlayerDeckType.DRAW, numberOfCards);

    return JOptionPane.showOptionDialog(panel, question, uiBundle.getString(PROMPT_SELECT_CARD_FROM_HAND_KEY),
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, cardDescriptions.toArray(),
        0);
  }
}
