package monsterfighter.core;

/**
 * The item class is an abstract class. It has four sub classes, shield, weapon, health heal and level up.
 * The item class has all the information about the cost, value, name and information for the items.
 *
 */
public abstract class Item {
	
	/**
	 * Abstract method which is implemented in all the subclasses of item.
	 * 
	 * @param monster Monster which the item will be used on
	 */
	public abstract void use(Monster monster);
	
	// The cost of the item in gold.
	private int cost;
	
	// The value of the item in gold.
	private int value;
	
	// The name of the item.
	private String name;
	
	//The information about the item. What the item does.
	private String information;
	
	/**
	 * Sets the cost and value of the item from the given cost.
	 * The value of the item is half of the cost.
	 * 
	 * @param cost The cost of the item.
	 */
	public void setCost(int cost) {
		this.cost = cost;
		this.value = (int) (0.5 * cost);
	}
	
	
	/**
	 * Returns the cost of the item.
	 * 
	 * @return cost The cost of the item.
	 */
	public int getCost() {
		return cost;
	}
	
	
	/**
	 * Gets the value of the item.
	 * 
	 * @return value The value of the item.
	 */
	public int getValue() {
		return value;
	}
	
	
	/**
	 * Returns the name of the item.
	 * 
	 * @return name The name of the item.
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Sets the name of the item to the given name.
	 * 
	 * @param name The new name of the item.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * The constructor for the item class. Sets the name, value, cost and information about the item.
	 * The name, cost and information are given. The value is half the cost.
	 * 
	 * @param name The name of the item.
	 * @param cost The cost of the item.
	 * @param information A string of information about the item.
	 */
	Item(String name, int cost, String information){
		this.cost = cost;
		this.value = (int) (cost * 0.5);
		this.name = name;
		this.information = information;
	}
	
	
	/**
	 * Returns all the information about the item. Includes the name, cost, value and what the item
	 * does.
	 * 
	 * @return result A string which gives all the values of the item to the player.
	 */
	public String toString() {
		String result = "\nItem Information:";
		result += "\nItem name: " + name + "\n" + information
				+ "\nCost: " + cost + "\nValue: " + value;
		return result;
	}
	
	/**
	 * Gets the description about the item.
	 * 
	 * @return The description about the item.
	 */
	public String getInformation() {
		return information;
	}
	
	/**
	 * Sets the description about the item.
	 * 
	 * @param information The description about the item.
	 */
	public void setInformation(String information) {
		this.information = information;
	}
	
}
