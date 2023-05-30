package monsterfighter.core;

/**
 * The level up class is a sub class of item. The level up class increases the statistics of a monster
 * by the upgrade multiplier.
 */
public class LevelUp extends Item{
	
	// How many times the statistics will increase by.
	private double upgradeMultiplier;

	/**
	 * This is the constructor for the level up class. It calls the items constructor with the values for
	 * name, cost and information. It also sets the upgrade multiplier.
	 * 
	 * @param name The name of the item.
	 * @param cost The cost of the item.
	 * @param upgradeMultiplier The amount the monsters statistics will increase by.
	 */
	public LevelUp(String name, int cost, double upgradeMultiplier) {
		super(name, cost, "Increase all statistics of a Monster by " + upgradeMultiplier + " times");
		this.upgradeMultiplier = upgradeMultiplier;
	}

	
	/**
	 * The use method calls the upgrade method is the monster class. This will upgrade the monster statistics
	 * by the upgrade multiplier.
	 * 
	 * @param monster The monster which will have its statistics increased.
	 */
	@Override
	public void use(Monster monster) {
		monster.upgrade(this.upgradeMultiplier);
	}

	
	/**
	 * Returns the upgrade multiplier which how many times the monsters statistics will increase by.
	 * 
	 * @return upgradeMultiplier How many times the monsters statistics will increase by.
	 */
	public double getUpgradeMultiplier() {
		return upgradeMultiplier;
	}

	
	
	/**
	 * Sets the upgrade multiplier which is how many times the monsters statistics will increase by.
	 * 
	 * @param upgradeMultiplier How many times the monsters statistics will increase by.
	 */
	public void setUpgradeMultiplier(double upgradeMultiplier) {
		this.upgradeMultiplier = upgradeMultiplier;
	}
	
	
	
}
