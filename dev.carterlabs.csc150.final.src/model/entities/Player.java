package model.entities;

import model.objects.Gun;
import model.objects.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity implements Attack {
    /**
     * Array of players weapons *needs to be build on first load
     */
    private List<Gun> guns;
    private Gun ActiveGun;
    private int currency;
    private int currentLevel;
    /**
     * Player constructor
     * @param health Player health
     * @param speed Player speed
     */
    public Player(int health, int speed){
        super(health, speed);
        guns = initGuns();
        setCurrency(0);
    }

    /**
     * A full player object construction
     * @param health Player health
     * @param speed  Player speed
     * @param currency Amount of currency the player has
     * @param level The current dungeon level the player is on
     */
    public Player(int health, int speed, int currency, int level){
        super(health, speed);
        guns = initGuns();
        setCurrency(currency);
        setCurrentLevel(level);
    }

    private ArrayList<Gun> initGuns() {
        ArrayList<Gun> guns = new ArrayList<>();
        for (WeaponType weaponType: WeaponType.values()) {
            if(weaponType != WeaponType.BATON){
                guns.add(new Gun(25, weaponType));
            }
        }
        return guns;
    }

    /**
     * attacks
     */
    @Override public void attack() {
        //do something
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public List<Gun> getGuns() {
        return guns;
    }

    public void setGuns(List<Gun> guns) {
        this.guns = guns;
    }

    public Gun getActiveGun() {
        return ActiveGun;
    }

    public void setActiveGun(Gun activeGun) {
        ActiveGun = activeGun;
    }

    /**
     * @return Player description
     */
    @Override public String toString() {
        return super.toString();
    }

}
