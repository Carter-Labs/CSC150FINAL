package model.entities;

import model.Globals;
import model.objects.Currency;
import model.objects.Gun;

public class ArmedOfficer extends Entity implements Attack, Die {
    /*
     * Variables
     */
    private Currency[] currencyToDrop;
    /*
     * Constructors
     */
    public ArmedOfficer(int health,int speed, Gun weapon) {
        super(health, speed, weapon);
        this.setWeapon(weapon);
        int numberOfCoins = Globals.rand.nextInt(5) + 1;
        currencyToDrop = new Currency[numberOfCoins];
        for (int i = 0; i < currencyToDrop.length - 1; i++) {
            currencyToDrop[i] = new Currency(Globals.rand.nextInt(5)+ 1);
        }
    }

    /*
     * To String
     */
    @Override public String toString() {
        return super.toString();
    }

    /*
     * attacks enemy
     */
    @Override public void attack() {
        // do something
    }

    /*
     * dies and drops currency
     */
    @Override public void die() {
        //do something (loop through currencyToDrop and emmit it from node)
    }
}
