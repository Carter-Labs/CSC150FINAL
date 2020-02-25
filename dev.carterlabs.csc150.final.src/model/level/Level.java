package model.level;

import model.Globals;
import java.util.Arrays;

public class Level implements Generate {
    /*
     *Variables
     */
    private Chamber[] chambers;

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
            chambers[i] = new Chamber();
        }
    }

    /*
     * Getters and Setters
     */
    public Chamber[] getChambers() {
        return chambers;
    }

    /*
     * To String
     */
    @Override public String toString() {
        return "Level{" +
                "chambers=" + Arrays.toString(chambers) +
                '}';
    }
}
