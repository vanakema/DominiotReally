package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GamePanel extends JPanel  
{
	public static interface Delegate {
		void userClickedActionSupplyCardAtIndex(int index);
		void userClickedResourceSupplyCardAtIndex(int index);
	}
	
	private Delegate delegate;
	
	private JTextArea actionsTextArea = new JTextArea();
	private JPanel actionSupplyPanel = new JPanel(new GridLayout(2, 5));
	private JPanel resourceSupplyPanel = new JPanel(new GridLayout(7, 1));
	private JPanel handPanel = new JPanel();
	private JLabel actionsLabel = new JLabel();
	private JLabel buysLabel = new JLabel();
	private JLabel coinsLabel = new JLabel();
	
	private ActionListener actionSupplyListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = Arrays.asList(GamePanel.this.actionSupplyPanel.getComponents()).indexOf(e.getSource());
			GamePanel.this.delegate.userClickedActionSupplyCardAtIndex(index);
		}
	};
	
	private ActionListener resourceSupplyListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = Arrays.asList(GamePanel.this.resourceSupplyPanel.getComponents()).indexOf(e.getSource());
			GamePanel.this.delegate.userClickedResourceSupplyCardAtIndex(index);
		}
	};
	
	GamePanel(Delegate delegate) {
		super(new BorderLayout());
		
		this.delegate = delegate;
		
		this.add(new JScrollPane(actionsTextArea), BorderLayout.CENTER);
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
		setSupplyCardsInPanel(cards, actionSupplyListener, actionSupplyPanel);
	}
	
	void setResourceCardsInSupply(Map<String,Integer> cards) {
		setSupplyCardsInPanel(cards, resourceSupplyListener, resourceSupplyPanel);
	}
	
	private void setSupplyCardsInPanel(Map<String,Integer> cards, ActionListener listener, JPanel panel) {
		panel.removeAll();
		for (Map.Entry<String,Integer> entry : cards.entrySet()) {
			JButton button = new JButton(entry.getKey() + " (" + entry.getValue() + ")");
			button.addActionListener(listener);
			panel.add(button);
		}
		panel.revalidate();
		this.doLayout();
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
