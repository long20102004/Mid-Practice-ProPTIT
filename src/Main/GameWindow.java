package Main;

import Entities.Player;
import Entities.PlayerManager;
import Inputs.KeyInputs;

import javax.swing.*;
import java.awt.*;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class GameWindow extends JFrame{
    private JFrame jframe;
    public GameWindow(Player player, String name, KeyInputs keyInputs){
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle(name);
        setWindowSize();
        jframe.add(player);
        if (keyInputs != null) jframe.addKeyListener(keyInputs);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
    public void setWindowSize(){
        Dimension dimension = new Dimension(SQUARE_WIDTH * NUMBER_OF_SQUARE + SQUARE_WIDTH / 2, SQUARE_HEIGHT * NUMBER_OF_SQUARE + SQUARE_HEIGHT / 2);
        jframe.setPreferredSize(dimension);
    }
    public void focus(){
        jframe.setFocusable(true);
        jframe.requestFocus();
    }
    public void setVisible(boolean visible){
        jframe.setVisible(visible);
    }

    public Component getJframe() {
        return jframe;
    }
}
