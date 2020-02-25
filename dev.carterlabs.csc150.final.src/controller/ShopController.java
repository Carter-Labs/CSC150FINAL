package controller;

import model.Globals;

public class ShopController {

    /**
     * Upgrades and saves the player gun reload speed
     */
    public void upgradeReloadSpeed() {
        Globals.playerReloadSpeed += 1;
        Globals.saveData();
    }

    /**
     * Upgrades and save the player gun projectile count
     */
    public void upgradeProjectileCount() {
        Globals.playerProjectileCount += 1;
        Globals.saveData();
    }

    /**
     * Upgrades and saves the player gun magazine size
     */
    public void upgradeMagSize() {
        Globals.playerMagSize += 1;
        Globals.saveData();
    }

    /**
     * Updates and saves the amount of currency the player has
     * @param cost Amount of currency spent
     */
    public void updateCurrency(int cost) {
        Globals.availableCurrency -= cost;
        Globals.saveData();
    }

    /**
     * Updates and saves the player health
     */
    public void upgradePlayerHealth() {
        Globals.playerHealth += 1;
        Globals.saveData();
    }

    /**
     * Updates ans saves the player speed
     */
    public void upgradePlayerSpeed() {
        Globals.playerSpeed += 1;
        Globals.saveData();
    }


}
