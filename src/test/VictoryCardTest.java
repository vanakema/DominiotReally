package test;

import static org.junit.Assert.*;

import main.VictoryCard;

import org.junit.Test;

public class VictoryCardTest {
	VictoryCard e = VictoryCard.makeEstate();
	VictoryCard d = VictoryCard.makeDuchy();
	VictoryCard p = VictoryCard.makeProvince();

	@Test
	public void nameTest() {		
		assertEquals("Estate", e.getName());
		assertEquals("Duchy", d.getName());
		assertEquals("Province", p.getName());
	}
	
	@Test
	public void descriptionTest() {
		assertEquals("Victory", e.getDescription());
		assertEquals("Victory", d.getDescription());
		assertEquals("Victory", p.getDescription());
	}
	
	@Test
	public void costTest() {
		assertEquals(2, e.getCost());
		assertEquals(5, d.getCost());
		assertEquals(8, p.getCost());
	}
	
	@Test
	public void victoryPointValue() {
		assertEquals(1, e.getVictoryPointValue());
		assertEquals(3, d.getVictoryPointValue());
		assertEquals(6, p.getVictoryPointValue());
	}

}
