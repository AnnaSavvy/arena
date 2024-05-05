/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arenagame;

/**
* COMP1030 Final Project Battle Arena Game
* Purpose: to demonstrate ability to integrate the material they have studied
* during the COMP1030 course into a functional single java game application. 
* @author Anna Savitskaia Georgian ID: 200535874 
* August 8, 2023
*/



import java.util.Random;
import java.util.Scanner;

public class Game {
    // Declare instance variables
    private Unit heroes[];  // Array to store hero units
    private Level levels[];  // Array to store game levels
    private int score;       // Player's score
    private int currentLvl;  // Current level index
    private int currentHero; // Current hero index
    
    // Constructor for the Game class
    public Game() {
        score = 0;
        currentLvl = 0;
        currentHero = 0;
        
        // Create hero units and set their attributes
        // Create boss units and set their attributes)
        
        Unit hero1 = new Unit ();
        hero1.setAttack(3);
        hero1.setDefence(3);
        hero1.setMaxHP(10);
        hero1.setName("Defender");
        
        Unit hero2 = new Unit ();
        hero2.setAttack(5);
        hero2.setDefence(0);
        hero2.setMaxHP(15);
        hero2.setName("Warrior");
        hero2.setSuperAttack(1);
        
        Unit hero3 = new Unit ();
        hero3.setAttack(2);
        hero3.setDefence(2);
        hero3.setMaxHP(10);
        hero3.setName("Wizard");
        hero3.setHeal(3);
        hero3.setSuperAttack(2);
        
        // Initialize the array of hero units and boss units
        heroes = new Unit[3];
        heroes[0] = hero1;
        heroes[1] = hero2;
        heroes[2] = hero3;
        
        Unit boss1 = new Unit ();
        boss1.setAttack(3);
        boss1.setDefence(0);
        boss1.setMaxHP(15);
        boss1.setName("Bob The Builder");
        
        Unit boss2 = new Unit ();
        boss2.setAttack(4);
        boss2.setDefence(2);
        boss2.setMaxHP(12);
        boss2.setName("Killer");
        boss2.setHeal(4);
        
        Unit boss3 = new Unit ();
        boss3.setAttack(5);
        boss3.setDefence(0);
        boss3.setMaxHP(25);
        boss3.setName("Slayer");
        boss3.setSuperAttack(1);
        
        Unit boss4 = new Unit ();
        boss4.setAttack(6);
        boss4.setDefence(2);
        boss4.setMaxHP(30);
        boss4.setName("Demon");
        boss4.setSuperAttack(2);
        
        Unit boss5 = new Unit ();
        boss5.setAttack(8);
        boss5.setDefence(1);
        boss5.setMaxHP(45);
        boss5.setName("Destroyer");
        boss5.setHeal(10);
        
        // Initialize the array of game levels
        levels = new Level[5];
        levels[0] = new Level (boss1, "Big Cave (Level 1)");
        levels[1] = new Level (boss2, "Foggy Forest (Level 2)");
        levels[2] = new Level (boss3, "Dark Ravine (Level 3)");        
        levels[3] = new Level (boss4, "Mythical Cave (Level 4)");
        levels[4] = new Level (boss5, "Deadly Lands (level 5)");
    }
    
