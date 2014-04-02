package test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import main.Card;

import org.junit.Test;

public class CardTest {

  @Test(expected = NoSuchElementException.class)
  public void makeInvalidCard(){
    Card.makeCard("Dutcher");
  }

}
