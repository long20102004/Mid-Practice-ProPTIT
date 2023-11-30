package Entities;

import GameState.GameState;
import utilz.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ShipManager {
    private Player player;

    public ShipManager(Player player) {
        this.player = player;
    }

    public boolean isAddShipDone;
    public ArrayList<Ship> shipsList = new ArrayList<>();

    public static BufferedImage getShip(int size, boolean isHorizontal) {
        switch (size) {
            case 1:
                return Utility.importImg(Utility.battleship1);
            case 2:
                if (isHorizontal) return Utility.importImg(Utility.battleship2);
                else return Utility.importImg(Utility.battleship2Rotate);
            case 3:
                if (isHorizontal) return Utility.importImg(Utility.battleship3);
                else return Utility.importImg(Utility.battleship3Rotate);
            case 4:
                if (isHorizontal) return Utility.importImg(Utility.battleship4);
                else return Utility.importImg(Utility.battleship4Rotate);
        }
        return null;
    }
    public void addShip(int size, int x, int y, boolean isHorizontal) {
        if (size == 0) {
            System.out.println("Chưa chọn loại tàu, chọn lại đi");
            return;
        }
        if (shipsList.size() >= 5) {
            System.out.println("Đã full tàu");
            return;
        }
        player.screenWhenAddShip = true;
        System.out.println("Tàu này là loại: " + size);
        Ship newShip = new Ship(player, size, isHorizontal);
        newShip.placedBattleShip(x, y);
        if (newShip.placedDone) shipsList.add(newShip);
        resetShipStatus();
        if (shipsList.size() == 5) {
            if (GameState.state == GameState.PLAYER1) GameState.state = GameState.PLAYER2;
            else GameState.state = GameState.PLAYER1;
            Player.countPlayerPlacedDone++;
            if (Player.countPlayerPlacedDone >= 2) Player.screenWhenAddShip = false;
        }
    }

    private void resetShipStatus() {
        player.isHorizontal = true;
        player.setTypeShip(0);
    }

    public void attackShip(int x, int y) {
        for (Ship ship : shipsList) {
            if (x >= ship.getxStartPosition() && x <= ship.getxEndPosition() && y >= ship.getyStartPosition() && y <= ship.getyEndPosition()) {
                ship.attack(x, y);
            }
        }
    }

    public void renderAllShip(Graphics g) {
        for (Ship ship : shipsList) {
            ship.renderShip(g);
        }
    }
}
