package GameState;

import Entities.Player;
import Main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utilz.ConstantVariable.*;

public class Menu implements StateMethods {
    private Game game;
    public Menu(Game game){
        this.game= game;
    }
    @Override
    public void run(Graphics g) {
        g.setColor(Color.PINK);
        g.drawString("MENU", SQUARE_WIDTH * NUMBER_OF_SQUARE / 2, SQUARE_HEIGHT * NUMBER_OF_SQUARE / 2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) GameState.state = GameState.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
