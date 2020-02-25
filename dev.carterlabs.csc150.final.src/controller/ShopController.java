package controller;

import model.Globals;

public class ShopController {

    public void upgradeReloadSpeed() {
        Globals.playerReloadSpeed += 1;
        Globals.saveData();
    }

    public void upgradeProjectileCount() {
        Globals.playerProjectileCount += 1;
        Globals.saveData();
    }

    public void upgradeMagSize() {
        Globals.playerMagSize += 1;
        Globals.saveData();
    }

    public void updateCurrency(int cost) {
        Globals.avaliableCurrency -= cost;
        Globals.saveData();
    }

    public void upgradePlayerHealth() {
        Globals.playerHealth += 1;
        Globals.saveData();
    }

    public void upgradePlayerSpeed() {
        Globals.playerSpeed += 1;
        Globals.saveData();
    }


}
