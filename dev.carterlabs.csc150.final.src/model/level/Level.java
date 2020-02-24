package model.level;

import java.util.Arrays;

public class Level implements Generate {
    /*
     *Variables
     */
    private Chamber[] chambers;
    private int bosses;

    /*
     * Constructor - automatically generates chamber array and number of bosses.
     */
    public Level(){}

    /*
     * Implementation Methods
     */
    @Override public void generate() {
        //do Something
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
