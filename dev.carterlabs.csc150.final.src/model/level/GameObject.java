package model.level;

import controller.GameController;
import model.Globals;
import model.entities.ArmedOfficer;
import model.entities.BatonGuard;
import model.events.Collided;
import model.events.Moved;
import view.ImageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObject extends ImageView implements Collided, Moved {
    private static final int PREVIOUS_FRAMES = 4;
    private static final int FRAME_CHOICE = 1;
    private List<Point> previousPoints = new ArrayList<Point>();
    private int rotation = 0;
    protected List<Collided> collisionEvents = new ArrayList<>();

    public GameObject(String imagePath) {
        super(imagePath);
        previousPoints.add(this.getLocation());
        GameController.objects.add(this);
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(getRotation()), getWidth() / 2, getHeight() / 2);
        super.paintComponent(g);
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

    public void setRotation(int rotation) {
        if(rotation > 360 || rotation < 0) throw new IllegalArgumentException("Rotation must be between 360 and 0");
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    public void addToCollisions(Collided object) {
        collisionEvents.add(object);
    }

    @Override
    public GameObject Collision(GameObject obj) {
        Rectangle bounds = obj.getBounds();
        if(this.getBounds().intersects(bounds)) {
            if(this instanceof BatonGuard || this instanceof ArmedOfficer){
                Globals.print("" + obj.getName()+" collided with Enemy");
            }
            obj.setLocation(obj.previousPoints.get(obj.previousPoints.size() - FRAME_CHOICE));
            return this;
        }
        for (int i = 0; i < obj.previousPoints.size() - PREVIOUS_FRAMES; i++) {
            obj.previousPoints.remove(obj.previousPoints.get(i));
        }
        return null;
    }

    @Override
    public void Move() {
        previousPoints.add(this.getLocation());
    }

    public void removeToCollision(GameObject objectc) {
        this.collisionEvents.remove(objectc);
    }
}
