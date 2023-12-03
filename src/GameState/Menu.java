package GameState;
import Main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton PVPButton;
    private JButton PVEButton;
    private JButton gameRulesButton;
    private Game game;
    public Menu(Game game){
        this.game = game;
        setTitle("MENU");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PVPButton = new JButton("PVP");
        PVEButton = new JButton("PVE");
        gameRulesButton = new JButton("LUẬT CHƠI");

        PVPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMode.gameMode = GameMode.PVP;
                game.initPlayerManager();
                game.setGameThread(new Thread(game));
                game.getGameThread().start();
                System.out.println("PVP");
                dispose();
            }
        });

        PVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMode.gameMode = GameMode.PVE;
                game.initPlayerManager();
                game.setGameThread(new Thread(game));
                game.getGameThread().start();
                System.out.println("PVE");
                dispose();
            }
        });

        gameRulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameRules();
            }
        });

        setLayout(new GridLayout(3,1));
        add(PVPButton);
        add(PVEButton);
        add(gameRulesButton);
        setVisible(true);
    }

}
