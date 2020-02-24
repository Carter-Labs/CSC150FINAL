import java.util.Objects;

public class ArmedOfficer extends Entity {
    /*
     * Constructors
     */
    public ArmedOfficer() { }
    public ArmedOfficer(int health,int speed, Gun weapon) {
        super(health, speed, weapon);
        this.setWeapon(weapon);
    }

    /*
     * To String
     */
    @Override public String toString() {
        return super.toString();
    }
}
