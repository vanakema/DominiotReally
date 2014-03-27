package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GamePanel extends JPanel  
{
	private JTextArea actionsTextArea = new JTextArea();
	private JPanel actionSupplyPanel = new JPanel(new GridLayout(2, 5));
	private JPanel resourceSupplyPanel = new JPanel(new GridLayout(7, 1));
	private JPanel handPanel = new JPanel();
	private JLabel actionsLabel = new JLabel();
	private JLabel buysLabel = new JLabel();
	private JLabel coinsLabel = new JLabel();
	
	GamePanel() {
		super(new BorderLayout());
		
		this.add(actionsTextArea, BorderLayout.CENTER);
		this.add(actionSupplyPanel, BorderLayout.NORTH);
		this.add(resourceSupplyPanel, BorderLayout.EAST);
		this.add(handPanel, BorderLayout.SOUTH);
		
		JPanel infoPanel = new JPanel(new GridLayout(4, 1));
		infoPanel.add(actionsLabel);
		infoPanel.add(buysLabel);
		infoPanel.add(coinsLabel);
		infoPanel.add(new JButton("End Turn"));
		this.add(infoPanel, BorderLayout.WEST);
	}
	
	void addActionLine(String line) {
		actionsTextArea.append("\n" + line);
	}
	
	void setActionCardsInSupply(Map<String,Integer> cards) {
		setSupplyCardsInPanel(cards, actionSupplyPanel);
	}
	
	void setResourceCardsInSupply(Map<String,Integer> cards) {
		setSupplyCardsInPanel(cards, resourceSupplyPanel);
	}
	
	private void setSupplyCardsInPanel(Map<String,Integer> cards, JPanel panel) {
		panel.removeAll();
		for (Map.Entry<String,Integer> entry : cards.entrySet())
			panel.add(new JButton(entry.getKey() + " (" + entry.getValue() + ")"));
	}
	
	void setCardsInHand(List<String> cards) {
		handPanel.removeAll();
		for (String card : cards)
			handPanel.add(new JButton(card));
	}
	
	void setNumberOfActions(int count) {
		this.actionsLabel.setText("Actions: " + count);
	}
	
	void setNumberOfBuys(int count) {
		this.buysLabel.setText("Buys " + count);
	}
	
	void setNumberOfCoins(int count) {
		this.coinsLabel.setText("Coin: " + count);
	}
}
