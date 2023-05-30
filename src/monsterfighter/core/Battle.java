package monsterfighter.core;
import java.util.ArrayList;
import java.util.Random;

/**
 * The battle class is where the battles occur. Battle generation for each day also occurs here.
 */

public class Battle {
	
	// Creates the random variable.
	Random rng = new Random();
	
	/**
	 * The complete fight methods controls the overall fight. The front monsters of both the player
	 * and system fight until one health is zero then the next monster fights until one teams
	 * monsters are completely wiped out. For every individual battle the individual fight method is called.
	 * 
	 * @param user The user player who is fighting.
	 * @param system The system player that the user will be fighting against.
	 * @return battleLog A string which reads out the individuals fights, results and health of monsters
	 * after fights.
	 */
	public String completeFight(Player user, Player system) {
		String battleLog = "";
		ArrayList<Monster> userMonsterList = user.getMonsterList();
		ArrayList<Monster> systemMonsterList = system.getMonsterList();
		int userPosition = 0;
		int systemPosition = 0;
		while (!(userPosition >= userMonsterList.size() && systemPosition >= systemMonsterList.size())) {
			Monster userMonster = userMonsterList.get(userPosition);
			Monster systemMonster = systemMonsterList.get(systemPosition);
			battleLog += userMonster.getMonsterName() + " is fighting against " + systemMonster.getMonsterName() + ".\n";
			if (this.individualFight(userMonster, systemMonster)) {
				battleLog += userMonster.getMonsterName() + " won against " + systemMonster.getMonsterName() + ".\n";
				battleLog += userMonster.getMonsterName() + " is left on " + userMonster.getCurrentHealth() + " health.\n";
				systemPosition += 1;
				if (systemPosition >= systemMonsterList.size()) {
					if (user.getDifficulty() == "Hard") {
						user.addScore(300 * systemMonsterList.size());
					} else {
						user.addScore(150 * systemMonsterList.size());
					}
					user.addGold(200 * systemMonsterList.size());
					battleLog += "Congratulations your monster team was successful and won the fight!\n";
					break;
				}else {
					systemMonster = systemMonsterList.get(systemPosition);
					this.individualFight(userMonster, systemMonster);
				}
			}else {
				battleLog += systemMonster.getMonsterName() + " won against " + userMonster.getMonsterName()+ ".\n";
				battleLog += systemMonster.getMonsterName() + " is left on " + systemMonster.getCurrentHealth() + " health.\n";
				userPosition += 1;
				if (userPosition >= userMonsterList.size()) {
					battleLog += "Sadly your monster team was wiped out :(\n";
					break;
				} else {
					userMonster = userMonsterList.get(userPosition);
					this.individualFight(userMonster, systemMonster);
				}
			}
		}
		return battleLog;
	}
	
	
	/**
	 * The individual fight methods takes two monsters and they take turns attacking each other.
	 * The fighting continues until one monster health is zero and then the result of the fight
	 * is returned. The user monster always get the first attack.
	 * 
	 * @param userMonster The user monster that is fighting. Gets the first attack.
	 * @param systemMonster The system monster that is fighting.
	 * @return boolean Either true or false depending on if you won the fight on not.
	 */
	public boolean individualFight(Monster userMonster, Monster systemMonster) {
		while((userMonster.getCurrentHealth() > 0) && (systemMonster.getCurrentHealth() > 0)) {
			this.individualAttack(userMonster, systemMonster);
			if (userMonster.getCurrentHealth() > 0) {
				this.individualAttack(systemMonster, userMonster);
			} else {
				break;
			}
		}
		if (systemMonster.getCurrentHealth() <= 0) {
			return true;//you won
		} else {
			return false;//you lost
		}
		
	}
	
	
	/**
	 * The individual attack sets the health of the monster that is being attacked. If the defender 
	 * has a shield value of 1 or more , so has a shield, then the attack does no damage and uses a shield.
	 * 
	 * @param attacker The monster attacking and doing damage.
	 * @param defender The monster defending and losing damage.
	 */
	public void individualAttack(Monster attacker, Monster defender) {
		if (defender.getShield() == 0) {
			defender.setCurrentHealth( defender.getCurrentHealth() - attacker.getDamage());
			if (defender.getCurrentHealth() < 0) {
				defender.setCurrentHealth(0);
			}
		} else {
			defender.setShield( defender.getShield() - 1);
		}
	}
	
	
	/**
	 * The battle generation creates a list of possible fights for each day. There are always three
	 * fights. The number of monsters is 1,2,3 per fight for the first 7 days and 2,3,4 for the remaining
	 * days. Each monster for each slot is selected randomly from the monsterList parameter. For every
	 * day each monster has a 1/6 chance of having all its statistics upgrading by 25%. This means that 
	 * the fights get harder as you progress through the game with a bit of variation between them.
	 * 
	 * @param player The player who will receive a list of possible fights.
	 * @param monsterList The selection of monsters that will randomly appear in the fights.
	 * @return fightsList The list of lists of monsters which is the available fights for each day.
	 */
	public ArrayList<ArrayList<Monster>> battleGeneration(Player player, ArrayList<Monster> monsterList){
		ArrayList<ArrayList<Monster>> fightsList = new ArrayList<ArrayList<Monster>>();
		for (int i = 1; i < 4; i++) {
			int size = 0;
			ArrayList<Monster> newList = new ArrayList<Monster>();
			if (player.getCurrentDay() > 7) {
				size = i + 1;
			} else {
				size = i;
			}
			while (newList.size() < size) {
				int index = rng.nextInt(monsterList.size());
				Monster monster = monsterList.get(index);
				for (int j = 0; j < player.getCurrentDay(); j++) {
					if (rng.nextInt(6) > 4) {
						monster.upgrade(1.25);
					}
				}
				newList.add(monster);
			}
			fightsList.add(newList); 
		}		
		return fightsList;
	}
	
}
