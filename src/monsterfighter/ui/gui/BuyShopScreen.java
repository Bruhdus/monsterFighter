package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import monsterfighter.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;
import monsterfighter.core.Purchasable;
import monsterfighter.ui.ScreenManager;

/**
 * The BuyShopScreen class is the GUI for the buy shop Screen. Displays all monsters and items that can be brought.
 */

public class BuyShopScreen {

	private JFrame frame;
	private ScreenManager manager;
	private GameEnvironment gameEnv;
	private Player player;
	private Purchasable purchasable = new Purchasable();
	private List<JPanel> panelsToDisable = new ArrayList<>();
	private JButton backButton;
	private JLabel buyMessage;
	private JPanel panelMessage;
	private JPanel panelMain;
	private int xAxis;
	
	/**
	 * Create the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public BuyShopScreen(ScreenManager incomingManager) {
		manager = incomingManager;
		gameEnv = manager.getGameEnv();
		player = manager.getPlayer();
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
	 * For all JPanels in the list of JPanels in panelsToDisable. The method will disable all componets in each
	 * of the JPanels.
	 */
	public void disableComponents() {
		backButton.setEnabled(false);
		for (JPanel panel : panelsToDisable) {
			Component[] components = panel.getComponents();
			for (Component component : components) {
				component.setEnabled(false);
			}
		}
	}
	
	
	/**
	 * Creates JPanels for every monster in the list and dislays them on the screen.
	 * 
	 * @param monsterList a list of monsters available to the shop for the day.
	 */
	public void displayMonsters(ArrayList<Monster> monsterList) {
		xAxis = 50;
		for (Monster monster: monsterList) {
			JPanel panelMonster = new JPanel();
			panelMonster.setBorder(new LineBorder(new Color(0, 0, 128), 2));
			panelMonster.setBounds(xAxis, 60, 150, 200);
			panelMain.add(panelMonster);
			panelMonster.setLayout(null);
			panelsToDisable.add(panelMonster);
			
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
			
			JLabel lblMonsterCost = new JLabel("Cost: " + Integer.toString(monster.getCost()), SwingConstants.CENTER);
			lblMonsterCost.setForeground(new Color(0, 0, 128));
			lblMonsterCost.setBounds(0, 140, 150, 16);
			panelMonster.add(lblMonsterCost);
			
			JButton btnBuy = new JButton("Buy Monster");
			btnBuy.setForeground(new Color(0, 0, 128));
			btnBuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message = purchasable.buyMonster(player, monster);
					buyMessage.setText("<html><div style='text-align: center;'>"+message+ "</html>");
					panelMessage.setVisible(true);
					btnBuy.setVisible(false);
					disableComponents();
				}
			});
			
			btnBuy.setBounds(10, 165, 130, 29);
			panelMonster.add(btnBuy);
			
			xAxis += 180;
		}
	}
	
	
	/**
	 * Creates JPanels for every item in the list and dislays them on the screen.
	 * 
	 * @param itemList List of items available to the player.
	 */
	public void displayItems(ArrayList<Item> itemList) {
		xAxis = 50;
		for (Item item: itemList) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(new LineBorder(new Color(0, 0, 128), 2));
			panelItem.setBounds(xAxis, 300, 150, 200);
			panelMain.add(panelItem);
			panelItem.setLayout(null);
			panelsToDisable.add(panelItem);
			
			JLabel lblItemName = new JLabel(item.getName(), SwingConstants.CENTER);
			lblItemName.setForeground(new Color(0, 0, 128));
			lblItemName.setBounds(0, 20, 150, 16);
			panelItem.add(lblItemName);
			
			JLabel lblItemInfo = new JLabel("<html><div style='text-align: center;'>"+"Info: " + item.getInformation() + "</html>");
			lblItemInfo.setForeground(new Color(0, 0, 128));
			lblItemInfo.setBounds(6, 40, 138, 110);
			panelItem.add(lblItemInfo);
			
			JLabel lblItemCost = new JLabel("Cost: " + item.getCost(), SwingConstants.CENTER);
			lblItemCost.setForeground(new Color(0, 0, 128));
			lblItemCost.setBounds(0, 150, 150, 16);
			panelItem.add(lblItemCost);
			
			JButton btnBuy = new JButton("Buy Item");
			btnBuy.setForeground(new Color(0, 0, 128));
			btnBuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message = purchasable.buyItem(player, item);
					buyMessage.setText("<html><div style='text-align: center;'>"+message+ "</html>");
					panelMessage.setVisible(true);
					btnBuy.setVisible(false);
					disableComponents();
				}
			});
			
			btnBuy.setBounds(10, 165, 130, 29);
			panelItem.add(btnBuy);
			
			xAxis += 180;
		}
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelMessage = new JPanel();
		panelMessage.setBounds(256, 207, 321, 176);
		frame.getContentPane().add(panelMessage);
		panelMessage.setVisible(false);
		panelMessage.setLayout(null);
		this.panelMessage = panelMessage;
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 224));
		panelMain.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(panelMain);
		this.panelMain = panelMain;
		
		
		JLabel lblCurrentGold = new JLabel("Gold: " + player.getGold());
		lblCurrentGold.setForeground(new Color(0, 0, 128));
		lblCurrentGold.setBounds(180, 11, 135, 16);
		panelMain.add(lblCurrentGold);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(0, 0, 128));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchHomeScreen();
				closeWindow();
			}
		});
		panelMain.setLayout(null);
		btnBack.setBounds(6, 6, 117, 29);
		panelMain.add(btnBack);
		backButton = btnBack;
		
		//The following two blocks of code create componenets in the message panel.
		JLabel lblBuyMessage = new JLabel("Message", SwingConstants.CENTER);
		lblBuyMessage.setForeground(new Color(0, 0, 128));
		lblBuyMessage.setBounds(6, 6, 309, 123);
		panelMessage.add(lblBuyMessage);
		buyMessage = lblBuyMessage;
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(0, 0, 128));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchBuyShopScreen();;
				closeWindow();
			}
		});
		btnOk.setBounds(235, 141, 80, 30);
		panelMessage.add(btnOk);
		
		// method used to display monsters and items.
		displayMonsters(gameEnv.buyMonsterShopSetUp());
		displayItems(gameEnv.buyItemShopSetUp());
	}
	
}
