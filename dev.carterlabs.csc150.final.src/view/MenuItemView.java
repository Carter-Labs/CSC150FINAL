package view;

import controller.ApplicationController;
import model.events.Rendered;
import model.objects.WeaponType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class MenuItemView extends ImageView implements Rendered {
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
        ItemImage = loadImage(ItemIconPath);
        ItemImage = scaleImage(ItemImage, calcImageScale(ItemImage));
        CurrentImage = DefaultImage;
        setBounds(getX(), getX(), DefaultImage.getWidth(), DefaultImage.getHeight());
        setIsActive(false);
        ApplicationController.renderEvents.add(this);
    }

    private double calcImageScale(BufferedImage itemImage) {
        if(itemImage.getWidth() <= DefaultImage.getWidth()){
            return itemImage.getWidth() / DefaultImage.getWidth() - .10;
        } else {
            return (((itemImage.getWidth() / DefaultImage.getWidth())*.01) + .02);
        }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        CurrentImage = getIsActive() ? HoverImage : DefaultImage;
        g2d.drawImage(CurrentImage, 0, 0, null);
        g2d.drawImage(ItemImage, 3, 3, null);
        paintChildren(g);
    }

    @Override
    public void Render(JFrame g) {
        this.repaint();
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
        MenuItemView item = new MenuItemView("./Resources/Guns/" + WeaponType.AR + ".png");
        item.setLocation(0, 0);
        frame.add(item);
        MenuItemView item2 = new MenuItemView("./Resources/Guns/" + WeaponType.SMG + ".png");
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
