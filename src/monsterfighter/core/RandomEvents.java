package monsterfighter.core;

import java.util.Random;

import java.util.ArrayList;

/**
 * The random events class deals with the random events that happen at the end of each day.
 */

public class RandomEvents {
	
	// Creates the random variable.
	Random rng = new Random();
	
	
	/**
	 * The levels up method takes a player and gives each monster the player owns a chance of being leveled up
	 * so that all the monsters statistics increase by 25%. Each monster has an equal chance of being upgraded.
	 * The chance depends on what day the player is on. With the chance increasing as the players current day 
	 * increases.
	 * 
	 * @param player The player who's monsters have a chance of being upgraded.
	 * @param seed The seed used for the random parameter.
	 * @return result Information about what random events occurred/did not occur
	 */
	public String levelsUp(Player player, int seed){
		rng = new Random(seed);
		boolean upgrade = false;
		String result = "";
		for(Monster monster : player.getMonsterList()) {
			int chance = (player.getCurrentDay()/player.getDaysLeft()) * 5;
			if (rng.nextInt(100) < chance){
				monster.upgrade(1.25);
				result += "Your monster " + monster.getMonsterName() + " was upgraded by 25%.\n";
				upgrade = true;
			}
		}
		if (!upgrade) {
			result += "None of your monsters were upgraded overnight\n";
		}
		return result;
	}
	
	
	/**
	 * The delete monster method takes a player and has a chance to delete one of the players monsters. Only one 
	 * of the players monsters can be deleted. The chance per monster is between 10% for a monster on zero 
	 * health and 5% for a monster on full health.
	 * 
	 * @param player The player who's monsters have chance of being deleted.
	 * @return result Information about what random events occurred/did not occur.
	 * @param seed The seed used for the random parameter.
	 */
	public String deleteMonster(Player player, int seed){
		rng = new Random(seed);
		String result  = "";
		boolean loopContinue = true;
		while (loopContinue) {
			for(Monster monster : player.getMonsterList()) {
				int chance = (10 - monster.getCurrentHealth()/monster.getMaxHealth() * 5);
				if (rng.nextInt(100) < chance) {
					player.removeMonster(monster);
					result += "Sadly Your monster " + monster.getMonsterName() + " was killed overnight\n";
					return result;
				}
			}
			loopContinue = false;
		}
		result += "None of your monsters were killed overnight\n";
		return result;
	}
	
	
	/**
	 * The add monster methods takes a player and has a chance of giving the player a new monster. The chance
	 * depends on how many free slots the player has. With the chance being 0% if the player has 4 full slots
	 * and 12% per slot if the player has no monsters. Only one monster can be added.
	 * 
	 * @param player The player who has the chance of getting a new monster.
	 * @param monsterList The new monster is randomly picked from this list.
	 * @return result Information about what random events occurred/did not occur
	 * @param seed The seed used for the random parameter.
	 */
	public String addMonster(Player player, ArrayList<Monster> monsterList, int seed) {
		rng = new Random(seed);
		String result = "";
		boolean loopContinue = true;
		while(loopContinue) {
			for(int i = 0; i < 4 - monsterList.size(); i++) {
				int chance = 3*(4 - player.getMonsterList().size()); 
				if (rng.nextInt(100) < chance) {
					int index = rng.nextInt(monsterList.size());
					player.addMonster(monsterList.get(index));
					result += "You gained an extra monster called " + monsterList.get(index).getMonsterName()
							+ " overnight\n";
					return result;
				}
			}
			loopContinue = false;
		}
		result += "You gained no extra monsters overnight\n";
		return result;
	}
	
	
	/**
	 * The all random events methods calls levels up, delete monster and add monster methods. This allows us
	 * to only have to call on method each day instead of three. The order is levels up first as we don't
	 * want you to be able to get a new monster and level it up in the same night. Delete is next as we don't
	 * want you to get a new monster and then lose it in the same night.
	 * 
	 * @param player The player who has the chance of getting a new monster.
	 * @param monsterList The new monster is randomly picked from this list.
	 * @return result The string with information about the random events.
	 */
	public String allRandomEvents(Player player, ArrayList<Monster> monsterList) {
		int seed = rng.nextInt();
		String result = "";
		result += this.levelsUp(player, seed);
		seed = rng.nextInt();
		result += this.deleteMonster(player, seed);
		seed = rng.nextInt();
		result += this.addMonster(player, monsterList, seed);
		return result;
	}
	
//	public static void main(String[] args) {
////		Player Hugo = new Player();
////		Hugo.setDays(15);
////		Hugo.setCurrentDay(1);
////		Monster Mike = new Monster("Mike", "common",100, 50, 25, 100);
////		Monster Mike2 = new Monster("Mike2", "common", 100, 50, 25, 100);
////		Monster James = new Monster("James", "common", 200, 100, 50, 250);
////		Hugo.addMonster(Mike);
////		Hugo.addMonster(James);
////		RandomEvents randomEvents = new RandomEvents();
////		ArrayList<Monster> monsterList = new ArrayList<Monster>();
////		monsterList.add(James);
////		monsterList.add(Mike);
////		Player jeesung = new Player();
//		
////		for (int i = 1; i < 15; i++) {
////			Hugo.setCurrentDay(i);
////			System.out.println(randomEvents.addMonster(jeesung,monsterList));
////		}
//		Monster mike = new Monster("Mike","Common", 100, 50, 25, 100);
//		Monster mike2 = new Monster("Mike2","Common", 100, 50, 25, 100);
//		Monster james = new Monster("James","Common", 200, 100, 50, 250);
//		Monster james2 = new Monster("James2","Common", 200, 100, 50, 250);
//		Player hugo = new Player();
//		hugo.setCurrentDay(1);
//		hugo.setDays(15);
//		ArrayList<Monster> monsterList = new ArrayList<Monster>();
//		monsterList.add(james);
//		monsterList.add(mike);
//		RandomEvents randomEvents = new RandomEvents();
//		
//		hugo.addMonster(james2);
//		hugo.addMonster(james);
//		
//		Random rng = new Random(1);
//		for(int i=1; i < 10; i++) {
//			System.out.println(randomEvents.deleteMonster(hugo, 311));
//			System.out.println(i);
//		}
//		
////		int totalcount = 0;
////		int runs = 10;
////		for (int i = 0; i < runs; i++) {
////			int count = 0;
////			while (Hugo.getMonsterList().get(0).getCurrentHealth() != (200 * 1.25)) {
////				count ++;
////				randomEvents.levelsUp(Hugo);
////			}	
////			James.setCurrentHealth(200);
////			totalcount += count;
////			System.out.print(count);
////		}
////		System.out.print("\n" + totalcount/runs);
////		while (Hugo.getMonsterList().size() == 3) {
////			randomEvents.deleteMonster(Hugo);
////		}
////		System.out.print(Hugo.getMonsterList().size());
////		for(Monster monster : Hugo.getMonsterList()) {
////			System.out.println(monster);
////		}
////		ArrayList<Monster> monsterList = new ArrayList<Monster>();
////		monsterList.add(James);
////		monsterList.add(Mike);
////		int count = 0;
////		for (int i = 0; i < runs; i++) {
////			while (Hugo.getMonsterList().size() == 2) {
////				randomEvents.addMonster(Hugo, monsterList);
////				count++;
////			}
////			Hugo.removeMonster(Hugo.getMonsterList().get(0));
////		}
////		System.out.println(count/runs);
////		for(Monster monster : Hugo.getMonsterList()) {
////			System.out.println(monster);
////		}
		
//	}
	
}	
