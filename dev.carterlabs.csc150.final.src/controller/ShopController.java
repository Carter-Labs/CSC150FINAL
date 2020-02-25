package controller;

import model.Globals;

public class ShopController {
    
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
