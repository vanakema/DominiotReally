package main;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeck {

	ArrayList<Card> deck = new ArrayList<Card>();

	public int getSize() {
		return this.deck.size();
	}

	// Adds 10 cards from the game
	// Can be revised later to add in random order
	public void addCard(Card card) {
		this.deck.add(card);

	}

	public List<Card> drawFive() throws IndexOutOfBoundsException {
		// makes sure there are atleast 5 cards in the deck
		if (this.deck.size() - 5 >= 0) {
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(this.deck.remove(0));
			hand.add(this.deck.remove(0));
			hand.add(this.deck.remove(0));
			hand.add(this.deck.remove(0));
			hand.add(this.deck.remove(0));

			return hand;
		} else {
			// if not enough cards add more
			throw new IndexOutOfBoundsException();
		}
	}

}
