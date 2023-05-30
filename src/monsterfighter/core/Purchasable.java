package monsterfighter.core;
/**
 * The purchasable class has all the logic for buying and selling monsters and items.
 */

public class Purchasable {

	/**
	 * The buy monster method checks to see if the player has enough slots to buy the monster, and enough gold. 
	 * If the player doesn't have enough slots or gold a message is returned telling the player why they can't
	 * purchase the monster. A success message is returned as well. The players gold is reduced as well and
	 * the monster is added to the player monster list.
	 * 
	 * @param player The player that is wanting to buy the monster.
	 * @param monster The monster that is trying to be bought.
	 * @return String The message being returned.
	 */
	public String buyMonster(Player player, Monster monster){
		if (player.getMonsterList().size() < 4) {
			if (player.getGold() < monster.getCost()) {
				return "Insufficient gold";
			} else {
				player.addMonster(monster);
				player.minusGold(monster.getCost());
				player.addScore(monster.getCost());
				return "Purchase successful";
			} 
		} else {
			return "You are only allowed 4 monsters, please sell a monster to buy more";
		}
	}
	
	
	/**
	 * The buy item methods checks to see if the player has enough slots to buy the monster, and enough gold.
	 * If the player doesn't have enough slots or gold a message is returned telling the player why they can't
	 * purchase the item. A success message is returned as well. The players gold is reduced as well and the item
	 * is added to the player item list.
	 * 
	 * @param player The player that is wanting to buy the item.
	 * @param item The item that player is trying to buy.
	 * @return String The message being returned.
	 */
	public String buyItem(Player player, Item item) {
		if (player.getItemList().size() < 3) {
			if (player.getGold() < item.getCost()) {
				return "Insufficient gold";
			} else {
				player.minusGold(item.getCost());
				player.addItem(item);
				player.addScore(item.getCost());
				return "Purchase successful";
			}
		} else {
			return "You are only allowed 3 items, please sell or use an item to buy more";
		}
	}
	
	
	/**
	 * The sell monster method checks if the person owns the monster. If they don't own the item it prints an error 
	 * message. If they do it adjusts the player gold and removes the monster.
	 * 
	 * @param player The player that is selling the monster.
	 * @param monster The monster that is being sold.
	 * @return String The message being returned.
	 */
	public String sellMonster(Player player, Monster monster) {
		if (player.getMonsterList().contains(monster)) {
			player.removeMonster(monster);
			player.addGold(monster.getValue());
			player.addScore(monster.getValue());
			return "Successfully Sold Your Monster";
		} else {
			return "Sorry It Looks Like You Do Not Own This Monster";
		}
	}
	
	
	/**
	 * The sell item method checks if the person owns the item. If they don't own the item it prints an error
	 * message. If they do own a item the player gold is adjusted and removed the item.
	 * 
	 * @param player The player that is selling the item.
	 * @param item The item that is being sold.
	 * @return String The message being returned.
	 */
	public String sellItem(Player player, Item item) {
		if (player.getItemList().contains(item)) {
			player.addGold(item.getValue());
			player.removeItem(item);
			player.addScore(item.getValue());
			return "Successfully Sold Your Item";
		} else {
			return "Sorry It Looks Like You Do Not Own This Item";
		}
	}
	
	
}
