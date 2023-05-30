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

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;
import monsterfighter.ui.ScreenManager;

/**
 * The InventoryScreen class is the GUI for the inventory screen. Displays all items that the player can use.
 */
public class InventoryScreen {

	private JFrame frame;
	private ScreenManager manager;
	private Player player;
	private ArrayList<JButton> monsterButtons = new ArrayList<>();
	private List<JPanel> panelsToDisable = new ArrayList<>();
	private int xAxis = 80;
	private int yAxis = 100;
	private Item selectedItem;

	
	/**
	 * Create the application.
	 * 
	 * @param incomingManager ScreenManager passed in from ScreenManager.
	 */
	public InventoryScreen(ScreenManager incomingManager) {
		manager = incomingManager;
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
	 * launches the homeScreen and calls closeWindow.
	 */
	public void backHome() {
		manager.launchHomeScreen();
		closeWindow();
	}
	
	
	/**
	 * For all JPanels in the list of JPanels in panelsToDisable. The method will disable all componets in each
	 * of the JPanels.
	 */
	public void disableComponents() {
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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 224));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelMonsterSelection = new JPanel();
		panelMonsterSelection.setBounds(620, 0, 180, 600);
		panelMonsterSelection.setBackground(new Color(255, 255, 224));
		frame.getContentPane().add(panelMonsterSelection);
		panelMonsterSelection.setVisible(true);
		panelMonsterSelection.setLayout(null);
		
		JPanel panelMessage = new JPanel();
		panelMessage.setBounds(256, 207, 321, 176);
		frame.getContentPane().add(panelMessage);
		panelMessage.setVisible(false);
		panelMessage.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 224));
		panelMain.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(panelMain);
		panelMain.setVisible(true);
		panelMain.setLayout(null);
		panelsToDisable.add(panelMain);
		
		//If the button is clicked it goes to the HomeSceen.
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(0, 0, 128));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backHome();
			}
		});
		btnBack.setBounds(6, 6, 117, 29);
		panelMain.add(btnBack);
		
		//The following two blocks of code create components in the message panel.
		JLabel lblUsedMessage = new JLabel("Message", SwingConstants.CENTER);
		lblUsedMessage.setForeground(new Color(0, 0, 128));
		lblUsedMessage.setBounds(6, 6, 309, 123);
		panelMessage.add(lblUsedMessage);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(0, 0, 128));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchInventoryScreen();
				closeWindow();
			}
		});
		btnOk.setBounds(235, 141, 80, 30);
		panelMessage.add(btnOk);
		
		//Creates a JPanel for each item that the player owns.
		for (Item item: player.getItemList()) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(new LineBorder(new Color(0, 0, 128), 2));
			panelItem.setBounds(xAxis, 170, 150, 200);
			panelItem.setLayout(null);
			panelMain.add(panelItem);
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
			
			JButton btnUse = new JButton("Use Item");
			btnUse.setForeground(new Color(0, 0, 128));
			btnUse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedItem = item;
					//Once the use item button is clicked all JButtons in monsterButtons are setEnabled(true).
					monsterButtons.forEach((JButton monster) -> {monster.setEnabled(true);});
					btnUse.setVisible(false);
					disableComponents();
				}
			});
			
			btnUse.setBounds(10, 165, 130, 29);
			panelItem.add(btnUse);
			
			xAxis += 180;
		}
		
		//Creates a total of four JButtons. The JButtons are first created for every monster that the player owns
		//then if there are empty slots then the method creates empty JButtons. All buttons are setEnabled(false).
		ArrayList<Monster> monsters = player.getMonsterList();
		for (int i = 0; i < 4; i++) {
			if (i < monsters.size()) {
				Monster monster = monsters.get(i);
				JButton btnMonster = new JButton(monster.getMonsterName());
				btnMonster.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//If the heal item wants to be used. It checks to see if the monster is already a full HP.
						if (selectedItem.getName() == "Heal" && monster.getCurrentHealth() == monster.getMaxHealth()) {
							lblUsedMessage.setText(monster.getMonsterName() + " is already max health!");
							panelMessage.setVisible(true);
						} else {
							player.useItem(monster, selectedItem);
							lblUsedMessage.setText("Item used successfully");
							player.removeItem(selectedItem);
							panelMessage.setVisible(true);
						}
					}
				});
				btnMonster.setBounds(10, yAxis, 150, 29);
				panelMonsterSelection.add(btnMonster);
				btnMonster.setEnabled(false);
				monsterButtons.add(btnMonster);
				yAxis += 100;
			} else {
				// Creates empty buttons if there a no more monsters.
				JButton btnEmptyMonster = new JButton("Empty");
				btnEmptyMonster.setBounds(10, yAxis, 150, 29);
				panelMonsterSelection.add(btnEmptyMonster);
				btnEmptyMonster.setEnabled(false);
				yAxis += 100;
			}
		}
	}
}