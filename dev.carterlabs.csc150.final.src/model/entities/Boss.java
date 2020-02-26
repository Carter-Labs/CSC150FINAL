package model.entities;

import model.Globals;
import model.objects.Currency;
import model.objects.Weapon;

public class Boss extends Entity implements Attack, Die {
    /**
     * Variables
     */
    private Currency[] currencyToDrop;

    /**
     * Boss Constructor
     * @param health Boss health
     * @param speed Boss speed
     * @param weapon Boss weapon
     */
    public Boss(int health,int speed, Weapon weapon){
        super(health,speed);
        int numberOfCoins = Globals.rand.nextInt(10) + 1;
        currencyToDrop = new Currency[numberOfCoins];
        for (int i = 0; i < currencyToDrop.length - 1; i++) {
            currencyToDrop[i] = new Currency(Globals.rand.nextInt(5)+ 1);
        }
    }

    /**
     * Attack
     */
    @Override public void attack() {
        //do something
    }

    /**
     * Die
     */
    @Override public void die() {
        //do something (loop through currencyToDrop and emmit it from node)
    }
    /**
     * @return Boss description
     */
    @Override public String toString() {
        return super.toString();
    }

}
