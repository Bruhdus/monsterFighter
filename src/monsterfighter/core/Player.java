package monsterfighter.core;
import java.util.ArrayList;

/**
 * The player class is where the player of the game information is stored. The players inventory,
 * gold, score is all stored and accessed through the player class.
 */

public class Player {
	
	// The players name which is between 3 and 15 characters and can not contain numbers or symbols.
	private String name;
	
	// The number of days the player wants the game to last. Is between 5 and 15 inclusive.
	private int days;
	
	// The current day the player is on. Increases by 1 when the player goes to sleep.
	private int currentDay = 1;
	
	// The difficulty level the player wants. Either easy or hard.
	private String difficulty;
	
	// The amount of gold the player has.
	private int gold;
	
	// The total amount of gold the player has received throughout playing the game. Used for score at end.
	private int totalGold;
	
	// The players score. 
	private int score;
	
	// The players item which are stored in a list. The player is only allowed 3 at once.
	private ArrayList<Item> itemList = new ArrayList<Item>();
	
	//The players monsters which are stored in a list. The player is only allowed 4 at once.
	private ArrayList<Monster> monsterList = new ArrayList<Monster>();
	
	
	/**
	 * Gets the players item list and returns it.
	 *
	 * @return itemList The current item list.
	 */
	public ArrayList<Item> getItemList(){
		return this.itemList;
	}
	
	
	/**
	 * Gets the players monster list and returns it.
	 *
	 * @return monsterList The current monster list.
	 */
	public ArrayList<Monster> getMonsterList(){
		return monsterList;
	}
	
	
	/**
	 * Adds the given item to the players inventory.
	 * 
	 * @param item The item which is being to be added to the player inventory.
	 */
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	
	/**
	 * Removes the item from the players inventory.
	 * 
	 * @param item The item which is being removed from the player inventory.
	 */
	public void removeItem(Item item) {
		itemList.remove(item);
	}
	
	
	/**
	 * Adds the given monster to the player inventory.
	 * 
	 * @param monster The monster which is being to be added to the player inventory.
	 */
	public void addMonster(Monster monster) {
		monsterList.add(monster);
	}
	
	
	/**
	 * Removes the monster from the players inventory.
	 * 
	 * @param monster The monster which is being removed from the player inventory.
	 */
	public void removeMonster(Monster monster) {
		monsterList.remove(monster);
	}
	
	
	/**
	 * Sets the name of the player. The name must not contain symbols or numbers and must be 3 to 15 characters long.
	 * 
	 * @param name The name of the player.
	 */
	public void setName(String name) {
		this.name = name;		
	}
	
	
	/**
	 * Sets the number of days the player want to play the game. It is between 5 and 15 inclusive.
	 * 
	 * @param days The number of days the players wants to play.
	 */
	public void setDays(int days) {
		this.days = days;
	}
	
	
	/**
	 * Sets the difficulty of the player. Either easy or hard.
	 * 
	 * @param difficulty The difficulty the player choose.
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	
	/**
	 * Returns the current day the player is on.
	 * 
	 * @return currentDay The current day the player is on. 
	 */
	public int getCurrentDay() {
		return currentDay;
	}
	
	
	/**
	 * Sets the current day the player is on.
	 * 
	 * @param currentDay The current day the player is on.
	 */
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
	
	
	/**
	 * Returns total gold the player is on.
	 * 
	 * @return totalGold The total gold the player is on.
	 */
	public int getTotalGold() {
		return this.totalGold;
	}
	
	
	/**
	 * Increases the current day the player is on by one.
	 * Gives the player 100 gold.
	 */
	public void nextDay() {
		this.currentDay++;
		for(Monster monster : this.getMonsterList()) {
			monster.dailyHeal();
		}
		this.gold += 100;
	}
	
	
	/**
	 * Returns the number of days the player has left before they finish the game.
	 * 
	 * @return daysLeft the number of days the player has left.
	 */
	public int getDaysLeft() {
		return days - currentDay;
	}
	
	
	/**
	 * Returns the players name.
	 * 
	 * @return name The players current name.
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Returns the players difficulty level.
	 * 
	 * @return difficulty The players difficulty level.
	 */
	public String getDifficulty() {
		return difficulty;
	}

	
	/**
	 * Returns how much gold the player has.
	 * 
	 * @return gold The amount of gold the player has.
	 */
	public int getGold() {
		return gold;
	}
	
	
	/**
	 * Decreases the players gold by the input amount.
	 * 
	 * @param gold The amount of gold the player will loses.
	 */
	public void minusGold(int gold) {
		this.gold -= gold;
	}
	
	
	/**
	 * Increases the players gold and total gold by the input amount.
	 * 
	 * @param gold The amount of gold the player will gain.
	 */
	public void addGold(int gold) {
		this.gold += gold;
		totalGold += gold;
	}
	
	
	/**
	 * Returns the players current score.
	 * 
	 * @return score The players current score.
	 */
	public int getScore() {
		return score;
	}
	

	/**
	 * Increases the players score by the input amount.
	 * 
	 * @param addScore The amount the players score will increase by.
	 */
	public void addScore(int addScore) {
		score += addScore;
	}
	
	
	/**
	 * Uses the input item on the input monster.
	 * 
	 * @param monster The monster the item is being used on.
	 * @param item The item that is being used.
	 */
	public void useItem(Monster monster, Item item) {
		item.use(monster);
	}

}
