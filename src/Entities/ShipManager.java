package Entities;

import Automatics.Bot;
import GameState.GameMode;
import GameState.GameState;
import utilz.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ShipManager {
    private Player player;
    private PlayerManager playerManager;
    public int countSize1Ship, countSize2Ship, countSize3Ship, countSize4Ship;  // Đếm số tàu mỗi loại
    public ShipManager(PlayerManager playerManager, Player player) {
        this.playerManager = playerManager;
        this.player = player;
    }
    public ArrayList<Ship> shipsList = new ArrayList<>();

    public static BufferedImage getShip(int size, boolean isHorizontal) {  // Lấy ảnh tàu tương ứng với size
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
    public boolean isSizeValid(int x){ // Kiểm tra size tàu
        if (x == 0 || x > 4) return false;
        if (x == 1 && countSize1Ship > 1) return false;
        if (x == 2 && countSize2Ship > 0) return false;
        if (x == 3 && countSize3Ship > 0) return false;
        if (x == 4 && countSize4Ship > 0) return false;
        return true;
    }

    public void addShip(int size, int x, int y, boolean isHorizontal) {  // Thêm tàu vào list
        if (!isSizeValid(size)) {
            if (!player.isAuto) JOptionPane.showMessageDialog(player.gameWindow.getJframe(), "Tàu không hợp lệ. Vui lòng đặt lại");
            return;
        }
        if (shipsList.size() < 5) {
            System.out.println("Tàu này là loại: " + size);
            Ship newShip = new Ship(player, size, isHorizontal);
            newShip.placedBattleShip(x, y);
            if (newShip.placedDone) {
                shipsList.add(newShip);
                if (size == 1) countSize1Ship++;
                if (size == 2) countSize2Ship++;
                if (size == 3) countSize3Ship++;
                if (size == 4) countSize4Ship++;
            }
            resetShipStatus();
        }

        if (shipsList.size() == 5) {
            JOptionPane.showMessageDialog(player.gameWindow, "Đã đặt xong!");
            PlayerManager.setCountNumberPlayer(PlayerManager.getCountNumberPlayer() + 1);
            if (PlayerManager.getCountNumberPlayer() == 1)
                if (GameMode.gameMode == GameMode.PVE) playerManager.getAutoPlace().autoAddBot();
            if (PlayerManager.getCountNumberPlayer() >= 2) {
                playerManager.setCurrentPlayer(playerManager.getPlayer1());
                playerManager.setSwitchStatus(true);
            }
            playerManager.updatePlayerState();
        }
    }

    private void resetShipStatus() {  // Cài lại trạng thái tàu khi đặt xong
        player.setHorizontal(true);
        player.setTypeShip(0);
    }

    public boolean attackShip(int x,int y, boolean showMesseage) {  // Tấn công tàu trong list
        if (!playerManager.isSwitchStatus()) return false;
        Player.changeTurn = false;
        boolean isAttacked = false;
        for (Ship ship : shipsList) {
            if (x >= ship.getxStartPosition() && x <= ship.getxEndPosition() && y >= ship.getyStartPosition() && y <= ship.getyEndPosition()) {
                ship.attack(x, y, showMesseage);
                isAttacked = true;
                break;
            }
        }
        if (!isAttacked) player.isFailedShot[x][y] = true;
        if (!Player.changeTurn && player.getPlayerManager().isSwitchStatus())
            player.getPlayerManager().updatePlayerState();
        return Player.changeTurn;
    }

    public void renderAllShip(Graphics g) { // Vẽ toàn bộ tàu
        for (Ship ship : shipsList) {
            ship.renderShip(g);
        }
    }
}
