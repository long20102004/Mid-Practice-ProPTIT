package Entities;

import utilz.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ShipManager {
    private Player player;
    private PlayerManager playerManager;

    public ShipManager(PlayerManager playerManager, Player player) {
        this.playerManager = playerManager;
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
        if (shipsList.size() < 5) {
            System.out.println("Tàu này là loại: " + size);
            Ship newShip = new Ship(player, size, isHorizontal);
            newShip.placedBattleShip(x, y);
            if (newShip.placedDone) shipsList.add(newShip);
            resetShipStatus();
        }

        if (shipsList.size() == 5) {
            PlayerManager.setCountNumberPlayer(PlayerManager.getCountNumberPlayer() + 1);
            if (PlayerManager.getCountNumberPlayer() >= 2){
                playerManager.setCurrentPlayer(playerManager.getPlayer1());
                playerManager.setSwitchStatus(true);
            }
            playerManager.updatePlayerState();
        }
    }

    private void resetShipStatus() {
        player.setHorizontal(true);
        player.setTypeShip(0);
    }

    public void attackShip(int x, int y) {
        if (!playerManager.isSwitchStatus()) return;
        player.changeTurn = false;
        boolean isAttacked = false;
        for (Ship ship : shipsList) {
            if (x >= ship.getxStartPosition() && x <= ship.getxEndPosition() && y >= ship.getyStartPosition() && y <= ship.getyEndPosition()) {
                ship.attack(x, y);
                isAttacked = true;
                break;
            }
        }
        if (!isAttacked) player.isFailedShot[x][y] = true;
        if (player.getPlayerManager().isSwitchStatus() && !player.changeTurn) player.getPlayerManager().updatePlayerState();
    }

    public void renderAllShip(Graphics g) {
        for (Ship ship : shipsList) {
            ship.renderShip(g);
        }
    }
}
