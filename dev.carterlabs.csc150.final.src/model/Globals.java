package model;

import java.util.Random;

public class Globals {
    /*
     * Generals
     */
    public static Random rand = new Random();

    /*
     * ShopController.java
     */
    public static double playerReloadSpeed;
    public static int playerProjectileCount;
    public static int playerMagSize;
    public static int avaliableCurrency;

    /*
     * UIController.java
     */
    public static int amountOfCurrency;

    /*
     * Info for Chamber.java
     */
    public static double inintalBossSpanPerc = 0.05;
    public static boolean isBossInChamber;
    public static int maxNumOfOfficersAndGuards = 5;

    public static void loadData()  {
        //load saved data
    }

    public static void saveData() {
        //Saves every value in this file except the generals
    }
}
