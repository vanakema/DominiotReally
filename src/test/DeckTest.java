package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import main.Card;
import main.PlayerDeck;

import org.junit.Test;

public class DeckTest {
	
	private void testFactoryForCardName(String name) {
		Card card = Card.makeCard(name);
		assertNotNull(card);
		assertEquals(name, card.getName());
	}
	
	@Test
	public void testFactoryForFestivalCard() {
		testFactoryForCardName(Card.CARD_NAME_FESTIVAL);
	}
	
	@Test
	public void testFactoryForLaboratory() {
		testFactoryForCardName(Card.CARD_NAME_LABORATORY);
	}
	
	@Test
	public void testFactoryForMarket() {
		testFactoryForCardName(Card.CARD_NAME_MARKET);
	}
	
	@Test
	public void testFactoryForSmithy() {
		testFactoryForCardName(Card.CARD_NAME_SMITHY);
	}
	
	@Test
	public void testFactoryForVillage() {
		testFactoryForCardName(Card.CARD_NAME_VILLAGE);
	}
	
	@Test
	public void testFactoryForWoodcutter() {
		testFactoryForCardName(Card.CARD_NAME_WOODCUTTER);
	}

	@Test
	public void testFactoryForEstate() {
		testFactoryForCardName(Card.CARD_NAME_ESTATE);
	}
	
	@Test
	public void testFactoryForDuchy() {
		testFactoryForCardName(Card.CARD_NAME_DUCHY);
	}
	
	@Test
	public void testFactoryForProvince() {
		testFactoryForCardName(Card.CARD_NAME_PROVINCE);
	}
	
	@Test
	public void testFactoryForCopper() {
		testFactoryForCardName(Card.CARD_NAME_COPPER);
	}
	
	@Test
	public void testFactoryForSilver() {
		testFactoryForCardName(Card.CARD_NAME_SILVER);
	}
	
	@Test
	public void testFactoryForGold() {
		testFactoryForCardName(Card.CARD_NAME_GOLD);
	}
	
	@Test
	public void sizeTest() {
		PlayerDeck deck1 = makeDeck();
		
		assertEquals(10, deck1.getSize());
		
		List<Card> hand = deck1.drawFive();
		
		assertEquals(5,deck1.getSize());
		
		List<Card> hand2 = deck1.drawFive();
		
		assertEquals(0, deck1.getSize());
		
		assertEquals(5, hand.size());
		assertEquals(5, hand2.size());
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException(){
		PlayerDeck emptyDeck = new PlayerDeck();
		List<Card> hand = emptyDeck.drawFive();
	}
	
	public PlayerDeck makeDeck() {
PlayerDeck deck1 = new PlayerDeck();
		
		Card newCard1 = Card.makeCard(Card.CARD_NAME_COPPER);
		Card newCard2= Card.makeCard(Card.CARD_NAME_COPPER);
		Card newCard3 = Card.makeCard(Card.CARD_NAME_COPPER);
		Card newCard4 = Card.makeCard(Card.CARD_NAME_SMITHY);
		Card newCard5 = Card.makeCard(Card.CARD_NAME_SMITHY);
		Card newCard6 = Card.makeCard(Card.CARD_NAME_FESTIVAL);
		Card newCard7 = Card.makeCard(Card.CARD_NAME_LABORATORY);
		Card newCard8 = Card.makeCard(Card.CARD_NAME_PROVINCE);
		Card newCard9 = Card.makeCard(Card.CARD_NAME_GOLD);
		Card newCard10 = Card.makeCard(Card.CARD_NAME_ESTATE);
		
		deck1.addCard(newCard1);
		assertEquals(1, deck1.getSize());
		
		deck1.addCard(newCard2);
		deck1.addCard(newCard3);
		deck1.addCard(newCard4);
		deck1.addCard(newCard5);
		deck1.addCard(newCard6);
		deck1.addCard(newCard7);
		deck1.addCard(newCard8);
		deck1.addCard(newCard9);
		deck1.addCard(newCard10);
		
		return deck1;
	}
	
	
}
