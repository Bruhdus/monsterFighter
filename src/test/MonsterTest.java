package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.*;

/**
 * The test class for the monster class.
 * Most of the tests for the monster class happen in conjunction with other tests.
 */
public class MonsterTest {

	//Creates a monster to use.
	private Monster monster;
	
	/**
	 * Creates game environment before each test.
	 */
	@BeforeEach
	void init() {
		monster = new Monster(null, null, 0, 0, 0, 0);
	}
	
	
	/**
	 * Tests that the getter and setters are working fine.
	 */
	@Test
	public void getterAndSettersTest() {
		monster.setCost(100);
		assertEquals(monster.getCost(),100);
		assertEquals(monster.getValue(),50);
		monster.setDailyHealth(50);
		assertEquals(monster.getDailyHeal(),50);
		monster.setMaxHealth(200);
		assertEquals(monster.getMaxHealth(),200);
		monster.setMonsterName("Hugo");
		assertEquals(monster.getMonsterName(),"Hugo");
		monster.setRarity("common");
		assertEquals(monster.getRarity(), "common");
	}
}
