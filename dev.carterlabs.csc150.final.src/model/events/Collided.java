package model.events;

import model.level.GameObject;

public interface Collided {
    /**
     * Checks for collision
     * @param obj
     * @return the object that had a collision if there was none return null
     */
    GameObject Collision(GameObject obj);
}
