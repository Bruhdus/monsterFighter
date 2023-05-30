package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.core.*;

/**
 * The test class for the battle class.
 */
class BattleTest {

	// Creates monsters to use.
	private Monster mike;
	private Monster james;
	private Monster mike2;
	private Monster james2;
	
	// Creates two players to battle.
	private Player hugo;
	private Player jeeSung;
	
	// Creates a battle object to use.
	private Battle battle;
	
	// Creates a array list that will be used for battle generation.
	private ArrayList<Monster> monsterList;
	
	// Creates a shield to use.
	private Shield shield;
	
	
	/**
	 * Creates the monsters and players to use in the fight.
	 * Gives the players monsters.
	 * Creates a battle object to use.
	 */
	@BeforeEach
	public void init() {
		mike = new Monster("Mike","Common", 100, 50, 25, 100);
		mike2 = new Monster("Mike2","Common", 100, 50, 25, 100);
		james = new Monster("James","Common", 200, 100, 50, 250);
		james2 = new Monster("James2","Common", 200, 100, 50, 250);
		hugo = new Player();
		hugo.setCurrentDay(1);
		hugo.setDays(5);
		jeeSung = new Player();
		jeeSung.setCurrentDay(7);
		jeeSung.setDays(15);
		hugo.addMonster(mike);
		hugo.addMonster(mike2);
		hugo.addMonster(james);
		jeeSung.addMonster(james2);
		battle  = new Battle();
		monsterList = new ArrayList<Monster>();
		monsterList.add(james);
		monsterList.add(mike);
		monsterList.add(james2);
		monsterList.add(mike2);
		shield = new Shield("Shield", 25);
	}
	
	
	/**
	 * Completes and tests a complete battle where the first player wins.
	 * Checks that health of all monsters is correctly updated.
	 * Checks that correct string is returned.
	 */
	@Test
	public void winBattleHardTest() {
		hugo.setDifficulty("Hard");
		assertEquals(battle.completeFight(hugo, jeeSung), 
				"Mike is fighting against James2."
				+"\nJames2 won against Mike."
				+"\nJames2 is left on 150 health."
				+"\nMike2 is fighting against James2."
				+"\nJames2 won against Mike2."
				+"\nJames2 is left on 100 health."
				+"\nJames is fighting against James2."
				+"\nJames won against James2."
				+"\nJames is left on 100 health."
				+"\nCongratulations your monster team was successful and won the fight!\n");
		assertEquals(hugo.getMonsterList().get(0).getCurrentHealth(),0);
		assertEquals(hugo.getMonsterList().get(1).getCurrentHealth(),0);
		assertEquals(hugo.getMonsterList().get(2).getCurrentHealth(),100);
		assertEquals(jeeSung.getMonsterList().get(0).getCurrentHealth(),0);
		assertEquals(hugo.getScore(),300);
		assertEquals(hugo.getGold(),200);
	}
	
	
	/**
	 * Completes and tests a complete battle where the first player wins.
	 * Checks that health of all monsters is correctly updated.
	 * Checks that correct string is returned.
	 */
	@Test
	public void winBattleEasyTest() {
		hugo.setDifficulty("Easy");
		assertEquals(battle.completeFight(hugo, jeeSung), 
				"Mike is fighting against James2."
				+"\nJames2 won against Mike."
				+"\nJames2 is left on 150 health."
				+"\nMike2 is fighting against James2."
				+"\nJames2 won against Mike2."
				+"\nJames2 is left on 100 health."
				+"\nJames is fighting against James2."
				+"\nJames won against James2."
				+"\nJames is left on 100 health."
				+"\nCongratulations your monster team was successful and won the fight!\n");
		assertEquals(hugo.getMonsterList().get(0).getCurrentHealth(),0);
		assertEquals(hugo.getMonsterList().get(1).getCurrentHealth(),0);
		assertEquals(hugo.getMonsterList().get(2).getCurrentHealth(),100);
		assertEquals(jeeSung.getMonsterList().get(0).getCurrentHealth(),0);
		assertEquals(hugo.getScore(),150);
		assertEquals(hugo.getGold(),200);
	}
	
	
	/**
	 * Completes and tests a complete battle where the second player wins.
	 * Checks that health of all monsters is correctly updated.
	 * Checks that correct string is returned.
	 */
	@Test
	public void loseBattleTest() {
		assertEquals(battle.completeFight(jeeSung, hugo), "James2 is fighting against Mike."
				+"\nJames2 won against Mike."
				+"\nJames2 is left on 150 health."
				+"\nJames2 is fighting against Mike2."
				+"\nJames2 won against Mike2."
				+"\nJames2 is left on 100 health."
				+"\nJames2 is fighting against James."
				+"\nJames won against James2."
				+"\nJames is left on 100 health."
				+"\nSadly your monster team was wiped out :(\n");
		assertEquals(hugo.getMonsterList().get(0).getCurrentHealth(),0);
		assertEquals(hugo.getMonsterList().get(1).getCurrentHealth(),0);
		assertEquals(hugo.getMonsterList().get(2).getCurrentHealth(),100);
		assertEquals(jeeSung.getMonsterList().get(0).getCurrentHealth(),0);
		assertEquals(jeeSung.getScore(),0);
		assertEquals(jeeSung.getGold(),0);
	}
	
	
	/**
	 * Completes and tests a complete battle where the second player wins and monster health goes below zero.
	 * Checks that health of all monsters is correctly updated.
	 * Checks that correct string is returned.
	 */
	@Test
	public void belowZeroHealthTest() {
		james2.setDamage(51);
		assertEquals(battle.completeFight(jeeSung, hugo), "James2 is fighting against Mike."
				+"\nJames2 won against Mike."
				+"\nJames2 is left on 150 health."
				+"\nJames2 is fighting against Mike2."
				+"\nJames2 won against Mike2."
				+"\nJames2 is left on 100 health."
				+"\nJames2 is fighting against James."
				+"\nJames won against James2."
				+"\nJames is left on 98 health."
				+"\nSadly your monster team was wiped out :(\n");
		assertEquals(hugo.getMonsterList().get(0).getCurrentHealth(),0);
		assertEquals(hugo.getMonsterList().get(1).getCurrentHealth(),0);
		assertEquals(hugo.getMonsterList().get(2).getCurrentHealth(),98);
		assertEquals(jeeSung.getMonsterList().get(0).getCurrentHealth(),0);
		assertEquals(jeeSung.getScore(),0);
		assertEquals(jeeSung.getGold(),0);
	}
	
