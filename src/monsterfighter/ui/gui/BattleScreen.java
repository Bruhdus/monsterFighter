package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Font;
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
 * The BattleScreen class is the GUI for the battle screen. Displays the battle log and the players monsters stats.
 */

public class BattleScreen {

	private JFrame frame;
	private ScreenManager manager;
	private Player player;
	private GameEnvironment gameEnv;
	private int xAxis = 50;
	
	
	/**
	 * Creates the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public BattleScreen(ScreenManager incomingManager) {
		manager = incomingManager;
		player = manager.getPlayer();
		gameEnv = manager.getGameEnv();
		initialize();
		frame.setVisible(true);
	}
	
	
	/**
	 * Closes the frame.
	 */
	public void closeWindow() {
		frame.dispose();
	}

	
	/**
	 * Creates a panel for every monster that the player owns and is displayed on the bottom of the screen.
	 * 
	 * @param monsterList the list of monsters that the player owns.
	 */
	public void displayMonsters(ArrayList<Monster> monsterList) {
		xAxis = 50;
		for (Monster monster: monsterList) {
			JPanel panelMonster = new JPanel();
			panelMonster.setBorder(new LineBorder(new Color(0, 0, 128), 2));
			panelMonster.setBounds(xAxis, 370, 150, 150);
			panelMonster.setLayout(null);
			frame.getContentPane().add(panelMonster);
			
			JLabel lblMonsterName = new JLabel(monster.getMonsterName(), SwingConstants.CENTER);
			lblMonsterName.setForeground(new Color(0, 0, 128));
			lblMonsterName.setBounds(0, 15, 150, 16);
			panelMonster.add(lblMonsterName);
			
			JLabel lblMonsterRarity = new JLabel("Rarity: " + monster.getRarity(), SwingConstants.CENTER);
			lblMonsterRarity.setForeground(new Color(0, 0, 128));
			lblMonsterRarity.setBounds(0, 40, 150, 16);
			panelMonster.add(lblMonsterRarity);
			
			JLabel lblMonsterHealth = new JLabel("Health: " + Integer.toString(monster.getCurrentHealth()), SwingConstants.CENTER);
			lblMonsterHealth.setForeground(new Color(0, 0, 128));
			lblMonsterHealth.setBounds(0, 65, 150, 16);
			panelMonster.add(lblMonsterHealth);
			
			JLabel lblMonsterDailyHeal = new JLabel("Daily Heal: " + Integer.toString(monster.getDailyHeal()), SwingConstants.CENTER);
			lblMonsterDailyHeal.setForeground(new Color(0, 0, 128));
			lblMonsterDailyHeal.setBounds(0, 90, 150, 16);
			panelMonster.add(lblMonsterDailyHeal);
			
			JLabel lblMonsterDamage = new JLabel("Damage: " + Integer.toString(monster.getDamage()), SwingConstants.CENTER);
			lblMonsterDamage.setForeground(new Color(0, 0, 128));
			lblMonsterDamage.setBounds(0, 115, 150, 16);
			panelMonster.add(lblMonsterDamage);
			
			xAxis += 180;
		}
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
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnv.setBattleComplete();
				manager.launchHomeScreen();
				closeWindow();
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBounds(677, 537, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblBattleTitle = new JLabel("Battle Outcome!", SwingConstants.CENTER);
		lblBattleTitle.setFont(new Font("", Font.BOLD, 30));
		lblBattleTitle.setForeground(new Color(0, 0, 128));
		lblBattleTitle.setBounds(6, 6, 794, 60);
		frame.getContentPane().add(lblBattleTitle);
		
		JLabel lblBattleLog = new JLabel("<html>" + gameEnv.startBattle().replaceAll("\n", "<br/>") + "</html>", SwingConstants.CENTER);
		lblBattleLog.setForeground(new Color(0, 0, 128));
		lblBattleLog.setBounds(50, 80, 700, 280);
		frame.getContentPane().add(lblBattleLog);
		
		displayMonsters(player.getMonsterList());
		
	}
}
