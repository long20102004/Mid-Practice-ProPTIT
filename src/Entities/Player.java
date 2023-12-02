package Entities;

import Main.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends JComponent {
    protected GameWindow gameWindow;
    protected int typeShip = 0;
    protected boolean isHorizontal = true;
    protected PlayerManager playerManager;
    protected Map map = new Map(this);
    public ShipManager shipManager;
    protected ExtraMethods extraMethods = new ExtraMethods(this);
    public boolean isPlaying = true;
    public boolean[][] isExploded = new boolean[100][100]; // Kiểm tra xem vị trí đã bị bắn hỏng hay chưa
    public boolean[][] isBroken = new boolean[100][100];
    public boolean[][] isFailedShot = new boolean[100][100];
    public boolean[][] isDrawed = new boolean[100][100];
    public boolean[][] isPlaced = new boolean[100][100];
    public int numberExplodedShip;
    public static boolean changeTurn;
    public boolean isLost;
    public boolean isVictory;
    protected boolean isActive;

    public Player(PlayerManager playerManager) {
        this.setPlayerManager(playerManager);
    }


    public void initClass(String mapName) {
        setShipManager(new ShipManager(getPlayerManager(), this));
        map.setMap(mapName);
        extraMethods.importExplodeAnimation();
        extraMethods.importFire();
        extraMethods.importSmoke();
        extraMethods.importBroken();
        extraMethods.importLostScreen();
        extraMethods.importVictoryScreen();

    }

    public void render(Graphics g) {
        if (isLost){
            extraMethods.drawLostScreen(g);
        }
        else if (isVictory){
            extraMethods.drawVictoryScreen(g);
        }
        else {
            map.renderMap(g);
            if (isPlaying) shipManager.renderAllShip(g);
            extraMethods.renderExtraMethods(g);
            if (!isPlaying && !playerManager.isSwitchStatus()) extraMethods.drawChangeTurnBackground(g);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public int getTypeShip() {
        return typeShip;
    }

    public void setTypeShip(int typeShip) {
        this.typeShip = typeShip;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }


    public ShipManager getShipManager() {
        return shipManager;
    }

    public void setShipManager(ShipManager shipManager) {
        this.shipManager = shipManager;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
}
