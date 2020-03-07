package model.entities;

import model.Globals;
import model.events.Moved;
import model.level.GameObject;

import java.awt.*;

public abstract class Enemy extends Entity implements Moved {
    /**
     * Constructor of the entity
     *
     * @param health Heath of the entity
     * @param speed  Speed of the entity
     * @param image  The image of the entity
     */
    public Enemy(int health, int speed, String image) {
        super(health, speed, image);
    }

    @Override
    public void Move() {
        if(Globals.canMove) {
            super.Move();
            //move
            float xDirection = (float)Math.sin((float) Math.toRadians(getRotation()))
                    * this.getSpeed();
            float yDirection = (float)Math.cos((float) Math.toRadians(getRotation()))
                    * -this.getSpeed();
            float newX = getX() + xDirection;
            float newY = getY() + yDirection;
            setLocation((int) newX, (int) newY);
            Point point = new Point(Globals.player.getX(), Globals.player.getY());
            setRotation(calcRotation(point));
        }
    }

    @Override
    public GameObject Collision(GameObject obj) {
        return super.Collision(obj);
    }
}
