package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class tracks a collection of cards representing the unowned game
 * objects. Cards are `bought' from this deck by players and added to their
 * deck, at which time they are removed from the supply deck.
 */
public class SupplyDeck {
	public class CardTuple {
		private Card card;
		private int supply;

		CardTuple(Card card, int supply) {
			this.card = card;
			this.supply = supply;
		}
		
		public String userDescription() {
		  return String.format("%s (%s)", this.card.getName(), this.supply);
		}

		public Card getCard() {
			return this.card;
		}

		public int getSupply() {
			return this.supply;
		}

		private void decrementSupply() {
			if (this.supply > 0)
				this.supply--;
		}
	}

	private List<CardTuple> actionCards = new ArrayList<CardTuple>();
	private List<CardTuple> resourceCards= new ArrayList<CardTuple>();

	/**
	 * Create a new SupplyDeck with the specified action cards. Victory and
	 * Treasure cards will be automatically populated according to game rules.
	 * 
	 * `actionCards' should be a list of exactly 10 cards. Any other size will
	 * result in a IllegalArgumentException.
	 */
	public SupplyDeck(List<Card> actionCards) {
		for (Card card : actionCards)
			this.actionCards.add(new CardTuple(card, 10));
	}

	/**
	 * Returns a mapping of available cards, and the number remaining for
	 * purchase of each, (may be zero). This map is immutable.
	 */
	public List<CardTuple> getActionCardRoster() {
		return Collections.unmodifiableList(this.actionCards);
	}

	public Card buyActionCardAtIndex(int index) {
		CardTuple tuple = this.actionCards.get(index);
		tuple.decrementSupply();
		return tuple.getCard();
	}

	public List<CardTuple> getResourceCardRoster() {		
		return Collections.unmodifiableList(this.resourceCards);
	}
}