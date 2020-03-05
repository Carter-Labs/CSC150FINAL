package model.level;

import model.Globals;
import model.events.Collided;

import java.awt.*;

public class Door extends GameObject implements Collided {

    private ChamberDoorOptions orientation;
    private int rotation = 0;

    public Door(ChamberDoorOptions option) {
        super("./Resources/LevelAssets/DOOR.png");
        this.orientation = option;
        Globals.player.addToCollisions(this);
    }

    private void determineRotation(ChamberDoorOptions option){
        switch (option){
            case EAST: setRotation(270);
            case WEST: setRotation(90);
            case NORTH: setRotation(0);
            case SOUTH: setRotation(180);
        }
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(getRotation()), getWidth() / 2, getHeight() / 2);
        super.paintComponent(g);
    }

    public void setRotation(int rotation) {
        if(rotation > 360 || rotation < 0) throw new IllegalArgumentException("Rotation must be between 360 and 0");
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    @Override
    public GameObject Collision(GameObject obj) {
        return this;
    }
}
