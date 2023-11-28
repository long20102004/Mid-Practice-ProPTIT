package Main;

import Entities.Player;

import javax.swing.*;
import java.awt.*;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(Player player, String name){
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.setTitle(name);
        setWindowSize();
        jframe.add(player);
        jframe.pack();
        jframe.setVisible(true);
    }
    public void setWindowSize(){
        Dimension dimension = new Dimension(SQUARE_WIDTH * NUMBER_OF_SQUARE + SQUARE_WIDTH / 2, SQUARE_HEIGHT * NUMBER_OF_SQUARE + SQUARE_HEIGHT / 2);
        jframe.setPreferredSize(dimension);
    }
}
