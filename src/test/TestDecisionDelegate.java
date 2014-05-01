package test;

import main.GameContext;
import main.GameContext.DecisionDelegate;
import main.PlayerDeck;

public class TestDecisionDelegate implements DecisionDelegate {

  @Override
  public boolean decideBoolean(GameContext context, String question) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int decideCardInHand(GameContext context, String question, boolean canIgnore) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean decideCardInDeck(GameContext context, PlayerDeck deck, String question) {
    // TODO Auto-generated method stub
    return false;
  }

}
