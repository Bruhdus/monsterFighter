package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import monsterfighter.GameEnvironment;
import monsterfighter.ui.ScreenManager;

/**
 * The BattleSelectScreen class is the GUI for the battle selection screen. Displays the battles that the player can choose from.
 */

public class BattleSelectScreen {

	private JFrame frame;
	private ScreenManager manager;
	private GameEnvironment gameEnv;
	private int selectedBattleIndex = -1;
	private int yAxis = 150;
	private ArrayList<JButton> battleButtonList = new ArrayList<>();
	
	
	/**
	 * Create the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public BattleSelectScreen(ScreenManager incomingManager) {
		manager = incomingManager;
		gameEnv = manager.getGameEnv();
		initialize();
		frame.setVisible(true);
	}
	
	
	/**
	 * Close the frame
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	
	/**
	 * Lauches HomeScreen and closes this frame.
	 */
	public void backHome() {
		manager.launchHomeScreen();
		closeWindow();
	}
	
	
	/**
	 * For monster lists in the battleSelectable list from GameEnvironment create JLabels to display the possible
	 * list of monsters to fight.
	 */
	public void createBattleLabels() {
		for(int index=0; index < 3; index++) {
			if (gameEnv.getBattlesSelectable().get(index).isEmpty() == false){
				JLabel lblBattle = new JLabel(gameEnv.getBattleLineUpString(gameEnv.getBattlesSelectable().get(index)) );
				lblBattle.setForeground(new Color(0, 0, 128));
				lblBattle.setBounds(210, yAxis, 590, 16);
				frame.getContentPane().add(lblBattle);
			} else {
				battleButtonList.get(index).setEnabled(false);
			}
			yAxis += 100;
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
		
		JLabel lblBattleSelected = new JLabel("Battle Selected: None");
		lblBattleSelected.setForeground(new Color(0, 0, 128));
		lblBattleSelected.setBounds(444, 505, 200, 16);
		frame.getContentPane().add(lblBattleSelected);
		
		JButton btnStartBattle = new JButton("Start Battle");
		btnStartBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedBattleIndex != -1) {
					gameEnv.setSelectedBattleIndex(selectedBattleIndex);
					gameEnv.setSelectedBattle();
					manager.launchBattleScreen();
					closeWindow();
				}
			}
		});
		btnStartBattle.setForeground(new Color(0, 0, 128));
		btnStartBattle.setBounds(657, 500, 117, 29);
		frame.getContentPane().add(btnStartBattle);
		
		JLabel lblSelectBattleTitle = new JLabel("Select A Battle!");
		lblSelectBattleTitle.setForeground(new Color(0, 0, 128));
		lblSelectBattleTitle.setBounds(314, 42, 267, 55);
		lblSelectBattleTitle.setFont(new Font("", Font.BOLD, 30));
		frame.getContentPane().add(lblSelectBattleTitle);
		
		JButton btnBattleOne = new JButton("Battle One");
		btnBattleOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblBattleSelected.setText("Battle Selected: Battle One");
				selectedBattleIndex = 0;
			}
		});
		btnBattleOne.setForeground(new Color(0, 0, 128));
		btnBattleOne.setBounds(81, 145, 117, 29);
		frame.getContentPane().add(btnBattleOne);
		battleButtonList.add(btnBattleOne);
		
		JButton btnBattleTwo = new JButton("Battle Two");
		btnBattleTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblBattleSelected.setText("Battle Selected: Battle Two");
				selectedBattleIndex = 1;
			}
		});
		btnBattleTwo.setForeground(new Color(0, 0, 128));
		btnBattleTwo.setBounds(81, 245, 117, 29);
		frame.getContentPane().add(btnBattleTwo);
		battleButtonList.add(btnBattleTwo);
		
		JButton btnBattleThree = new JButton("Battle Three");
		btnBattleThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblBattleSelected.setText("Battle Selected: Battle Three");
				selectedBattleIndex = 2;
			}
		});
		btnBattleThree.setForeground(new Color(0, 0, 128));
		btnBattleThree.setBounds(81, 345, 117, 29);
		frame.getContentPane().add(btnBattleThree);
		battleButtonList.add(btnBattleThree);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backHome();
			}
		});
		btnBack.setForeground(new Color(0, 0, 128));
		btnBack.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		
		createBattleLabels();
		
	}
}
