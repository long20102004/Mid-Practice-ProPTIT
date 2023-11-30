package Inputs;

import Entities.Player;
import Entities.PlayerManager;
import GameState.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private PlayerManager playerManager;
    private Player player;

    public MouseInputs(PlayerManager playerManager, Player player) {
        this.playerManager = playerManager;
        this.player = player;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!playerManager.isSwitchStatus()) {
            if (GameState.state == GameState.PLAYER1 && player == playerManager.getPlayer1()) {
                playerManager.getPlayerState().mousePressed(e);
            }
            else if (GameState.state == GameState.PLAYER2 && player == playerManager.getPlayer2())
                playerManager.getPlayerState().mousePressed(e);
        }
        else {
            if (GameState.state == GameState.PLAYER1 && player == playerManager.getPlayer2())
                playerManager.getPlayerState().mousePressed(e);
            else if (GameState.state == GameState.PLAYER2 && player == playerManager.getPlayer1())
                playerManager.getPlayerState().mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
