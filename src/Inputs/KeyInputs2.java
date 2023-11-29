package Inputs;

import Entities.Player;
import GameState.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs2 implements KeyListener {
    private Player player;

    public KeyInputs2(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_1:
                player.setTypeShip(1);
                break;
            case KeyEvent.VK_2:
                player.setTypeShip(2);
                break;
            case KeyEvent.VK_3:
                player.setTypeShip(3);
                break;
            case KeyEvent.VK_4:
                player.setTypeShip(4);
                break;
            case KeyEvent.VK_R:
                player.isHorizontal = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
