package model.level;

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
    public GameObject(String imagePath) {
        super(imagePath);
        previousPoints.add(this.getLocation());
    }

    @Override
    public GameObject Collision(GameObject obj) {
        Rectangle bounds = obj.getBounds();
        if(this.getBounds().intersects(bounds)) {
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
}
