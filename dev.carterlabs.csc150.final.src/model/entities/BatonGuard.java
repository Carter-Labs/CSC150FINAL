package model.entities;

import model.Globals;
import model.objects.Currency;
import model.objects.Weapon;

public class BatonGuard  extends Entity implements Attack, Die {
    /*
     * Variables
     */
    private Currency[] currencyToDrop;
    /*
     * Constructors
     */
    public BatonGuard(int health,int speed, Weapon baton) {
        super(health, speed, baton);
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

    @Override public void attack() {
       //do something
    }

    @Override public void die() {
        //do something (loop through currencyToDrop and emmit it from node)
    }
}
