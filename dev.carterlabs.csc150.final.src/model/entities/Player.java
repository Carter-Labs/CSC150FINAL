package model.entities;

import controller.ApplicationController;
import model.events.Attack;
import model.objects.Gun;
import model.objects.WeaponType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity implements Attack {
    /**
     * Array of players weapons
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
        ApplicationController.attackEvents.add(this);
        ApplicationController.updateEvents.add(this);
        ApplicationController.renderEvents.add(this);
        ApplicationController.startEvents.add(this);
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
        ApplicationController.updateEvents.add(this::Update);
    }

    private List<Gun> initGuns() {
        List<Gun> guns = new ArrayList<>();
        for (WeaponType weaponType: WeaponType.values()) {
            if(weaponType != WeaponType.BATON){
                guns.add(new Gun(25, weaponType));
            }
        }
        setActiveGun(guns.get(0));
        return guns;
    }

    /**
     * attacks
     */
    @Override public void attack() {
        //do something
    }

    @Override
    public void Start() {
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

    @Override
    public void Render(JFrame g) {
    }

    @Override
    public void Update() {
    }
}
