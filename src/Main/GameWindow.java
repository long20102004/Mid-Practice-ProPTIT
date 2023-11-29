package Main;

import Entities.Player;
import Inputs.KeyInputs;
import Inputs.KeyInputs2;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(Player player, String name, KeyInputs keyInputs){
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.setTitle(name);
        setWindowSize();
        jframe.add(player);
        jframe.pack();
        jframe.setVisible(true);
        jframe.addKeyListener(keyInputs);
    }
    public void setWindowSize(){
        Dimension dimension = new Dimension(SQUARE_WIDTH * NUMBER_OF_SQUARE + SQUARE_WIDTH / 2, SQUARE_HEIGHT * NUMBER_OF_SQUARE + SQUARE_HEIGHT / 2);
        jframe.setPreferredSize(dimension);
    }
    public void focus(){
        jframe.setFocusable(true);
        jframe.requestFocus();
    }
}
