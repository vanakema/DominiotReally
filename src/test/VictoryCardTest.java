package test;

import static org.junit.Assert.*;

import main.GameContext;
import main.VictoryCard;

import org.junit.Test;

public class VictoryCardTest {
  VictoryCard e = VictoryCard.makeEstate();
  VictoryCard d = VictoryCard.makeDuchy();
  VictoryCard p = VictoryCard.makeProvince();

  @Test
  public void testName() {
    assertEquals("Estate", e.getName());
    assertEquals("Duchy", d.getName());
    assertEquals("Province", p.getName());
  }

  @Test
  public void testDescription() {
    assertEquals("Victory", e.getDescription());
    assertEquals("Victory", d.getDescription());
    assertEquals("Victory", p.getDescription());
  }

  @Test
  public void testCost() {
    assertEquals(2, e.getCost());
    assertEquals(5, d.getCost());
    assertEquals(8, p.getCost());
  }

  @Test
  public void TestVictoryPointValue() {
    assertEquals(1, e.getVictoryPointValue());
    assertEquals(3, d.getVictoryPointValue());
    assertEquals(6, p.getVictoryPointValue());
  }

  @Test
  public void testPerformActionsDoesNothing() {
    GameContext context = new GameContext();
    p.performAction(context);
    d.performAction(context);
    e.performAction(context);
    assertEquals(1, context.getActionCount());
    assertEquals(1, context.getBuyCount());
    assertEquals(0, context.getTreasureCount());
  }

}
