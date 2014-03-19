package main;
import java.util.ArrayList;
import java.util.List;


public class Deck {

	ArrayList<Card> deck;
	int currentPos;
	
	
	public Deck(){
		this.deck = new ArrayList<Card>();
		this.currentPos =0;
	}
	
	public void addCard(){
		Card newCard1 = new Card("Militia");
		Card newCard2= new Card("Remodel");
		Card newCard3 = new Card("Smithy");
		Card newCard4 = new Card("Festival");
		Card newCard5 = new Card("Adventurer");
		Card newCard6 = new Card("Chapel");
		Card newCard7 = new Card("Moat");
		Card newCard8 = new Card("Village");
		Card newCard9 = new Card("Workshop");
		Card newCard10 = new Card("Feast");
		
		this.deck.add(newCard1);
		this.deck.add(newCard2);
		this.deck.add(newCard3);
		this.deck.add(newCard4);
		this.deck.add(newCard5);
		this.deck.add(newCard6);
		this.deck.add(newCard7);
		this.deck.add(newCard8);
		this.deck.add(newCard9);
		this.deck.add(newCard10);
		
	}
	
	public List<Card> drawFive(){
		//makes sure there are atleast 5 cards in the deck
		if(this.deck.size()-5>=0){
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(this.deck.remove(currentPos++));
		hand.add(this.deck.remove(currentPos++));
		hand.add(this.deck.remove(currentPos++));
		hand.add(this.deck.remove(currentPos++));
		hand.add(this.deck.remove(currentPos++));
		
		return hand;
		}else{
			//if not enough cards add more 
			addCard();
			drawFive();
		}
		//never reached
		return deck;
		
		
	}
	
}
