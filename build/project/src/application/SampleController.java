package application;

import java.math.BigDecimal;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SampleController {

	@FXML
    private AnchorPane main;
    @FXML
    private TextField consoleinp;
    @FXML
    private ScrollPane consoleTextScrollPane;
    @FXML
    private TitledPane titledPane;
    @FXML
    private  Text consoleText;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonEnter;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar xpBar;
    @FXML
    private Text playermoney;
    @FXML
    private Text playerLevel;
    @FXML
    private Text xpAmmount;
    @FXML
    private Text hpAmmount;
    @FXML
    private Button buttonAttack;
    @FXML
    private Button buttonRun;
    @FXML
    private Button buttonPot;
    @FXML
    private Text enemyName;
    @FXML
    private ProgressBar enemyHealthbar;
    @FXML
    private Text enemyhpAmmount;
    @FXML
    private Text hppotAmmount;
    @FXML
    private Text menuPlayerMaxHealth;
    @FXML
    private Text menuPlayerAttackDamage;
    @FXML
    private Text menuPlayerLuck;
    @FXML
    private Button increaseDamageStat;
    @FXML
    private Button increaseHealthStat;
    @FXML
    private Button increaseLuckStat;
    @FXML
    private Text perkpoints;
    @FXML
    private Text menuPlayerIntelligence;




    Random rand = new Random();
	boolean response = true;
	static String consoleTXT;
	String inputTXT;
	boolean input = false;
	boolean encounterIsWaitingForInput = false;
	boolean scrollbarListener = false;
	boolean hpListener = false;
	boolean xpListener = false;
	int turn = 0;
	boolean yourTurn = false;
	int posx;
	int posy;

	//Player

	/*
	float health = 100;
	float Monster.Player.maxHealth = 100;
	int Monster.Player.attackDamage = 50;
	int Monster.Player.healthPots = 5;
	float Monster.Player.healthPotsHeal = 40;
	float luck = 10;
	float money = 0;
	 */

	//Enemy

	int maxEnemyHealth = 100;
	
	private int getMaxEnemyHealth() {
		return maxEnemyHealth;
	}

	private void setMaxEnemyHealth(int maxEnemyHealth) {
		this.maxEnemyHealth = maxEnemyHealth;
	}

	private static float getEnemyNewHealth() {
		return enemyNewHealth;
	}

	private static void setEnemyNewHealth(float enemyNewHealth) {
		SampleController.enemyNewHealth = enemyNewHealth;
	}

	private int getMaxEnemyAttackDMG() {
		return maxEnemyAttackDMG;
	}

	private void setMaxEnemyAttackDMG(int maxEnemyAttackDMG) {
		this.maxEnemyAttackDMG = maxEnemyAttackDMG;
	}

	private static float getEnemyHealth() {
		return enemyHealth;
	}

	private static void setEnemyHealth(float enemyHealth) {
		SampleController.enemyHealth = enemyHealth;
	}

	private static String getEnemy() {
		return enemy;
	}

	private static void setEnemy(String enemy) {
		SampleController.enemy = enemy;
	}
	static float enemyNewHealth;
	int maxEnemyAttackDMG = 25;
	static float enemyHealth;
	String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
	static String enemy;
	static double hpbar;

	private static double getHpbar() {
		return hpbar;
	}

	private static void setHpbar(double hpbar) {
		SampleController.hpbar = hpbar;
	}

	public void startgame() {
		consoleText.setText("Welcome to the never ending Tomb!");
		consoleTXT = "Welcome to the never ending Tomb!";
		consoleWrite("----------------------------------------------");
		Float maxHealth = Monster.Player.getMaxHealth();
    	int maxAttackDamage = Monster.Player.getAttackDamage();
    	int Luck = Monster.Player.getLuck();
		menuPlayerMaxHealth.setText(""+maxHealth);
		menuPlayerAttackDamage.setText(""+maxAttackDamage);
		menuPlayerLuck.setText(""+Luck);
		if (!scrollbarListener){
			scrollbarListener = true;
			titledPane.heightProperty().addListener(
				    (observable, oldValue, newValue) -> {
				    	main.layout();
				        consoleTextScrollPane.setVvalue( 1.0d );
				    }
				);
		}

		if (response) {
			response = false;
			newEnemy();
		}
	}

	private void newEnemy() {
		enemyHealth = rand.nextInt(maxEnemyHealth);
		enemy = enemies[rand.nextInt(enemies.length)];
		enemyNewHealth = enemyHealth;
		hpbar = (Monster.Player.health/Monster.Player.maxHealth);
		//consoleWrite("debug hp" + Monster.Player.health);
		//consoleWrite("debug max hp" + Monster.Player.maxHealth);
		//consoleWrite("debug hp double" + hpbar);
		consoleWrite("\t An enemy of the "+ enemy + " type just appeard");
		enemyName.setText(enemy);
		update();
		encounter();
	}

	private void encounter() {
		turn ++;
		consoleWrite("----------------------------------------------");
		consoleWrite("Turn number " + turn);
		consoleWrite("----------------------------------------------");
		consoleWrite("\t your HP: " + Monster.Player.health);
		consoleWrite("\t " + enemy + "'s HP: " + enemyHealth);
		consoleWrite("\t What would you like to do? \n");
		consoleWrite("\t 1. attack");
		consoleWrite("\t 2. drink health pot");
		consoleWrite("\t 3. run!");
		yourTurn = true;
		encounterIsWaitingForInput = true;
	}

	private void action() {
		if(inputTXT.equals("1")){
			damageCalc();
			consoleWrite("debug action damage");
		}
		else if(inputTXT.equals("2")){
			takePot();
			consoleWrite("debug action pots");
		}
		else if(inputTXT.equals("3")){
			run();
			consoleWrite("debug action run");
		}
		else {
			consoleWrite("debug invalid");
			action();
		}

	}

	public void newInput() {
		inputTXT = consoleinp.getText();
		if (inputTXT.length()==0) {
			input = false;
		}
		else {
			input = true;
			inputTXT = consoleinp.getText();
			consoleWrite(inputTXT);
			consoleinp.setText(null);
			if (encounterIsWaitingForInput) {
				encounterIsWaitingForInput = false;
				action();
			}
		}
	}
	private void consoleWrite(String text) {
		if (consoleTXT == null) {
			System.out.println("consoleTXT == null");
			System.out.println(text);
			System.out.println(consoleTXT);
			consoleText.setText(text);
			consoleTXT = text;
		}
		else{
			System.out.println("else");
			System.out.println(text);
			System.out.println(consoleTXT);
			consoleTXT = consoleTXT + "\n " + text;
			consoleText.setText(consoleTXT);
			
		}
	}
	public void damageCalc() {
		if (yourTurn){
			yourTurn = false;
			int damageDealt = rand.nextInt(Monster.Player.attackDamage);
			int damageTaken = rand.nextInt(maxEnemyAttackDMG);
			enemyHealth -= damageDealt;
			Monster.Player.health -= damageTaken;
			hpbar = (Monster.Player.health/Monster.Player.maxHealth);
			consoleWrite("Youve done " + damageDealt + "damage, the enemy has " + enemyHealth + " health left.");
			consoleWrite("Youve taken " + damageTaken + "damage, you have " + Monster.Player.health + " health left.");
			update();
			if (Monster.Player.health <= 0) {
				consoleWrite("Youve taken too much damage and have died.");
				response = true;
			}
			else if (enemyHealth <= 0) {
				consoleWrite("The enemy has died");
				lootdrop();
				consoleWrite("----------------------------------------------");
				newEnemy();
			}
			else {
				encounter();
			}
		}
		else {

		}

	}

	public void takePot() {
		if (yourTurn){
			yourTurn = false;
			update();
			if(Monster.Player.healthPots > 0) {
				Monster.Player.health += Monster.Player.healthPotsHeal;
				Monster.Player.healthPots --;
				if (Monster.Player.health > Monster.Player.maxHealth) {
					Monster.Player.health = Monster.Player.maxHealth;
					update();
				}
				healthBar.setProgress(Monster.Player.health/Monster.Player.maxHealth);
				consoleWrite("Youve drank a health pot and youve heald to " + Monster.Player.health + "HP.");
				response = true;
				if (Monster.Player.health <= 0) {
					consoleWrite("Youve taken too much damage and have died.");
				}
				else if (enemyHealth <= 0) {
					consoleWrite("The enemy has died");
					lootdrop();
					newEnemy();
				}
				else {
					encounter();
				}
			}
			else {
				consoleWrite("No health pots left");
				response = true;
				if (Monster.Player.health <= 0) {
					consoleWrite("Youve taken too much damage and have died.");
					response = true;
				}
				else if (enemyHealth <= 0) {
					consoleWrite("The enemy has died");
					newEnemy();
				}
				else {
					encounter();
				}
			}
		}
		else {

		}
	}
	public void run() {
		if (yourTurn){
			yourTurn = false;
			consoleWrite("Youve ran away from the enemy!");
			response = true;
			update();
			newEnemy();
		}
		else {

		}
	}
	private void lootdrop() {
		float moneyDrop = (rand.nextInt(100)*Monster.Player.luck)/100;
		consoleWrite("!!!!youve found " + moneyDrop + " money on the ground!");
		Monster.Player.money += moneyDrop;
        BigDecimal result;
        result = round(Monster.Player.money,2);
        Monster.Player.money = result.floatValue();
        displaymoney(Monster.Player.money);
        float potChance = (Monster.Player.healthPotDropChance*Monster.Player.luck/25);
        int potRoll = rand.nextInt(100);
        consoleWrite("Youve rolled a " + potRoll + " / " + potChance);
        if (potRoll<=potChance) {
        	Monster.Player.healthPots ++;
        	consoleWrite("!!!!Youve just looted a health pot!");
        }
        else {
        	consoleWrite("Thus you did not find a health pot on his body.");
        }
        int xpRoll = (rand.nextInt(10)/10*200);
        float experience = (Monster.Player.getIntelligence()*xpRoll);
        Monster.Player.setExperience(experience);
        if ((Monster.Player.getExperience())>=(Monster.Player.getLevel()*150)) {
        	Monster.Player.setExperience(0);
        	Monster.Player.setLevel(Monster.Player.getLevel() + 1);
        	Monster.Player.setPerkpoints(Monster.Player.getPerkpoints()+1);
        }
	}
	private static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
	private void displaymoney(float money) {
		playermoney.setText(""+money);
	}
	private void update() {
    	
    	int healthPots = Monster.Player.getHealthPots();
    	double hpbar = SampleController.getHpbar();
    	float health = Monster.Player.getHealth();
    	float enemyHealth = SampleController.getEnemyHealth();
    	float enemyNewHealth = SampleController.getEnemyNewHealth();
    	String enemy = SampleController.getEnemy();
    	Float maxHealth = Monster.Player.getMaxHealth();
    	int maxAttackDamage = Monster.Player.getAttackDamage();
    	int Luck = Monster.Player.getLuck();
    	int pp = Monster.Player.getPerkpoints();
    	int intelligence = Monster.Player.getIntelligence();
    	
    	hppotAmmount.setText("" + healthPots);
		healthBar.setProgress(hpbar);
		hpAmmount.setText("" + health);
		enemyHealthbar.setProgress(enemyHealth/enemyNewHealth);
		enemyhpAmmount.setText("" + enemyHealth);
		enemyName.setText(enemy);
		menuPlayerMaxHealth.setText(""+maxHealth);
		menuPlayerAttackDamage.setText(""+maxAttackDamage);
		menuPlayerLuck.setText(""+Luck);
		menuPlayerIntelligence.setText(""+intelligence);
		perkpoints.setText(""+pp);
		
	}
	public void luckincrease() {
		int pp = Monster.Player.getPerkpoints();
		if (pp>0) {
			Monster.Player.setPerkpoints(pp-1);
			Monster.Player.setLuck(Monster.Player.getLuck()+1);
			consoleWrite("increased luck");
			System.out.println("increased luck");
			update();
		}	
	}
	public void healthincrease() {
		int pp = Monster.Player.getPerkpoints();
		if (pp>0) {
			Monster.Player.setPerkpoints(pp-1);
			Monster.Player.setMaxHealth(Monster.Player.getMaxHealth()+10);
			Monster.Player.setHealth(Monster.Player.getHealth()+10);
			consoleWrite("increased max health");
			System.out.println("increased max health");
			update();
		}
	}
	public void damageincrease() {
		int pp = Monster.Player.getPerkpoints();
		if (pp>0) {
			Monster.Player.setPerkpoints(pp-1);
			Monster.Player.setAttackDamage(Monster.Player.getAttackDamage()+5);
			consoleWrite("increased attack damage");
			System.out.println("increased attack damage");
			update();
		}
	}
	public void intelligenceincrease() {
		int pp = Monster.Player.getPerkpoints();
		if (pp>0) {
			Monster.Player.setPerkpoints(pp-1);
			Monster.Player.setIntelligence(Monster.Player.getIntelligence()+1);
			consoleWrite("increased Intelligence");
			System.out.println("increased Intelligence");
			update();
		}
	}
}
