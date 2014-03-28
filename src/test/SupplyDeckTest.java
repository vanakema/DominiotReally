package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import main.Card;
import main.SupplyDeck;
import main.SupplyDeck.CardTuple;

import org.junit.Test;

public class SupplyDeckTest {
	
	List<Card> cards = Arrays.asList(new Card[]{ Card.makeCard(Card.CARD_NAME_FESTIVAL),
		     Card.makeCard(Card.CARD_NAME_LABORATORY),
		     Card.makeCard(Card.CARD_NAME_MARKET),
		     Card.makeCard(Card.CARD_NAME_SMITHY),
		     Card.makeCard(Card.CARD_NAME_VILLAGE),
		     Card.makeCard(Card.CARD_NAME_WOODCUTTER) });

	@Test
	public void testBuyIndex0Card() {
		SupplyDeck supplyDeck = new SupplyDeck(cards);
		Card festCard = supplyDeck.buyActionCardAtIndex(0);
		assertEquals(festCard.getName(), Card.CARD_NAME_FESTIVAL);
		assertEquals(supplyDeck.getActionCardRoster().get(0).getSupply(), 9);
	}
	
	@Test
	public void testBuyIndex3Card() {
		SupplyDeck supplyDeck = new SupplyDeck(cards);
		Card festCard = supplyDeck.buyActionCardAtIndex(3);
		assertEquals(festCard.getName(), Card.CARD_NAME_SMITHY);
		assertEquals(supplyDeck.getActionCardRoster().get(3).getSupply(), 9);
	}
	
	@Test
	public void testBuyIndex5Card() {
		SupplyDeck supplyDeck = new SupplyDeck(cards);
		Card festCard = supplyDeck.buyActionCardAtIndex(5);
		assertEquals(festCard.getName(), Card.CARD_NAME_WOODCUTTER);
		assertEquals(supplyDeck.getActionCardRoster().get(5).getSupply(), 9);
	}
	
	@Test
	public void testGetRoster() {
		SupplyDeck supplyDeck = new SupplyDeck(cards);
		List<CardTuple> cardTuples = supplyDeck.getActionCardRoster();
		for (int i = 0; i < cards.size(); i++)
			assertEquals(cardTuples.get(i).getCard(), cards.get(i));
	}
}