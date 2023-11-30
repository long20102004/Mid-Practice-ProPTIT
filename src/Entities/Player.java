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
    private BufferedImage[][] gameMap;
    private BufferedImage stick;
    private boolean[][] explodedAnimation = new boolean[100][100];
    private boolean isPlaying = true;
    private BufferedImage[][] explodeFrame = new BufferedImage[100][100];
    private boolean[][] isExploded = new boolean[100][100]; // Kiểm tra xem vị trí đã bị bắn hỏng hay chưa
    private BufferedImage[][] smokeFrame = new BufferedImage[100][100];
    private boolean[][] isBroken = new boolean[100][100];
    private boolean[][] isFailedShot = new boolean[100][100];
    private boolean[][] isDrawed = new boolean[100][100];
    private boolean[][] isPlaced = new boolean[100][100];

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
        getMap().renderMap(g);
        if (isPlaying()) getShipManager().renderAllShip(g);
        getExtraMethods().renderExtraMethods(g);
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

    public BufferedImage[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(BufferedImage[][] gameMap) {
        this.gameMap = gameMap;
    }

    public BufferedImage getStick() {
        return stick;
    }

    public void setStick(BufferedImage stick) {
        this.stick = stick;
    }

    public boolean[][] getExplodedAnimation() {
        return explodedAnimation;
    }

    public void setExplodedAnimation(boolean[][] explodedAnimation) {
        this.explodedAnimation = explodedAnimation;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public BufferedImage[][] getExplodeFrame() {
        return explodeFrame;
    }

    public void setExplodeFrame(BufferedImage[][] explodeFrame) {
        this.explodeFrame = explodeFrame;
    }

    public boolean[][] getIsExploded() {
        return isExploded;
    }

    public void setIsExploded(boolean[][] isExploded) {
        this.isExploded = isExploded;
    }

    public BufferedImage[][] getSmokeFrame() {
        return smokeFrame;
    }

    public void setSmokeFrame(BufferedImage[][] smokeFrame) {
        this.smokeFrame = smokeFrame;
    }

    public boolean[][] getIsBroken() {
        return isBroken;
    }

    public void setIsBroken(boolean[][] isBroken) {
        this.isBroken = isBroken;
    }

    public boolean[][] getIsFailedShot() {
        return isFailedShot;
    }

    public void setIsFailedShot(boolean[][] isFailedShot) {
        this.isFailedShot = isFailedShot;
    }

    public boolean[][] getIsDrawed() {
        return isDrawed;
    }

    public void setIsDrawed(boolean[][] isDrawed) {
        this.isDrawed = isDrawed;
    }

    public boolean[][] getIsPlaced() {
        return isPlaced;
    }

    public void setIsPlaced(boolean[][] isPlaced) {
        this.isPlaced = isPlaced;
    }
}
