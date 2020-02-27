package controller;

import model.entities.Player;
import view.PlayerView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {
    Player player; //The player model
    PlayerView view; // The player view

    /**
     * This class shall control the player.
     * @param player The player that is controlled
     */
    public PlayerController(Player player) {
        view = new PlayerView();
        setPlayer(player);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
