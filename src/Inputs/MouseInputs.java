package Inputs;

import Entities.Player;
import GameState.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static utilz.ConstantVariable.SQUARE_HEIGHT;
import static utilz.ConstantVariable.SQUARE_WIDTH;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private Player player1, player2;

    public MouseInputs(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
            case PLAYING:

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xPos = e.getX() / SQUARE_WIDTH, yPos = e.getY() / SQUARE_HEIGHT;
        player1.setPlaying(true);
        player2.setPlaying(false);
        if (e.getButton() == MouseEvent.BUTTON1) {
            player1.ship.attackBattleShip(xPos, yPos);
            player2.ship.attackBattleShip(xPos, yPos);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            player1.ship.placedBattleShip(xPos, yPos);
//            player2.ship.placedBattleShip(xPos, yPos);
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
