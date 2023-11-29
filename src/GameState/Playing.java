package GameState;

import Entities.Player;
import Main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utilz.ConstantVariable.SQUARE_HEIGHT;
import static utilz.ConstantVariable.SQUARE_WIDTH;

public class Playing implements StateMethods{
    private Game game;
    public Playing(Game game){
        this.game = game;
    }
    @Override
    public void run(Graphics g) {

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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) GameState.state = GameState.MENU;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
