package model.entities;

import controller.GameController;
import model.Globals;
import model.events.Moved;
import model.events.Rendered;
import model.events.Started;
import model.events.Updated;
import model.level.GameObject;

import java.awt.*;
import java.util.Objects;

public abstract class Entity extends GameObject implements Updated, Started, Rendered, Moved {
    /**
     * Variables
     */
    private int health;
    private int speed;
    private String im;
    private int rotation = 0;
    /**
     * Directions of movement
     */
    private boolean isMovingNorth = false;
    private boolean isMovingEast = false;
    private boolean isMovingSouth = false;
    private boolean isMovingWest = false;

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
        this.im = image;
        setImage(loadImage(image));
    }

    protected void initEvents() {
        GameController.updateEvents.add(this);
        GameController.renderEvents.add(this);
        GameController.startEvents.add(this);
        GameController.moveEvents.add(this);
        GameController.collisionEvents.add(this);
    }

    protected int calcRotation(Point point) {
        if(point != null) {
            double dx = point.getX() - Globals.player.getX() + (Globals.player.getWidth() / 2);
            double dy = point.getY() - Globals.player.getY() + (Globals.player.getHeight() / 2);
            return (int) toPositiveAngle(Math.toDegrees(Math.atan2(dy, dx)) + 90);
        } else {
            return 0;
        }
    }

    private double toPositiveAngle(double angle)
    {
        angle = angle % 360;
        while(angle < 0) {
            angle += 360.0;
        }
        return angle;
    }

    public String getIm() {
        return im;
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
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the entity speed
     * @param speed Entity speed
     */
    public void setSpeed(int speed) {
        if(speed < 0){
            throw new IllegalArgumentException("speed must be greater than 0.");
        }
        this.speed = speed;
    }

    public boolean isMovingNorth() {
        return isMovingNorth;
    }

    public void setMovingNorth(boolean movingNorth) {
        isMovingNorth = movingNorth;
    }

    public boolean isMovingEast() {
        return isMovingEast;
    }

    public void setMovingEast(boolean movingEast) {
        isMovingEast = movingEast;
    }

    public boolean isMovingSouth() {
        return isMovingSouth;
    }

    public void setMovingSouth(boolean movingSouth) {
        isMovingSouth = movingSouth;
    }

    public boolean isMovingWest() {
        return isMovingWest;
    }

    public void setMovingWest(boolean movingWest) {
        isMovingWest = movingWest;
    }

    public Direction getDirection() {
        if (this.isMovingNorth() && this.isMovingEast()) return Direction.NORTH_EAST;
        if (this.isMovingSouth() && this.isMovingEast()) return Direction.SOUTH_EAST;
        if (this.isMovingSouth() && this.isMovingWest()) return Direction.SOUTH_WEST;
        if (this.isMovingNorth() && this.isMovingWest()) return Direction.NORTH_WEST;
        if (this.isMovingNorth()) return Direction.NORTH;
        if (this.isMovingEast()) return Direction.EAST;
        if (this.isMovingSouth()) return Direction.SOUTH;
        if (this.isMovingWest()) return Direction.WEST;
        else return Direction.NONE;
    }
    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(getRotation()), getWidth() / 2, getHeight() / 2);
        super.paintComponent(g);
    }

    public void rotateEnemy() {
        Point point = new Point(Globals.player.getX(), Globals.player.getY());
        double angle;
        double dx = point.getX() - this.getX() + (this.getWidth() / 2);
        double dy = point.getY() - this.getY() + (this.getHeight() / 2);
        angle = Math.toDegrees(Math.atan2(dy, dx)) + 90;
        angle = angle % 360;
        while (angle < 0) {
            angle += 360.0;
        }
        setRotation((int)angle);
        moveEnemy(point);
        this.repaint();
    }

    public void moveEnemy(Point point) {
        //move
        float xDirection = (float)Math.sin((float) Math.toRadians(getRotation()))
                * this.getSpeed();
        float yDirection = (float)Math.cos((float) Math.toRadians(getRotation()))
                * -this.getSpeed();
        float newX = getX() + xDirection;
        float newY = getY() + yDirection;
        setLocation((int) newX, (int) newY);
    }

    public void setRotation(int rotation) {
        if(rotation > 360 || rotation < 0) throw new IllegalArgumentException("Rotation must be between 360 and 0");
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    /**
     * Movement
     */
    @Override
    public void Move() {
        super.Move();
        int s = this.getSpeed();
        int x, y;
        switch (getDirection()) {
            case NORTH:
                if(this.getY() <= Globals.MAX_Y){y = this.getY();}else{y = this.getY() - s;}
                this.setLocation(this.getX(), y);
                break;
            case EAST:
                if(this.getX() >= Globals.MAX_X){x = this.getX();}else {x = this.getX() + s;}
                this.setLocation(x, this.getY());
                break;
            case SOUTH:
                if(this.getY() >= Globals.MIN_Y){y = this.getY();}else {y = this.getY() + s;}
                this.setLocation(this.getX(), y);
                break;
            case WEST:
                if(this.getX() <= Globals.MIN_X){x = this.getX();}else {x = this.getX() - s;}
                this.setLocation(x, this.getY());
                break;
            case NORTH_EAST:
                if(this.getY() <= Globals.MAX_Y || this.getX() >= Globals.MAX_X){x = this.getX(); y = this.getY();}else {x = this.getX() + s; y = this.getY() - s;}
                this.setLocation(x, y);
                break;
            case NORTH_WEST:
                if(this.getY() <= Globals.MAX_Y || this.getX() <= Globals.MIN_X){x = this.getX(); y = this.getY();}else {x = this.getX() - s; y = this.getY() - s;}
                this.setLocation(x, y);
                break;
            case SOUTH_EAST:
                if(this.getY() >= Globals.MIN_Y || this.getX() >= Globals.MAX_X){x = this.getX(); y = this.getY();}else {x = this.getX() + s; y = this.getY() + s;}
                this.setLocation(x, y);
                break;
            case SOUTH_WEST:
                if(this.getY() >= Globals.MIN_Y || this.getX() <= Globals.MIN_X){x = this.getX(); y = this.getY();}else {x = this.getX() - s; y = this.getY() + s;}
                this.setLocation(x, y);
                break;
        }
        Point mouse = GameController.getFrames()[0].getMousePosition();
        this.setRotation(calcRotation(mouse));
        this.repaint();
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
