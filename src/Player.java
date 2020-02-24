import java.util.Objects;

public class Player extends Entity{
    /*
     * Constructors
     */
    public Player(){}
    public Player(int health,int speed, Gun weapon){
        super(health, speed, weapon);
    }

    /*
     * To String
     */
    @Override public String toString() {
        return super.toString();
    }
}
