package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.BoringActionCard;
import main.Card;
import main.Deck;

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
	public void sizeTest(){
		
		//Tests addCard, getSize, and drawFive
		
//		Deck deck1 = new Deck();
//		
//		Card newCard1 = new Card("Militia");
//		Card newCard2= new Card("Remodel");
//		Card newCard3 = new Card("Smithy");
//		Card newCard4 = new Card("Festival");
//		Card newCard5 = new Card("Adventurer");
//		Card newCard6 = new Card("Chapel");
//		Card newCard7 = new Card("Moat");
//		Card newCard8 = new Card("Village");
//		Card newCard9 = new Card("Workshop");
//		Card newCard10 = new Card("Feast");
//		
//		deck1.addCard(newCard1);
//		assertEquals(1, deck1.getSize());
//		
//		deck1.addCard(newCard2);
//		deck1.addCard(newCard3);
//		deck1.addCard(newCard4);
//		deck1.addCard(newCard5);
//		deck1.addCard(newCard6);
//		deck1.addCard(newCard7);
//		deck1.addCard(newCard8);
//		deck1.addCard(newCard9);
//		deck1.addCard(newCard10);
//		assertEquals(10, deck1.getSize());
//		
//		List<Card> hand = deck1.drawFive();
//		
//		assertEquals(5,deck1.getSize());
//		
//		List<Card> hand2 = deck1.drawFive();
//		
//		assertEquals(0, deck1.getSize());
//		
//		assertEquals(5, hand.size());
//		assertEquals(5, hand2.size());
//		try{
//			deck1.drawFive();
//		}catch(Exception e){
//			System.out.println("Deck is empty");
//		}
		
	}
	
	
}
