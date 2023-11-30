package Inputs;

import Entities.Player;
import GameState.GameState;
import Main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    private Player player;

    public KeyInputs(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameState.state == GameState.PLAYER1) player.player1State.keyPressed(e);
        else player.player2State.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
