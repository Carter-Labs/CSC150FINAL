package model;

import model.objects.WeaponType;

import java.util.Random;

public class Globals {
    /**
     * Generals
     */
    public static Random rand = new Random();

    /**
     * ShopController.java used variables
     */
    public static double playerReloadSpeed;
    public static int playerProjectileCount;
    public static int playerMagSize;
    public static int avaliableCurrency;
    public static int playerHealth;
    public static int playerSpeed;

    /**
     * UIController.java used variables
     */
    public static int amountOfCurrency;

    /**
     * Gun Selection variables
     */
    public static WeaponType weaponType;

    /**
     * Info for Chamber.java
     */
    public static double inintalBossSpanPerc = 0.05;
    public static boolean isBossInChamber;
    public static int maxNumOfOfficersAndGuards = 5;

    /**
     * Loads all the saved variables in this file and set them directly
     */
    public static void loadData()  {
        //load saved data
    }

    /**
     * Saves all the variables in this file to the save location
     */
    public static void saveData() {
        //Saves every value in this file except the generals
    }
}
