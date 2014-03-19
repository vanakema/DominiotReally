package test;

import static org.junit.Assert.*;

import main.Card;

import org.junit.Before;
import org.junit.Test;

/**
 * TODO Put here a description of what this class does.
 *
 * @author vanakema.
 *         Created Mar 19, 2014.
 */
public class CardTest {

	@Test
	public void nameTest() {
		Card c1 = new Card("Witch");
		Card c2 = new Card("Militia");
		Card c3 = new Card("Remodel");
		Card c4 = new Card("Village");
		assertEquals("Witch", c1.getName());
		assertEquals("Militia", c2.getName());
		assertEquals("Remodel", c3.getName());
		assertEquals("Village", c4.getName());
	}

}
