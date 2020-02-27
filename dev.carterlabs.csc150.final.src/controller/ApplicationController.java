package controller;

import model.Globals;
import model.events.Attack;
import model.events.Rendered;
import model.events.Started;
import model.events.Updated;
import model.level.Chamber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationController extends JFrame implements Runnable {
    public static List<Rendered> renderEvents = new ArrayList<>();
    public static List<Updated> updateEvents = new ArrayList<>();
    public static List<Started> startEvents = new ArrayList<>();
    public static List<Attack> attackEvents = new ArrayList<>();
    public static UIController uiController;

    public ApplicationController() {
        this.setBounds(0,0,Globals.WIDTH, Globals.HEIGHT);
        uiController = new UIController(this);
        Chamber chamber = new Chamber(this);
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
                for (Rendered re: renderEvents) {
                    re.Render(this);
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
        ApplicationController app = new ApplicationController();
        Thread thread = new Thread(app);
        thread.start();
    }
}
