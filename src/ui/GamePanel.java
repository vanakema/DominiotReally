package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.SupplyDeck.CardTuple;
import main.cards.Card;

public class GamePanel extends JPanel {

  public static interface Delegate {
    void userSelectedActionSupplyCardAtIndex(int index);

    void userSelectedResourceSupplyCardAtIndex(int index);

    void userSelectedCardInHandAtIndex(int index);

    void userClickedEndTurnButton();
  }

  private static final ResourceBundle uiBundle = ResourceBundle.getBundle("UI");
  
  private static final String END_TURN_BUTTON_KEY = "EndTurn";
  private static final String ACTION_COUNT_KEY = "Actions";
  private static final String BUY_COUNT_KEY = "Buys";
  private static final String COIN_COUNT_KEY = "Coin";
  
  private Delegate delegate;

  private JTextArea actionsTextArea = new JTextArea();
  private JLabel actionsLabel = new JLabel();
  private JLabel buysLabel = new JLabel();
  private JLabel coinsLabel = new JLabel();

  private JPanel actionSupplyPanel = new JPanel(new GridLayout(2, 5));
  private ActionListener actionSupplyListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      int index =
          Arrays.asList(GamePanel.this.actionSupplyPanel.getComponents())
              .indexOf(event.getSource());
      GamePanel.this.delegate.userSelectedActionSupplyCardAtIndex(index);
    }
  };

  private JPanel resourceSupplyPanel = new JPanel(new GridLayout(7, 1));
  private ActionListener resourceSupplyListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      int index =
          Arrays.asList(GamePanel.this.resourceSupplyPanel.getComponents()).indexOf(
              event.getSource());
      GamePanel.this.delegate.userSelectedResourceSupplyCardAtIndex(index);
    }
  };

  private JPanel handPanel = new JPanel();
  private ActionListener handListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      int index =
          Arrays.asList(GamePanel.this.handPanel.getComponents()).indexOf(event.getSource());
      GamePanel.this.delegate.userSelectedCardInHandAtIndex(index);
    }
  };

  GamePanel(Delegate delegate) {
    super(new BorderLayout());

    this.delegate = delegate;

    this.add(new JScrollPane(actionsTextArea), BorderLayout.CENTER);
    this.add(actionSupplyPanel, BorderLayout.NORTH);
    this.add(resourceSupplyPanel, BorderLayout.EAST);
    this.add(handPanel, BorderLayout.SOUTH);

    JPanel infoPanel = new JPanel(new GridLayout(4, 1));
    infoPanel.add(actionsLabel);
    infoPanel.add(buysLabel);
    infoPanel.add(coinsLabel);
    this.add(infoPanel, BorderLayout.WEST);

    JButton button = new JButton(uiBundle.getString(END_TURN_BUTTON_KEY));
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        GamePanel.this.delegate.userClickedEndTurnButton();
      }
    });
    infoPanel.add(button);
  }

  void addActionLine(String line) {
    actionsTextArea.append("\n" + line);
  }

  void setActionCardsInSupply(List<CardTuple> cardNames) {
    setSupplyCardsInPanel(cardNames, actionSupplyListener, actionSupplyPanel);
  }

  void setResourceCardsInSupply(List<CardTuple> cardNames) {
    setSupplyCardsInPanel(cardNames, resourceSupplyListener, resourceSupplyPanel);
  }

  void setCardsInHand(List<Card> cards) {
    handPanel.removeAll();
    
    for (Card card : cards) {
      JButton button = new JButton(card.getName());
      button.setToolTipText(card.getDescription());
      button.addActionListener(handListener);
      
      handPanel.add(button);
    }
    
    handPanel.updateUI();
  }

  private void setSupplyCardsInPanel(List<CardTuple> cards, ActionListener listener, JPanel panel) {
    panel.removeAll();

    for (CardTuple tuple : cards) {
      JButton button = new JButton(tuple.userDescription());
      button.setToolTipText(tuple.getCard().getDescription());
      button.addActionListener(listener);

      panel.add(button);
    }

    panel.updateUI();
  }

  void setNumberOfActions(int count) {
    this.actionsLabel.setText(MessageFormat.format(uiBundle.getString(ACTION_COUNT_KEY), count));
  }

  void setNumberOfBuys(int count) {
    this.buysLabel.setText(MessageFormat.format(uiBundle.getString(BUY_COUNT_KEY), count));
  }

  void setNumberOfCoins(int count) {
    this.coinsLabel.setText(MessageFormat.format(uiBundle.getString(COIN_COUNT_KEY), count));
  }
}
