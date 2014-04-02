package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerDeck {

	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> discardDeck = new ArrayList<Card>();
	ArrayList<Card> hand = new ArrayList<Card>();

	public int getSize() {
		return this.deck.size();
	}

	// Can be revised later to add in random order
	public void addCard(Card card) {
		this.deck.add(card);

	}

	public List<Card> drawNum(int numToDraw) throws IndexOutOfBoundsException {

		if (!this.hand.isEmpty()) { // to avoid dumping hand on first turn
			for (int i = 0; i < this.hand.size(); i++) {
				if (this.hand.get(0) != null) { // to avoid empty spaces in hand
					this.discardDeck.add(this.hand.remove(0));
				}
			}
		}

		// makes sure there are enough cards to draw
		if (this.deck.size() - numToDraw >= 0) {
			for (int i = 0; i < numToDraw; i++) {
				this.hand.add(this.deck.remove(0));
			}

			return hand;
		} else {
			int remainingToDraw = numToDraw - this.deck.size();
			this.hand = this.deck;
			while (this.deck.size() != 0) {
				this.discardDeck.add(this.deck.remove(0));
			}
			shuffleDeck();
			for (int i = 0; i < remainingToDraw; i++) {
				this.hand.add(this.deck.remove(0));
			}

			// if not enough cards add more
			throw new IndexOutOfBoundsException();
		}
	}

	private void shuffleDeck() {
		Collections.shuffle(this.discardDeck);
		this.deck = this.discardDeck;
		this.discardDeck.clear();

	}

}
