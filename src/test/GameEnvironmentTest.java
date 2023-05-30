package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monsterfighter.*;
import monsterfighter.core.*;
import monsterfighter.ui.*;

/**
 * The test class for the game environment class.
 */
public class GameEnvironmentTest {

	// Creates a new game environment glass
	private GameEnvironment gameEnvEasy;
	private GameEnvironment gameEnvHard;
	
	//Creates screen manager
	private ScreenManager manager;
	
	//Creates players
	private Player easy;
	private Player hard;
	private Player system;
	
	//Creates battle
	private Battle battle;
	
	/**
	 * Creates game environment before each test.
	 */
	@BeforeEach
	void init() {
		easy = new Player();
		easy.setDifficulty("Easy");
		easy.setCurrentDay(1);
		easy.setDays(15);
		easy.setName("Easy");
		hard = new Player();
		hard.setDifficulty("Hard");
		hard.setCurrentDay(1);
		hard.setDays(15);
		hard.setName("Hard");
		system = new Player();
		battle = new Battle();
		manager = new ScreenManager();
		manager.screenManagerSetUp(gameEnvEasy, easy);
		gameEnvEasy = new GameEnvironment(manager, easy, system, battle);
		gameEnvHard= new GameEnvironment(manager, hard, system, battle);
	}
	
	
	/**
	 * Tests the game start up runs.
	 */
	@Test
	public void easyGameStartTest() {
		gameEnvEasy.gameStart();
		gameEnvHard.gameStart();
	}
	
	
	/**
	 * Tests that the game set up gives the player the correct values.
	 */
	@Test
	public void gameSetUpTest() {
		Monster Hugo = new Monster("Hugo","Common",200, 100, 49, 100);
		gameEnvEasy.gameSetup("name", 15, "Hard", Hugo);
		assertEquals(easy.getDifficulty(),"Hard");
		assertEquals(easy.getMonsterList().get(0),Hugo);
		assertEquals(easy.getName(),"name");
		assertEquals(easy.getGold(),100);
		assertEquals(easy.getScore(),1000);
	}
	
	
	/**
	 * Tests that the correct monsters are being displayed in the shop depending on the day.
	 */
	@Test
	public void buyMonsterShopSetUpEasyTest() {
		Monster Pidgeotto = new Monster("Pidgeotto", "Common", 236, 118, 60, 100);
		Monster Raticate = new Monster("Raticate", "Common", 220, 110, 76, 100);
		Monster Nidoran = new Monster("Nidoran", "Common", 180, 90, 80, 100);
		Monster Sandslash = new Monster("Sandslash", "Common", 260, 130, 55, 100);
		Monster Machamp = new Monster("Machamp", "Rare", 384, 190, 130, 200);
		Monster Golem = new Monster("Golem", "Rare", 368, 180, 139, 200);
		Monster Rapidash = new Monster("Rapidash", "Rare", 334, 160, 188, 200);
		Monster Muk = new Monster("Muk", "Rare", 414, 205, 105, 200);
		Monster Eternatus = new Monster("Eternatus", "Epic", 584, 250, 157, 350);
		Monster Rayquaza = new Monster("Rayquaza", "Epic", 514, 244, 244, 350);
		ArrayList<Monster> shopMonsterList = new ArrayList<>(Arrays.asList(Pidgeotto, Raticate, Nidoran, Sandslash));
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvEasy.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
		easy.setCurrentDay(5);
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvEasy.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
		easy.setCurrentDay(6);
		shopMonsterList = new ArrayList<>(Arrays.asList(Pidgeotto, Sandslash, Machamp, Golem));
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvEasy.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
		easy.setCurrentDay(10);
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvEasy.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
		easy.setCurrentDay(11);
		shopMonsterList = new ArrayList<>(Arrays.asList(Rapidash,Muk,Eternatus,Rayquaza));
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvEasy.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
	}
	
	
	/**
	 * Tests that the correct monsters are being displayed in the shop depending on the day.
	 */
	@Test
	public void buyMonsterShopSetUpHardTest() {
		Monster Pidgeotto = new Monster("Pidgeotto", "Common", 236, 59, 60, 200);
		Monster Raticate = new Monster("Raticate", "Common", 220, 55, 76, 200);
		Monster Nidoran = new Monster("Nidoran", "Common", 180, 45, 80, 200);
		Monster Sandslash = new Monster("Sandslash", "Common", 260, 65, 55, 200);
		Monster Machamp = new Monster("Machamp", "Rare", 384, 80, 130, 400);
		Monster Golem = new Monster("Golem", "Rare", 368, 90, 139, 400);
		Monster Rapidash = new Monster("Rapidash", "Rare", 334, 80, 188, 400);
		Monster Muk = new Monster("Muk", "Rare", 414, 102, 105, 400);
		Monster Eternatus = new Monster("Eternatus", "Epic", 584, 125, 157, 700);
		Monster Rayquaza = new Monster("Rayquaza", "Epic", 514, 122, 244, 700);
		ArrayList<Monster> shopMonsterList = new ArrayList<>(Arrays.asList(Pidgeotto, Raticate, Nidoran, Sandslash));
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvHard.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
		hard.setCurrentDay(5);
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvHard.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
		hard.setCurrentDay(6);
		shopMonsterList = new ArrayList<>(Arrays.asList(Pidgeotto, Sandslash, Machamp, Golem));
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvHard.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
		hard.setCurrentDay(10);
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvHard.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
		hard.setCurrentDay(11);
		shopMonsterList = new ArrayList<>(Arrays.asList(Rapidash,Muk,Eternatus,Rayquaza));
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvHard.buyMonsterShopSetUp().get(i).toString(),shopMonsterList.get(i).toString());
	}
	
	
	/**
	 * Tests that the correct items are being displayed in the shop depending on the day.
	 */
	@Test
	public void buyItemShopSetUpEasyTest() {
		Item shield = new Shield("Shield", 20);
		Item weapon = new Weapon("Weapon", 20, 10);
		Item heal = new HealthHeal("Heal", 20, 100);
		Item levelUp = new LevelUp("Level Up", 40, 1.15);
		ArrayList<Item> shopItemList = new ArrayList<>(Arrays.asList(shield, weapon, heal, levelUp));
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvEasy.buyItemShopSetUp().get(i).toString(),shopItemList.get(i).toString());
		
		shield = new Shield("Shield", 30);
		weapon = new Weapon("Weapon", 30, 5);
		heal = new HealthHeal("Heal", 35, 100);
		levelUp = new LevelUp("Level Up", 60, 1.1);
		shopItemList = new ArrayList<>(Arrays.asList(shield, weapon, heal, levelUp));
		for (int i=1; i < 4; i++)
			assertEquals(gameEnvHard.buyItemShopSetUp().get(i).toString(),shopItemList.get(i).toString());
	}
	
	
	/**
	 * Checks that the player reaches end game when they don't have enough gold the buy monsters and have no monsters.
	 */
	@Test
	public void checkIfEndGameTest() {
		Monster Hugo = new Monster("Hugo","Common",200, 100, 49, 100);
		gameEnvEasy.gameSetup("name", 15, "Hard", Hugo);
		assertEquals(gameEnvEasy.checkIfEndGame(),false);
		easy.minusGold(100);
		easy.removeMonster(easy.getMonsterList().get(0));
		assertEquals(gameEnvEasy.checkIfEndGame(), true);
	}
	
	
	/**
	 * Tests combinations of errors for the name and number of days input boxes and checks that the correct error message is returned.
	 */
	@Test
	public void loginDetailsCheckTest() {
		assertEquals(gameEnvEasy.loginDetailsCheck("", "1"),"Please enter a name.");
		assertEquals(gameEnvEasy.loginDetailsCheck("name", ""),"Please enter the number of days.");
		assertEquals(gameEnvEasy.loginDetailsCheck("name", "ten"),"Number of days must be a number. The number of days must be between 5-15 days.");
		assertEquals(gameEnvEasy.loginDetailsCheck("name", "16"),"The number of days must be between 5-15 days.");
		assertEquals(gameEnvEasy.loginDetailsCheck("n", "15"),"Your name must be between 3-15 letters and must not contain any numbers or symbols.");
		assertEquals(gameEnvEasy.loginDetailsCheck("n______", "15"),"Your name must be between 3-15 letters and must not contain any numbers or symbols.");
		assertEquals(gameEnvEasy.loginDetailsCheck("n__", "15"),"Your name must be between 3-15 letters and must not contain any numbers or symbols.");
		assertEquals(gameEnvEasy.loginDetailsCheck("2348967", "15"),"Your name must be between 3-15 letters and must not contain any numbers or symbols.");
		assertEquals(gameEnvEasy.loginDetailsCheck("too longgggggggggggg", "15"),"Your name must be between 3-15 letters and must not contain any numbers or symbols.");
		assertEquals(gameEnvEasy.loginDetailsCheck("name", "15"), "");
	}
	
	
	/**
	 * Tests to see that the chick if player can't battle returns true and false when all the players monster are on zero health.
	 */
	@Test
	public void checkIfPlayerCantBattleTest() {
		Monster Hugo = new Monster("Hugo","Common",200, 100, 49, 100);
		gameEnvEasy.gameSetup("name", 15, "Hard", Hugo);
		assertEquals(gameEnvEasy.checkIfPlayerCantBattle(),false);
		easy.getMonsterList().get(0).setCurrentHealth(0);
		assertEquals(gameEnvEasy.checkIfPlayerCantBattle(),true);
		easy.removeMonster(easy.getMonsterList().get(0));
		assertEquals(gameEnvEasy.checkIfPlayerCantBattle(),true);
	}
	
	
	/**
	 * Tests that the start battle function works correctly.
	 */
	@Test
	public void startBattleTest() {
		gameEnvEasy.systemMonstersSetUp();
		gameEnvEasy.setSelectedBattleIndex(0);
		gameEnvEasy.setSelectedBattle();
		Monster Hugo = new Monster("Hugo","Common",200, 100, 490, 100);
		gameEnvEasy.gameSetup("name", 15, "Hard", Hugo);
		String testString = gameEnvEasy.startBattle().substring(gameEnvEasy.startBattle().length() - 68);
		assertEquals(testString.toString(),"Congratulations your monster team was successful and won the fight!\n".toString());
	}
	
	
	/**
	 * Test the swap monster positions method and that it does swap the monsters positions.
	 */
	@Test
	public void swapMonsterPositionsTest() {
		Monster Pidgeotto = new Monster("Pidgeotto", "Common", 236, 59, 60, 200);
		Monster Raticate = new Monster("Raticate", "Common", 220, 55, 76, 200);
		easy.addMonster(Pidgeotto);
		easy.addMonster(Raticate);
		assertEquals(easy.getMonsterList().get(0).toString(), Pidgeotto.toString());
		assertEquals(easy.getMonsterList().get(1).toString(), Raticate.toString());
		gameEnvEasy.swapMonsterPositions(Pidgeotto, Raticate);
		assertEquals(easy.getMonsterList().get(1).toString(), Pidgeotto.toString());
		assertEquals(easy.getMonsterList().get(0).toString(), Raticate.toString());
	}
}
