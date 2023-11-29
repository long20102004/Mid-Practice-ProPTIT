package Entities;

import GameState.GameState;
import GameState.Menu;
import GameState.Playing;
import Main.GameWindow;
import utilz.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;

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
    public BufferedImage battleship;
    public BufferedImage battleshipRotate;
    public boolean[][] isPlaced = new boolean[100][100]; // Kiểm tra xem vị trí đó đã được đặt tàu hay chưa
    public static boolean[][] isExploded = new boolean[100][100]; // Kiểm tra xem vị trí đã bị bắn hỏng hay chưa


    public void initClass(String mapName) {
        map.setMap(mapName);
        extraMethods.importExplodeAnimation();
        extraMethods.importFire();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public void render(Graphics g) {
        map.renderMap(g);
        shipManager.renderAllShip(g);
        extraMethods.renderExtraMethods(g);
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