    // Method to get player input
    public static int getPlayerInput() {
        int value = 0;
        while( value == 0 ) {
            try {
                Scanner input = new Scanner(System.in);
                value = input.nextInt();
                if ( value < 1 || value > 3 ) {
                    value = 0;
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Values 1 to 3 only: ");
            }
        }
        return value;
    }
    
    // Method to start the game
    public void start() {
        // Initialize a random number generator
        Random rand = new Random();
        
        // Display introductory messages and hero information
        System.out.println("Welcome, traveller! You are almost ready to fight... Please choose your figther (input 1, 2 or 3):");
        System.out.println();
        
        // Get player's choice of hero
        for (int i = 0; i < 3; i++) {
            System.out.print("Hero " + ( i + 1 ) + ". " + heroes[i].getName());
            System.out.print("  (Health: " + heroes[i].getHealth() + ", Attack power: " + heroes[i].getAttack());
            System.out.println(", Defence: " + heroes[i].getDefence() + ", Heal power: " + heroes[i].getHeal() + ")");
            switch(heroes[i].getSuperAttack()) {
                case 0:
                    System.out.println("   Super attack can do double damage! ");
                    break;
                case 1: 
                    System.out.println("   Super attack can do 1.5 damage and ignores enemy's defence! ");
                    break;
                case 2:
                    System.out.println("   Super attack destroys half of enemy's health! ");
                    break;
            }
            System.out.println();
        }
        
        int character = getPlayerInput();
        currentHero = character - 1;
        System.out.println("You have selected a " +  heroes[currentHero].getName() + " hero. Good choice!");
        System.out.println();
        
        System.out.println("For basic attack input 1. For ultimate attack input 2 (you need 3 energe to use it). To heal input 3.");
        
        do {
            // Get the current boss for the level
            Unit boss = levels[currentLvl].getBoss();
            System.out.println();
            // Display level and boss information
            System.out.print("You enter a " + levels[currentLvl].getDescription() + "! You see " + boss.getName());
            System.out.println(" (HP: " + boss.getHealth() + ", Defence: " + boss.getDefence() + ") who is going to attack you! Please choose your move: ");
            
            boolean bossCanHeal = boss.getHeal() > 0;
            
            // Battle loop for player and boss actions
            while (boss.isAlive() && heroes[currentHero].isAlive()) {
                int move = getPlayerInput();
                // Perform the chosen move
                if (move == 1) { 
                    heroes[currentHero].punch(boss);
                }
                else if (move == 2) {
                    heroes[currentHero].ult(boss);
                }
                else if (move == 3) {
                    heroes[currentHero].heal();
                }

                // Handle boss actions
                if (boss.isAlive()) {
                    if (boss.getEnergy() >= 3) {
                        boss.ult(heroes[currentHero]);
                    } else if (bossCanHeal && boss.getHealth() < boss.getMaxHP() / 2) {
                        boss.heal();
                        bossCanHeal = false;
                    } 
                    else {
                        boss.punch(heroes[currentHero]);
                    }
                }
                // Display updated stats
                System.out.println("****************");
                System.out.println("Hero current HP: " + heroes[currentHero].getHealth() + ". Hero current energy: " + heroes[currentHero].getEnergy());
                System.out.println("Boss current HP: " + boss.getHealth());
            }
            System.out.println();
            
            // Determine if the player won or lost the level
            // Provide rewards based on outcome
            if (heroes[currentHero].isAlive()) {
                int maxHP = heroes[currentHero].getMaxHP();
                score += 500 - (maxHP - heroes[currentHero].getHealth()) * 7;
                
                heroes[currentHero].setHealth(maxHP);
                System.out.println("You win this level.");
                
                int randomLoot = rand.nextInt(4);
                switch(randomLoot) {
                    case 0:
                        System.out.println("You found Helmet! Increases defence + 2");
                        heroes[currentHero].setDefence(heroes[currentHero].getDefence() + 2);
                        break;
                    case 1: 
                        System.out.println("You found Magic Flower! Increases heal + 3");
                        heroes[currentHero].setHeal(heroes[currentHero].getHeal() + 3);
                        break;
                    case 2:
                        System.out.println("You found Sword! Increases attack + 2");
                        heroes[currentHero].setAttack(heroes[currentHero].getAttack() + 2);
                        break;
                    case 3:
                        System.out.println("You found Magic Potato! Increases max health + 5");
                        heroes[currentHero].setMaxHP(heroes[currentHero].getMaxHP() + 5);
                        break;
                }
                currentLvl++;
                
            } else {
                score -= 200;
                if (score < 1) {
                    score = 1;
                }
                System.out.println("You lose. Try again!");
                heroes[currentHero].setHealth(heroes[currentHero].getMaxHP());
                boss.setHealth(boss.getMaxHP());
                
                int randomLoot = rand.nextInt(4);
                switch(randomLoot) {
                    case 0:
                        System.out.println("You found Poison bottle! Decreases enemy's health by 20%");
                        boss.setMaxHP(boss.getMaxHP() * 4 / 5);
                        break;
                    case 1: 
                        System.out.println("You found green Acid! Decreases enemy's defence by 2");
                        boss.setDefence(boss.getDefence() - 2);
                        break;
                    case 2:
                        System.out.println("You found Knife! Increases attack by 1");
                        heroes[currentHero].setAttack(heroes[currentHero].getAttack() + 1);
                        break;
                    case 3:
                        System.out.println("You found Soup! Increases max health by 2");
                        heroes[currentHero].setMaxHP(heroes[currentHero].getMaxHP() + 2);
                        break;
                }
            }
        // Continue until all levels are completed
        } while (currentLvl < 5);
        // Display the final score
        System.out.println("Congrats!!! Your score is: " + score);
    }    
}
