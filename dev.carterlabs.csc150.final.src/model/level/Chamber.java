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
    private ChamberDoorOptions[] doors;

    public Chamber(){
        this.generate();
        doors = new ChamberDoorOptions[4];
    }

    /*
     * Generates Chamber
     */
    @Override public void generate() {
        //add boss if in range to list of entities
        generateDoors();
        isBossInChamber();
        //add num of enemies to list of entities

    }

    private void generateDoors() {
        int doors = Globals.rand.nextInt(4);
        for (int i = 0; i < doors; i++) {
            int dir = Globals.rand.nextInt(4);
            switch (dir) {
                case 0:
                    if(!addDoor(ChamberDoorOptions.NORTH, i)) --i;
                    break;
                case 1:
                    if(!addDoor(ChamberDoorOptions.EAST, i)) --i;
                    break;
                case 2:
                    if(!addDoor(ChamberDoorOptions.SOUTH, i)) --i;
                    break;
                case 3:
                    if(!addDoor(ChamberDoorOptions.WEST, i)) --i;
                    break;
            }
        }
    }

    /**
     * @param option What option you want to add
     * @param i The index to set it to
     * @return If it was able to add to the array
     */
    private boolean addDoor(ChamberDoorOptions option, int i) {
        if(!contained(option, doors)){
            doors[i] = option;
            return  true;
        } else {
            return  false;
        }
    }

    private boolean contained(ChamberDoorOptions option, ChamberDoorOptions[] array) {
        for (ChamberDoorOptions opt: array) {
            return opt.equals(option);
        }
        return false;
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
