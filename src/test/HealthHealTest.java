package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.HealthHeal;
import monsterfighter.core.Monster;

/**
 * The test class for health heal.
 */
class HealthHealTest {
	
	// Creates a monster to use.
	private Monster Mike;
	
	// Create a health heal object to use.
	private HealthHeal healthHeal;

	/**
	 * Creates a monster and health heal object before each test.
	 */
	@BeforeEach
	public void init() {
		Mike = new Monster("Mike", "Common", 100, 50, 25, 100);
		healthHeal = new HealthHeal("Health Heal", 10, 25);
	}
	
	
	/**
	 * Checks that Mikes current health is increased from 75 to 100 when using the item.
	 */
	@Test
	public void applyHealthHealTest() {
		Mike.setCurrentHealth(75);
		healthHeal.use(Mike);
		assertEquals(Mike.getCurrentHealth(),100);
	}
	
	
	/**
	 * Checks that the to string methods returns the correct string.
	 */
	@Test
	public void printHealthHealTest() {
		assertEquals(healthHeal.toString(), "\nItem Information:\nItem name: Health Heal"
			+ "\nHeal monster by 25 points"
			+ "\nCost: 10"
			+ "\nValue: 5");
	}
	
	
	/**
	 * Checks that the getter and setter methods work fine.
	 */
	@Test
	public void getterAndSetterTest() {
		assertEquals(healthHeal.getAdditionalHealth(),25);
		healthHeal.setAdditionalHealth(50);
		assertEquals(healthHeal.getAdditionalHealth(),50);
	}
	
	
	/**
	 * Checks that if the monster is healed past maximum health their health ends up
	 * at their maximum health.
	 */
	@Test
	public void maximumHealthTest() {
		healthHeal.setAdditionalHealth(1000000);
		healthHeal.use(Mike);
		assertEquals(Mike.getCurrentHealth(),100);
		Mike.setCurrentHealth(0);
		healthHeal.use(Mike);
		assertEquals(Mike.getCurrentHealth(),100);
	}
}
