package Inputs;

import Entities.Player;
import GameState.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    private Player player1, player2;
    public KeyInputs(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
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
