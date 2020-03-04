package model.entities;

import model.Globals;
import model.events.Attack;
import model.objects.Currency;
import model.objects.Gun;

import javax.swing.*;

public class ArmedOfficer extends Entity implements Attack, Die {
    /**
     * Variables
     */
    private Currency[] currencyToDrop;

    /**
     * Armed Officer Constructor
     * @param health Armed Officer health
     * @param speed Armed Officer speed
     * @param weapon Armed Officer weapon
     */
    public ArmedOfficer(int health,int speed, Gun weapon) {
        super(health, speed, "./Resources/Enemies/armedOfficer"+weapon.getWeaponType().toString()+".png");
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
        // do something
    }

    /**
     * Die
     */
    @Override public void die() {
        //do something (loop through currencyToDrop and emmit it from node)
    }
    /**
     * @return Armed Officer description
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
    public void Update() {
    }
}
