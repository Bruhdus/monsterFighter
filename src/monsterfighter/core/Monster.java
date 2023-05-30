package monsterfighter.core;

/**
 * The monster class is where all of the monsters information is keep. The monsters health, value, 
 * name, rarity, etc is all accessed and stored through the monster class.
 *
 */
public class Monster {
	
	// The maximum health for a monster.
	private int maxHealth;
	
	// The damage a monster does for each attack.
	private int damage;
	
	// The amount a monster heals each night.
	private int dailyHeal;
	
	// The current health a monster is on.
	private int currentHealth;
	
	// The name of the monster.
	private String monsterName;
	
	// How rare the monster is.
	private String rarity;
	
	// How much a monster costs in gold.
	private int cost;
	
	// How much a monster is worth in gold.
	private int value;
	
	// How many hits a monster can take before taking damage.
	private int shield;
	
	/**
	 * The constructor method for the monster class. Sets the monsters name, maximum health, daily heal, 
	 * damage, rarity, cost from input. The value of value is half the cost.
	 * 
	 * @param monsterName The monsters name.
	 * @param rarity How rare a monster is.
	 * @param maxHealth The maximum health of a monster.
	 * @param dailyHeal How much a monster heals each night.
	 * @param damage The damage a monster does for each attack.
	 * @param cost The cost of the monster.
	 */
	public Monster(String monsterName, String rarity, int maxHealth, int dailyHeal, int damage, int cost){
		this.monsterName = monsterName;
		this.rarity = rarity;
		this.maxHealth = maxHealth;
		this.dailyHeal = dailyHeal;
		this.damage = damage;
		this.cost = cost;
		this.currentHealth = maxHealth;
		this.value = (int) (cost * 0.5);
	}
	
	
	/**
	 * Sets the maximum health of the monster.
	 * 
	 * @param maxHealth The maximum health of the monster.
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	
	/**
	 * Sets the damage that the monster does for each attack.
	 * 
	 * @param damage The damage a monster does for each attack.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
	/**
	 * Sets the daily health which is the amount he monster heals each day.
	 * 
	 * @param dailyHeal The amount the monster heals each day.
	 */
	public void setDailyHealth(int dailyHeal) {
		this.dailyHeal = dailyHeal;
	}
	
	
	/**
	 * Sets the current health of the monster.
	 * 
	 * @param currentHealth The current health of the monster.
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	
	/**
	 * Sets the monster name.
	 * 
	 * @param monsterName The monsters name.
	 */
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	
	
	/**
	 * Sets the rarity of the monster.
	 * 
	 * @param monsterRarity The rarity of the monster.
	 */
	public void setRarity(String monsterRarity) {
		rarity = monsterRarity;
	}
	
	
	/**
	 * Sets the cost and value of the monster. The value is half of the cost.
	 * 
	 * @param cost The cost of the monster.
	 */
	public void setCost(int cost) {
		this.cost = cost;
		this.value = (int) (0.5 * cost);
	}
	
	
	/**
	 * Returns the maximum health of the monster.
	 * 
	 * @return maxHealth The maximum health of the monster.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	
	/**
	 * Returns the damage that a monster does for each attack.
	 * 
	 * @return damage The damage that a monster does for each attack.
	 */
	public int getDamage() {
		return damage;
	}
	
	
	/**
	 * Returns the daily heal of the monster.
	 * 
	 * @return dailyHeal The daily heal of the monster.
	 */
	public int getDailyHeal() {
		return dailyHeal;
	}
	
	
	/**
	 * Returns the current health of the monster.
	 * 
	 * @return currentHealth The current health of the monster.
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	
	/**
	 * Returns the monster name of the monster.
	 * 
	 * @return monsterName The name of the monster.
	 */
	public String getMonsterName() {
		return monsterName;
	}
	
	
	/**
	 * Returns the rarity of the monster.
	 * 
	 * @return rarity The rarity of the monster.
	 */
	public String getRarity() {
		return rarity;
	}
	
	
	/**
	 * Returns the cost of the monster.
	 * 
	 * @return cost The cost of the monster.
	 */
	public int getCost() {
		return cost;
	}
	
	
	/**
	 * Returns the value of the monster.
	 * 
	 * @return value The value of monster.
	 */
	public int getValue() {
		return value;
	}
	
	
	/**
	 * Upgrades the monster statistics by the given amount.
	 * 
	 * @param times How many times the monster statistics will be upgraded.
	 */
	public void upgrade(double times) {
		this.maxHealth = (int) Math.round(this.maxHealth * times); 
		this.damage = (int) Math.round(this.damage * times);
		this.dailyHeal = (int) Math.round(this.dailyHeal * times);
		this.currentHealth = (int) Math.round(this.currentHealth * times);
		this.value = (int) Math.round(this.value * times);
		
	}
	
	
	/**
	 * Returns a string of the monsters information includes name, current health, max health, daily heal,
	 * damage, cost, and value.
	 * 
	 * @return result Information about the monster.
	 */
	public String toString() {
		String result = "\nMonster Information:";
		result += "\nMonster Name: " + getMonsterName() + "\nCurrent Health:"
				+ getCurrentHealth() + "\nMax Health: " + getMaxHealth() 
				+ "\nDaily Heal: " + getDailyHeal()  + "\nDamage: " + getDamage()
				+ "\nCost: " + getCost() + "\nValue: " + getValue(); 
		return result;
	}

	
	/**
	 * Returns the number of attacks that do no damage to the monster.
	 * 
	 * @return shield The number of attacks that do no damage to the monster.
	 */
	public int getShield() {
		return shield;
	}

	
	/**
	 * Sets the number of attacks that do no damage to the monster.
	 * 
	 * @param freeAttacks The number of attacks that do no damage to the monster.
	 */
	public void setShield(int freeAttacks) {
		this.shield = freeAttacks;
	}
	
	/**
	 * Increases the monster health.
	 * If the monster health is above the maximum health, its health is set to its maximum health.
	 */
	public void dailyHeal() {
		this.setCurrentHealth(this.getCurrentHealth() + this.getDailyHeal());
		if (this.getCurrentHealth() > this.getMaxHealth()) {
			this.setCurrentHealth( this.getMaxHealth() );
		}
	}
	

}
