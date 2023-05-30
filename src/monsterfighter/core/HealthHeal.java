package monsterfighter.core;

/**
 * The health heal class is a sub class of item. The health heal is an item which increases the monster
 * current health by the given amount.
 */
public class HealthHeal extends Item{

	// The amount of health the monster will gain.
	private int additionalHealth;
	
	/**
	 * The constructor of the health heal class. It calls the constructor of the item class and sets
	 * the additional health value for the item.
	 * 
	 * @param name The name of the item
	 * @param cost The cost in gold of the item
	 * @param additionalHealth The additional health the item will give.
	 */
	public HealthHeal(String name, int cost, int additionalHealth) {
		super(name, cost, "Heal monster by " + additionalHealth + " points");
		this.additionalHealth = additionalHealth;
	}

	
	/**
	 * The use method increases the given monster health by the additional health of the item.
	 * If the monsters health goes over the maximum amount, it is set at the monster maximum health.
	 * 
	 * @param monster The monster which gets the health increase.
	 */
	@Override
	public void use(Monster monster) {
		monster.setCurrentHealth( monster.getCurrentHealth() + this.additionalHealth );
		if (monster.getCurrentHealth() > monster.getMaxHealth()){
			monster.setCurrentHealth(monster.getMaxHealth());
		}
	}

	
	/**
	 * Returns the extra health a monster gains from using the item.
	 * 
	 * @return additionalHealth The extra health a monster gains from using the item.
	 */
	public int getAdditionalHealth() {
		return additionalHealth;
	}

	
	/**
	 * Sets the additional health the monster gains from using the item. Called in the constructor.
	 * 
	 * @param additionalHealth The extra health a monster gains from using the item.
	 */
	public void setAdditionalHealth(int additionalHealth) {
		this.additionalHealth = additionalHealth;
	}
	
	
	
}
