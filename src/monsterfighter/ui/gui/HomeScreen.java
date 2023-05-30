package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import monsterfighter.GameEnvironment;
import monsterfighter.core.Player;
import monsterfighter.core.RandomEvents;
import monsterfighter.ui.ScreenManager;

/**
 * The HomeScreen class is the GUI for the home screen.
 */
public class HomeScreen {

	private JFrame frame;
	private ScreenManager manager;
	private GameEnvironment gameEnv;
	private Player player;
	private RandomEvents randomEvents = new RandomEvents();
	private String randomEventMessage;
	private JPanel mainPanel;
	private JPanel whichShopPanel;
	private JPanel sleepPanel;
	private JPanel cantBattleMessagePanel;
	
	
	/**
	 * Create the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public HomeScreen(ScreenManager incomingManager) {
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
	 * Creates the panel for selecting the buy shop or sell shop. The panel is initially set be
	 * not visible until the shop button is clicked.
	 */
	public void createWhichShopJPanel() {
		JPanel whichShopPanel = new JPanel();
		whichShopPanel.setBackground(new Color(255, 255, 224));
		whichShopPanel.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(whichShopPanel);
		whichShopPanel.setVisible(false);
		whichShopPanel.setLayout(null);
		this.whichShopPanel = whichShopPanel;
		
		JButton btnShopBack = new JButton("Back");
		btnShopBack.setForeground(new Color(0, 0, 128));
		btnShopBack.setBounds(6, 6, 117, 29);
		btnShopBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whichShopPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		whichShopPanel.add(btnShopBack);
		
		JButton btnBuyShop = new JButton("Buy");
		btnBuyShop.setForeground(new Color(0, 0, 128));
		btnBuyShop.setBounds(225, 270, 150, 100);
		btnBuyShop.setFont(new Font("", Font.PLAIN, 20));
		btnBuyShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchBuyShopScreen();
				closeWindow();
			}
		});
		whichShopPanel.add(btnBuyShop);
		
		JButton btnSellShop = new JButton("Sell");
		btnSellShop.setForeground(new Color(0, 0, 128));
		btnSellShop.setFont(new Font("", Font.PLAIN, 20));
		btnSellShop.setBounds(425, 270, 150, 100);
		btnSellShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchSellShopScreen();
				closeWindow();
			}
		});
		whichShopPanel.add(btnSellShop);
		
		JLabel lblWhichShopInfo = new JLabel("Would You Like To Buy Or Sell, Items And Monsters?");
		lblWhichShopInfo.setForeground(new Color(0, 0, 128));
		lblWhichShopInfo.setBounds(150, 200, 550, 30);
		lblWhichShopInfo.setFont(new Font("", Font.PLAIN, 20));
		whichShopPanel.add(lblWhichShopInfo);
	}
	
	
	/**
	 * Creates a JPanel with components to display a message of the random events that occurred over night.
	 * The method is called only when the player clicks the next day button in the sleep panel.
	 */
	public void createRandomEventMessage() {
		sleepPanel.setEnabled(false);
		JPanel panelMessage = new JPanel();
		panelMessage.setBounds(256, 207, 321, 176);
		frame.getContentPane().add(panelMessage);
		panelMessage.setVisible(true);
		panelMessage.setLayout(null);
		
		JLabel lblRandomEventMessage = new JLabel("<html>" + randomEventMessage.replaceAll("\n", "<br/>") + "</html>", SwingConstants.CENTER);
		lblRandomEventMessage.setForeground(new Color(0, 0, 128));
		lblRandomEventMessage.setBounds(6, 6, 309, 123);
		panelMessage.add(lblRandomEventMessage);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(0, 0, 128));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchHomeScreen();
				closeWindow();
			}
		});
		btnOk.setBounds(235, 141, 80, 30);
		panelMessage.add(btnOk);
	}
	
	
	/**
	 * Creates a JPanel with the a message telling the user they can not battle.
	 * This methdo is only called if the players monsters are all at zero health.
	 */
	public void createCantBattleMessage() {
		JPanel cantBattleMessagePanel = new JPanel();
		cantBattleMessagePanel.setBounds(256, 207, 321, 176);
		mainPanel.add(cantBattleMessagePanel);
		cantBattleMessagePanel.setVisible(false);
		cantBattleMessagePanel.setLayout(null);
		this.cantBattleMessagePanel = cantBattleMessagePanel;
		
		JLabel lblRandomEventMessage = new JLabel("<html>" + "Your monsters do not have enough health to battle."
				+ "\nPlease heal your monsters." + "</html>", SwingConstants.CENTER);
		lblRandomEventMessage.setForeground(new Color(0, 0, 128));
		lblRandomEventMessage.setBounds(6, 6, 309, 123);
		cantBattleMessagePanel.add(lblRandomEventMessage);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(0, 0, 128));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cantBattleMessagePanel.setVisible(false);
			}
		});
		btnOk.setBounds(235, 141, 80, 30);
		cantBattleMessagePanel.add(btnOk);
	}
	
	
	/**
	 * Creates a JPanel that shows the users that they are sleeping. The method calls a method checkIfGameEnd from GameEnvironment
	 * and if returned true the method will launch the end screen. Otherwise the method will call RandomEvents and go on to the 
	 * next day. Also calls createRandomEventMessage to display the random events message.
	 */
	public void createSleepJPanel() {
		JPanel sleepPanel = new JPanel();
		sleepPanel.setBackground(new Color(128, 128, 128));
		sleepPanel.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(sleepPanel);
		sleepPanel.setVisible(false);
		sleepPanel.setLayout(null);
		this.sleepPanel = sleepPanel;
		
		JLabel lblSleeping = new JLabel("Sleeping...");
		lblSleeping.setForeground(new Color(255, 255, 224));
		lblSleeping.setBounds(198, 224, 469, 129);
		lblSleeping.setFont(new Font("Serif", Font.BOLD, 100));
		sleepPanel.add(lblSleeping);
		
		JButton btnNextDay = new JButton("Next Day");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnv.checkIfEndGame()) {
					manager.launchEndScreen();
					closeWindow();
				} else {
					randomEventMessage = randomEvents.allRandomEvents(player, gameEnv.buyMonsterShopSetUp());
					player.nextDay();
					sleepPanel.setVisible(false);
					createRandomEventMessage();
				}
			}
		});
		btnNextDay.setForeground(new Color(0, 0, 128));
		btnNextDay.setBounds(615, 470, 117, 29);
		sleepPanel.add(btnNextDay);
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
		
		// initializing all Panels
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 224));
		mainPanel.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		this.mainPanel = mainPanel;
		
		createCantBattleMessage();
		createWhichShopJPanel();
		createSleepJPanel();
		
		// Display stuff for the mainPanel
		JLabel displayPlayerScore = new JLabel("Score: " + player.getScore());
		displayPlayerScore.setForeground(new Color(0, 0, 128));
		displayPlayerScore.setBounds(200, 25, 120, 20);
		mainPanel.add(displayPlayerScore);
		
		JLabel displayPlayerGold = new JLabel("Gold: " + player.getGold());
		displayPlayerGold.setForeground(new Color(0, 0, 128));
		displayPlayerGold.setBounds(300, 25, 120, 20);
		mainPanel.add(displayPlayerGold);
		
		JLabel displayCurrentDay = new JLabel("Day: " + player.getCurrentDay());
		displayCurrentDay.setForeground(new Color(0, 0, 128));
		displayCurrentDay.setBounds(440, 25, 60, 20);
		mainPanel.add(displayCurrentDay);
		
		JLabel displayDaysRemaining = new JLabel("Days left: " + player.getDaysLeft());
		displayDaysRemaining.setForeground(new Color(0, 0, 128));
		displayDaysRemaining.setBounds(500, 25, 90, 20);
		mainPanel.add(displayDaysRemaining);
		
		JButton btnViewMonsters = new JButton("Monsters");
		btnViewMonsters.setForeground(new Color(0, 0, 128));
		btnViewMonsters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchPlayerMonsterScreen();
				closeWindow();
			}
		});
		btnViewMonsters.setBounds(300, 75, 200, 60);
		mainPanel.add(btnViewMonsters);
		
		JButton btnViewInventory = new JButton("Inventory");
		btnViewInventory.setForeground(new Color(0, 0, 128));
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchInventoryScreen();
				closeWindow();
			}
		});
		btnViewInventory.setBounds(300, 175, 200, 60);
		mainPanel.add(btnViewInventory);
		
		JButton btnViewShop = new JButton("Shop");
		btnViewShop.setForeground(new Color(0, 0, 128));
		btnViewShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whichShopPanel.setVisible(true);
				mainPanel.setVisible(false);
			}
		});
		btnViewShop.setBounds(300, 275, 200, 60);
		mainPanel.add(btnViewShop);
		
		JButton btnViewBattles = new JButton("Upcoming Battles");
		btnViewBattles.setForeground(new Color(0, 0, 128));
		btnViewBattles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnv.checkIfPlayerCantBattle()) {
					cantBattleMessagePanel.setVisible(true);
				} else {
					manager.launchBattleSelectionScreen();
					closeWindow();
				}
			}
		});
		btnViewBattles.setBounds(300, 375, 200, 60);
		mainPanel.add(btnViewBattles);
		
		JButton btnSleep = new JButton("Sleep");
		btnSleep.setForeground(new Color(0, 0, 128));
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sleepPanel.setVisible(true);
				mainPanel.setVisible(false);
				gameEnv.systemMonstersSetUp();
			}
		});
		btnSleep.setBounds(300, 475, 200, 60);
		mainPanel.add(btnSleep);
		
	}
}
