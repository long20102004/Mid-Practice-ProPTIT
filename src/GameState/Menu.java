package GameState;
import Main.Game;
import Settings.SizeMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    // Chọn chế độ cho game
    private JButton PVPButton;
    private JButton PVEButton;
    private JButton gameRulesButton;
    private JButton settingButton;
    private Game game;
    public Menu(Game game){
        this.game = game;
        setTitle("MENU");
        setSize(500,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PVPButton = new JButton("PVP");
        PVEButton = new JButton("PVE");
        gameRulesButton = new JButton("LUẬT CHƠI");
        settingButton = new JButton("CÀI ĐẶT");

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

        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SizeMap();
            }
        });

        setLayout(new GridLayout(2,2));
        add(PVPButton);
        add(PVEButton);
        add(gameRulesButton);
        add(settingButton);
        setVisible(true);
    }

}