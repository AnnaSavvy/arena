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
 * Level: boss (unit), lose drop, win drop, new hero, description
 * This Level class encapsulates the properties and behavior related to a game level
 */
public class Level {
    private Unit boss;
    private String description;
    
    // Constructor for the Level class
    public Level(Unit newBoss, String newDescription) {
        
        this.boss = newBoss;
        this.description = newDescription;
    }
    
    // Method to update the boss unit for the level
    public void setBoss (Unit bossChange) {
        this.boss = bossChange;
    }
    
    // Method to update the description of the level
    public void setDescription (String descriptionChange) {
        this.description = descriptionChange;
    }
    
    // Method to retrieve the boss unit for the level
    public Unit getBoss (){
        return boss;
    }
    
    // Method to retrieve the description of the level
    public String getDescription (){
        return description;
    }
}


