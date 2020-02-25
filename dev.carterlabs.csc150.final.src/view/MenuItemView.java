package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuItemView extends JComponent {
    int ix, iy;
    public MenuItemView (int x, int y) {
        setLocation(x, y);
        ix = x;
        iy = y;
        setBackground(new Color(0, 0,0, 1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
        g2d.setComposite(alphaComposite);
        BufferedImage img;
        File file = new File("./Resources/ItemUIBackground.png");
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Attempted to load an image named %s in %s and failed!", file.getAbsolutePath(), getClass()));
        }
        g2d.drawImage(img, ix, iy, null);
        setBounds(ix, iy, getWidth(), getHeight());
        paintChildren(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuItemView Test Main");
        frame.setBounds(200, 200, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.RED);
        MenuItemView menu = new MenuItemView(0, 0);
        frame.add(menu);
        frame.setVisible(true);
    }
}
