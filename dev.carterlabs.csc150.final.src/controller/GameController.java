package controller;

import model.Globals;
import model.events.*;
import model.level.Chamber;
import model.level.GameObject;
import model.level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class GameController extends JFrame implements Runnable {
    public static List<Rendered> renderEvents = new ArrayList<>();
    public static List<Updated> updateEvents = new ArrayList<>();
    public static List<Started> startEvents = new ArrayList<>();
    public static List<Moved> moveEvents = new ArrayList<>();
    public static List<Attack> attackEvents = new ArrayList<>();
    public static List<GameObject> objects = new ArrayList<>();
    public static UIController uiController;
    public static Chamber currentChamber;
    private Level level;

    public GameController() {
        setLookAndFeel();
        uiController = new UIController(this);
        this.setBounds(0, 0, Globals.WIDTH, Globals.HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Thread t = new Thread(this::buildNewLevel);
        t.start();
        Globals.game = this;
    }

    public void buildNewLevel() {
        level = new Level(this);
        level.loadChamber(0);
    }

    public void reset() {
        int input = JOptionPane.showOptionDialog(this, "Task Failed Successfully", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, null, null);
        if(input == JOptionPane.OK_OPTION || input == JOptionPane.CLOSED_OPTION){
         this.getContentPane().removeAll();
            for (Chamber c: level.getChambers()) {
                c.clearChamber();
            }
             this.repaint();
             Globals.collidedEntities.clear();
             Globals.hasExited = true;
             this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
             this.dispose();
        }
    }



    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Globals.print("Failed to change look and feel");
        }
        try {
            String[][] icons = {
                    {"OptionPane.warningIcon",     "65581"},
                    {"OptionPane.questionIcon",    "65583"},
                    {"OptionPane.errorIcon",       "65585"},
                    {"OptionPane.informationIcon", "65587"}
            };
            Method getIconBits = Class.forName("sun.awt.shell.Win32ShellFolder2").getDeclaredMethod("getIconBits", long.class, int.class);
            getIconBits.setAccessible(true);
            int icon32Size = 40;
            Globals.print("" + icon32Size);
            Object[] arguments = {null, icon32Size};
            for (String[] s : icons) {
                if (UIManager.get(s[0]) instanceof ImageIcon) {
                    arguments[0] = Long.valueOf(s[1]);
                    int[] iconBits = (int[]) getIconBits.invoke(null, arguments);
                    if (iconBits != null) {
                        BufferedImage img = new BufferedImage(icon32Size, icon32Size, BufferedImage.TYPE_INT_ARGB);
                        img.setRGB(0, 0, icon32Size, icon32Size, iconBits, 0, icon32Size);
                        ImageIcon newIcon = new ImageIcon(img);
                        UIManager.put(s[0], newIcon);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                try {
                    for (Updated ue : updateEvents) {
                        ue.Update();
                    }
                } catch (ConcurrentModificationException ignored){}
                try {
                    for (Moved me : moveEvents) {
                        if (me != null) {
                            try {
                                me.Move();
                            } catch (NullPointerException npe) {
                                npe.printStackTrace();
                            }
                        }
                    }
                } catch (ConcurrentModificationException ignore) {
                    ignore.printStackTrace();
                }
                for (Attack ae : attackEvents) {
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
                    for (Rendered re : renderEvents) {
                        re.Render(this);
                    }
                } catch (ConcurrentModificationException cme) {
                    System.err.println("Skipped a frame due to a Concurrent Modification");
                }
                frames++;
            }

            if (System.currentTimeMillis() - lastTimer > 1000) {
                lastTimer += 1000;
//                System.out.println(ticks + "ticks, " + frames + "frames");
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
