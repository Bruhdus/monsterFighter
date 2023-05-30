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
 * The SellShopScreen class is a GUI for the sell shop screen and displays all monsters and items that the 
 * player owns and the player can sell those items and monsters.
 */

public class SellShopScreen {

	private JFrame frame;
	private ScreenManager manager;
	private Player player;
	private GameEnvironment gameEnv;
	private Purchasable purchasable = new Purchasable();
	private List<JPanel> panelsToDisable = new ArrayList<>();
	private JButton backButton;
	

	/**
	 * Create the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public SellShopScreen(ScreenManager incomingManager) {
		manager = incomingManager;
		gameEnv = manager.getGameEnv();
		player = manager.getPlayer();
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<Monster> playerMonsterList = player.getMonsterList();
		ArrayList<Item> playerItemList = player.getItemList();
		int xAxis = 50;
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 224));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelMessage = new JPanel();
		panelMessage.setBounds(256, 207, 321, 176);
		frame.getContentPane().add(panelMessage);
		panelMessage.setVisible(false);
		panelMessage.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 224));
		panelMain.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(panelMain);

		
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
		
		JLabel lblCurrentGold = new JLabel("Gold: " + player.getGold());
		lblCurrentGold.setForeground(new Color(0, 0, 128));
		lblCurrentGold.setBounds(180, 11, 135, 16);
		panelMain.add(lblCurrentGold);
		
		//The following two blocks of code create components in the message panel
		JLabel lblSellMessage = new JLabel("Message", SwingConstants.CENTER);
		lblSellMessage.setForeground(new Color(0, 0, 128));
		lblSellMessage.setBounds(6, 6, 309, 123);
		panelMessage.add(lblSellMessage);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(0, 0, 128));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnv.checkIfEndGame()) {
					manager.launchEndScreen();
				} else {
					manager.launchSellShopScreen();
				}
				closeWindow();
			}
		});
		btnOk.setBounds(235, 141, 80, 30);
		panelMessage.add(btnOk);
		
		// Creates JPanels for each monster that the player owns.
		for (Monster monster: playerMonsterList) {
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
			
			JLabel lblMonsterValue = new JLabel("Value: " + Integer.toString(monster.getValue()), SwingConstants.CENTER);
			lblMonsterValue.setForeground(new Color(0, 0, 128));
			lblMonsterValue.setBounds(0, 140, 150, 16);
			panelMonster.add(lblMonsterValue);
			
			JButton btnSell = new JButton("Sell Monster");
			btnSell.setForeground(new Color(0, 0, 128));
			btnSell.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sellMessage = purchasable.sellMonster(player, monster);
					lblSellMessage.setText(sellMessage);
					panelMessage.setVisible(true);
					btnSell.setVisible(false);
					disableComponents();
				}
			});
			
			btnSell.setBounds(10, 165, 130, 29);
			panelMonster.add(btnSell);
			
			xAxis += 180;
		}
		
		
		//Creates JPanels for every item that the player owns.
		xAxis = 50;
		for (Item item: playerItemList) {
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
			
			JLabel lblItemValue = new JLabel("Value: " + item.getValue(), SwingConstants.CENTER);
			lblItemValue.setForeground(new Color(0, 0, 128));
			lblItemValue.setBounds(0, 150, 150, 16);
			panelItem.add(lblItemValue);
			
			JButton btnSell = new JButton("Sell Item");
			btnSell.setForeground(new Color(0, 0, 128));
			btnSell.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sellMessage = purchasable.sellItem(player, item);
					lblSellMessage.setText(sellMessage);
					panelMessage.setVisible(true);
					btnSell.setVisible(false);
					disableComponents();
				}
			});
			
			btnSell.setBounds(10, 165, 130, 29);
			panelItem.add(btnSell);
			
			xAxis += 180;
		}
		
	}
}
