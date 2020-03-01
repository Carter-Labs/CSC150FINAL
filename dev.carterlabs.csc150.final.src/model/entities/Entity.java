package model.entities;

import model.events.Rendered;
import model.events.Started;
import model.events.Updated;
import model.level.GameObject;

import java.util.Objects;

public abstract class Entity extends GameObject implements Updated, Started, Rendered {
    /**
     * Variables
     */
    private int health;
    private double speed;

    /**
     * Constructor of the entity
     * @param health Heath of the entity
     * @param speed Speed of the entity
     * @param image The image of the entity
     */
    public Entity(int health, int speed, String image) {
        super(image);
        this.setHealth(health);
        this.setSpeed(speed);
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
            throw new IllegalArgumentException("health must be between 0 and 300.");
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
