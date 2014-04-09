package main;

public class ThroneRoomCard extends ActionCard {

  public ThroneRoomCard() {
    super(Card.CARD_NAME_THRONE_ROOM, "Choose an Action card in your hand. Play it twice.", 4);
  }
  
  //TODO: Actually do it.
  @Override
  public void performAction(GameContext context) {
    super.performAction(context);
    int index = context.decideCardInHand("What card do you want to play twice?", true);
    
  }

}
