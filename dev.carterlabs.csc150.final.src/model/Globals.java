package model;

import model.objects.Gun;
import model.objects.WeaponType;

import java.util.Random;

public class Globals {
    /**
     * Generals
     */
    public static Random rand = new Random();

    /**
     * UIController.java used variables
     */
    public static int amountOfCurrency;
    public static int levelNumber;

    /**
     * Array of players weapons *needs to be build on first load
     */
    public static Gun[] playerGuns;

    /**
     * Info for Chamber.java
     */
    public static double bossSpanPerc;//////////////
    public static boolean isBossInChamber; //found from level number in level.java
    public static int maxNumOfOfficersAndGuards; //found from level number in level.java

    /**
     * Loads all the saved variables in this file and set them directly
     */
    public static void loadData() {
        //load saved data
        //load file
        //if(file is null) {
            //firstLoadSaveData();
        //}
    }

    /**
     * Saves all the variables in this file to the save location
     */
    public static void saveData() {
        //Saves every value in this file except the generals
    }

    /**
     * Called only once, used to set values on first load
     */
    public static void firstLoadSaveData() {
        Gun ar = new Gun(25, WeaponType.AR);
        Gun smg = new Gun(25, WeaponType.SMG);
        Gun shotgun = new Gun(25, WeaponType.SHOTGUN);
        Gun sniper = new Gun(25, WeaponType.SNIPER);
        Gun rocketLauncher = new Gun(75, WeaponType.ROCKET_LAUNCHER);
        Gun rayGun = new Gun(25, WeaponType.RAY_GUN);
        playerGuns = new Gun[]{ar,smg,shotgun,sniper,rocketLauncher,rayGun};
        amountOfCurrency = 0;
        saveData();
    }
}
