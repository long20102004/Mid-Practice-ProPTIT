package Entities;

import Automatics.AutoPlace;
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
    public Player player1;
    public Player player2;
    private PlayerState playerState;
    private Player currentPlayer;
    private Game game;
    private boolean switchStatus;
    private static int countNumberPlayer;
    public AutoPlace autoPlace;


    public PlayerManager(Game game) {
        this.game = game;
        initClass();
    }

    public static int getCountNumberPlayer() {
        return countNumberPlayer;
    }

    public static void setCountNumberPlayer(int countNumberPlayer) {
        PlayerManager.countNumberPlayer = countNumberPlayer;
    }

    public void initClass(){
        player1 = new Player(this);
        player1.initClass(Utility.getRandomBackGround());
        player2 = new Player(this);
        player2.initClass(Utility.getRandomBackGround());

        player1.setGameWindow(new GameWindow(player1, "PLAYER 1", new KeyInputs(this, player1)));
        MouseInputs mouseInputs = new MouseInputs(this, player1);
        player1.addMouseListener(mouseInputs);
        player1.addMouseMotionListener(mouseInputs);
        player1.addKeyListener(new KeyInputs(this, player1));


        player2.setGameWindow(new GameWindow(player2, "PLAYER2", new KeyInputs(this, player2)));
        MouseInputs mouseInputs2 = new MouseInputs(this, player2);
        player2.addMouseListener(mouseInputs2);
        player2.addMouseMotionListener(mouseInputs2);
        player2.addKeyListener(new KeyInputs(this, player2));

        playerState = new PlayerState(this, player1);
        autoPlace = new AutoPlace(this);
    }


    public void updatePlayerState(){
        if (!isSwitchStatus()){
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
            player1.setPlaying(true);
            player2.setPlaying(false);
        } else {
            player1.setPlaying(false);
            player2.setPlaying(true);
        }
    }
    public Player getPlayer1(){
        return player1;
    }
    public Player getPlayer2(){
        return player2;
    }


    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(boolean switchStatus) {
        this.switchStatus = switchStatus;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

}