	/**
	 * Checks that battle generation is returning lists contains monsters and of the correct length.
	 */
	@Test
	public void battleGenerationTest() {
		ArrayList<ArrayList<Monster>> fightsList = battle.battleGeneration(jeeSung, monsterList);
		int i = 1;
		for (ArrayList<Monster> newMonsterList : fightsList) {
			assertEquals(newMonsterList.size(),i);
			i++;
			for(Monster monster: newMonsterList) {
				assertTrue(monster instanceof Monster);
			}
		}
		jeeSung.nextDay();
		fightsList = battle.battleGeneration(jeeSung, monsterList);
		i = 2;
		for (ArrayList<Monster> newMonsterList : fightsList) {
			assertEquals(newMonsterList.size(),i);
			i++;
		}
	}
	
	
	/**
	 * Checks that shield is only used once. On the first hit the defender has no damage done.
	 * On the second hit the defender has regular damage don.e
	 */
	@Test
	public void shieldUseTest() {
		shield.use(james);
		assertEquals(james.getShield(),1);
		battle.individualAttack(james2, james);
		assertEquals(james.getShield(),0);
		assertEquals(james.getCurrentHealth(),200);
		battle.individualAttack(james2, james);
		assertEquals(james.getCurrentHealth(),150);
	}

}

