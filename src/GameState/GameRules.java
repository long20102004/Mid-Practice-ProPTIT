package GameState;

import utilz.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameRules extends JFrame {
    BufferedImage img;
    public GameRules(){
        img = Utility.importImg(Utility.gameRules);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setName("Luật chơi");
//        Image scaledImage = img.getScaledInstance(600,800, Image.SCALE_SMOOTH);
        JLabel jLabel = new JLabel(new ImageIcon(img));
        this.add(jLabel);
        this.pack();
        this.setVisible(true);
    }
}
