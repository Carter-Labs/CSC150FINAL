package controller;

import model.Globals;
import model.objects.Gun;

import javax.swing.*;
import java.awt.*;

public class UIController {
    private JFrame frame;
    MenuController menuController;

    public UIController(JFrame frame) {
        this.frame = frame;
        initFrame();
        initMenu();
    }

    private void initFrame() {
        frame.setTitle("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, Globals.WIDTH, Globals.HEIGHT);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void initMenu() {
        menuController = new MenuController(frame, Globals.WIDTH / 2 - (384 / 2), Globals.HEIGHT - 100);
        for (Gun gun: Globals.player.getGuns()) {
            menuController.addItem(gun);
        }
        menuController.setActiveItem(Globals.player.getActiveGun());
    }

    public static void main(String[] args) {
        UIController uiController = new UIController(new JFrame("Hello"));
    }
}
