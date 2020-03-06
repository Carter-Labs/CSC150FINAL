package controller;

import javafx.util.Pair;
import model.Globals;
import model.events.Rendered;
import model.level.GameObject;
import model.objects.Weapon;
import model.objects.WeaponType;
import view.MenuItemView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MenuController implements ComponentListener, Rendered, KeyListener, MouseWheelListener {
    private JFrame frame; //The frame that will have MenuItemView's added to
    private List<Pair<Weapon, MenuItemView>> weapons;// Every Weapon shall have a view with it.
    private int x = 0, y = 0, index = 0; // The x, y for where the menus will started being placed at

    /**
     * Creates a Menu Component
     * @param frame the frame the component will be added to
     * @param x where it will be placed on the horizontal axis
     * @param y where it will be placed on the vertical axis
     */
    public MenuController(JFrame frame, int x, int y) {
        setFrame(frame);
        weapons = new ArrayList<>();
        setX(x);
        setY(y);
        GameController.renderEvents.add(this);
        getFrame().addKeyListener(this);
        getFrame().addComponentListener(this);
        getFrame().addMouseWheelListener(this);
    }

    /**
     * Adds a weapon to the Menu and displays it to the component
     * @param weapon the weapon to be added
     */
    public void addItem(Weapon weapon) {
        weapons.add(new Pair<>(weapon, new MenuItemView("./Resources/Guns/" + weapon.getWeaponType() + ".png")));
        int x = 0 + getX();
        for (Pair<Weapon, MenuItemView> item: weapons) {
            MenuItemView view = item.getValue();
            view.setLocation(x, getY());
            frame.add(view);
            x += view.getWidth();
        }
    }

    /**
     * This will invoke the repaint of the component when a component is resized
     */
    private void drawMenu() {
        for (Pair<Weapon, MenuItemView> pair: weapons) {
            pair.getValue().repaint();
        }
    }

    /**
     * Set's what weapon in the menu is active
     * @param weapon the weapon that is active
     */
    public void setActiveItem(Weapon weapon) {
        for (Pair<Weapon, MenuItemView> item: weapons) {
            MenuItemView view = item.getValue();
            if(item.getKey() == weapon) {
                view.setIsActive(true);
            } else {
                view.setIsActive(false);
            }
        }
    }

    /**
     * Sets the active item based off an index
     * @param index what index should be active
     */
    public void setActiveItem(int index) {
        for (Pair<Weapon, MenuItemView> item: weapons) {
            MenuItemView view = item.getValue();
            view.setIsActive(false);
        }
        weapons.get(index).getValue().setIsActive(true);
    }

    public JFrame getFrame() { return frame; }

    public void setFrame(JFrame frame) { this.frame = frame; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    private void nextWeapon() {
        index ++;
        if(index > Globals.player.getGuns().size() - 1) index = 0;
        Globals.player.setActiveGun(Globals.player.getGuns().get(index));
    }

    private void prevWeapon() {
        index --;
        if(index < 0) index = Globals.player.getGuns().size() - 1;
        Globals.player.setActiveGun(Globals.player.getGuns().get(index));
    }

    @Override
    public void Render(JFrame g) {
    }

    @Override
    public void componentResized(ComponentEvent e) { drawMenu(); }

    @Override
    public void componentMoved(ComponentEvent e) { drawMenu(); }

    @Override
    public void componentShown(ComponentEvent e) { drawMenu(); }

    @Override
    public void componentHidden(ComponentEvent e) { drawMenu(); }

    @Override
    public void keyTyped(KeyEvent e) {
        //To cycle through gun selection
        char key = e.getKeyChar();
        if(key == 'q') {prevWeapon(); checkVisibility();}
        if(key == 'e') {nextWeapon(); checkVisibility();}
        if(key == '1') {Globals.player.setActiveGun(Globals.player.getGuns().get(0)); checkVisibility();}
        if(key == '2') {Globals.player.setActiveGun(Globals.player.getGuns().get(1)); checkVisibility();}
        if(key == '3') {Globals.player.setActiveGun(Globals.player.getGuns().get(2)); checkVisibility();}
        if(key == '4') {Globals.player.setActiveGun(Globals.player.getGuns().get(3)); checkVisibility();}
        if(key == '5') {Globals.player.setActiveGun(Globals.player.getGuns().get(4)); checkVisibility();}
        if(key == '6') {Globals.player.setActiveGun(Globals.player.getGuns().get(5)); checkVisibility();}
        setActiveItem(Globals.player.getActiveGun());
    }

    private void checkVisibility() {
        for(Component obj : this.getFrame().getContentPane().getComponents()){
            if(obj.getName() != null && obj.getName().equals("Reload")){
                obj.setVisible(false);
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int units = e.getUnitsToScroll();
        if (units > 0) {prevWeapon(); checkVisibility();}
        if (units < 0) {nextWeapon(); checkVisibility();}
        setActiveItem(Globals.player.getActiveGun());
    }

    /**
     * Test method for MenuController
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuItemView Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setLayout(null);
        MenuController controller = new MenuController(frame, 0, 0);
        Weapon weapon = new Weapon(5, WeaponType.AR);
        controller.addItem(weapon);
        frame.addKeyListener(controller);
        controller.addItem(new Weapon(5, WeaponType.SHOTGUN));
        controller.addItem(new Weapon(5, WeaponType.SMG));
        controller.addItem(new Weapon(5, WeaponType.SNIPER));
        controller.addItem(new Weapon(5, WeaponType.RAY_GUN));
        controller.setActiveItem(weapon);
        frame.setVisible(true);
    }
}
