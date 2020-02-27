package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageView extends JComponent {
    BufferedImage image;

    public ImageView(){
        setBounds(getX(), getX(), getWidth(), getHeight());
    }
    public ImageView(String s) {
        this.image = loadImage(s);
        setBounds(getX(), getX(), image.getWidth(), image.getHeight());
    }
    /**
     * Makes loading images easier.
     * @param path Where the image is located
     * @return The loaded image
     */
    public BufferedImage loadImage(String path) {
        File file = new File(path);
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Attempted to load an image named %s in %s and failed!", file.getAbsolutePath(), getClass()));
        }
    }

    /**
     * This will scale an image used to make sure the item fits in the menu
     * @param image The image to be scaled
     * @param factor How much it will be scaled on both axises
     * @return the scaled image.
     */
    public BufferedImage scaleImage(BufferedImage image, double factor){
        BufferedImage scaledImage = new BufferedImage((int)(image.getWidth() / factor), (int)(image.getHeight() / factor), BufferedImage.TYPE_INT_ARGB);
        AffineTransform transform = AffineTransform.getScaleInstance(factor, factor);
        AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return transformOp.filter(image, scaledImage);
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        paintChildren(g);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public static void main(String[] args) {
        ImageView imageView = new ImageView("./Resources/LevelAssets/floor_01.png");
        imageView.setLocation(100,0);
        JFrame frame = new JFrame("ImageViewTest Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        frame.getContentPane().setBackground(Color.MAGENTA);
        frame.setLayout(null);
        frame.add(imageView);
        frame.setVisible(true);
    }
}
