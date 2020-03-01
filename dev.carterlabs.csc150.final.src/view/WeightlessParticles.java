package view;

import controller.GameController;
import model.Globals;
import model.events.Updated;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WeightlessParticles extends JComponent implements Updated {
    private List<OpaqueImageView> particles;
    /**
     * How many ticks the particle will live for
     */
    private int lifetime;
    /**
     * How many ticks have passed
     */
    private int time;

    /**
     * Creates a element that can be added to have a 'weightless' particle
     * with an image, lifetime, and the amount.
     * @param particleCount How many particles
     * @param lifetime How long the particle will last
     * @param particlePath The path to the particles image
     */
    public WeightlessParticles(int particleCount, int lifetime, String particlePath) {
        GameController.updateEvents.add(this::Update);
        this.lifetime = lifetime;
        time = 1;
        particles = new ArrayList<>();
        for (int i = 0; i < particleCount; i++) {
            OpaqueImageView particle = new OpaqueImageView(particlePath, 1);
            particle.setLocation(250 - (particle.getWidth() / 2), 250 - (particle.getHeight() / 2));
            add(particle);
            particles.add(particle);
        }
        setBounds(0, 0, Globals.WIDTH, Globals.HEIGHT);
    }

    /**
     * finds the area that the particles take up
     * @return
     */
    private Rectangle findBounds() {
        int smallestX = Integer.MAX_VALUE, smallestY = Integer.MAX_VALUE;
        int largestX = Integer.MIN_VALUE, largestY = Integer.MIN_VALUE;
        for (ImageView particle : particles) {
            if (particle.getX() < smallestX && particle.getY() < smallestY) {
                smallestX = particle.getX();
                smallestY = particle.getY();
            }
            if (particle.getX() + particle.getWidth() > largestX
                    && particle.getY() + particle.getHeight() > largestY) {
                largestX = particle.getX();
                largestY = particle.getY();
            }
        }
        return new Rectangle(smallestX, smallestY, largestX + particles.get(0).getWidth(), largestY + particles.get(0).getHeight());
    }

    @Override
    public void Update() {
        for (OpaqueImageView particle : particles) {
            particle.setOpacity(.5f);
            int y = particle.getY() - 1;
            if (y + particle.getHeight() < 0) {
                particles.remove(particle);
                break;
            }
            if(time >= lifetime) {
                particle.setVisible(false);
                remove(particle);
                particles.remove(particle);
                if(particles.size() == 0){
                    GameController.renderEvents.remove(this);
                    try {
                        finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
                break;
            }
            y += (lifetime/time) * .0001;
            particle.setLocation(particle.getX(), y);
        }
        time++;
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Particle Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        WeightlessParticles particles = new WeightlessParticles(50, 200, "./Resources/Particles/CashIcon1.png");
        frame.add(particles);
        frame.setVisible(true);
    }
}
