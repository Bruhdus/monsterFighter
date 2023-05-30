package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Monster;
import monsterfighter.core.Weapon;

/**
 * The test class for the weapon class.
 */
class WeaponTest {
	
	// Creates a monster to use.
	private Monster Mike;
	
	// Create a health heal object to use.
	private Weapon weapon;
	
	/**
	 * Creates a monster and health heal object before each test.
	 */
	@BeforeEach
	public void init() {
		Mike = new Monster("Mike", "Common", 100, 50, 25, 100);
		weapon = new Weapon("Weapon", 10, 25);
	}
	
	
	/**
	 * Checks that the damage value increases when using the weapon.
	 */
	@Test
	public void applyWeaponTest() {
		weapon.use(Mike);
		assertEquals(Mike.getDamage(),50);
	}
	
	
	/**
	 * Checks that the to string methods returns the correct string.
	 */
	@Test
	public void printWeaponTest() {
		assertEquals(weapon.toString(), "\nItem Information:\nItem name: Weapon\n"
				+ "Increase monsters damage by 25 points\n"
				+ "Cost: 10\n"
				+ "Value: 5");
	}
	
	
	/**
	 * Checks that the getter and setter methods work fine.
	 */
	@Test
	public void getterAndSetterTest() {
		assertEquals(weapon.getAdditionalDamage(),25);
		weapon.setAdditionalDamage(150);
		assertEquals(weapon.getAdditionalDamage(),150);
	}
	

}
