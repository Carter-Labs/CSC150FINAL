package view;

import javax.swing.*;
import java.awt.*;


public class Rectangle extends JComponent  {

    /**    All values are measured in pixels:  x - horizonal location,  y - vertical location,
     *     w - the width,  h - the height,   color - black
     */
    public Rectangle(int x, int y, int w, int h)  {
        super();
        setBounds(x, y, w, h);
        setBackground(Color.black);
    }

    /**    All values are measured in pixels:  x - horizonal location,  y - vertical location,
     *     w - the width,  h - the height,   color - fill color
     */
    public Rectangle(int x, int y, int w, int h, Color c)  {
        super();
        setBounds(x, y, w, h);
        setBackground(c);
    }

    /**
     *     Returns the color of the container
     */
    public Color getContainerColor()
    {
        return this.getParent().getBackground();
    }

    public int getContainerX()
    {
        return this.getParent().getX();
    }

    public int getContainerY()
    {
        return this.getParent().getY();
    }

    public int getContainerWidth()
    {
        return this.getParent().getWidth();
    }

    public int getContainerHeight()
    {
        return this.getParent().getHeight();
    }

    public void setContainerBackground(Color c)
    {
        this.getParent().setBackground(c);
    }

    /**    This method draws a filled Rectangle and is called when the screen is refreshed or repainted
     */
    public void paint(Graphics g)  {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth()-1, getHeight()-1);
        paintChildren(g);
    }
}