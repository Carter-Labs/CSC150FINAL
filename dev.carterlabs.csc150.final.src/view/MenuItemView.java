package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuItemView extends JComponent implements MouseListener {
    BufferedImage DefaultImage, HoverImage, CurrentImage, ItemImage;
    private boolean isClicked;

    public MenuItemView(String ItemIconPath){
        DefaultImage = loadImage("./Resources/ItemUIBackground.png");
        HoverImage = loadImage("./Resources/ItemUIBackgroundHover.png");
        ItemImage = scaleImage(loadImage(ItemIconPath), .90);
        CurrentImage = DefaultImage;
        setBounds(getX(), getX(), DefaultImage.getWidth(), DefaultImage.getHeight());
        addMouseListener(this);
        isClicked = false;
    }

    private BufferedImage scaleImage(BufferedImage image, double factor){
        BufferedImage scaledImage = new BufferedImage((int)(image.getWidth() / factor), (int)(image.getHeight() / factor), BufferedImage.TYPE_INT_ARGB);
        AffineTransform transform = AffineTransform.getScaleInstance(factor, factor);
        AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return transformOp.filter(image, scaledImage);
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
        g.drawImage(ItemImage, 3, 3, null);
        paintChildren(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setIsClicked(!getIsClicked());
        if(getIsClicked()){
            CurrentImage = HoverImage;
        } else {
            CurrentImage = DefaultImage;
        }
        repaint();
    }

    public void setIsClicked(boolean clicked) {
        isClicked = clicked;
        repaint();
    }

    public boolean getIsClicked() {
        return isClicked;
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
        MenuItemView item = new MenuItemView("./Resources/TestPistolIcon.png");
        item.setLocation(0, 0);
        frame.add(item);
        MenuItemView item2 = new MenuItemView("./Resources/TestPistolIcon.png");
        item2.setLocation(item.getWidth(), 0);
        frame.add(item2);
        frame.setVisible(true);
    }
}
