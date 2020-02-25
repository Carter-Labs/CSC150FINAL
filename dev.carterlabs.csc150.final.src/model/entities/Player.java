package model.entities;

import model.objects.Gun;

public class Player extends Entity implements Attack {
    /**
     * Player constructor
     * @param health Player health
     * @param speed Player speed
     * @param weapon PLayer weapon
     */
    public Player(int health,int speed, Gun weapon){
        super(health, speed, weapon);
    }

    /**
     * attacks
     */
    @Override public void attack() {
        //do something
    }
    /**
     * @return Player description
     */
    @Override public String toString() {
        return super.toString();
    }

}
