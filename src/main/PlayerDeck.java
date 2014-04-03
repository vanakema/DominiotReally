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
		int handSize = hand.size();// to avoid prevent the loop from
		// running to original hand.size()
		if (!hand.isEmpty()) { // to avoid dumping hand on first turn
			for (int i = 0; i < handSize; i++) {
				this.discardDeck.add(hand.remove(0));
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
			this.hand.addAll(this.deck);
			this.deck.clear();
			shuffleDeck();
			if (this.deck.size() > numToDraw) {
				for (int i = 0; i < remainingToDraw; i++) {
					this.hand.add(this.deck.remove(0));
				}
			}else{
				this.hand.addAll(this.deck);
				this.deck.clear();
			}
			// if not enough cards add more
			return this.hand;
		}
	}

	private void shuffleDeck() {
		Collections.shuffle(this.discardDeck);
		this.deck.addAll(this.discardDeck);
		this.discardDeck.clear();

	}

}
