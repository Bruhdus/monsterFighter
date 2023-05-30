package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import monsterfighter.core.Player;
import monsterfighter.ui.ScreenManager;

/**
 * The EndScreen class is the GUI for the end screen displayed at the end of the game.
 */
public class EndScreen {

	private JFrame frame;
	private ScreenManager manager;
	private Player player;

	
	/**
	 * Create the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public EndScreen(ScreenManager incomingManager) {
		manager = incomingManager;
		player = manager.getPlayer();
		initialize();
		frame.setVisible(true);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 224));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEndTitle = new JLabel(String.format("Thank You %s For Playing Monster Survivor", player.getName()), SwingConstants.CENTER);
		lblEndTitle.setForeground(new Color(0, 0, 128));
		lblEndTitle.setBounds(6, 50, 788, 80);
		frame.getContentPane().add(lblEndTitle);
		lblEndTitle.setFont(new Font("", Font.BOLD, 20));
		
		JLabel lblPlayerName = new JLabel("Player Name: " + player.getName(), SwingConstants.CENTER);
		lblPlayerName.setForeground(new Color(0, 0, 128));
		lblPlayerName.setBounds(6, 200, 788, 20);
		frame.getContentPane().add(lblPlayerName);
		
		JLabel lblDifficulty = new JLabel("Difficulty: " + player.getDifficulty(),SwingConstants.CENTER);
		lblDifficulty.setForeground(new Color(0, 0, 128));
		lblDifficulty.setBounds(6, 250, 788, 20);
		frame.getContentPane().add(lblDifficulty);
		
		JLabel lblDays = new JLabel("Days Played: " + player.getCurrentDay(), SwingConstants.CENTER);
		lblDays.setForeground(new Color(0, 0, 128));
		lblDays.setBounds(6, 300, 788, 20);
		frame.getContentPane().add(lblDays);
		
		JLabel lblTotalGold = new JLabel("Total Gold Earned: " + player.getTotalGold(), SwingConstants.CENTER);
		lblTotalGold.setForeground(new Color(0, 0, 128));
		lblTotalGold.setBounds(6, 350, 788, 20);
		frame.getContentPane().add(lblTotalGold);
		
		JLabel lblScore = new JLabel("Total Score: " + player.getScore(), SwingConstants.CENTER);
		lblScore.setForeground(new Color(0, 0, 128));
		lblScore.setBounds(6, 400, 788, 20);
		frame.getContentPane().add(lblScore);
	}
}
