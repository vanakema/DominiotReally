package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class GameController {

	public static void main(String[] args) {
		new GameController();
	}
	
	private static final String APPLICATION_NAME = "Dominion";
	
	private JFrame applicationFrame;
	private GamePanel panel;

	public GameController() {
		panel = new GamePanel();
		panel.setCardsInHand(Arrays.asList(new String[]{ "One", "Two", "Three", "Four", "Five" }));
		
		Map<String,Integer> supply = new HashMap<String,Integer>();
		for (int idx = 0; idx < 10; ++idx)
			supply.put(String.valueOf((char) ('A' + idx)), idx);
		panel.setActionCardsInSupply(supply);
		
		supply.clear();
		String[] names = new String[]{ "Copper", "Silver", "Gold", "Estate", "Duchy", "Province", "Curse" };
		Integer[] values = new Integer[]{ 10, 10, 10, 12, 13, 14, 99 };
		for (int idx = 0; idx < names.length; ++idx)
			supply.put(names[idx], values[idx]);
		panel.setResourceCardsInSupply(supply);
		
		panel.addActionLine("Game Started!");
		panel.addActionLine("Player 1's Turn Begin");
		
		panel.setNumberOfActions(1);
		panel.setNumberOfBuys(1);
		panel.setNumberOfCoins(0);

		applicationFrame = new JFrame(APPLICATION_NAME);
		applicationFrame.setContentPane(panel);
		applicationFrame.setBounds(0, 0, 600, 600);
		applicationFrame.setVisible(true);
	}
}
