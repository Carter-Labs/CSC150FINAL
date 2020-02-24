package model.entities;

import model.Gun;

public class ArmedOfficer extends Entity implements Attack {
    /*
     * Constructors
     */
    public ArmedOfficer() { }
    public ArmedOfficer(int health,int speed, Gun weapon) {
        super(health, speed, weapon);
        this.setWeapon(weapon);
    }

    /*
     * To String
     */
    @Override public String toString() {
        return super.toString();
    }

    @Override public void attack() {
        // do something
    }
}
