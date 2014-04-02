package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class GameController implements GamePanel.Delegate {

  public static void main(String[] args) {
    new GameController();
  }

  private static final String APPLICATION_NAME = "Dominion";

  private JFrame applicationFrame;
  private GamePanel panel;

  private PlayerDeck playerDeck = new PlayerDeck();
  private SupplyDeck supplyDeck;

  public GameController() {
    panel = new GamePanel(this);
    panel.setCardsInHand(Arrays.asList(new String[] {"One", "Two", "Three", "Four", "Five"}));

    List<Card> cards =
        Arrays.asList(new Card[] {Card.makeCard(Card.CARD_NAME_FESTIVAL),
            Card.makeCard(Card.CARD_NAME_LABORATORY), Card.makeCard(Card.CARD_NAME_MARKET),
            Card.makeCard(Card.CARD_NAME_SMITHY), Card.makeCard(Card.CARD_NAME_VILLAGE),
            Card.makeCard(Card.CARD_NAME_WOODCUTTER)});
    supplyDeck = new SupplyDeck(cards);

    String[] names =
        new String[] {"Copper (10)", "Silver (10)", "Gold (10)", "Estate (12)", "Duchy (14)", "Province (14)", "Curse (Inf)"};
    panel.setResourceCardsInSupply(Arrays.asList(names));

    panel.addActionLine("Game Started!");
    panel.addActionLine("Player 1's Turn Begin");

    panel.setNumberOfActions(1);
    panel.setNumberOfBuys(1);
    panel.setNumberOfCoins(0);

    applicationFrame = new JFrame(APPLICATION_NAME);
    applicationFrame.setContentPane(panel);
    applicationFrame.setBounds(0, 0, 600, 600);
    applicationFrame.setVisible(true);

    updateUI();
  }

  private void updateUI() {
    List<String> actionCardRoster = new ArrayList<String>();
    for (SupplyDeck.CardTuple tuple : supplyDeck.getActionCardRoster())
      actionCardRoster.add(tuple.userDescription());
    
    panel.setActionCardsInSupply(actionCardRoster);
  }

  @Override
  public void userSelectedActionSupplyCardAtIndex(int index) {
    panel.addActionLine("Bought action card at index " + index);

    Card card = supplyDeck.buyActionCardAtIndex(index);
    playerDeck.addCard(card);

    updateUI();
  }

  @Override
  public void userSelectedResourceSupplyCardAtIndex(int index) {
    panel.addActionLine("Bought resource card at index " + index);
  }

  @Override
  public void userSelectedCardInHandAtIndex(int index) {
    panel.addActionLine("Play card " + index);
  }

  @Override
  public void userClickedEndTurnButton() {
    panel.addActionLine("Switch to next player...");
  }
}
