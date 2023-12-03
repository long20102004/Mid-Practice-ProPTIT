package Entities;

import Automatics.Bot;
import GameState.GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class Ship {

    private int xStartPosition; // Lưu vị trí các ô mà tàu đang nằm
    private int xEndPosition; // Lưu vị trí các ô mà tàu đang nằm
    private int yStartPosition; // Lưu vị trí các ô mà tàu đang nằm
    private int yEndPosition; // Lưu vị trí các ô mà tàu đang nằm
    private int size;
    private int height;
    private int width;
    private int HP;
    private Player player;
    private boolean isHorizontal = true; // Kiểm tra xem tàu có nằm ngang không
    public BufferedImage battleship; // Ảnh tàu nằm ngang
    public BufferedImage battleshipRotate; // Ảnh tàu nằm dọc
    public boolean placedDone; // Kiểm tra xem việc đặt tàu có thành công để thêm vào danh sách tàu
    public boolean[][] markHeadShip = new boolean[100][100]; // Đánh dấu ô chứa đầu tàu để xử lý vẽ hình

    public Ship(Player player, int size, boolean isHorizontal) {
        this.player = player;
        this.size = size;
        this.HP = size;
        this.isHorizontal = isHorizontal;
        if (isHorizontal) {
            width = size;
            height = 1;
        } else {
            height = size;
            width = 1;
        }
        battleship = ShipManager.getShip(size, isHorizontal);
    }



    private boolean isAvailbleToPlace(int xPos, int yPos) {  // Kiểm tra điều kiện đặt tàu
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (xPos + i + 1 > NUMBER_OF_SQUARE || yPos + j + 1 > NUMBER_OF_SQUARE || player.isPlaced[xPos + i][yPos + j] || player.isExploded[xPos + i][yPos + j] || player.isDrawed[xPos + i][yPos + j])
                    return false;
            }
        }
        return true;
    }


    public void placedBattleShip(int x, int y) {  // Đặt tàu
        if (isAvailbleToPlace(x, y)) {
            markHeadShip[x][y] = true;
            placedDone = true;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    player.isPlaced[x + i][y + j] = true;
                }
            }
            xStartPosition = x;
            xEndPosition = x + width - 1;
            yStartPosition = y;
            yEndPosition = y + height - 1;
            System.out.println(xStartPosition + " " + xEndPosition);
        } else {
            if (!player.isAuto)
                JOptionPane.showMessageDialog(player.gameWindow, "Vị trí không hợp lệ, vui lòng chọn lại!");
            System.out.println("K đặt được nữa");
        }
    }


    private boolean isAvailbleToDraw(int xPos, int yPos) {  // Kiểm tra điều kiện vẽ tàu
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!player.isPlaced[xPos + i][yPos + j] || player.isExploded[xPos + i][yPos + j]) return false;
            }
        }
        return true;
    }

    public void drawShip(Graphics g, int xPos, int yPos) {  // Vẽ tàu
        if (isAvailbleToDraw(xPos, yPos) && markHeadShip[xPos][yPos]) {
            g.drawImage(battleship, xPos * SQUARE_HEIGHT, yPos * SQUARE_WIDTH, SQUARE_WIDTH * width, SQUARE_HEIGHT * height, null);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    player.isDrawed[xPos + i][yPos + j] = true;
                }
            }
        }
    }


    public void attack(int x, int y, boolean showMessage) {  // Thuyền bị tấn công
        if (HP <= 0) {
            if (showMessage) JOptionPane.showMessageDialog(player.gameWindow.getJframe(), "Tàu đã nổ.");
            Player.changeTurn = true;
            return;
        }
        if (player.isBroken[x][y]) {
            Player.changeTurn = true;
            if (showMessage) JOptionPane.showMessageDialog(player.gameWindow.getJframe(), "Ô này đã bị bắn");
        }
        if (!player.isPlaced[x][y] || player.isExploded[x][y] || player.isBroken[x][y] || player.isFailedShot[x][y]) {
            System.out.println("Bắn xịt");
        } else {
            Player.changeTurn = true;
            player.isBroken[x][y] = true;
            HP--;
            if (HP <= 0) {
                player.numberExplodedShip++;
                if (player.numberExplodedShip == 5) {
                    player.isLost = true;
                }
                for (int i = xStartPosition; i <= xEndPosition; i++) {
                    for (int j = yStartPosition; j <= yEndPosition; j++) {
                        player.isExploded[i][j] = true;
                    }
                }
            }
        }
    }


    public void renderShip(Graphics g) {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                drawShip(g, i, j);
            }
        }
    }

    public int getxStartPosition() {
        return xStartPosition;
    }

    public int getxEndPosition() {
        return xEndPosition;
    }

    public int getyStartPosition() {
        return yStartPosition;
    }

    public int getyEndPosition() {
        return yEndPosition;
    }

}

