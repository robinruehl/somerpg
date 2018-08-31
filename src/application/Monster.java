package application;

import java.util.Random;

public class Monster {
	Random rand = new Random();
	String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
	


	public static class Enemy {
		int maxEnemyHealth = 100;
		float enemyNewHealth;
		int maxEnemyAttackDMG = 25;
		float enemyHealth;
		int level;
		String enemyname;
		static int enemyNBR;

		public static int getEnemyNBR() {
			return enemyNBR;
		}

		public static void setEnemyNBR(int enemyNBR) {
			Enemy.enemyNBR = enemyNBR;
		}

		/*public static void getnewEnemy() {
			enemyHealth = rand.nextInt(maxEnemyHealth);
			enemyname = enemies[rand.nextInt(enemies.length)];
			return ;
		}*/
	}
	public static class Player {
		static int luck = 10;
		static float money = 0;
		static float health = 100;
		static float maxHealth = 100;
		static int attackDamage = 50;
		static int healthPots = 10;
		static int intelligence = 10;
		static float experience = 0;
		static int level = 1;
		static int perkpoints = 25;
		
		public static int getPerkpoints() {
			return perkpoints;
		}
		public static void setPerkpoints(int perkpoints) {
			Player.perkpoints = perkpoints;
		}
		public static float getExperience() {
			return experience;
		}
		public static void setExperience(float experience) {
			Player.experience = experience;
		}
		public static int getIntelligence() {
			return intelligence;
		}
		public static void setIntelligence(int intelligence) {
			Player.intelligence = intelligence;
		}
		public static int getLuck() {
			return luck;
		}
		public static void setLuck(int luck) {
			Player.luck = luck;
		}
		public static float getMoney() {
			return money;
		}
		public static void setMoney(float money) {
			Player.money = money;
		}
		public static int getLevel() {
			return level;
		}
		public static void setLevel(int level) {
			Player.level = level;
		}
		public static float getHealth() {
			return health;
		}
		public static void setHealth(float health) {
			Player.health = health;
		}
		public static float getMaxHealth() {
			return maxHealth;
		}
		public static void setMaxHealth(float maxHealth) {
			Player.maxHealth = maxHealth;
		}
		public static int getAttackDamage() {
			return attackDamage;
		}
		public static void setAttackDamage(int attackDamage) {
			Player.attackDamage = attackDamage;
		}
		public static int getHealthPots() {
			return healthPots;
		}
		public static void setHealthPots(int healthPots) {
			Player.healthPots = healthPots;
		}
		public static float getHealthPotsHeal() {
			return healthPotsHeal;
		}
		public static void setHealthPotsHeal(float healthPotsHeal) {
			Player.healthPotsHeal = healthPotsHeal;
		}
		public static int getHealthPotDropChance() {
			return healthPotDropChance;
		}
		public static void setHealthPotDropChance(int healthPotDropChance) {
			Player.healthPotDropChance = healthPotDropChance;
		}
		static float healthPotsHeal = 40;
		static int healthPotDropChance = 50; //in %

	}
}
