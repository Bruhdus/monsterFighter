package monsterfighter.core;
/**
 * The weapon class is a sub class of item. The weapon class increases the damage the monster does each attack
 * by the additional damage value.
 */
public class Weapon extends Item{
	
	// The extra damage the monster will do every attack.
	private int additionalDamage;

	/**
	 * This is the constructor for the weapon class. It calls the items constructor with the values for
	 * name, cost and information. It also sets the additional damage value.
	 * 
	 * @param name The name of the monster.
	 * @param cost The cost of the monster
	 * @param additionalDamage The extra damage the monster will do every attack.
	 */
	public Weapon(String name, int cost, int additionalDamage) {
		super(name, cost, "Increase monsters damage by " + additionalDamage + " points");
		this.additionalDamage = additionalDamage;
	}
	
	
	/**
	 * Sets the value for additional damage.
	 * 
	 * @param additionalDamage The extra damage the monster will do every attack.
	 */
	public void setAdditionalDamage(int additionalDamage) {
		this.additionalDamage = additionalDamage;
	}

	
	/**
	 * Returns the value for additional damage.
	 * 
	 * @return additionalDamage The additional damage the monster will do every attack.
	 */
	public int getAdditionalDamage() {
		return this.additionalDamage;
	}
	
	/**
	 * This increases the monsters attack damage by the additional damage.
	 * 
	 * @param monster The monster getting the additional damage.
	 */
	@Override
	public void use(Monster monster) {
		monster.setDamage( monster.getDamage() + this.additionalDamage );
	}

	
}
