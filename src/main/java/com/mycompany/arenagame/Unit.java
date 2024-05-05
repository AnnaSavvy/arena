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


/**
 * Unit class encapsulates game's heroes and bosses
 */
// Constructor for the Unit class
public class Unit {
    private int health;
    private int attack;
    private int defence;
    private int heal;
    private String name;
    private int energy;
    private int superAttack;
    private int maxHP;
    
    public Unit() {
        health = 10;
        attack = 1;
        defence = 0;
        heal = 0;
        name = " ";
        energy = 0;
        superAttack = 0;
        maxHP = 10;
    }
    
    public boolean isAlive() {
        return this.health > 0;
    }

    public void setHealth (int healthChange) {
        this.health = healthChange;
    }
    
    public void setAttack (int attackChange) {
        this.attack = attackChange;
    }
    
    public void setDefence (int defenceChange) {
        this.defence = defenceChange;
        if (defence < 0) {
            defence = 0;
        }
    }
    
    public void setHeal (int healChange) {
        this.heal = healChange;
    }
    
    public void setName (String nameChange) {
        this.name = nameChange;
    }
    
    public void setEnergy (int energyChange) {
        this.energy = energyChange;
    }
    
    public void setSuperAttack (int superAttackChange) {
        this.superAttack = superAttackChange;
    }
    
    public void setMaxHP (int maxHPChange) {
        this.maxHP = maxHPChange;
        this.health = maxHPChange;
    }
    
    public int getHealth (){
        return health;
    }
    
    public int getAttack (){
        return attack;
    }
    
    public int getDefence (){
        return defence;
    }
    
    public int getHeal (){
        return heal;
    }
    
    public String getName (){
        return name;
    }
    
    public int getEnergy (){
        return energy;
    }
    
    public int getSuperAttack (){
        return superAttack;
    }
    
    public int getMaxHP (){
        return maxHP;
    }
    
    // Method to perform a regular attack
    public void punch (Unit enemy) {
        int damage = attack - enemy.defence;
        if (damage < 0) {
            damage = 0;
        }
        enemy.setHealth(enemy.health - damage);
        energy++;
        
        System.out.println(name + " hits for " + damage + " damage!");
    }
    
    // Method to perform an ultimate attack
    public void ult (Unit enemy) {
        if (energy < 3) {
            System.out.println("You don't have enough energy to ult!");
            return;
        }
        
        int damage = 1;
        switch(superAttack) {
            case 0: 
                damage = 2 * attack - enemy.defence;
                break;
            case 1: 
                damage = attack * 3 / 2;
                break;
            case 2:
                damage = ( enemy.health + 1 ) / 2;
                break;
        }
        
        if (damage < 0) {
            damage = 0;
        }
        enemy.setHealth(enemy.health - damage);
        energy = 0;
        
        System.out.println(name + " critically hits for " + damage + " damage!");
    }
    
    // Method to heal the unit
    public void heal () {
        if (heal <= 0) {
            System.out.println(name + " tries to heal by cannot...");
            return;
        }
        
        health = health + heal;
        if (health > maxHP) {
            health = maxHP;
        }
        energy++;
        System.out.println(name + " heals for " + heal + "!");
    }  
    
}


