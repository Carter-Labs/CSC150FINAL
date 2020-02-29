package view;

import java.awt.*;

public class OpaqueImageView extends ImageView{
    private float opacity;

    public OpaqueImageView(String s, float opacity) {
        super(s);
        setOpacity(opacity);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.getOpacity()));
        super.paintComponent(g);
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }
}
