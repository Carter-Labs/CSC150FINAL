package model.level;

import model.Globals;
import model.entities.Boss;
import model.objects.Weapon;
import model.objects.WeaponType;

import java.util.Arrays;
import java.util.Random;

public class Level implements Generate {
    /*
     *Variables
     */
    private Chamber[] chambers;
    private int bosses;

    /*
     * Constructor - automatically generates chamber array and number of bosses.
     */
    public Level(){
        this.generate();
    }

    /*
     * Implementation Methods
     */
    @Override public void generate() {
        //create array of chambers
        int numOfChambers = (Globals.rand.nextInt(4) + 1) * 2;
        chambers = new Chamber[numOfChambers];
        for (int i = 0; i < chambers.length - 1; i++) {
            Chamber chamber = new Chamber();
            chamber.generate();
            chambers[i] = chamber;
        }
    }

    public Chamber[] getChambers() {
        return chambers;
    }
    public int getBosses() {
        return bosses;
    }

    /*
     * To String
     */
    @Override public String toString() {
        return "Level{" +
                "chambers=" + Arrays.toString(chambers) +
                ", bosses=" + bosses +
                '}';
    }
}
