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
    private boolean isDead = false;
    public Enemy(int health, int speed, String image) {
        super(health, speed, image);
        int i = 0;
        while(i <= 5) {
            for (GameObject object: GameController.objects) {
                for (GameObject objectc: GameController.objects) {
                    if(objectc != object) {
                        object.addToCollisions(objectc);
                    }
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    @Override
    public void Move() {
        this.setVisible(true);
        this.repaint();
        if(Globals.canMove && canMove && !isDead) {
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
        if(isDead) {
            this.setBounds(0, 0, 0, 0); return null;
        }
        if(this.getBounds().intersects(obj.getBounds())) {
            if(obj.getName().equals("Bullet")){
                isDead = true;
                Globals.SCORE ++;
                Globals.game.updatePointsLbl(Globals.SCORE);
                canMove = false;
                this.setSize(0, 0);
                GameController.moveEvents.remove(this);
                GameController.updateEvents.remove(this);
                GameController.renderEvents.remove(this);
                GameController.currentChamber.entities.remove(this);
                for (Entity en: GameController.currentChamber.entities) {
                    this.removeToCollision(en);
                    en.removeToCollision(this);
                }
                Globals.player.removeToCollision(this);
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

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
