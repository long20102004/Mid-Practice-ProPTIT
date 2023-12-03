package GameState;

import Entities.Player;
import Entities.PlayerManager;
import utilz.Utility;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;

public class PlayerState implements StateMethods {
    public Player currentPlayer;
    public PlayerManager playerManager;
    public BufferedImage waitingBack;

    public PlayerState(PlayerManager playerManager, Player currentPlayer) {
        this.setPlayerManager(playerManager);
        this.setCurrentPlayer(currentPlayer);
        setWaitingBack(Utility.importImg(Utility.waitingBackground));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getWaitingBack(), 0, 0, SQUARE_WIDTH * NUMBER_OF_SQUARE, SQUARE_HEIGHT * NUMBER_OF_SQUARE, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(GameState.state);
        int xPos = e.getX() / SQUARE_WIDTH, yPos = e.getY() / SQUARE_HEIGHT;
        if (e.getButton() == MouseEvent.BUTTON3) {
            getCurrentPlayer().getShipManager().addShip(getCurrentPlayer().getTypeShip(), xPos, yPos, getCurrentPlayer().isHorizontal());
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            if (GameMode.gameMode == GameMode.PVE && currentPlayer == playerManager.getBot()) {
                currentPlayer.shipManager.attackShip(xPos, yPos, true);
                if (!currentPlayer.isBroken[xPos][yPos]) playerManager.getBot().autoAttack();
            }
            if (GameMode.gameMode == GameMode.PVP) {
                currentPlayer.shipManager.attackShip(xPos, yPos, true);
            }
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
                getCurrentPlayer().setTypeShip(1);
                break;
            case KeyEvent.VK_2:
                getCurrentPlayer().setTypeShip(2);
                break;
            case KeyEvent.VK_3:
                getCurrentPlayer().setTypeShip(3);
                break;
            case KeyEvent.VK_4:
                getCurrentPlayer().setTypeShip(4);
                break;
            case KeyEvent.VK_R:
                getCurrentPlayer().setHorizontal(false);
                break;
            case KeyEvent.VK_ENTER:
                break;
            case KeyEvent.VK_SPACE:
                playerManager.getAutoPlace().autoAddPlayer1();
                break;
            case KeyEvent.VK_BACK_SPACE:
                if (GameMode.gameMode == GameMode.PVP) playerManager.getAutoPlace().autoAddPlayer2();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public BufferedImage getWaitingBack() {
        return waitingBack;
    }

    public void setWaitingBack(BufferedImage waitingBack) {
        this.waitingBack = waitingBack;
    }
}