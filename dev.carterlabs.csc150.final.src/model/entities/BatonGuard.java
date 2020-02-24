package model.entities;

import model.Weapon;

public class BatonGuard  extends Entity implements Attack {
    /*
     * Constructors
     */
    public BatonGuard(){ }
    public BatonGuard(int health,int speed, Weapon baton) {
        super(health, speed, baton);
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
}
