package model.entities;

import controller.GameController;
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
    private boolean canMove = true;
    public Enemy(int health, int speed, String image) {
        super(health, speed, image);
    }

    @Override
    public void Move() {
        if(Globals.canMove && canMove) {
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
        if(this.getBounds().intersects(obj.getBounds())) {
            if(obj.getName().equals("Bullet")){
                Globals.print("Collided with Bullet");
                canMove = false;
                System.out.println(this.collisionEvents.size());
                this.setBounds(0, 0,0,0);
                GameController.moveEvents.remove(this);
                GameController.updateEvents.remove(this);
                GameController.renderEvents.remove(this);
                Globals.game.remove(this);
                GameController.currentChamber.entities.remove(this);
                for (Entity en: GameController.currentChamber.entities) {
                    this.removeToCollision(en);
                    en.removeToCollision(this);
                }
                System.out.println(this.collisionEvents.size());
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            super.Collision(obj);
            return this;
        }
        return null;
    }
}
