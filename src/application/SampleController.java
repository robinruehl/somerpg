package application;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

import javax.swing.event.EventListenerList;

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
    private Button buttonclose;

    @FXML
    private TextField consoleinp;

    @FXML
    private ScrollPane consoleTextScrollPane;

    @FXML
    private TitledPane titledPane;

    @FXML
    private Text consoleText;

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonEnter;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private ProgressBar xpBar;

    @FXML
    private ProgressBar enemyHealthbar;

    @FXML
    private Text enemyName;

    @FXML
    private Text playermoney;




    Random rand = new Random();

	String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
	int maxEnemyHealth = 100;
	float enemyNewHealth;
	int maxEnemyAttackDMG = 25;
	float enemyHealth;
	float health = 100;
	float maxHealth = 100;
	int attackDamage = 50;
	int healthPots = 5;
	float healthPotsHeal = 40;
	int healthPotDropChance = 50; //in %
	boolean response = true;
	String consoleTXT;
	String inputTXT;
	boolean input = false;
	boolean encounterIsWaitingForInput = false;
	String enemy;
	boolean scrollbarListener = false;
	boolean hpListener = false;
	boolean xpListener = false;
	int turn = 0;
	float luck = 10;
	float money = 0;
	boolean yourTurn = false;

	double hpbar;

	public void startgame() {

		consoleWrite("start");

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
			healthBar.setProgress(health/maxHealth);
			newEnemy();
		}
	}

	public void newEnemy() {
		enemyHealth = rand.nextInt(maxEnemyHealth);
		enemy = enemies[rand.nextInt(enemies.length)];

		enemyNewHealth = enemyHealth;
		enemyHealthbar.setProgress(enemyHealth/enemyNewHealth);

		consoleWrite("\t"+ enemy + " appeard \n");

		enemyName.setText(enemy);

		encounter();
	}

	public void encounter() {
		turn ++;
		consoleWrite("Turn number " + turn);

		consoleWrite("\t your HP: " + health);
		consoleWrite("\t " + enemy + "'s HP: " + enemyHealth);
		consoleWrite("\t What would you like to do?");
		consoleWrite("\t 1. attack");
		consoleWrite("\t 2. drink health pot");
		consoleWrite("\t 3. run!");
		yourTurn = true;
		encounterIsWaitingForInput = true;
	}

	public void action() {
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
			consoleText.setText(text);
			consoleTXT = text;
		}
		else{
			consoleTXT = consoleTXT + "\n " + text;
			consoleText.setText(consoleTXT);
		}

	}


	public void damageCalc() {
		if (yourTurn){
			yourTurn = false;
			int damageDealt = rand.nextInt(attackDamage);
			int damageTaken = rand.nextInt(maxEnemyAttackDMG);
			enemyHealth -= damageDealt;
			health -= damageTaken;

			hpbar = (health/maxHealth);
			consoleWrite("debug hp" + health);
			consoleWrite("debug max hp" + maxHealth);
			consoleWrite("debug hp double" + hpbar);

			healthBar.setProgress(hpbar);
			enemyHealthbar.setProgress(enemyHealth/enemyNewHealth);

			consoleWrite("Youve done " + damageDealt + "damage, the enemy has " + enemyHealth + " health left.");
			consoleWrite("Youve taken " + damageTaken + "damage, you have " + health + " health left.");

			if (health <= 0) {
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

		}

	}

	public void takePot() {
		if (yourTurn){
			yourTurn = false;
			if(healthPots > 0) {
				health += healthPotsHeal;
				healthPots --;
				if (health > maxHealth) {
					health = maxHealth;
				}
				healthBar.setProgress(health/maxHealth);
				consoleWrite("Youve drank a health pot and youve heald to " + health + "HP.");
				response = true;
				if (health <= 0) {
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
				if (health <= 0) {
					consoleWrite("Youve taken too much damage and have died.");
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
			newEnemy();
		}
		else {

		}
	}
	public void lootdrop() {
		float moneyDrop = (rand.nextInt(10)*luck)/100;
		consoleWrite("!!!!youve found " + moneyDrop + " money on the ground!");
        money += moneyDrop;
        BigDecimal result;
        result = round(money,2);
        money = result.floatValue();
        displaymoney(money);
        if ((rand.nextInt(100)<=50)) {
        	healthPots ++;
        }
        consoleWrite("!!!!Youve just looted a health pot!");

	}
	public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
	private void displaymoney(float money) {
		playermoney.setText(""+money);
	}
}
