package model.level;

import model.Globals;
import model.events.Collided;

public class Door extends GameObject implements Collided {

    private ChamberDoorOptions orientation;

    public Door(ChamberDoorOptions option) {
        super("./Resources/LevelAssets/DOOR.png");
        this.orientation = option;
        
    }



    @Override
    public GameObject Collision(GameObject obj) {
        return this;
    }
}
