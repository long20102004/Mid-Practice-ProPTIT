package Entities;
import GameState.GameState;
import GameState.Player1State;
import GameState.Player2State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;



public class Player extends JPanel {
    private int typeShip = 0;
    public boolean isHorizontal = true;
    public Map map = new Map(this);
    public ShipManager shipManager = new ShipManager(this);
    public ExtraMethods extraMethods = new ExtraMethods(this);
    public BufferedImage[][] gameMap;
    public BufferedImage stick;
    public boolean[][] explodedAnimation = new boolean[100][100];
    public boolean isPlaying;
    public BufferedImage[][] explodeFrame = new BufferedImage[100][100];
    public static boolean[][] isExploded = new boolean[100][100]; // Kiểm tra xem vị trí đã bị bắn hỏng hay chưa
    public BufferedImage[][] smokeFrame = new BufferedImage[100][100];
    public static boolean[][] isBroken = new boolean[100][100];
    public static boolean[][] isFailedShot = new boolean[100][100];
    public boolean[][] isDrawed = new boolean[100][100];
    public Player1State player1State = new Player1State(this);
    public Player2State player2State = new Player2State(this);
    public static boolean screenWhenAddShip = true;
    public static int countPlayerPlacedDone;
    public void initClass(String mapName) {
        map.setMap(mapName);
        extraMethods.importExplodeAnimation();
        extraMethods.importFire();
        extraMethods.importSmoke();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public void render(Graphics g) {
        if (screenWhenAddShip){
            if (isPlaying){
                map.renderMap(g);
                shipManager.renderAllShip(g);
                extraMethods.renderExtraMethods(g);
            }
            else{
                if (GameState.state == GameState.PLAYER1) player2State.draw(g);
                else player1State.draw(g);
            }
        }
        else {
            map.renderMap(g);
            if (isPlaying) shipManager.renderAllShip(g);
            extraMethods.renderExtraMethods(g);
        }
    }


    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getTypeShip() {
        return typeShip;
    }

    public void setTypeShip(int typeShip) {
        this.typeShip = typeShip;
    }
}
