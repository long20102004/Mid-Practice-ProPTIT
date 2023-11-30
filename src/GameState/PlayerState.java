package GameState;

import Entities.Player;
import Entities.PlayerManager;
import Main.Game;
import utilz.Utility;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;

public class PlayerState implements StateMethods {
    public Player currentPlayer;
    public PlayerManager playerManager;
    BufferedImage waitingBack;

    public PlayerState(PlayerManager playerManager, Player currentPlayer) {
        this.playerManager = playerManager;
        this.currentPlayer = currentPlayer;
        waitingBack = Utility.importImg(Utility.waitingBackground);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(waitingBack, 0, 0, SQUARE_WIDTH * NUMBER_OF_SQUARE, SQUARE_HEIGHT * NUMBER_OF_SQUARE, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(GameState.state);
        int xPos = e.getX() / SQUARE_WIDTH, yPos = e.getY() / SQUARE_HEIGHT;
        if (e.getButton() == MouseEvent.BUTTON3) {
            currentPlayer.shipManager.addShip(currentPlayer.getTypeShip(), xPos, yPos, currentPlayer.isHorizontal);
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            currentPlayer.shipManager.attackShip(xPos, yPos);
            playerManager.updatePlayerState();
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
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
                currentPlayer.setTypeShip(1);
                break;
            case KeyEvent.VK_2:
                currentPlayer.setTypeShip(2);
                break;
            case KeyEvent.VK_3:
                currentPlayer.setTypeShip(3);
                break;
            case KeyEvent.VK_4:
                currentPlayer.setTypeShip(4);
                break;
            case KeyEvent.VK_R:
                currentPlayer.isHorizontal = false;
                break;
            case KeyEvent.VK_ENTER:
                currentPlayer.shipManager.isAddShipDone = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}