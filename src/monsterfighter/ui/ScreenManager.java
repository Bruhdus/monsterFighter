package monsterfighter.ui;

import monsterfighter.GameEnvironment;
import monsterfighter.core.*;
import monsterfighter.ui.gui.*;

/**
 * The ScreenManager class opens and closes GUI windows.
 */

public class ScreenManager {
	private GameEnvironment gameEnv;
	private Player player;
	private Monster[] startingMonsters;
	
	
	/**
	 * Initialize GameEnvironment and Player. The method is only called by GameEnvironment.
	 * 
	 * @param newGame A Object GameEnvironment
	 * @param newPlayer A Object Player 
	 */
	public void screenManagerSetUp(GameEnvironment newGame, Player newPlayer) {
		gameEnv = newGame;
		player = newPlayer;
	}
	
	
	/**
	 * Returns player.
	 * 
	 * @return player.
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Returns the object GameEnvironment.
	 * 
	 * @return gameEnv.
	 */
	
	public GameEnvironment getGameEnv() {
		return gameEnv;
	}
	
	
	/**
	 * Returns the list of starting monsters.
	 * 
	 * @return startingMonsters a list of monsters.
	 */
	public Monster[] getStartingMonsters() {
		return startingMonsters;
	}
	
	
	/**
	 * Launches the HomeScreen.
	 */
	public void launchHomeScreen() {
		HomeScreen homeScreen = new HomeScreen(this);
	}
	
	
	/**
	 * Launches the LoginScreeen and sets startingMonster to a monsterArray given by the class GameEnvironment.
	 * 
	 * @param monsterArray A List of starting Monters given by GameEnvironment.
	 */
	public void launchLoginScreen(Monster[] monsterArray) {
		LoginScreen loginScreen = new LoginScreen(this);
		startingMonsters = monsterArray;
	}
	
	
	/**
	 * Launches the PlayerMonsterScreen.
	 */
	public void launchPlayerMonsterScreen() {
		PlayerMonsterScreen playerMonsterScreen =  new PlayerMonsterScreen(this);
	}
	
	
	/**
	 * Launches the InventoryScreen.
	 */
	public void launchInventoryScreen() {
		InventoryScreen inventoryScreen = new InventoryScreen(this);
	}
	
	
	/**
	 * Launches the SellShopScreen.
	 */
	public void launchSellShopScreen() {
		SellShopScreen sellShopScreen = new SellShopScreen(this);
	}
	
	
	/**
	 * Launches the BuyShopScreen.
	 */
	public void launchBuyShopScreen() {
		BuyShopScreen buyShopScreen = new BuyShopScreen(this);
	}
	
	
	/**
	 * Launches the EndScreen.
	 */
	public void launchEndScreen() {
		EndScreen endScreen = new EndScreen(this);
	}
	
	
	/**
	 * Launches the BattleSelectionScreen.
	 */
	public void launchBattleSelectionScreen() {
		BattleSelectScreen battleSelectScreen = new BattleSelectScreen(this);
	}
	
	
	/**
	 * Launches the BattleScreen.
	 */
	public void launchBattleScreen() {
		BattleScreen battleScreen = new BattleScreen(this);
	}
	
	
	/**
	 * Calls gameSetup from GameEnvironment and calls launchHomeScreen and closes LoginScreen.
	 * 
	 * @param loginScreen the GUI class.
	 */
	public void closeLoginScreen(LoginScreen loginScreen) {
		gameEnv.gameSetup(loginScreen.getName(), loginScreen.getNumDays(),loginScreen.getDifficulty(), loginScreen.getStartingMonster());
		launchHomeScreen();
		loginScreen.closeWindow();
	}
	
	
	/**
	 * Closes the HomeScreen.
	 * 
	 * @param homeScreen the GUI class.
	 */
	public void closeHomeScreen(HomeScreen homeScreen) {
		homeScreen.closeWindow();
	}
	
}
