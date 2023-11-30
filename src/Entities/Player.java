package Entities;
import Main.GameWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;



public class Player extends JComponent {
    private GameWindow gameWindow;
    private int typeShip = 0;
    private boolean isHorizontal = true;
    private PlayerManager playerManager;
    private Map map = new Map(this);
    private ShipManager shipManager;
    private ExtraMethods extraMethods = new ExtraMethods(this);
    public BufferedImage[][] gameMap;
    private BufferedImage stick;
    public boolean[][] explodedAnimation = new boolean[100][100];
    private boolean isPlaying = true;
    public BufferedImage[][] explodeFrame = new BufferedImage[100][100];
    public boolean[][] isExploded = new boolean[100][100]; // Kiểm tra xem vị trí đã bị bắn hỏng hay chưa
    public BufferedImage[][] smokeFrame = new BufferedImage[100][100];
    public boolean[][] isBroken = new boolean[100][100];
    public boolean[][] isFailedShot = new boolean[100][100];
    public boolean[][] isDrawed = new boolean[100][100];
    public boolean[][] isPlaced = new boolean[100][100];

    public Player(PlayerManager playerManager){
        this.setPlayerManager(playerManager);
    }


    public void initClass(String mapName) {
        setShipManager(new ShipManager(getPlayerManager(), this));
        getMap().setMap(mapName);
        getExtraMethods().importExplodeAnimation();
        getExtraMethods().importFire();
        getExtraMethods().importSmoke();
    }
    public void render(Graphics g){
        map.renderMap(g);
        if (isPlaying()) shipManager.renderAllShip(g);
        extraMethods.renderExtraMethods(g);
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

    public GameWindow getGameWindow() {
        return gameWindow;
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ShipManager getShipManager() {
        return shipManager;
    }

    public void setShipManager(ShipManager shipManager) {
        this.shipManager = shipManager;
    }

    public ExtraMethods getExtraMethods() {
        return extraMethods;
    }

    public void setExtraMethods(ExtraMethods extraMethods) {
        this.extraMethods = extraMethods;
    }

    public BufferedImage getStick() {
        return stick;
    }

    public void setStick(BufferedImage stick) {
        this.stick = stick;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
