package controller;

import model.Globals;

public class ShopController {

    /**
     * Updates and saves the player health
     */
    public void upgradePlayerHealth() {
        Globals.player.setHealth(Globals.player.getHealth() + 1);
        Globals.saveData();
    }

    /**
     * Updates ans saves the player speed
     */
    public void upgradePlayerSpeed() {
        Globals.player.setHealth(Globals.player.getHealth() + 1);
        Globals.saveData();
    }


}
