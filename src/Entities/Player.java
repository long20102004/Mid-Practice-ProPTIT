package Entities;

import Main.GameWindow;

import javax.swing.*;
import java.awt.*;


public class Player extends JComponent {
    protected GameWindow gameWindow;
    protected int typeShip = 0;
    protected boolean isHorizontal = true;
    protected PlayerManager playerManager;
    protected Map map = new Map(this);
    public ShipManager shipManager;
    protected ExtraMethods extraMethods = new ExtraMethods(this);
    public boolean isPlaying = true;
    public boolean[][] isExploded = new boolean[100][100]; // Kiểm tra xem bị bắn nổ
    public boolean[][] isBroken = new boolean[100][100]; // Kiểm tra xem bị bắn vỡ
    public boolean[][] isFailedShot = new boolean[100][100]; // Kiểm tra bị bắn xịt
    public boolean[][] isDrawed = new boolean[100][100]; // Kiểm tra đã vẽ tàu
    public boolean[][] isPlaced = new boolean[100][100]; // Kiểm tra đã đặt tàu
    public int numberExplodedShip; // Đếm số tàu đã nổ
    public static boolean changeTurn; // Kiểm tra đổi lượt
    public boolean isLost; // Kiểm tra thua
    public boolean isVictory; // Kiểm tra thắng
    protected boolean isActive; // Kiểm tra có đang chơi (Phân biệt PVP và PVE)
    public boolean isAuto; // Kiểm tra có tự động đặt tàu không (Để xử lý hiệu ứng)

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
    public GameWindow getGameWindow(){
        return gameWindow;
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
