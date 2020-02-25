package controller;

import javafx.util.Pair;
import model.objects.Weapon;
import model.objects.WeaponType;
import view.MenuItemView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MenuController implements ComponentListener {
    JFrame frame;
    List<Pair<Weapon, MenuItemView>> weapons;
    private int x = 0, y = 0;

    public MenuController(JFrame frame, int x, int y) {
        setFrame(frame);
        weapons = new ArrayList<>();
        getFrame().addComponentListener(this);
        setX(x);
        setY(y);
    }

    public void AddItem(Weapon weapon) {
        weapons.add(new Pair<>(weapon, new MenuItemView("./Resources/TestPistolIcon.png")));
        int x = 0 + getX();
        for (Pair<Weapon, MenuItemView> item: weapons) {
            MenuItemView view = item.getValue();
            view.setLocation(x, getY());
            frame.add(view);
            x += view.getWidth();
        }
    }

    private void drawMenu() {
        for (Pair<Weapon, MenuItemView> pair: weapons) {
            pair.getValue().repaint();
        }
    }

    private void SetActiveItem(Weapon weapon) {
        for (Pair<Weapon, MenuItemView> item: weapons) {
            MenuItemView view = item.getValue();
            if(item.getKey() == weapon) {
                view.setIsActive(true);
            } else {
                view.setIsActive(false);
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        drawMenu();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        drawMenu();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        drawMenu();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        drawMenu();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuItemView Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setLayout(null);
        MenuController controller = new MenuController(frame, 0, 0);
        Weapon weapon = new Weapon(5, WeaponType.AR);
        controller.AddItem(weapon);
        controller.AddItem(new Weapon(5, WeaponType.SHOTGUN));
        controller.AddItem(new Weapon(5, WeaponType.SMG));
        controller.AddItem(new Weapon(5, WeaponType.SNIPER));
        controller.AddItem(new Weapon(5, WeaponType.RAY_GUN));
        controller.SetActiveItem(weapon);
        frame.setVisible(true);
    }
}
