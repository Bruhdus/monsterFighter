package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.*;

/**
 * The test class for the player class.
 * There are limited tests for player class as most methods are used and tested in other test classes.
 */

public class PlayerTest {

	// Creates a players to battle.
	private Player easy;
	
	// Creates a monster to use.
	private Monster Pidgeotto;
	
	//Creates items to use.
	private Shield shield;
	private Item weapon;
	private Item heal;
	private Item levelUp;
	
	/**
	 * Creates a player object before each test.
	 */
	@BeforeEach
	void init() {
		easy = new Player();
		easy.setDifficulty("Easy");
		easy.setCurrentDay(1);
		easy.setDays(15);
		easy.setName("Easy");
		Pidgeotto = new Monster("Pidgeotto", "Common", 236, 59, 60, 200);
		shield = new Shield("Shield", 20);
		weapon = new Weapon("Weapon", 20, 10);
		heal = new HealthHeal("Heal", 20, 50);
		levelUp = new LevelUp("Level Up", 40, 1.15);
	}
	
	
	/**
	 * Tests the using the items increases the correct statistics of the monster.
	 */
	@Test
	public void useItemTests() {
		easy.addMonster(Pidgeotto);
		easy.useItem(Pidgeotto, shield);
		assertEquals(easy.getMonsterList().get(0).getShield(), 1);
		easy.useItem(Pidgeotto, weapon);
		assertEquals(easy.getMonsterList().get(0).getDamage(), 70);
		easy.getMonsterList().get(0).setCurrentHealth(0);
		easy.useItem(Pidgeotto, heal);
		assertEquals(easy.getMonsterList().get(0).getCurrentHealth(), 50);
		easy.useItem(Pidgeotto, levelUp);
		assertEquals(easy.getMonsterList().get(0).getCurrentHealth(), 57);
	}
		
	
}
