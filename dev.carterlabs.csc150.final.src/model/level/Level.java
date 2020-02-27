package model.level;

import model.Globals;
import model.entities.Player;

import java.util.Arrays;

public class Level implements Generate {
    /**
     * Variables
     */
    private Chamber[] chambers;
    private Player player;

    /**
     * Constructor - automatically generates chamber array and number of bosses.
     */
    public Level(){
        this.generate();
    }

    /**
     * Generates the level and adds the player with its saved values
     */
    @Override public void generate() {
        //create array of chambers
        int numOfChambers = (Globals.rand.nextInt(4) + 1) * 2;
        chambers = new Chamber[numOfChambers];
        for (int i = 0; i < chambers.length - 1; i++) {
//            chambers[i] = new Chamber();
        }
        //add the player to this level
        player = Globals.player;
    }

    /**
     * @return Chambers in the level
     */
    public Chamber[] getChambers() {
        return chambers;
    }

    /**
     * @return Level description
     */
    @Override public String toString() {
        return "Level{" +
                "chambers=" + Arrays.toString(chambers) +
                '}';
    }
}
