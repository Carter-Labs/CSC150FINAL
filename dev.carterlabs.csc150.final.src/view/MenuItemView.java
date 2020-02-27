package view;

import controller.GameController;
import model.events.Rendered;
import model.objects.WeaponType;

import javax.swing.*;
import java.awt.*;
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
        setBounds(getX(), getY(), DefaultImage.getWidth(), DefaultImage.getHeight());
        setIsActive(false);
        GameController.renderEvents.add(this);
    }

    /**
     * Calculates how large the image should be to fit with in the item frame
     * @param itemImage The image to scale
     * @return the factor to scale the image by
     */
    private double calcImageScale(BufferedImage itemImage) {
        if(itemImage.getWidth() <= DefaultImage.getWidth()){
            return itemImage.getWidth() / DefaultImage.getWidth() - .10;
        } else {
            return (((itemImage.getWidth() / DefaultImage.getWidth())*.01) + .02);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        CurrentImage = getIsActive() ? HoverImage : DefaultImage;
        g.drawImage(CurrentImage, 0, 0, null);
        g.drawImage(ItemImage, 3, 3, null);
//        paintChildren(g);
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
