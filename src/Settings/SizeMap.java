package Settings;

import utilz.ConstantVariable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utilz.ConstantVariable.*;

public class SizeMap extends JFrame {
    private JButton[] jButtons = new JButton[20];
    public SizeMap(){
        setTitle("CHỌN MAP");
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(600, 450));
        setLayout(new GridLayout(6,2));
        for (int i=10; i<=20; i++){
            jButtons[i-10] = new JButton(i + "x" + i);
            int sizeMap = i;
            jButtons[i-10].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(SizeMap.this, "Chọn kích thước thành công!");
                    NUMBER_OF_SQUARE = sizeMap;
                    SQUARE_WIDTH = (int) (IMAGE_WIDTH / NUMBER_OF_SQUARE);
                    SQUARE_HEIGHT = (int) (IMAGE_HEIGHT / NUMBER_OF_SQUARE);
                    dispose();
                }
            });
        }
        for (int i=0; i<=10; i++){
            add(jButtons[i]);
        }
        setVisible(true);
    }
}
