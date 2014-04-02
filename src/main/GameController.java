package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		panel.setCardsInHand(Arrays.asList(new String[] { "One", "Two",
				"Three", "Four", "Five" }));

		List<Card> cards = Arrays.asList(new Card[] {
				Card.makeCard(Card.CARD_NAME_FESTIVAL),
				Card.makeCard(Card.CARD_NAME_LABORATORY),
				Card.makeCard(Card.CARD_NAME_MARKET),
				Card.makeCard(Card.CARD_NAME_SMITHY),
				Card.makeCard(Card.CARD_NAME_VILLAGE),
				Card.makeCard(Card.CARD_NAME_WOODCUTTER) });
		supplyDeck = new SupplyDeck(cards);

		Map<String, Integer> supply = new HashMap<String, Integer>();
		String[] names = new String[] { "Copper", "Silver", "Gold", "Estate",
				"Duchy", "Province", "Curse" };
		Integer[] values = new Integer[] { 10, 10, 10, 12, 13, 14, 99 };
		for (int idx = 0; idx < names.length; ++idx)
			supply.put(names[idx], values[idx]);
		panel.setResourceCardsInSupply(supply);

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
		Map<String, Integer> actionCardRoster = new HashMap<String, Integer>();
		for (SupplyDeck.CardTuple tuple : supplyDeck.getActionCardRoster())
			actionCardRoster.put(tuple.getCard().getName(), tuple.getSupply());
		System.out.println(actionCardRoster);
		panel.setActionCardsInSupply(actionCardRoster);
	}

	@Override
	public void userClickedActionSupplyCardAtIndex(int index) {
		panel.addActionLine("Bought action card at index " + index);

		Card card = supplyDeck.buyActionCardAtIndex(index);
		playerDeck.addCard(card);

		updateUI();
	}

	@Override
	public void userClickedResourceSupplyCardAtIndex(int index) {
		panel.addActionLine("Bought resource card at index " + index);
	}
}
