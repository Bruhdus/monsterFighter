package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.*;

/**
 * The test class for the shield class.
 */
class ShieldTest {

	
	// Creates a monster to use.
	private Monster Mike;
	
	// Create a health heal object to use.
	private Shield shield;

	
	/**
	 * Creates a monster and health heal object before each test.
	 */
	@BeforeEach
	public void init() {
		Mike = new Monster("Mike", "Common", 100, 50, 25, 100);
		shield = new Shield("Shield", 50);
	}
	
	
	/**
	 * Checks that the value of shield increases by one when using the shield.
	 */
	@Test
	public void applyShieldTest() {
		shield.use(Mike);
		assertEquals(Mike.getShield(),1);
		shield.use(Mike);
		assertEquals(Mike.getShield(),2);
	}
	
	
	/**
	 * Checks that the to string methods returns the correct string.
	 */
	@Test
	public void printShieldTest() {
		assertEquals(shield.toString(), "\nItem Information:\nItem name: Shield"
			+ "\nGives a monster one block against any attack"
			+ "\nCost: 50"
			+ "\nValue: 25");
	}
}
