package Entities;
import Main.GameWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;



public class Player extends JComponent {
    public GameWindow gameWindow;
    private int typeShip = 0;
    public boolean isHorizontal = true;
    public PlayerManager playerManager;
    public Map map = new Map(this);
    public ShipManager shipManager;
    public ExtraMethods extraMethods = new ExtraMethods(this);
    public BufferedImage[][] gameMap;
    public BufferedImage stick;
    public boolean[][] explodedAnimation = new boolean[100][100];
    public boolean isPlaying = true;
    public BufferedImage[][] explodeFrame = new BufferedImage[100][100];
    public static boolean[][] isExploded = new boolean[100][100]; // Kiểm tra xem vị trí đã bị bắn hỏng hay chưa
    public BufferedImage[][] smokeFrame = new BufferedImage[100][100];
    public static boolean[][] isBroken = new boolean[100][100];
    public static boolean[][] isFailedShot = new boolean[100][100];
    public boolean[][] isDrawed = new boolean[100][100];

    public Player(PlayerManager playerManager){
        this.playerManager = playerManager;
    }


    public void initClass(String mapName) {
        shipManager = new ShipManager(playerManager, this);
        map.setMap(mapName);
        extraMethods.importExplodeAnimation();
        extraMethods.importFire();
        extraMethods.importSmoke();
    }
    public void render(Graphics g){
        map.renderMap(g);
        if (isPlaying) shipManager.renderAllShip(g);
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
}
