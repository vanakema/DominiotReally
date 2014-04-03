package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GamePanel extends JPanel {

  public static interface Delegate {
    void userSelectedActionSupplyCardAtIndex(int index);

    void userSelectedResourceSupplyCardAtIndex(int index);

    void userSelectedCardInHandAtIndex(int index);

    void userClickedEndTurnButton();
  }

  private Delegate delegate;

  private JTextArea actionsTextArea = new JTextArea();
  private JLabel actionsLabel = new JLabel();
  private JLabel buysLabel = new JLabel();
  private JLabel coinsLabel = new JLabel();

  private JPanel actionSupplyPanel = new JPanel(new GridLayout(2, 5));
  private ActionListener actionSupplyListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      int index = Arrays.asList(GamePanel.this.actionSupplyPanel.getComponents()).indexOf(event.getSource());
      GamePanel.this.delegate.userSelectedActionSupplyCardAtIndex(index);
    }
  };

  private JPanel resourceSupplyPanel = new JPanel(new GridLayout(7, 1));
  private ActionListener resourceSupplyListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      int index = Arrays.asList(GamePanel.this.resourceSupplyPanel.getComponents()).indexOf(event.getSource());
      GamePanel.this.delegate.userSelectedResourceSupplyCardAtIndex(index);
    }
  };

  private JPanel handPanel = new JPanel();
  private ActionListener handListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      int index = Arrays.asList(GamePanel.this.handPanel.getComponents()).indexOf(event.getSource());
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

    JButton button = new JButton("End Turn");
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

  void setActionCardsInSupply(List<String> cardNames) {
    setSupplyCardsInPanel(cardNames, actionSupplyListener, actionSupplyPanel);
  }

  void setResourceCardsInSupply(List<String> cardNames) {
    setSupplyCardsInPanel(cardNames, resourceSupplyListener, resourceSupplyPanel);
  }
  
  void setCardsInHand(List<String> cardNames) {
    setSupplyCardsInPanel(cardNames, handListener, handPanel);
  }

  private void setSupplyCardsInPanel(List<String> cardNames, ActionListener listener, JPanel panel) {
    panel.removeAll();

    for (String name : cardNames) {
      JButton button = new JButton(name);
      button.addActionListener(listener);

      panel.add(button);
    }

    panel.revalidate();
    this.doLayout();
  }

  void setNumberOfActions(int count) {
    this.actionsLabel.setText("Actions: " + count);
  }

  void setNumberOfBuys(int count) {
    this.buysLabel.setText("Buys " + count);
  }

  void setNumberOfCoins(int count) {
    this.coinsLabel.setText("Coin: " + count);
  }
}
