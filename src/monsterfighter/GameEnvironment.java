package monsterfighter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import monsterfighter.core.*;
import monsterfighter.ui.*;

/**
 * The GameEnvironment class is where all of the logic for the GUI classes are stored. 
 */

public class GameEnvironment {
	private ScreenManager manager;
	private Player player;
	private Player systemPlayer;
	private Battle battle;
	private ArrayList<Monster> shopMonsterList;
	private ArrayList<Item> shopItemList;
	private ArrayList<Monster> systemMonsterList;
	private ArrayList<ArrayList<Monster>> battlesSelectable;
	private ArrayList<Monster> selectedBattle;
	private int selectedBattleIndex;
	
	
	/**
	 * Initialize varaibles that the GameEnvironment class needs.
	 * 
	 * @param incomingManager ScreenManager
	 * @param newPlayer Player
	 * @param newSystemPlayer Player
	 * @param newBattle Battle
	 */
	public GameEnvironment(ScreenManager incomingManager, Player newPlayer, Player newSystemPlayer, Battle newBattle){
		manager = incomingManager;
		player = newPlayer;
		systemPlayer = newSystemPlayer;
		battle = newBattle;
	}
	
	
	/**
	 * This method is called to start the game. The method creates three new Monster objects that are then added into the
	 * list startingMonsters. And then opens up the loginScreen and calls the method systemMonsterSetUp().
	 */
	public void gameStart() {
		Monster Pidgeotto = new Monster("Pidgeotto", "Common", 236, 59, 60, 200);
		Monster Raticate = new Monster("Raticate", "Common", 220, 55, 76, 200);
		Monster Nidoran = new Monster("Nidoran", "Common", 180, 45, 80, 200);
		Monster[] startingMonsters = {Pidgeotto, Raticate, Nidoran};
		manager.launchLoginScreen(startingMonsters);
		systemMonstersSetUp();
	}
	
	
	/**
	 * This method sets all the basic variables the Player class needs to start the game.
	 * If the user selected the hard difficulty then the method will add 1000 points to player.
	 * 
	 * @param name The players name.
	 * @param days How many days the players wants the game to last.
	 * @param difficulty The players chosen difficulty.
	 * @param startingMonster The players free first monster.
	 */
	public void gameSetup(String name, int days, String difficulty, Monster startingMonster) {
		player.setName(name);
		player.setDays(days);
		player.setDifficulty(difficulty);
		if (player.getDifficulty() == "Hard") {
			player.addScore(1000);
		}
		player.addGold(100);
		player.addMonster(startingMonster);
		buyMonsterShopSetUp();
	}
	
	
	/**
	 * This method determines which monsters to display, depending on the currentDay and the difficulty
	 * the player has chosen.
	 * 
	 * @return shopMonsterList A list of monsters that are used to be displayed in the buyShopScreen.
	 */
	public ArrayList<Monster> buyMonsterShopSetUp() {
		if (player.getDifficulty() == "Easy") {
			if (player.getCurrentDay() <= 5) {
				Monster Pidgeotto = new Monster("Pidgeotto", "Common", 236, 118, 60, 100);
				Monster Raticate = new Monster("Raticate", "Common", 220, 110, 76, 100);
				Monster Nidoran = new Monster("Nidoran", "Common", 180, 90, 80, 100);
				Monster Sandslash = new Monster("Sandslash", "Common", 260, 130, 55, 100);
				shopMonsterList = new ArrayList<>(Arrays.asList(Pidgeotto, Raticate, Nidoran, Sandslash));
			} else if (player.getCurrentDay() <= 10){
				Monster Pidgeotto = new Monster("Pidgeotto", "Common", 236, 118, 60, 100);
				Monster Sandslash = new Monster("Sandslash", "Common", 260, 130, 55, 100);
				Monster Machamp = new Monster("Machamp", "Rare", 384, 190, 130, 200);
				Monster Golem = new Monster("Golem", "Rare", 368, 180, 139, 200);
				shopMonsterList = new ArrayList<>(Arrays.asList(Pidgeotto, Sandslash, Machamp, Golem));
			} else {
				Monster Rapidash = new Monster("Rapidash", "Rare", 334, 160, 188, 200);
				Monster Muk = new Monster("Muk", "Rare", 414, 205, 105, 200);
				Monster Eternatus = new Monster("Eternatus", "Epic", 584, 250, 157, 350);
				Monster Rayquaza = new Monster("Rayquaza", "Epic", 514, 244, 244, 350);
				shopMonsterList = new ArrayList<>(Arrays.asList(Rapidash,Muk,Eternatus,Rayquaza));
			}
		} else {
			if (player.getCurrentDay() <= 5) {
				Monster Pidgeotto = new Monster("Pidgeotto", "Common", 236, 59, 60, 200);
				Monster Raticate = new Monster("Raticate", "Common", 220, 55, 76, 200);
				Monster Nidoran = new Monster("Nidoran", "Common", 180, 45, 80, 200);
				Monster Sandslash = new Monster("Sandslash", "Common", 260, 65, 55, 200);
				shopMonsterList = new ArrayList<>(Arrays.asList(Pidgeotto, Raticate, Nidoran, Sandslash));
			} else if (player.getCurrentDay() <= 10){
				Monster Pidgeotto = new Monster("Pidgeotto", "Common", 236, 59, 60, 200);
				Monster Sandslash = new Monster("Sandslash", "Common", 260, 65, 55, 200);
				Monster Machamp = new Monster("Machamp", "Rare", 384, 80, 130, 400);
				Monster Golem = new Monster("Golem", "Rare", 368, 90, 139, 400);
				shopMonsterList = new ArrayList<>(Arrays.asList(Pidgeotto, Sandslash, Machamp, Golem));
			} else {
				Monster Rapidash = new Monster("Rapidash", "Rare", 334, 80, 188, 400);
				Monster Muk = new Monster("Muk", "Rare", 414, 102, 105, 400);
				Monster Eternatus = new Monster("Eternatus", "Epic", 584, 125, 157, 700);
				Monster Rayquaza = new Monster("Rayquaza", "Epic", 514, 122, 244, 700);
				shopMonsterList = new ArrayList<>(Arrays.asList(Rapidash,Muk,Eternatus,Rayquaza));
			}
		}
		return shopMonsterList;
	}
	
	
	/**
	 * The method determines all items attributes depending on the difficulty the player has chosen.
	 * 
	 * @return A list of items that are used to be displayed in the buyShopScreen.
	 */
	public ArrayList<Item> buyItemShopSetUp(){
		if (player.getDifficulty() == "Easy") {
			Item shield = new Shield("Shield", 20);
			Item weapon = new Weapon("Weapon", 20, 10);
			Item heal = new HealthHeal("Heal", 20, 100);
			Item levelUp = new LevelUp("Level Up", 40, 1.15);
			shopItemList = new ArrayList<>(Arrays.asList(shield, weapon, heal, levelUp));
		} else {
			Item shield = new Shield("Shield", 30);
			Item weapon = new Weapon("Weapon", 30, 5);
			Item heal = new HealthHeal("Heal", 35, 100);
			Item levelUp = new LevelUp("Level Up", 60, 1.1);
			shopItemList = new ArrayList<>(Arrays.asList(shield, weapon, heal, levelUp));
		}
		return shopItemList;
	}
	
	
	/**
	 * Checks if the player has no monster and if the player does not have enough gold to buy another monster.
	 * Also checks if all days have passed in the game. and if any of the two conditions are true the 
	 * method calls screenManager to launch the end screen.
	 * 
	 * @return true if the game needs to end false otherwise
	 */
	public boolean checkIfEndGame() {
		Monster lowestCostMonster = shopMonsterList.stream().min(Comparator.comparingInt(Monster::getCost)).get();
		if((player.getMonsterList().size() == 0 && player.getGold() < lowestCostMonster.getCost()) || player.getDaysLeft() == 0) {
			manager.launchEndScreen();
			return true;
		}
		return false;
	}
	
	
	/**
	 * Checks if the player name has 3-15 characters and if the name is only letters.
	 * 
	 * @param playerName a string with the players name
	 * @return true if the name is valid
	 */
	public boolean checkName(String playerName) {
		if (playerName.length() >= 3 && playerName.length() <= 15) {
			for (int i = 0; i < playerName.length(); i++) {
				if ((Character.isLetter(playerName.charAt(i))) == false) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Checks if the number of days entered is between 5-15.
	 * 
	 * @param numDays integer representation of the number of days the player entered.
	 * @return true if the number of days entered is valid.
	 */
	public boolean checkNumDays(int numDays) {
		if (numDays >= 5 && numDays <= 15 ) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * The method checks if the players name entered and the number of days entered are both valid.
	 * 
	 * @param playersName is a string that the user will have entered.
	 * @param numOfDays is a string that the user will have entered.
	 * @return error messages if any.
	 */
	public String loginDetailsCheck(String playersName, String numOfDays) {
		String errorMessage = "";
		if (playersName.equals("")) {
			errorMessage += "Please enter a name.";
		} else if (numOfDays.equals("")) {
			errorMessage += "Please enter the number of days.";
		} else {
			int numDays = 0;
			try {
				numDays = Integer.parseInt(numOfDays);
			} catch (NumberFormatException e){
				errorMessage += "Number of days must be a number. ";
			}
			if (checkNumDays(numDays) == false) {
				errorMessage += "The number of days must be between 5-15 days.";
			}
			if (checkName(playersName) == false ) {
				errorMessage += "Your name must be between 3-15 letters and must not contain any numbers or symbols.";
			}
		}
		return errorMessage;
	}
	
	
	/**
	 * The method checks if the all of players monsters are at zero health. If so the method returns true and
	 * returns false otherwise.
	 * 
	 * @return monstersHealZero true if the player can not battle and false otherwise.
	 */
	public boolean checkIfPlayerCantBattle() {
		boolean monstersHealZero = true;
		for (Monster monster: player.getMonsterList()) {
			if (monster.getCurrentHealth() != 0) {
				monstersHealZero = false;
			}
		}
		return monstersHealZero;
	}
	
	
	/**
	 * Depending on the difficulty setting the method creates monsters that are to be set to
	 * the systems monster list. So the monsters that the system will be battling with.
	 * 
	 * @return battlesSelectable a list of 3lists that contain the 3 possible battles that the player can battle.
	 */
	public ArrayList<ArrayList<Monster>> systemMonstersSetUp(){
		if (player.getDifficulty() == "Easy") {
			Monster Blipbug = new Monster("Blipbug", "Common", 186, 68, 40, 100);
			Monster Sunkern = new Monster("Sunkern", "Common", 170, 70, 56, 100);
			Monster Snom = new Monster("Snom", "Common", 130, 80, 60, 100);
			Monster Cosmog = new Monster("Cosmog", "Common", 240, 110, 29, 100);
			Monster Wurmple = new Monster("Wurmple", "Common", 210, 110, 35, 100);
			Monster Pidgy = new Monster("Pidgy", "Common", 234, 80, 37, 100);
			Monster Silcoon = new Monster("Silcoon", "Common", 260, 110, 23, 100);
			Monster Magikarp = new Monster("Magikarp", "Common", 185, 88, 12, 100);
			systemMonsterList = new ArrayList<>(Arrays.asList(Blipbug,Sunkern,Snom,Cosmog,Wurmple,Pidgy,Silcoon,Magikarp));
		} else {
			Monster Blipbug = new Monster("Blipbug", "Rare", 286, 126, 90, 200);
			Monster Sunkern = new Monster("Sunkern", "Rare", 270, 140, 112, 200);
			Monster Snom = new Monster("Snom", "Rare", 230, 160, 120, 200);
			Monster Cosmog = new Monster("Cosmog", "Rare", 340, 170, 89, 200);
			Monster Wurmple = new Monster("Wurmple", "Rare", 310, 155, 70, 200);
			Monster Pidgy = new Monster("Pidgy", "Rare", 334, 146, 83, 200);
			Monster Silcoon = new Monster("Silcoon", "Rare", 360, 168, 48, 200);
			Monster Magikarp = new Monster("Magikarp", "Rare", 285, 159, 56, 200);
			systemMonsterList = new ArrayList<>(Arrays.asList(Blipbug,Sunkern,Snom,Cosmog,Wurmple,Pidgy,Silcoon,Magikarp));
		}
		battlesSelectable = battle.battleGeneration(player, systemMonsterList);
		return battlesSelectable;
	}
	
	
	/**
	 * The method gets a list of monsters and returns a list of the given monsters name only.
	 * The method is used to display the names of monsters in the upcoming battles in BattleSelectionScreen.
	 * 
	 * @param monsters is a array list of monsters.
	 * @return a list of monster names.
	 */
	public String getBattleLineUpString(ArrayList<Monster> monsters) {
		return String.join(", ", monsters.stream().map(Monster::getMonsterName).toList());
	}
	
	
	/**
	 * The method sets selectedBattle to a list from battleSelectable at the given index (selectedBattleIndex). 
	 */
	public void setSelectedBattle() {
		selectedBattle = battlesSelectable.get(selectedBattleIndex);
	}

	
	/**
	 * The method sets selectedBattleIndex to a index that was passed through from BattleSelectScreen.
	 * 
	 * @param index represents the selected list of monsters index.
	 */
	public void setSelectedBattleIndex(int index) {
		selectedBattleIndex = index;
	}
	
	
	/**
	 * Once a battle is complete this method is called to remove the list of monsters that was just
	 * in battle from the battlesSelectable list, so that the same list of monsters can not be fought
	 * again.
	 */
	public void setBattleComplete() {
		battlesSelectable.get(selectedBattleIndex).removeAll(selectedBattle);
	}
	
	
	/**
	 * The method is called to start the battle. The method clears the list of monster that the systemPlayer had
	 * from a previous fight, if any. Then adds new monsters to systemPlayer monsterList. Then calls the
	 * completeFight method from Battle class to start the battle and get a string with the battle log.
	 * 
	 * @return a string with the battle log.
	 */
	public String startBattle() {
		systemPlayer.getMonsterList().clear();
		for (Monster m: selectedBattle) {
			systemPlayer.addMonster(m);
		}
		return battle.completeFight(player, systemPlayer);
	}
	
	
	/**
	 * Returns a list of lists of monsters that the player can battle.
	 * 
	 * @return a list of lists of monsters.
	 */
	public ArrayList<ArrayList<Monster>> getBattlesSelectable(){
		return battlesSelectable;
	}
	
	
	/**
	 * The method swaps the position of two monsters in the list of monsters that the player owns.
	 * 
	 * @param swapPosOne monster that is going to be swapped.
	 * @param swapPosTwo monster being swapped with.
	 */
	public void swapMonsterPositions(Monster swapPosOne, Monster swapPosTwo) {
		Collections.swap(player.getMonsterList(), player.getMonsterList().indexOf(swapPosOne), player.getMonsterList().indexOf(swapPosTwo));
	}
	
	
	/**
	 * Main method to start running the game.
	 * 
	 * @param args parameters passed into the main function from the command line.
	 */
	public static void main(String[] args) {
		Player player = new Player();
		Player systemPlayer = new Player();
		ScreenManager screenManager = new ScreenManager();
		Battle battle = new Battle();
		GameEnvironment gameEnvironment = new GameEnvironment(screenManager, player, systemPlayer, battle);
		screenManager.screenManagerSetUp(gameEnvironment, player);
		gameEnvironment.gameStart();
	}
	
}