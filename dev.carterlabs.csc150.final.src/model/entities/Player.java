package model.entities;

import controller.GameController;
import model.Globals;
import model.events.Attack;
import model.events.Collided;
import model.events.Moved;
import model.level.GameObject;
import model.objects.Gun;
import model.objects.Weapon;
import model.objects.WeaponType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity implements Attack, Moved, MouseMotionListener, Collided, KeyListener {
    /**
     * Array of players weapons
     */
    private List<Gun> guns;
    private Gun ActiveGun;
    private int currency;
    private int currentLevel;
    private int rotation = 0;

    /**
     * Player constructor
     * @param health Player health
     * @param speed Player speed
     */
    public Player(int health, int speed){
        super(health, speed, "./Resources/Player/PLAYER_AR.png");
        guns = initGuns();
        setCurrency(0);
        setImage(loadImage(computePlayerImagePath(getActiveGun())));
        setName("Player");
        initEvents();
    }

    /**
     * A full player object construction
     * @param health Player health
     * @param speed  Player speed
     * @param currency Amount of currency the player has
     * @param level The current dungeon level the player is on
     */
    public Player(int health, int speed, int currency, int level){
        super(health, speed, "./Resources/Player/PLAYER_AR.png");
        guns = initGuns();
        setCurrency(currency);
        setCurrentLevel(level);
        setImage(loadImage(computePlayerImagePath(getActiveGun())));
        initEvents();
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        GameController.attackEvents.add(this);
        GameController.updateEvents.add(this);
        GameController.renderEvents.add(this);
        GameController.startEvents.add(this);
        GameController.moveEvents.add(this);
    }

    private List<Gun> initGuns() {
        List<Gun> guns = new ArrayList<>();
        for (WeaponType weaponType: WeaponType.values()) {
            if(weaponType != WeaponType.BATON){
                guns.add(new Gun(25, weaponType));
            }
        }
        setActiveGun(guns.get(0));
        return guns;
    }

    private String computePlayerImagePath(Weapon weapon) {
        return "./Resources/Player/PLAYER_" + weapon.getWeaponType().toString() + ".png";
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(getRotation()), getWidth() / 2, getHeight() / 2);
        super.paintComponent(g);
    }

    /**
     * attacks
     */
    @Override public void attack() {
        //do something
    }

    @Override
    public void Start() {
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public List<Gun> getGuns() {
        return guns;
    }

    public void setGuns(List<Gun> guns) {
        this.guns = guns;
    }

    public Gun getActiveGun() {
        return ActiveGun;
    }

    public void setActiveGun(Gun activeGun) {
        this.setImage(loadImage(computePlayerImagePath(activeGun)));
        ActiveGun = activeGun;
        repaint();
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        if(rotation > 360 || rotation < 0) throw new IllegalArgumentException("Rotation must be between 360 and 0");
        this.rotation = rotation;
    }

    @Override
    public void Move() {
        super.Move();
        int s = this.getSpeed() / 2;
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
    }

    @Override
    public void Render(JFrame g) {
    }

    @Override
    public void Update() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'a') {
            this.setMovingWest(true);
        }

        if (key == 'd') {
            this.setMovingEast(true);
        }

        if (key == 'w') {
            this.setMovingNorth(true);
        }

        if (key == 's') {
            this.setMovingSouth(true);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'a') {
            this.setMovingWest(false);
        }

        if (key == 'd') {
            this.setMovingEast(false);
        }

        if (key == 'w') {
            this.setMovingNorth(false);
        }

        if (key == 's') {
            this.setMovingSouth(false);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.setRotation(calcRotation(e.getPoint()));
        this.repaint();
    }

    /**
     * @return Player description
     */
    @Override public String toString() {
        return super.toString();
    }

    @Override
    public GameObject Collision(GameObject obj) {
        if(this.getBounds().intersects(obj.getBounds())) {
            if(obj instanceof BatonGuard || obj instanceof ArmedOfficer || obj instanceof Boss){
                if(obj instanceof Enemy) {
                    Enemy e = (Enemy) obj;
                    if(!e.isDead()){
                        Globals.game.remove(this);
                        if(e.Collision(this) != null) {
                            Globals.game.reset();
                        }
                    }
                }
            }
            super.Collision(obj);
            return this;
        }
        return null;
    }
}
