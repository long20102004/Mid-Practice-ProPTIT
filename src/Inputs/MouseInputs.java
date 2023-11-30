package Inputs;

import Entities.Player;
import GameState.GameState;
import Main.Game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static utilz.ConstantVariable.SQUARE_HEIGHT;
import static utilz.ConstantVariable.SQUARE_WIDTH;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private Player player;

    public MouseInputs(Player player) {
        this.player = player;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (GameState.state == GameState.PLAYER1) {
            player.player1State.mousePressed(e);
        }
        else player.player2State.mousePressed(e);
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
