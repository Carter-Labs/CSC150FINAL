package model.level;

import controller.GameController;
import model.Globals;
import model.entities.ArmedOfficer;
import model.entities.BatonGuard;
import model.entities.Boss;
import model.entities.Entity;
import model.events.Moved;
import model.objects.Gun;
import model.objects.Weapon;
import model.objects.WeaponType;
import view.ImageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class Chamber implements Generate, Moved, KeyListener, MouseMotionListener, MouseListener {
    /**
     * Variables
     */
    private GameObject[] objects;
    public List<Entity> entities = new ArrayList<>();
    private ChamberDoorOptions[] doors;
    private JFrame jFrame;
    private HashMap componentMap;
    private List<GameObject> bullets = new ArrayList<>();
    private GameObject reload = new GameObject("./Resources/Particles/reload.png");
    JLabel pointLabel = new JLabel("Points: ",SwingConstants.CENTER);
    JLabel waveLabel = new JLabel("Wave: ",SwingConstants.CENTER);
    private int shots = 0;
    private int wave = 1;

    /**
     * Default Constructor
     */
    public Chamber(JFrame g){
        this.jFrame = g;
        Globals.player.setLocation(Globals.WIDTH / 2 - 30, Globals.HEIGHT / 2 - 10);
    }

    /**
     * Generates chamber and it's entities
     */
    @Override public void generate() {
        //add boss if in range to list of entities
        isBossInChamber();
        this.jFrame.add(pointLabel);
        this.jFrame.add(waveLabel);
        doors = new ChamberDoorOptions[4];
        //add num of enemies to list of entities
        jFrame.addKeyListener(this);
        jFrame.addKeyListener(Globals.player);
        jFrame.addMouseMotionListener(this);
        jFrame.addMouseListener(this);
        addOfficersAndGuards();
        generateFloor(this.jFrame);
        GameController.objects.addAll(entities);
        GameController.moveEvents.add(this);
        //Sets labels on screen
        updatePointsLbl(0);
        updateWaveLbl(Globals.WAVE);
        Timer timer = new Timer("Wave Timer");
        TimerTask waveTimerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    WaveTimerTask();
                } catch (ConcurrentModificationException cme) {}
            }
        };
        timer.scheduleAtFixedRate(waveTimerTask, 5000, 5000);
    }

    private void WaveTimerTask() {
        System.out.println("Timer");
        addOfficersAndGuards();
        spawnEnemies(jFrame);
        Globals.WAVE ++;
        updateWaveLbl(Globals.WAVE);
    }

    private void updatePointsLbl(int points) {
        pointLabel.setPreferredSize(new Dimension(250, 64));
        pointLabel.setLocation(100,0);
        pointLabel.setBackground(Color.BLACK);
        pointLabel.setForeground(Color.WHITE);
        pointLabel.setFont(new Font("Futra", Font.BOLD, 48));
        pointLabel.setSize(pointLabel.getPreferredSize());
        pointLabel.setOpaque(true);
        pointLabel.setText("Points: "+ points);
        this.jFrame.getContentPane().add(pointLabel, BorderLayout.CENTER);
        this.jFrame.getContentPane().setComponentZOrder(pointLabel, 3);
        pointLabel.repaint();
    }
    private void updateWaveLbl(int wave) {
        waveLabel.setPreferredSize(new Dimension(250, 64));
        waveLabel.setLocation(Globals.WIDTH - 350,0);
        waveLabel.setBackground(Color.BLACK);
        waveLabel.setForeground(Color.WHITE);
        waveLabel.setFont(new Font("Futra", Font.BOLD, 48));
        waveLabel.setSize(waveLabel.getPreferredSize());
        waveLabel.setOpaque(true);
        waveLabel.setText("Wave: "+ wave);
        this.jFrame.getContentPane().add(waveLabel, BorderLayout.CENTER);
        this.jFrame.getContentPane().setComponentZOrder(waveLabel, 3);
        waveLabel.repaint();
    }

    /**
     * Generates the side location of the doors
     */
    private void generateDoors(JFrame j) {
        int doors = Globals.rand.nextInt(4) + 1;
        GameObject door;
        for (int i = 0; i < doors; i++) {
            int dir = Globals.rand.nextInt(4);
            switch (dir) {
                case 0:
                    if(!addDoor(ChamberDoorOptions.NORTH, i)) { --i;}
                        door = new GameObject("./Resources/LevelAssets/DOORN.png");
                        door.setName("Door");
                        j.add(door);
                        door.setLocation(Globals.rand.nextInt(Globals.WIDTH - 64) + 64, 3);

                    break;
                case 1:
                    if(!addDoor(ChamberDoorOptions.EAST, i)) { --i;}
                        door = new GameObject("./Resources/LevelAssets/DOORE.png");
                        door.setName("Door");
                        j.add(door);
                        door.setLocation(Globals.WIDTH - 74, Globals.rand.nextInt(Globals.WIDTH - 64) + 64);

                    break;
                case 2:
                    if(!addDoor(ChamberDoorOptions.SOUTH, i)) { --i;}
                        door = new GameObject("./Resources/LevelAssets/DOORS.png");
                        door.setName("Door");
                        j.add(door);
                        door.setLocation(Globals.rand.nextInt(Globals.WIDTH - 64) + 64, Globals.HEIGHT - 95);

                    break;
                case 3:
                    if(!addDoor(ChamberDoorOptions.WEST, i)) { --i;}
                        door = new GameObject("./Resources/LevelAssets/DOORW.png");
                        door.setName("Door");
                        j.add(door);
                        door.setLocation(5, Globals.rand.nextInt(Globals.WIDTH - 64) + 64);

                    break;
            }
        }
    }

    /**
     * @param option What option you want to add
     * @param i The index to set it to
     * @return If it was able to add to the array
     */
    private boolean addDoor(ChamberDoorOptions option, int i) {
        if(!contained(option, doors)){
            doors[i] = option;
            return  true;
        } else {
            return  false;
        }
    }

    /**
     * @param option What option the door is at
     * @param array The array of door options
     * @return If the option was contained in the array
     */
    private boolean contained(ChamberDoorOptions option, ChamberDoorOptions[] array) {
        for (ChamberDoorOptions opt: array) {
            if(opt == null){
                return false;
            }
            return opt.equals(option);
        }
        return false;
    }

    /**
     * Draws the chamber in the view
     */
    @Override public void Move() {
        try {
            for(GameObject bullet : this.bullets){
                for (int i = 0; i < 10 ; i++) {
                    double angle = Math.toRadians(bullet.getRotation() + 90);
                    double x = bullet.getX(), y = bullet.getY();
                    x = x + Math.cos(angle) * -1 * 5;
                    y = y + Math.sin(angle) * -1 * 5;
                    bullet.setLocation((int)x,(int)y);
                    bullet.repaint();
                }
            }
        } catch (ConcurrentModificationException cme) {}
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if(key == ' ') {
            Globals.canMove = true;
        }
        if(key == 'r'){
            if(reload.isVisible()) {
                reload.setVisible(false);
                reload.repaint();
            }
            Globals.player.getActiveGun().setMagSize(shots);
            shots = 0;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Shoot bullet
        Point clickPoint = new Point(e.getX(), e.getY());
        float xDirection = (float)Math.sin((float) Math.toRadians(Globals.player.getRotation()))
                * (Globals.player.getSpeed() * 2);
        float yDirection = (float)Math.cos((float) Math.toRadians(Globals.player.getRotation()))
                * -(Globals.player.getSpeed() * 2);
        float newX = clickPoint.x + xDirection;
        float newY = clickPoint.y + yDirection;
        shots++;
        for (int i = 0; i < Globals.player.getActiveGun().getProjectTileCount(); i++) {
            if(Globals.player.getActiveGun().getMagSize() >= 1) {
                GameObject bullet = new GameObject("./Resources/Particles/BULLET.png");
                this.jFrame.add(bullet);
                Globals.player.getActiveGun().setMagSize(Globals.player.getActiveGun().getMagSize() - 1);
                this.jFrame.getContentPane().setComponentZOrder(bullet, 3);
                bullet.setRotation(Globals.player.getRotation());
                bullet.setLocation(Globals.player.getX() + 32 + (i * 5), Globals.player.getY() + 32 + (i * 5));
                bullet.setName("Bullet");
                for (Entity en: entities) {
                    bullet.addToCollisions(en);
                    en.addToCollisions(bullet);
                }
                bullets.add(bullet);
            }
            else {
                reload.setLocation(Globals.WIDTH / 2 - (384 / 2) - 25, Globals.HEIGHT - 130);
                reload.setVisible(true);
                reload.setName("Reload");
                this.jFrame.add(reload);
                this.jFrame.getContentPane().setComponentZOrder(reload, 3);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Determines the chances a boss spawning in the chamber
     */
    private void isBossInChamber() {
        int num = Globals.rand.nextInt(100) + 1;
        if (num < (Globals.bossSpawnPerc + (Globals.player.getCurrentLevel() / 100)) * 100) {
            Globals.isBossInChamber = true;
            List<Entity> newArr = new ArrayList<>();
            newArr.add(new Boss(300,2, new Weapon(20, WeaponType.SHOTGUN)));
            newArr.addAll(this.getEntities());
            this.setEntities(newArr);
        }
        else {
            Globals.isBossInChamber = false;
        }
    }

    /**
     * Adds random amount of armed officers and baton guards
     */
    public void addOfficersAndGuards() {
        int numOfGuards = Globals.rand.nextInt((Globals.maxNumOfOfficersAndGuards + Globals.player.getCurrentLevel()) - 1) + 1;
        int numOfOfficers = Globals.rand.nextInt(Globals.maxNumOfOfficersAndGuards + Globals.player.getCurrentLevel()) + 1 - numOfGuards;
        List<Entity> newArr = new ArrayList<>();
        for (int i = 0; i < numOfGuards; i++) {
            newArr.add(new BatonGuard(110,2,new Weapon(100, WeaponType.BATON)));
        }
        for (int i = 0; i < numOfOfficers; i++) {
            WeaponType randomWeapon = WeaponType.values()[Globals.rand.nextInt(WeaponType.values().length - 1)];
            if (randomWeapon == WeaponType.BATON) {randomWeapon = WeaponType.AR;}
            newArr.add(new ArmedOfficer(100,2,new Gun(20, randomWeapon)));
        }
        newArr.addAll(this.getEntities());
        this.setEntities(newArr);
    }

    /**
     * Generates the floor to draw it.
     */
    private void generateFloor(JFrame g) {
        g.add(Globals.player);
        g.getContentPane().setComponentZOrder(Globals.player, 3);
        spawnEnemies(g);
        generateDoors(g);
        String[] walls = new String[]{"./Resources/LevelAssets/Wall_01.png","./Resources/LevelAssets/Wall_02.png","./Resources/LevelAssets/Wall_03.png"};
        GameObject wall = new GameObject(walls[Globals.rand.nextInt(3)]);
        for (int i = 0; i <=Globals.HEIGHT / wall.getHeight() ; i++) {
            for (int j = 0; j <=Globals.WIDTH / wall.getWidth() ; j++) {
                if(i == 0 || i == Globals.HEIGHT / wall.getHeight() - 1) {
                    wall = new GameObject(walls[Globals.rand.nextInt(3)]);
                    wall.setLocation((j * wall.getWidth()),(i * wall.getHeight()));
                    g.add(wall);
                    wall.setName("Wall");
                }
                else  {
                    wall = new GameObject(walls[Globals.rand.nextInt(3)]);
                    wall.setLocation(0,wall.getHeight() * i); //
                    wall.setName("Wall");
                    g.add(wall);
                    wall = new GameObject(walls[Globals.rand.nextInt(3)]);
                    wall.setLocation( Globals.WIDTH - wall.getWidth() - 7,wall.getHeight() * i); //
                    g.add(wall);
                    wall.setName("Wall");
                }
            }
        }
        ImageView image = new ImageView("./Resources/LevelAssets/floor_01.png");
        for (int i = 0; i <=Globals.HEIGHT / image.getHeight(); i++) {
            for (int j = 0; j <=Globals.WIDTH / image.getWidth() ; j++) {
                image = new ImageView("./Resources/LevelAssets/floor_01.png");
                image.setLocation((j * image.getWidth()),(i * image.getHeight()));
                g.add(image);
            }
        }
        g.repaint();
    }

    public void spawnEnemies(JFrame j) {
        int randX, randY;
        for (Entity en : this.getEntities()){
            randX = Globals.rand.nextInt(jFrame.getContentPane().getWidth() - 128) + 128;
            randY = Globals.rand.nextInt(jFrame.getContentPane().getHeight() - 128) + 128;
            en.setLocation(randX, randY);
            j.add(en);
            j.getContentPane().setComponentZOrder(en, 3);
        }
    }

    public void clearChamber() {
        for (GameObject object: GameController.objects) {
            for (GameObject objectc: GameController.objects) {
                if(objectc != object) {
                    object.removeToCollision(objectc);
                }
            }
        }
        entities.clear();
        bullets.clear();
        Globals.canMove = false;
        shots = 0;
    }


    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    /**
     * @return Array of GameObjects
     */
    public GameObject[] getObjects() {
        return objects;
    }

    /**
     * Sets the array of GameObjects
     * @param objects
     */
    public void setObjects(GameObject[] objects) {
        this.objects = objects;
    }

    /**
     * @return List of entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Sets the list of entities
     * @param entities
     */
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    /**
     * @return Chamber description
     */
    @Override public String toString() {
        return "Chamber{" +
                "objects=" + Arrays.toString(objects) +
                ", entities=" + entities +
                '}';
    }

    public JFrame getjFrame() {
        return this.jFrame;
    }
}
