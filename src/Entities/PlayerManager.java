package Entities;

import GameState.GameState;
import Inputs.KeyInputs;
import Inputs.MouseInputs;
import Main.Game;
import Main.GameWindow;
import utilz.Utility;
import GameState.PlayerState;
import javax.swing.*;
import java.awt.*;

public class PlayerManager extends JPanel {
    private Player player1;
    private Player player2;
    public PlayerState playerState;
    public Player currentPlayer;
    private Game game;
    public boolean switchStatus;
    public static int countNumberPlayer;

    public PlayerManager(Game game) {
        this.game = game;
        initClass();
    }
    public void initClass(){
        player1 = new Player(this);
        player1.initClass(Utility.getRandomBackGround());
        player2 = new Player(this);
        player2.initClass(Utility.getRandomBackGround());

        player1.gameWindow = new GameWindow(player1, "PLAYER 1", new KeyInputs(this, player1));
        MouseInputs mouseInputs = new MouseInputs(this, player1);
        player1.addMouseListener(mouseInputs);
        player1.addMouseMotionListener(mouseInputs);
        player1.addKeyListener(new KeyInputs(this, player1));


        player2.gameWindow = new GameWindow(player2, "PLAYER2", new KeyInputs(this, player2));
        MouseInputs mouseInputs2 = new MouseInputs(this, player2);
        player2.addMouseListener(mouseInputs2);
        player2.addMouseMotionListener(mouseInputs2);
        player2.addKeyListener(new KeyInputs(this, player2));

        playerState = new PlayerState(this, player1);
    }

    public Game getGame(){
        return game;
    }
    public void render(Graphics g) {
        player1.render(g);
        player2.render(g);
    }
    public void updatePlayerState(){
        if (!switchStatus){
            if (GameState.state == GameState.PLAYER1){
                GameState.state = GameState.PLAYER2;
                playerState.currentPlayer = player2;
            }
            else{
                GameState.state = GameState.PLAYER1;
                playerState.currentPlayer = player1;
            }
        }
        else{
            if (GameState.state == GameState.PLAYER1){
                GameState.state = GameState.PLAYER2;
                playerState.currentPlayer = player1;
            }
            else{
                GameState.state = GameState.PLAYER1;
                playerState.currentPlayer = player2;
            }
        }
    }
    public void update() {
        if (GameState.state == GameState.PLAYER1) {
            player1.isPlaying = true;
            player2.isPlaying = false;
        } else {
            player1.isPlaying = false;
            player2.isPlaying = true;
        }
    }
    public Player getPlayer1(){
        return player1;
    }
    public Player getPlayer2(){
        return player2;
    }
}
