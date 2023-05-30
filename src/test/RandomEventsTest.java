package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.*;


/**
 * The test class for the random events class.
 */
public class RandomEventsTest {
	
	// Creates monsters to use.
	private Monster mike;
	private Monster james;
	private Monster mike2;
	private Monster james2;
	
	// Creates two players to battle.
	private Player hugo;
	
	// Creates a random events object to use.
	private RandomEvents randomEvents;
	
	// Creates a array list that will be used for battle generation.
	private ArrayList<Monster> monsterList;
	
	// Creates the random variable with set 1.
	Random rng;
	
	/**
	 * Creates a monster and health heal object before each test.
	 */
	@BeforeEach
	public void init() {
		mike = new Monster("Mike","Common", 100, 50, 25, 100);
		mike2 = new Monster("Mike2","Common", 100, 50, 25, 100);
		james = new Monster("James","Common", 200, 100, 50, 250);
		james2 = new Monster("James2","Common", 200, 100, 50, 250);
		hugo = new Player();
		hugo.setCurrentDay(1);
		hugo.setDays(15);
		monsterList = new ArrayList<Monster>();
		monsterList.add(james);
		monsterList.add(mike);
		randomEvents = new RandomEvents();
	}
	
	
	/**
	 * Checks that a monster is added to the player.
	 * This means that add monster method has a chance of adding a monster.
	 */
	@Test
	public void addMonsterTest() {
		rng = new Random(1);
		for(int i=1; i < 4; i++) {
			assertEquals(randomEvents.addMonster(hugo, monsterList, 311), "You gained an extra monster called James overnight\n");
		}
		assertEquals(randomEvents.addMonster(hugo, monsterList, 311), "You gained no extra monsters overnight\n");
		assertEquals(hugo.getMonsterList().get(0),james);
	}
	
	
	/**
	 * Checks that monsters are sometimes deleted from a player.
	 * First checks that for a player with no monsters, none are killed.
	 * Then checks that on the third nights the monster james is lost.
	 */
	@Test
	public void deleteMonsterTest() {
		hugo.addMonster(james2);
		hugo.addMonster(james);
		assertEquals(randomEvents.deleteMonster(hugo, 311), "Sadly Your monster James2 was killed overnight\n");
		assertEquals(randomEvents.deleteMonster(hugo, 311), "Sadly Your monster James was killed overnight\n");
		assertEquals(randomEvents.deleteMonster(hugo, 311), "None of your monsters were killed overnight\n");
		hugo.addMonster(james2);
		hugo.addMonster(james);
		assertEquals(randomEvents.deleteMonster(hugo, 3), "None of your monsters were killed overnight\n");
	}
	
	
	/**
	 * Checks that monsters are sometimes leveled up.
	 * First checks that both are leveled up and then that none are.
	 */
	@Test
	public void levelUpTest() {
		hugo.addMonster(james2);
		hugo.addMonster(james);
		hugo.setCurrentDay(14);
		assertEquals(randomEvents.levelsUp(hugo, 311), "Your monster James2 was upgraded by 25%.\n"
				+ "Your monster James was upgraded by 25%.\n"
				+ "");
		assertEquals(hugo.getMonsterList().get(0).getCurrentHealth(),250);
		assertEquals(hugo.getMonsterList().get(1).getCurrentHealth(),250);
		hugo.setCurrentDay(1);
		assertEquals(randomEvents.levelsUp(hugo, 311), "None of your monsters were upgraded overnight\n");
	}
}
