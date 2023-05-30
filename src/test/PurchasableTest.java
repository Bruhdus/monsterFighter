package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.*;

/**
 * The test class for purchasable.
 */

public class PurchasableTest {
	
	// Creates monsters to use.
	private Monster mike;
	private Monster james;
	private Monster mike2;
	private Monster james2;
	private Monster james3;
	
	// Creates a players to use.
	private Player hugo;
	
	// Creates a purchasable object to use.
	private Purchasable purchasable;
	
	// Creates the four different items.
	private Item shield;
	private Item weapon;
	private Item heal;
	private Item levelUp;
	
	
	/**
	 * Creates the monsters and items and player to test
	 */
	@BeforeEach
	public void init() {
		mike = new Monster("Mike","Common", 100, 50, 25, 100);
		mike2 = new Monster("Mike2","Common", 100, 50, 25, 100);
		james = new Monster("James","Common", 200, 100, 50, 250);
		james2 = new Monster("James2","Common", 200, 100, 50, 250);
		james3 = new Monster("James3","Common", 200, 100, 50, 250);
		hugo = new Player();
		shield = new Shield("Shield", 20);
		weapon = new Weapon("Weapon", 20, 10);
		heal = new HealthHeal("Heal", 20, 50);
		levelUp = new LevelUp("Level Up", 20, 1.2);
		purchasable = new Purchasable();
	}
	
	
	/**
	 * Tests for buying a monster.
	 * If purchasing a monster if the player already has four monsters, a message is displayed and the player
	 * can't buy the monster. If they player is short on gold a message is displayed and the player can't buy
	 * the monster. If the monster is purchased it goes into the player inventory and the players gold is 
	 * reduced by the correct amount.
	 */
	@Test
	public void buyingMonsterTest() {
		//first monster purchase
		hugo.addGold(10000);
		assertEquals(purchasable.buyMonster(hugo, mike),"Purchase successful");
		assertEquals(hugo.getGold(),9900);
		assertEquals(hugo.getMonsterList().get(0), mike);
		//second monster purchase
		assertEquals(purchasable.buyMonster(hugo, mike2),"Purchase successful");
		assertEquals(hugo.getGold(),9800);
		assertEquals(hugo.getMonsterList().get(1), mike2);
		//third monster purchase
		assertEquals(purchasable.buyMonster(hugo, james),"Purchase successful");
		assertEquals(hugo.getGold(),9550);
		assertEquals(hugo.getMonsterList().get(2), james);
		//fourth monster purchase
		assertEquals(purchasable.buyMonster(hugo, james2),"Purchase successful");
		assertEquals(hugo.getGold(),9300);
		assertEquals(hugo.getMonsterList().get(3), james2);
		//5th monster purchase
		assertEquals(purchasable.buyMonster(hugo, james3),"You are only allowed 4 monsters, please sell a monster to buy more");
		assertEquals(hugo.getGold(),9300);
		//not enough gold monster purchase
		hugo.removeMonster(mike);
		hugo.minusGold(9300);
		assertEquals(hugo.getGold(),0);
		assertEquals(purchasable.buyMonster(hugo, james3),"Insufficient gold");
	}
	
	/**
	 * Tests for buying a item.
	 * If purchasing a item if the player already has three items, a message is displayed and the player
	 * can't buy the item. If they player is short on gold a message is displayed and the player can't buy
	 * the item. If the item is purchased it goes into the player inventory and the players gold is 
	 * reduced by the correct amount.
	 */
	@Test
	public void buyingItemTest() {
		//first item purchase
		hugo.addGold(10000);
		assertEquals(purchasable.buyItem(hugo, shield),"Purchase successful");
		assertEquals(hugo.getGold(),9980);
		assertEquals(hugo.getItemList().get(0), shield);
		//second item purchase
		assertEquals(purchasable.buyItem(hugo, weapon),"Purchase successful");
		assertEquals(hugo.getGold(),9960);
		assertEquals(hugo.getItemList().get(1), weapon);
		//thirds item purchase
		assertEquals(purchasable.buyItem(hugo, heal),"Purchase successful");
		assertEquals(hugo.getGold(),9940);
		assertEquals(hugo.getItemList().get(2), heal);
		//fourth item purchase
		assertEquals(purchasable.buyItem(hugo, levelUp),"You are only allowed 3 items, please sell or use an item to buy more");
		assertEquals(hugo.getGold(),9940);
		//not enough gold item purchase
		hugo.removeItem(heal);
		hugo.minusGold(9940);
		assertEquals(hugo.getGold(),0);
		assertEquals(purchasable.buyItem(hugo, levelUp),"Insufficient gold");
	}
	
	
	/**
	 * Tests selling a monster.
	 * If the player owns the monster then the monster is removed and the value of the monster
	 * is added to the players gold. If the player doesn't own the monster then a message is printed.
	 */
	@Test
	public void sellingMonsterTest() {
		//selling monster player doesn't own
		assertEquals(purchasable.sellMonster(hugo, mike),"Sorry It Looks Like You Do Not Own This Monster");
		//selling monster player does own
		hugo.addMonster(mike);
		hugo.addMonster(mike2);
		hugo.addMonster(james);
		hugo.addMonster(james2);
		assertEquals(hugo.getMonsterList().get(0),mike);
		assertEquals(purchasable.sellMonster(hugo, mike),"Successfully Sold Your Monster");
		assertEquals(hugo.getGold(),50);
		assertEquals(hugo.getMonsterList().get(0),mike2);
	}
	
	
	/**
	 * Tests selling a item.
	 * If the player owns the item then the monster is removed and the value of the item
	 * is added to the players gold. If the player doesn't own the item then a message is printed.
	 */
	@Test
	public void sellingItemTest() {
		//selling monster player doesn't own
		assertEquals(purchasable.sellItem(hugo, shield),"Sorry It Looks Like You Do Not Own This Item");
		//selling monster player does own
		hugo.addItem(shield);
		hugo.addItem(weapon);
		hugo.addItem(levelUp);
		assertEquals(hugo.getItemList().get(0),shield);
		assertEquals(purchasable.sellItem(hugo, shield),"Successfully Sold Your Item");
		assertEquals(hugo.getGold(),10);
		assertEquals(hugo.getItemList().get(0),weapon);
	}
}
