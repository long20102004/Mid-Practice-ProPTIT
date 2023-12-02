package Entities;

import Automatics.AutoPlace;
import Automatics.Bot;
import GameState.GameState;
import Inputs.KeyInputs;
import Inputs.MouseInputs;
import Main.Game;
import Main.GameWindow;
import utilz.Utility;
import GameState.PlayerState;
import javax.swing.*;
import GameState.GameMode;

public class PlayerManager extends JPanel {
    private Bot bot;
    private Player player1;
    private Player player2;
    private PlayerState playerState;
    private Player currentPlayer;
    private Game game;
    private boolean switchStatus;
    private static int countNumberPlayer;
    private AutoPlace autoPlace;


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

        if (GameMode.gameMode == GameMode.PVP) {
            player2.setGameWindow(new GameWindow(player2, "PLAYER2", new KeyInputs(this, player2)));
            MouseInputs mouseInputs2 = new MouseInputs(this, player2);
            player2.addMouseListener(mouseInputs2);
            player2.addMouseMotionListener(mouseInputs2);
            player2.addKeyListener(new KeyInputs(this, player2));


        }


        if (GameMode.gameMode == GameMode.PVE) {
            bot = new Bot(this);
            bot.initClass(Utility.getRandomBackGround());
            bot.setGameWindow(new GameWindow(bot, "BOT", new KeyInputs(this, bot)));
            MouseInputs mouseInputsBot = new MouseInputs(this, bot);
            bot.addMouseListener(mouseInputsBot);
            bot.addMouseMotionListener(mouseInputs);
            bot.addKeyListener(new KeyInputs(this, bot));
        }
        playerState = new PlayerState(this, player1);
        autoPlace = new AutoPlace(this);
    }


    public void updatePlayerState(){
        if (GameMode.gameMode == GameMode.PVP) {
            if (!isSwitchStatus()) {
                if (GameState.state == GameState.PLAYER1) {
                    GameState.state = GameState.PLAYER2;
                    playerState.currentPlayer = player2;
                } else {
                    GameState.state = GameState.PLAYER1;
                    playerState.currentPlayer = player1;
                }
            } else {
                if (GameState.state == GameState.PLAYER1) {
                    GameState.state = GameState.PLAYER2;
                    playerState.currentPlayer = player1;
                } else {
                    GameState.state = GameState.PLAYER1;
                    playerState.currentPlayer = player2;
                }
            }
        }
        else if (GameMode.gameMode == GameMode.PVE){
            // Xử lý đổi trạng thái của chế độ PVE
            if (!isSwitchStatus()) {
                if (GameState.state == GameState.PLAYER1) {
                    GameState.state = GameState.BOT;
                    playerState.currentPlayer = bot;
                } else {
                    GameState.state = GameState.PLAYER1;
                    playerState.currentPlayer = player1;
                }
            } else {
                if (GameState.state == GameState.PLAYER1) {
                    GameState.state = GameState.BOT;
                    playerState.currentPlayer = player1;
                } else {
                    GameState.state = GameState.PLAYER1;
                    playerState.currentPlayer = bot;
                }
            }
        }
    }
    public void update() {
        if (GameMode.gameMode == GameMode.PVP){
            player1.setIsActive(true);
            player2.setIsActive(true);
//            bot.setIsActive(false);
        }
        else if (GameMode.gameMode == GameMode.PVE){
            player1.setIsActive(true);
            bot.setIsActive(true);
//            player2.setIsActive(false);
        }
        if (GameMode.gameMode == GameMode.PVP) {
            if (GameState.state == GameState.PLAYER1) {
                player1.setPlaying(true);
                player2.setPlaying(false);
            } else {
                player1.setPlaying(false);
                player2.setPlaying(true);
            }
            if (player1.isLost) player2.isVictory = true;
            if (player2.isLost) player1.isVictory = true;
        }
        else if (GameMode.gameMode == GameMode.PVE){
            // Xử lý đổi trạng thái của chế độ PVE
            if (GameState.state == GameState.PLAYER1) {
                player1.setPlaying(true);
                bot.setPlaying(false);
            } else {
                player1.setPlaying(false);
                bot.setPlaying(true);
            }
            if (player1.isLost) bot.isVictory = true;
            if (bot.isLost) player1.isVictory = true;
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

    public void setAutoPlace(AutoPlace autoPlace) {
        this.autoPlace = autoPlace;
    }

    public AutoPlace getAutoPlace() {
        return autoPlace;
    }

    public Bot getBot() {
        return bot;
    }
}
