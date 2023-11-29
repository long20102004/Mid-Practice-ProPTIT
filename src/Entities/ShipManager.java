package Entities;

import java.awt.*;
import java.util.ArrayList;

public class ShipManager {
    private Player player;

    public ShipManager(Player player) {
        this.player = player;
    }

    private ArrayList<Ship> shipsList = new ArrayList<>();

    public void addShip(int size, int x, int y, boolean isHorizontal) {
        if (size == 0) System.out.println("Chưa chọn loại tàu, đặt lại");
        else {
            System.out.println("Tàu này là loại: " + size);
            Ship newShip = new Ship(player, size, isHorizontal);
            newShip.placedBattleShip(x, y);
            shipsList.add(newShip);
            resetShipStatus();
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
