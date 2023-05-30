package monsterfighter.core;
/**
 * The shield class is a sub class of item. The shield class gives a monster an extra hit before the monster 
 * can take damage.
 */
public class Shield extends Item{

	/**
	 * This is the constructor for the shield class. It calls the items constructor with the values for
	 * name, cost and information.
	 * 
	 * @param name The name of the item.
	 * @param cost The cost of the item.
	 */
	public Shield(String name, int cost) {
		super(name, cost, "Gives a monster one block against any attack");
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Gives the input monster a extra shield.
	 * 
	 * @param monster The monster getting the shield.
	 */
	@Override
	public void use(Monster monster) {
		monster.setShield(monster.getShield() + 1);	
	}
	
}
