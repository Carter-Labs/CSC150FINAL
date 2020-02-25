package model.entities;

import model.objects.Weapon;

import java.util.Objects;

public class Entity {
    /**
     * Variables
     */
    private int health;
    private double speed;
    private Weapon weapon;

    /**
     * Constructor of the entity
     * @param health Heath of the entity
     * @param speed Speed of the entity
     * @param weapon Weapon of the entity
     */
    public Entity(int health,int speed, Weapon weapon) {
        this.setHealth(health);
        this.setSpeed(speed);
        this.setWeapon(weapon);
    }

    /**
     * @return entity health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the entity health
     * @param health Entity health
     */
    public void setHealth(int health) {
        if(health < 0 || health > 300) {
            throw new IllegalArgumentException("health must be between 0 and 100.");
        }
        this.health = health;
    }

    /**
     * @return Entity speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the entity speed
     * @param speed Entity speed
     */
    public void setSpeed(double speed) {
        if(speed < 0){
            throw new IllegalArgumentException("speed must be greater than 0.");
        }
        this.speed = speed;
    }

    /**
     * @return Entity weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Sets the entity weapon
     * @param weapon Entity weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Movement
     */
    public void movement() {
        //move
    }

    /**
     * Compares the entities
     * @param o Entity to compare
     * @return If the entities are equal
     */
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return health == entity.health &&
                Double.compare(entity.speed, speed) == 0;
    }

    /**
     * @return Hash of the entity
     */
    @Override public int hashCode() {
        return Objects.hash(health, speed);
    }

    /**
     * @return Entity description
     */
    @Override public String toString() {
        return "model.entities.Entity{" +
                "health=" + health +
                ", speed=" + speed +
                '}';
    }
}
