package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import monsterfighter.GameEnvironment;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;
import monsterfighter.ui.ScreenManager;

/**
 * The PlayerMonsterScreen class is the GUI for the player monster screen that displays all of players monsters.
 */

public class PlayerMonsterScreen {

	private JFrame frame;
	private ScreenManager manager;
	private GameEnvironment gameEnv;
	private Player player;
	private ArrayList<Monster> playerMonsterList;
	private int xAxis = 150;
	private int yAxis = 65;
	private int monsterOrder = 1;
	private boolean haventDisplayedMonster = true;
	private Monster swapPosOne;
	private Monster swapPosTwo;
	

	/**
	 * Create the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public PlayerMonsterScreen(ScreenManager incomingManager) {
		manager = incomingManager;
		gameEnv = manager.getGameEnv();
		player = manager.getPlayer();
		playerMonsterList = player.getMonsterList();
		initialize();
		frame.setVisible(true);
	}
	
	
	/**
	 * Close the frame.
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	
	/**
	 * Calls launchHomeScreen from ScreenManager and calls closeWindow.
	 */
	public void backHome() {
		manager.launchHomeScreen();
		closeWindow();
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
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(0, 0, 128));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backHome();
			}
		});
		btnBack.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		
		JLabel lblSwapMessage = new JLabel("Please select another monster to swap with.", SwingConstants.CENTER);
		lblSwapMessage.setForeground(new Color(0, 0, 128));
		lblSwapMessage.setBounds(200, 26, 280, 16);
		frame.getContentPane().add(lblSwapMessage);
		lblSwapMessage.setVisible(false);

		JButton btnCancelSwap = new JButton("Cancel Swap");
		btnCancelSwap.setForeground(new Color(0, 0, 128));
		btnCancelSwap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapPosOne = null;
				lblSwapMessage.setVisible(false);
				btnCancelSwap.setVisible(false);
			}
		});
		btnCancelSwap.setBounds(485, 20, 117, 29);
		frame.getContentPane().add(btnCancelSwap);
		btnCancelSwap.setVisible(false);
		
		//Creates 4 JPanels that are to be displayed on screen. The panels are first displayed using the 
		//monsters that the player owns then if there are any slot empty they are left displaying empty.
		for (int i =0; i < 4; i++) {
			if (haventDisplayedMonster) {
				haventDisplayedMonster = false;
				//This creates the monster panels
				for (Monster monster: playerMonsterList) {
					i++;
					
					JPanel panelMonster = new JPanel();
					panelMonster.setBorder(new LineBorder(new Color(0, 0, 128), 2));
					panelMonster.setBounds(xAxis, yAxis, 200, 200);
					frame.getContentPane().add(panelMonster);
					panelMonster.setLayout(null);
					
					JLabel lblMonsterName = new JLabel("("+Integer.toString(monsterOrder)+") "+monster.getMonsterName(), SwingConstants.CENTER);
					lblMonsterName.setForeground(new Color(0, 0, 128));
					lblMonsterName.setBounds(6, 20, 188, 16);
					panelMonster.add(lblMonsterName);
					
					JLabel lblMonsterRarity = new JLabel("Rarity: " + monster.getRarity(), SwingConstants.CENTER);
					lblMonsterRarity.setForeground(new Color(0, 0, 128));
					lblMonsterRarity.setBounds(6, 50, 188, 16);
					panelMonster.add(lblMonsterRarity);
					
					JLabel lblMonsterHealth = new JLabel("Health: " + Integer.toString(monster.getCurrentHealth()) 
					+ " (" + Integer.toString(monster.getMaxHealth()) + ")", SwingConstants.CENTER);
					lblMonsterHealth.setForeground(new Color(0, 0, 128));
					lblMonsterHealth.setBounds(6, 75, 188, 16);
					panelMonster.add(lblMonsterHealth);
					
					JLabel lblMonsterDailyHeal = new JLabel("Daily Heal: " + Integer.toString(monster.getDailyHeal()), SwingConstants.CENTER);
					lblMonsterDailyHeal.setForeground(new Color(0, 0, 128));
					lblMonsterDailyHeal.setBounds(6, 100, 188, 16);
					panelMonster.add(lblMonsterDailyHeal);
					
					JLabel lblMonsterDamage = new JLabel("Damage: " + Integer.toString(monster.getDamage()), SwingConstants.CENTER);
					lblMonsterDamage.setForeground(new Color(0, 0, 128));
					lblMonsterDamage.setBounds(6, 125, 188, 16);
					panelMonster.add(lblMonsterDamage);
					
					JLabel lblMonsterValue = new JLabel("Value: " + Integer.toString(monster.getValue()), SwingConstants.CENTER);
					lblMonsterValue.setForeground(new Color(0, 0, 128));
					lblMonsterValue.setBounds(6, 150, 188, 16);
					panelMonster.add(lblMonsterValue);
					
					JButton btnSwap = new JButton("Swap");
					btnSwap.setForeground(new Color(0, 0, 128));
					btnSwap.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (swapPosOne == null) {
								swapPosOne = monster;
								lblSwapMessage.setVisible(true);
								btnCancelSwap.setVisible(true);
							} else {
								swapPosTwo = monster;
								gameEnv.swapMonsterPositions(swapPosOne, swapPosTwo);
								manager.launchPlayerMonsterScreen();
								closeWindow();
							}
						}
					});
					btnSwap.setBounds(6, 170, 188, 29);
					panelMonster.add(btnSwap);
					
					if (player.getMonsterList().size() == 1) {
						btnSwap.setEnabled(false);
					}
					
					
					if (xAxis == 150) {
						xAxis += 300;
					} else {
						xAxis = 150;
						yAxis = 300;
					}
					
					monsterOrder++;
				}
			}
			
			//This creates the empty panels
			JPanel panelMonster = new JPanel();
			panelMonster.setBorder(new LineBorder(new Color(0, 0, 128), 2));
			panelMonster.setBounds(xAxis, yAxis, 200, 200);
			frame.getContentPane().add(panelMonster);
			panelMonster.setLayout(null);
			
			JLabel lblEmptyMonster = new JLabel("Empty", SwingConstants.CENTER);
			lblEmptyMonster.setForeground(new Color(0, 0, 128));
			lblEmptyMonster.setBounds(6, 20, 188, 16);
			panelMonster.add(lblEmptyMonster);
			
			if (xAxis == 150) {
				xAxis += 300;
			} else {
				xAxis = 150;
				yAxis = 300;
			}
		}

	
	}
}
