package model.level;

import model.Globals;
import model.entities.BatonGuard;
import model.entities.Boss;
import model.entities.Entity;
import model.objects.Weapon;
import model.objects.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class Chamber implements Generate {
    /*
     * Variables
     */
    private GameObject[] objects;
    private List<Entity> entities = new ArrayList<>();

    public Chamber(){
        this.generate();
    }

    /*
     * Generates Chamber
     */
    @Override public void generate() {
        //add boss if in range to list of entities
        isBossInChamber();
        //add num of enemies to list of entities

    }

    public void draw() {
        //Draws the chamber in the view
    }

    /*
     * Determines the chance of a boss spawning in a chamber
     */
    private void isBossInChamber() {
        int num = Globals.rand.nextInt(100) + 1;
        if (num < Globals.inintalBossSpanPerc * 100) {
            Globals.isBossInChamber = true;
            List<Entity> newArr = new ArrayList<>(this.getEntities());
            newArr.add(new Boss(300,100, new Weapon(20, WeaponType.SHOTGUN)));
            this.setEntities(newArr);
        }
        else {
            Globals.isBossInChamber = false;
        }
    }

    /*
     * adds random amount of armed officers and baton guards
     */
    private void addOfficersAndGuards() {
//        int numOfGuards = Globals.rand.nextInt(Globals.maxNumOfOfficersAndGuards - 2) + 1;
//        int numOfOfficers = Globals.maxNumOfOfficersAndGuards - numOfGuards;
//        for (int i = 1; i < numOfGuards; i++) {
//            List<Entity> newArr = new ArrayList<>(this.getEntities());
//            newArr.add(new BatonGuard(110,100,));
//        }
    }
    /*
     * Getters and Setters
     */
    public GameObject[] getObjects() {
        return objects;
    }
    public void setObjects(GameObject[] objects) {
        this.objects = objects;
    }
    public List<Entity> getEntities() {
        return entities;
    }
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
