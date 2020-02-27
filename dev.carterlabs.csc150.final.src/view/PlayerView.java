package view;

import model.events.Started;
import model.events.Updated;

import java.awt.*;

public class PlayerView extends ImageView implements Started, Updated {
    public PlayerView() {
        super.setLayout(null);
    }

    @Override
    public void Start() {
    }

    @Override
    public void Update() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintChildren(g);
    }
}
