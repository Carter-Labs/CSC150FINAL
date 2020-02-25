package model.entities;

import model.objects.Gun;

public class Player extends Entity implements Attack {
    /*
     * Constructors
     */
    public Player(int health,int speed, Gun weapon){
        super(health, speed, weapon);
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
