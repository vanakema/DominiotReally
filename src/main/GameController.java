package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

public class GameController implements GamePanelDelegate {

	public static void main(String[] args) {
		new GameController();
	}
	
	private static final String APPLICATION_NAME = "Dominion";
	
	private JFrame applicationFrame;
	private GamePanel panel;
	
	private Deck deck;

	public GameController() {
		deck = demo_makeFakeDeck();
		
		panel = new GamePanel(this);
		
		applicationFrame = new JFrame(APPLICATION_NAME);
		applicationFrame.setContentPane(panel);
		applicationFrame.setBounds(0, 0, 600, 100);
		applicationFrame.setVisible(true);
		
		demo_reshuffle();
	}
	
	private void demo_reshuffle() {
		List<Card> cards;
		try {
			cards = deck.drawFive();
		} catch (IndexOutOfBoundsException e) {
			panel.clearPlayerHand();
			return;
		}
		
		List<String> cardNames = new ArrayList<String>();
		for (Card card : cards)
			cardNames.add(card.getName());
		
		panel.setPlayerHand(cardNames);
	}
	
	private Deck demo_makeFakeDeck() {
		Deck fakeDeck = new Deck();
		for (int i = 0; i < 25; ++i)
			fakeDeck.addCard(new Card("Card #" + i));
		
		return fakeDeck;
	}
	
	@Override
	public void gameShouldReshuffle() {
		System.out.println("Reshuffle...");
		demo_reshuffle();
	}

	@Override
	public void playerDidSelectHandCardAtIndex(int index) {
		System.out.println("Did select card at index: " + index);
	}
}
