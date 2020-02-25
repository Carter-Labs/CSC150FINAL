package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuItemView extends JComponent implements MouseListener {
    BufferedImage DefaultImage, HoverImage;
    BufferedImage CurrentImage;
    boolean isClicked;
    public MenuItemView(){
        DefaultImage = loadImage("./Resources/ItemUIBackground.png");
        HoverImage = loadImage("./Resources/ItemUIBackgroundHover.png");
        CurrentImage = DefaultImage;
        setBounds(getX(), getX(), DefaultImage.getWidth(), DefaultImage.getHeight());
        addMouseListener(this);
        isClicked = false;
    }

    private BufferedImage loadImage(String path) {
        File file = new File(path);
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Attempted to load an image named %s in %s and failed!", file.getAbsolutePath(), getClass()));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(CurrentImage, 0, 0, null);
        paintChildren(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isClicked = !isClicked;
        if(isClicked){
            CurrentImage = HoverImage;
        } else {
            CurrentImage = DefaultImage;
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) { }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuItemView Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        frame.getContentPane().setBackground(Color.RED);
        frame.setLayout(null);
        MenuItemView item = new MenuItemView();
        item.setLocation(0, 0);
        frame.add(item);
        MenuItemView item2 = new MenuItemView();
        item2.setLocation(item.getWidth(), 0);
        frame.add(item2);
        frame.setVisible(true);
    }
}
