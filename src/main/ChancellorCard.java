package main;

public class ChancellorCard extends ActionCard {

  protected ChancellorCard() {
    super(Card.CARD_NAME_CHANCELLOR, "", 0);
    
    this.additionalCoins = 2;
  }
  
  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    
    if (context.decideBoolean("Put your deck into the discard pile?")) {
      System.out.println("Threw out some cards!");
    }
  }

}
