package model.entities;

import model.objects.Weapon;

public class Boss extends Entity implements Attack {
    /*
     * Constructors
     */
    public Boss(){}
    public Boss(int health,int speed, Weapon weapon){
        super(health,speed,weapon);
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
