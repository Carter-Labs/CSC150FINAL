package controller;

import model.Globals;
import model.events.*;
import model.level.Level;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class GameController extends JFrame implements Runnable {
    public static List<Rendered> renderEvents = new ArrayList<>();
    public static List<Updated> updateEvents = new ArrayList<>();
    public static List<Started> startEvents = new ArrayList<>();
    public static List<Moved> moveEvents = new ArrayList<>();
    public static List<Attack> attackEvents = new ArrayList<>();
    public static List<Collided> collisionEvents = new ArrayList<>();
    public static UIController uiController;
    private Level level;

    public GameController() {
        uiController = new UIController(this);
        this.setBounds(0,0,Globals.WIDTH, Globals.HEIGHT);
        Thread t = new Thread(this::buildNewLevel);
        t.start();
    }

    public void buildNewLevel() {
        level = new Level(this);
        level.loadChamber(0);
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        int frames = 0;
        int ticks = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        while (!Globals.hasExited) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = false;

            while (delta >= 1) {
                ticks++;
                for (Updated ue: updateEvents) {
                    ue.Update();
                }
                for (Moved me: moveEvents) {
                    me.Move();
                }
                for (Attack ae: attackEvents) {
                    ae.attack();
                }
                delta -= 1;
                shouldRender = true;
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (shouldRender) {
                try {
                    for (Rendered re: renderEvents) {
                        re.Render(this);
                    }
                } catch (ConcurrentModificationException cme) {
                    System.err.println("Skipped a frame due to a Concurrent Modification");
                }
                frames++;
            }

            if (System.currentTimeMillis() - lastTimer > 1000) {
                lastTimer += 1000;
                System.out.println(ticks + "ticks, " + frames + "frames");
                frames = 0;
                ticks = 0;
            }
        }
    }

    public static void main(String[] args) {
        GameController app = new GameController();
        Thread thread = new Thread(app);
        thread.start();
    }
}
