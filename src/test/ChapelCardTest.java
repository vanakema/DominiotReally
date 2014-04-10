package test;

import static org.junit.Assert.*;

import java.util.List;

import main.Card;
import main.GameContext;
import main.ChapelCard;
import main.PlayerDeck;

import org.junit.Test;

public class ChapelCardTest {
  
    ChapelCard card = new ChapelCard();
    GameContext con = new GameContext();
    
  @Test
  public void testName(){
    assertEquals("Chapel", card.getName());
  }
  
  @Test
  public void testCost(){
    assertEquals(2,card.getCost());
  }
  
  @Test
  public void testDescription(){
    assertEquals("Trash up to 4 cards from your hand.", card.getDescription());
  }
  
  @Test
  public void testPerformAction(){
  
    
  }
  
 
  
  
  
  
}
