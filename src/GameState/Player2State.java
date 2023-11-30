package GameState;

import Entities.Player;
import utilz.Utility;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class Player2State implements StateMethods {
    private Player player;
    BufferedImage waitingBack;

    public Player2State(Player player) {
        this.player = player;
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
            int xPos = e.getX() / SQUARE_WIDTH, yPos = e.getY() / SQUARE_HEIGHT;
            if (e.getButton() == MouseEvent.BUTTON3) {
                player.shipManager.addShip(player.getTypeShip(), xPos, yPos, player.isHorizontal);
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                if (GameState.state == GameState.PLAYER1) GameState.state = GameState.PLAYER2;
                else GameState.state = GameState.PLAYER1;
                player.shipManager.attackShip(xPos, yPos);
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
                case KeyEvent.VK_ENTER:
                    player.shipManager.isAddShipDone = true;
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
