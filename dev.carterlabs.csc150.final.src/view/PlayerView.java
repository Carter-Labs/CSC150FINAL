package view;

import model.events.Updated;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends ImageView implements Updated {
    
    public PlayerView() {
        super.setLayout(null);
        setBounds(getX(), getY(), image.getHeight(), image.getHeight());
    }

    @Override
    public void Update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintChildren(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Player View Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        frame.getContentPane().setBackground(Color.RED);
        frame.setLayout(null);
        PlayerView view = new PlayerView();
        frame.add(view);
        frame.setVisible(true);
    }
}
