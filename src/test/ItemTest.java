package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.*;

/**
 * The test class for the item class.
 */
class ItemTest {
	
	// Create a health heal object to use.
	private LevelUp levelUp;

	
	/**
	 * Creates item object before each test.
	 */
	@BeforeEach
	public void init() {
		levelUp = new LevelUp("LevelUp", 50, 1.5);
	}
	
	
	/**
	 * Checks that the getter and setter methods work fine.
	 */
	@Test
	void getterAndSettertest() {
		levelUp.setCost(100);
		assertEquals(levelUp.getCost(),100);
		assertEquals(levelUp.getValue(),50);
		levelUp.setInformation("nothing");
		assertEquals(levelUp.getInformation(),"nothing");
		levelUp.setName("newName");
		assertEquals(levelUp.getName(),"newName");
	}

}
