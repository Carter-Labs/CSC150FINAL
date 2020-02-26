package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuItemView extends JComponent {
    /**
     * Images are 64px x 64px
     */
    BufferedImage DefaultImage, HoverImage, CurrentImage, ItemImage;
    /**
     * A MenuItemView 'isActive' when the component is 'focused on', or 'selected' when painted it will display with a
     * more highlighted look and feel.
     */
    private boolean isActive;

    /**
     * MenuItemView provides a simple way to add MenuItem look and feel with it's images and an Item in the frame
     * @param ItemIconPath Is the path for the icon that will be displayed in the menu
     */
    public MenuItemView(String ItemIconPath){
        DefaultImage = loadImage("./Resources/ItemUIBackground.png");
        HoverImage = loadImage("./Resources/ItemUIBackgroundHover.png");
        ItemImage = scaleImage(loadImage(ItemIconPath), .90);
        CurrentImage = DefaultImage;
        setBounds(getX(), getX(), DefaultImage.getWidth(), DefaultImage.getHeight());
        setIsActive(false);
    }

    /**
     * This will scale an image used to make sure the item fits in the menu
     * @param image The image to be scaled
     * @param factor How much it will be scaled on both axises
     * @return the scaled image.
     */
    private BufferedImage scaleImage(BufferedImage image, double factor){
        BufferedImage scaledImage = new BufferedImage((int)(image.getWidth() / factor), (int)(image.getHeight() / factor), BufferedImage.TYPE_INT_ARGB);
        AffineTransform transform = AffineTransform.getScaleInstance(factor, factor);
        AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return transformOp.filter(image, scaledImage);
    }

    /**
     * Makes loading images easier.
     * @param path Where the image is located
     * @return The loaded image
     */
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
        CurrentImage = getIsActive() ? HoverImage : DefaultImage;
        g.drawImage(CurrentImage, 0, 0, null);
        g.drawImage(ItemImage, 3, 3, null);
        paintChildren(g);
    }

    public void setIsActive(boolean active) {
        isActive = active;
        repaint();
    }

    public boolean getIsActive() {
        return isActive;
    }

    /**
     * This is just a simple test method to show that MenuItemView is working
     * @param args
     */
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
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            item2.setIsActive(!item2.getIsActive());
        }
    }
}
