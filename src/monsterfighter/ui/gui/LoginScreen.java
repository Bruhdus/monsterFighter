package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import monsterfighter.GameEnvironment;
import monsterfighter.core.Monster;
import monsterfighter.ui.ScreenManager;

/**
 * The LoginScreen is the GUI for the login screen. Dislays information that the player must enter to play the game.
 */
public class LoginScreen {

	private JFrame frame;
	private JTextField playersNameEntered;
	private JTextField numOfDaysEntered;
	private JComboBox<String> difficultyComboBox;
	private JComboBox<String> monsterComboBox;
	private ScreenManager manager;
	private GameEnvironment gameEnv;
	private String playerName;
	private int numDays;
	private String difficulty;
	private Monster startingMonster;
	private String errorMessage = "";
	private JTextField monsterNameEntered;
	
	
	/**
	 * Create the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public LoginScreen(ScreenManager incomingManager) {
		manager = incomingManager;
		gameEnv = manager.getGameEnv();
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
	 * Calls the method closeLoginScreen in ScreenManager.
	 */
	public void closeThisScreen() {
		manager.closeLoginScreen(this);
	}
	
	
	/**
	 * Returns the name of the player.
	 * 
	 * @return the string of players name.
	 */
	public String getName() {
		return playerName;
	}
	
	
	/**
	 * Returns the number of days.
	 * 
	 * @return the int number of days.
	 */
	public int getNumDays() {
		return numDays;
	}
	
	
	/**
	 * Returns the difficulty.
	 * 
	 * @return the String difficulty.
	 */
	public String getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Returns the starting monster the player selected.
	 * 
	 * @return Monster of the selected monster by player.
	 */
	public Monster getStartingMonster(){
		return startingMonster;
	}
	
	
	/**
	 * This methods gets the text from the JTextField and sets playerName.
	 */
	public void setPlayerName() {
		playerName =  playersNameEntered.getText();
	}
	
	
	/**
	 * This method gets the text from the JTextField and turns it into an integer and sets numDays 
	 */
	public void setNumDays() {
		numDays = Integer.parseInt(numOfDaysEntered.getText());
	}
	
	
	/**
	 * Gets the selected difficulty from the difficultyComboBox and sets it to difficulty.
	 */
	public void setDifficulty() {
		difficulty = (String) difficultyComboBox.getSelectedItem();
	}
	
	
	/**
	 * The method gets the list of starting monsters from ScreenManager and uses the index from the monsterComboBox
	 * to set startingMonster to a monster from the list using the given index. Also the method checks if the user has
	 * typed anything into monsterNameEntered JTextField and if so the method calls setMonsterName.
	 */
	public void setStartingMonster() {
		startingMonster = manager.getStartingMonsters()[monsterComboBox.getSelectedIndex()];;
		if (monsterNameEntered.getText().length() > 0) {
			startingMonster.setMonsterName(monsterNameEntered.getText());
		}
	}
	
	
	/**
	 * Sets the name of the starting monster to a new given name by the user.
	 * 
	 * @param monsterName A name entered by the user.
	 */
	public void setMonsterName(String monsterName) {
		startingMonster.setMonsterName(monsterName);
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
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 224));
		panelMain.setBounds(146, 77, 500, 400);
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(null);
		
		JLabel playerNameLabel = new JLabel("Name:");
		playerNameLabel.setForeground(new Color(0, 0, 128));
		playerNameLabel.setBounds(108, 36, 40, 16);
		panelMain.add(playerNameLabel);
		
		playersNameEntered = new JTextField();
		playersNameEntered.setForeground(new Color(0, 0, 128));
		playersNameEntered.setBounds(247, 31, 162, 26);
		playersNameEntered.setColumns(10);
		panelMain.add(playersNameEntered);
		
		JLabel lblNewLabel = new JLabel("Number of days:");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(108, 64, 104, 16);
		panelMain.add(lblNewLabel);
		
		numOfDaysEntered = new JTextField();
		numOfDaysEntered.setForeground(new Color(0, 0, 128));
		numOfDaysEntered.setBounds(247, 59, 162, 26);
		panelMain.add(numOfDaysEntered);
		numOfDaysEntered.setColumns(10);
		
		JLabel ErrorBox = new JLabel("");
		ErrorBox.setForeground(new Color(0, 0, 128));
		ErrorBox.setBounds(74, 199, 359, 109);
		panelMain.add(ErrorBox);
		
		String[] difficulties = {"Easy", "Hard"};
		difficultyComboBox = new JComboBox<>(difficulties);
		difficultyComboBox.setForeground(new Color(0, 0, 128));
		difficultyComboBox.setBounds(247, 88, 162, 27);
		panelMain.add(difficultyComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Difficulty:");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(108, 92, 83, 16);
		panelMain.add(lblNewLabel_1);
		
		String[] monsters = {"Pidgeotto", "Raticate", "Nidoran"};
		monsterComboBox = new JComboBox<>(monsters);
		monsterComboBox.setForeground(new Color(0, 0, 128));
		monsterComboBox.setBounds(247, 116, 162, 27);
		panelMain.add(monsterComboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Choose a monster:");
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setBounds(108, 120, 126, 16);
		panelMain.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name your monster:");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setBounds(108, 148, 137, 16);
		panelMain.add(lblNewLabel_3);
		
		monsterNameEntered = new JTextField();
		monsterNameEntered.setForeground(new Color(0, 0, 128));
		monsterNameEntered.setBounds(247, 143, 162, 26);
		monsterNameEntered.setColumns(10);
		panelMain.add(monsterNameEntered);
		
		JButton btnNext = new JButton("Start Game");
		btnNext.setForeground(new Color(0, 0, 128));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorMessage = gameEnv.loginDetailsCheck(playersNameEntered.getText(), numOfDaysEntered.getText());
				if (errorMessage.isEmpty()) {
					setPlayerName();
					setDifficulty();
					setStartingMonster();
					setNumDays();
					closeThisScreen(); 
				}
				ErrorBox.setText("<html>" + errorMessage + "</html>");
			}
		});
		
		btnNext.setBounds(377, 365, 117, 29);
		panelMain.add(btnNext);
		
		
	}
}
