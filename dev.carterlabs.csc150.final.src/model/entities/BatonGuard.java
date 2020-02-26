package model.entities;

import model.Globals;
import model.events.Attack;
import model.objects.Currency;
import model.objects.Weapon;

import javax.swing.*;

public class BatonGuard  extends Entity implements Attack, Die {
    /**
     * Variables
     */
    private Currency[] currencyToDrop;

    /**
     * Baton Guard constructor
     * @param health Baton Guard health
     * @param speed Baton Guard speed
     * @param baton Baton Guard baton
     */
    public BatonGuard(int health,int speed, Weapon baton) {
        super(health, speed);
        int numberOfCoins = Globals.rand.nextInt(5) + 1;
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
     * @return Baton Guard description
     */
    @Override public String toString() {
        return super.toString();
    }

    @Override
    public void Render(JFrame g) {

    }

    @Override
    public void Start() {

    }

    @Override
    public void Update() { }
}
