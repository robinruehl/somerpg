package application;

import java.util.Random;

public class Monster {
	Random rand = new Random();
	String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
	


	public class Enemy {
		int maxEnemyHealth = 100;
		float enemyNewHealth;
		int maxEnemyAttackDMG = 25;
		float enemyHealth;
		int level;
		String enemyname;

		public void getnewEnemy() {
			enemyHealth = rand.nextInt(maxEnemyHealth);
			enemyname = enemies[rand.nextInt(enemies.length)];
			return ;
		}
	}
	public static class Player {
		static int luck = 10;
		static float money = 0;
		int level = 1;
		static float health = 100;
		static float maxHealth = 100;
		static int attackDamage = 50;
		static int healthPots = 5;
		
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
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
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
