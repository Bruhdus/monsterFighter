package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.LevelUp;
import monsterfighter.core.Monster;

/**
 * The test class for the level up class.
 */
class LevelUpTest {
	
	// Creates a monster to use.
	private Monster Mike;
	
	// Create a health heal object to use.
	private LevelUp levelUp;
	
	/**
	 * Creates a monster and health heal object before each test.
	 */
	@BeforeEach
	public void init() {
		Mike = new Monster("Mike", "Common", 100, 50, 25, 100);
		levelUp = new LevelUp("LevelUp", 50, 1.5);
	}
	
	/**
	 * Checks that all values of the monster are increased by 1.5 times after using the level up item.
	 */
	@Test
	public void applyLevelUpTest() {
		levelUp.use(Mike);
		assertEquals(Mike.getMaxHealth(),150);
		assertEquals(Mike.getCurrentHealth(),150);
		assertEquals(Mike.getDamage(),38);
		assertEquals(Mike.getValue(),75);
		assertEquals(Mike.getDailyHeal(),75);
	}
	
	
	/**
	 * Checks that the to string methods returns the correct string.
	 */
	@Test
	public void printLevelUpTest() {
		assertEquals(levelUp.toString(), "\nItem Information:\nItem name: LevelUp"
			+ "\nIncrease all statistics of a Monster by 1.5 times"
			+ "\nCost: 50"
			+ "\nValue: 25");
	}
	
	
	/**
	 * Checks that the getter and setter methods work fine.
	 */
	@Test
	public void getterAndSetterTest() {
		assertEquals(levelUp.getUpgradeMultiplier(),1.5);
		levelUp.setUpgradeMultiplier(1.75);
		assertEquals(levelUp.getUpgradeMultiplier(),1.75);
	}

}
