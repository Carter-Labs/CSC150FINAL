import java.util.Objects;

public class BatonGuard  extends Entity{
    /*
     * Constructors
     */
    public BatonGuard(){ }
    public BatonGuard(int health,int speed, Weapon baton) {
        super(health, speed, baton);
    }
    /*
     * To String
     */
    @Override public String toString() {
        return super.toString();
    }
}
