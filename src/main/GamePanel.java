package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

interface GamePanelDelegate {
	void gameShouldReshuffle();
	void playerDidSelectHandCardAtIndex(int index);
}

public class GamePanel extends JPanel {
	
	private GamePanelDelegate delegate;
	
	private List<JLabel> cardLabels = new ArrayList<JLabel>();
	private PlayerHandListener cardListener = new PlayerHandListener();
	
	/**
	 * Construct a new GamePanel object with the specified delegate. The panel
	 * is constructed in an uninitialized configuration, without a hand, supply
	 * deck, or player information.
	 * 
	 * @param delegate
	 */
	GamePanel(GamePanelDelegate delegate) {
		this.delegate = delegate;
		
		JButton reshuffleButton = new JButton("Reshuffle");
		reshuffleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel.this.delegate.gameShouldReshuffle();
			}
		});
		this.add(reshuffleButton);
	}
	
	/**
	 * Sets the cards in the player's current hand. These appear at the bottom
	 * of the panel in a row. When the player selects a card
	 * delegate.gamePanelDidSelectCardAtIndex(int) will be called with the 
	 * selected card's index.
	 * 
	 * @param cardNames
	 */
	public void setPlayerHand(List<String> cardNames) {
		clearPlayerHand();
		
		for (String name : cardNames) {
			JLabel label = new JLabel(name, JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.addMouseListener(cardListener);
			label.setBorder(new EmptyBorder(10, 10, 10, 10));
			label.setBackground(Color.LIGHT_GRAY);
			label.setOpaque(true);
			
			this.add(label);
			this.revalidate();
			cardLabels.add(label);
		}
	}
	
	/**
	 * Empties the player's current hand.
	 */
	public void clearPlayerHand() {
		for (JLabel label : cardLabels)
			this.remove(label);
		
		this.revalidate();
		cardLabels.clear();
	}
	
	/**
	 * Instead of creating multiple anonymous listeners for cards in the player's
	 * hand, just create one and reuse it when the hand changes.
	 */
	private class PlayerHandListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int cardIndex = GamePanel.this.cardLabels.indexOf(e.getComponent());
			if (cardIndex >= 0)
				GamePanel.this.delegate.playerDidSelectHandCardAtIndex(cardIndex);
		}
		
		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
}
