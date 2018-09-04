package application;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

//import application.InventoryManager.Weapon;
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
    @FXML
    private Text playerXperienceDisplay;
    @FXML
    private Text playerXPtofin;




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
	static Set<Integer> foo = new HashSet<Integer>();
	static boolean canloopde = false;
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
		ItemID = new int[1000];
		ItemDamage = new int[1000];
		ItemLuck = new int[1000];
		ItemIntelligence = new int[1000];
		ItemType = new String[1000];
		ItemName = new String[1000];
		int d = 1;
		int e = 1000;
		canloopde = true;
		while (canloopde) {
			if (d<e) {
				foo.add(10*d);
				d ++;
				System.out.println(d);
			}
			else {
				canloopde = false;
			}
		}
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
		if (foo.contains(Monster.Enemy.getEnemyNBR())) {
			enemyHealth = (rand.nextInt(maxEnemyHealth/2)+maxEnemyHealth/2)*10;
			enemy = enemies[rand.nextInt(enemies.length)];
			enemyNewHealth = enemyHealth;
			maxEnemyAttackDMG = 25+25*Monster.Enemy.getEnemyNBR()/50;
			consoleWrite("\t !!!!A special enemy of the "+ enemy + " type just appeard");
		}
		else {
			enemyHealth = (rand.nextInt(maxEnemyHealth/2)+maxEnemyHealth/2);
			enemy = enemies[rand.nextInt(enemies.length)];
			enemyNewHealth = enemyHealth;
			maxEnemyAttackDMG = 25+25*Monster.Player.getLevel()/50;
			consoleWrite("\t An enemy of the "+ enemy + " type just appeard");
		}
		hpbar = (Monster.Player.health/Monster.Player.maxHealth);
		//consoleWrite("debug hp" + Monster.Player.health);
		//consoleWrite("debug max hp" + Monster.Player.maxHealth);
		//consoleWrite("debug hp double" + hpbar);
		enemyName.setText(enemy);
		Monster.Enemy.setEnemyNBR(Monster.Enemy.getEnemyNBR()+1);
		update();
		encounter();
	}

	private void encounter() {
		turn ++;
		consoleWrite("----------------------------------------------");
		consoleWrite("Turn number " + turn + ", Enemy number " + Monster.Enemy.getEnemyNBR());
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
			int damageDealt = rand.nextInt((Monster.Player.attackDamage/2))+(Monster.Player.attackDamage/2);
			int damageTaken = rand.nextInt(maxEnemyAttackDMG);
			enemyHealth -= damageDealt;
			Monster.Player.health -= damageTaken;
			hpbar = (Monster.Player.health/Monster.Player.maxHealth);
			consoleWrite("Youve done " + damageDealt + " damage, the enemy has " + enemyHealth + " health left.");
			consoleWrite("Youve taken " + damageTaken + " damage, you have " + Monster.Player.health + " health left.");
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
				update();
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
        int xpRoll = ((rand.nextInt(5)+5)*50/10);
        consoleWrite("XP Roll: "+xpRoll);
        float experience = (Monster.Player.getIntelligence()*xpRoll);
        Monster.Player.setExperience(Monster.Player.getExperience()+experience);
        consoleWrite("XP: "+experience);
        if ((Monster.Player.getExperience())>=(Monster.Player.getLevel()*150)) {
        	Monster.Player.setExperience(Monster.Player.getExperience()-(Monster.Player.getLevel()*150));
        	Monster.Player.setLevel(Monster.Player.getLevel() + 1);
        	Monster.Player.setPerkpoints(Monster.Player.getPerkpoints()+1);
        }
        if (foo.contains(Monster.Enemy.getEnemyNBR())) {
        	moneyDrop = (rand.nextInt(100)*Monster.Player.luck)/50;
    		consoleWrite("!!!!youve found " + moneyDrop + " extra money on the special enemys Body!");
    		Monster.Player.money += moneyDrop;
            result = round(Monster.Player.money,2);
            Monster.Player.money = result.floatValue();
            displaymoney(Monster.Player.money);
            xpRoll = ((rand.nextInt(5)+5)*50/5);
            consoleWrite("XP Roll: "+xpRoll);
            experience = (Monster.Player.getIntelligence()*xpRoll);
            Monster.Player.setExperience(Monster.Player.getExperience()+experience);
            consoleWrite("XP: "+experience);
            if ((Monster.Player.getExperience())>=(Monster.Player.getLevel()*150)) {
            	Monster.Player.setExperience(Monster.Player.getExperience()-(Monster.Player.getLevel()*150));
            	Monster.Player.setLevel(Monster.Player.getLevel() + 1);
            	Monster.Player.setPerkpoints(Monster.Player.getPerkpoints()+1);
            }
        }
        update();
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
    	float xptomax = (Monster.Player.getLevel()*150);
    	
    	playerLevel.setText(""+Monster.Player.getLevel());
    	playerXperienceDisplay.setText(""+Monster.Player.getExperience()+"/"+xptomax);
    	xpBar.setProgress(Monster.Player.getExperience()/xptomax);
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
	}/*
	String items[];
	int itemsA = 0;
	Random rand2 = new Random();
	String[] Types = {"Sword", "Axe", "Fist"};*/
	/*
	public void CreateWeapon (){
		itemsA = itemsA+1;
		//items[itemsA]=""+itemsA;
		Weapon Weapon = new Weapon();
		int ayy = rand.nextInt(30);
		Weapon.dmg = ayy;
		Weapon.type = Types[rand.nextInt(Types.length)];
		Weapon.iD = itemsA;
		System.out.println("created a " + Weapon.type + ", with a Damage stat of "+Weapon.dmg+", with the ID "+Weapon.iD);
	}
	public void ReadWeapon (int iD) {
		Weapon Weapon = Weapon.equals("Weapon"+iD)
	}
	class Weapon {
		String type;
		int dmg;
		int iD;
		String name;
	}*/
	String[] NameIntelli = {" ","slightly sparkling ","sparkling ","somewhat magical ","magical "};
	String[] NameLuck = {" ","slightly special ","special ","somewhat Irish ","Irish "};
	String[] NameDamage = {"blunt ","dull ","slightly sharpened ","sharp ","Nippon ", "way too sharp "};
	String[] Types = {"Sword", "Axe", "Bow"};
	int itemsA = 0;
	Random rand2 = new Random();
	//ArrayList ItemName;
	int[] ItemID;
	int[] ItemDamage;
	int[] ItemLuck;
	int[] ItemIntelligence;
	String[] ItemName;
	String[] ItemType;
	
	public class Item {
		int ItemID;
		int ItemDamage;
		int ItemLuck;
		int ItemIntelligence;
		String ItemType;
		class Weapon extends Item {
			
		}
	}
	
	public void CreateWeaponArray (){
		
		//mit arrays?
		System.out.println(itemsA);
		ItemID[itemsA] = itemsA;
		ItemDamage[itemsA] = rand2.nextInt(60);
		ItemLuck[itemsA] = rand2.nextInt(30);
		ItemIntelligence[itemsA] = rand2.nextInt(25);
		ItemType[itemsA] = Types[rand.nextInt(Types.length)];
		if (ItemDamage[itemsA]>50) {
			ItemName[itemsA] = NameDamage[4];
		}
		else if (ItemDamage[itemsA]>45) {
			ItemName[itemsA] = NameDamage[3];
		}
		else if (ItemDamage[itemsA]>25) {
			ItemName[itemsA] = NameDamage[2];
		}
		else if (ItemDamage[itemsA]>10) {
			ItemName[itemsA] = NameDamage[1];
		}
		else {
			ItemName[itemsA] = NameDamage[0];
		}
		
		if (ItemLuck[itemsA]>25) {
			ItemName[itemsA] = ItemName[itemsA] + NameLuck[4];
		}
		else if (ItemLuck[itemsA]>22) {
			ItemName[itemsA] = ItemName[itemsA] + NameLuck[3];
		}
		else if (ItemLuck[itemsA]>17) {
			ItemName[itemsA] = ItemName[itemsA] + NameLuck[2];
		}
		else if (ItemLuck[itemsA]>12) {
			ItemName[itemsA] = ItemName[itemsA] + NameLuck[1];
		}
		else {
			ItemName[itemsA] = ItemName[itemsA] + NameLuck[0];
		}
		
		if (ItemIntelligence[itemsA]>22) {
			ItemName[itemsA] = ItemName[itemsA] + NameIntelli[4];
		}
		else if (ItemIntelligence[itemsA]>19) {
			ItemName[itemsA] = ItemName[itemsA] + NameIntelli[3];
		}
		else if (ItemIntelligence[itemsA]>12) {
			ItemName[itemsA] = ItemName[itemsA] + NameIntelli[2];
		}
		else if (ItemIntelligence[itemsA]>8) {
			ItemName[itemsA] = ItemName[itemsA] + NameIntelli[1];
		}
		else {
			ItemName[itemsA] = ItemName[itemsA] + NameIntelli[0];
		}
		
		ItemName[itemsA] += ItemType[itemsA];
		System.out.println("created the item "+ ItemName[itemsA] + " with the Type " + ItemType[itemsA] + ", with a Damage stat of "+ItemDamage[itemsA]+", with a Luck stat of "+ItemLuck[itemsA]+", with an Intelligence stat of " +ItemDamage[itemsA]+" and with the ID "+ItemID[itemsA]);
		itemsA = itemsA + 1;
		
		
	
	}
	public void CreateWeaponClass () throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		//mit class?
		System.out.println(itemsA);
		String className = "Weapon";
		Item.Weapon Weapon = (application.SampleController.Item.Weapon) Class.forName(className).newInstance();
		//Item Weapon = new Item();
		Weapon.ItemID = itemsA;
		Weapon.ItemDamage = rand2.nextInt(30)+30;
		Weapon.ItemLuck = rand2.nextInt(15)+10;
		Weapon.ItemIntelligence = rand2.nextInt(15)+10;
		Weapon.ItemType = Types[rand.nextInt(Types.length)];
		System.out.println("created a class based " + Weapon.ItemType + ", with a Damage stat of "+Weapon.ItemDamage+", with a Luck stat of "+Weapon.ItemLuck+", with an Intelligence stat of" +Weapon.ItemIntelligence+" and with the ID "+Weapon.ItemID);
		itemsA = itemsA + 1;
	}
	
	public void ReadWeaponArray () {
		int arg = 0;
		while (arg<itemsA) {
			System.out.println("ItemID: "+ItemID[arg]);
			System.out.println("ItemType: "+ItemType[arg]);
			System.out.println("ItemDamage: "+ItemDamage[arg]);
			System.out.println("ItemLuck: "+ItemLuck[arg]);
			System.out.println("ItemIntelligence: "+ItemIntelligence[arg]);
			arg++;
		}
		
	}
	
	public void ReadWeaponClass () {
		Class<?>[] a = Item.class.getClasses();
		int i = 0;
		System.out.println("im trying :(");
		while (i<a.length)
		{	
			
			Class<? extends Class[]> c = a.getClass();
			System.out.println(c.getSimpleName());
			System.out.println(i);
			String b = a[i].getName();
			System.out.println(b);
			i++;
		}
		
	}
}
