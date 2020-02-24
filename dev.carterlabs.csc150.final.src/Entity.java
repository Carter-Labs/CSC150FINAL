import java.util.Objects;

public class Entity {
    /*
     * Variables
     */
    private int health;
    private double speed;
    private Weapon weapon;

    /*
     * Constructors
     */
    public Entity(){}
    public Entity(int health,int speed, Weapon weapon) {
        this.setHealth(health);
        this.setSpeed(speed);
        this.setWeapon(weapon);
    }

    /*
     * Getters and Setters
     */
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if(health < 0 || health > 100) {
            throw new IllegalArgumentException("health must be between 0 and 100.");
        }
        this.health = health;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        if(speed < 0){
            throw new IllegalArgumentException("spped must be greater than 0.");
        }
        this.speed = speed;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /*
     * Equals and Hash
     */
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return health == entity.health &&
                Double.compare(entity.speed, speed) == 0;
    }
    @Override public int hashCode() {
        return Objects.hash(health, speed);
    }

    /*
     * To String
     */
    @Override public String toString() {
        return "Entity{" +
                "health=" + health +
                ", speed=" + speed +
                '}';
    }
}
