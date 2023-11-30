package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class Ship {
    private int xStartPosition; // Lưu số ô hàng ngang mà tàu này đang nằm
    private int xEndPosition;
    private int yStartPosition;
    private int yEndPosition;
    private int size;
    private int height;
    private int width;
    private int HP;
    private Player player;
    private boolean isHorizontal = true;
    public BufferedImage battleship;
    public BufferedImage battleshipRotate;
    public boolean[][] isPlaced = new boolean[100][100]; // Kiểm tra xem vị trí đó đã được đặt tàu hay chưa
    public boolean placedDone;

    public Ship(Player player, int size, boolean isHorizontal) {
        this.player = player;
        this.size = size;
        HP = size;
        this.isHorizontal = isHorizontal;
        if (isHorizontal){
            width = size;
            height = 1;
        }
        else{
            height = size;
            width = 1;
        }
        battleship = ShipManager.getShip(size, isHorizontal);
    }


    public void attack(int x, int y) {
        if (!isPlaced[x][y] || Player.isExploded[x][y]) {
            System.out.println("Bắn xịt");
        } else {
            System.out.println("T đang bắn");
            Player.isBroken[x][y] = true;
            HP--;
            if (HP <= 0) {
                HP = 0;
                for (int i=xStartPosition; i<xEndPosition; i++){
                    for (int j=yStartPosition; j<yEndPosition; j++){
                        Player.isExploded[i][j] = true;
                    }
                }
            }
        }
    }


    public void placedBattleShip(int x, int y) {
        if (isAvailbleToPlace(x, y)) {
            placedDone = true;
            System.out.println("T đã đặt: "  + size);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    isPlaced[x + i][y + j] = true;
                }
            }
            xStartPosition = x;
            xEndPosition = x + width;
            yStartPosition = y;
            yEndPosition = y + height;
        } else {
            System.out.println("Đ đặt được nữa");
        }
    }


    public void drawShip(Graphics g, int xPos, int yPos) {
        if (isAvailbleToDraw(xPos, yPos)){
            g.drawImage(battleship, xPos * SQUARE_HEIGHT, yPos * SQUARE_WIDTH, SQUARE_WIDTH * width, SQUARE_HEIGHT * height, null);
            for (int i=0; i<width; i++){
                for (int j=0; j<height; j++){
                    player.isDrawed[xPos+i][yPos+j] = true;
                }
            }
        }
    }

    private boolean isAvailbleToDraw(int xPos, int yPos) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!isPlaced[xPos + i][yPos + j] || Player.isExploded[xPos + i][yPos + j] ) return false;
            }
        }
        for (int i = 1; i < width; i++){
            if (xPos >= width - i && xPos + width <= SQUARE_WIDTH && isPlaced[xPos - width + i][yPos] && isPlaced[xPos + width][yPos]) return false;
        }
        for (int i=1; i<height; i++) {
            if (yPos >= height - i && yPos + height <= SQUARE_HEIGHT && isPlaced[xPos][yPos - height + i] && isPlaced[xPos][yPos + height])
                return false;
        }
        return true;
    }

    private boolean isAvailbleToPlace(int xPos, int yPos) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (isPlaced[xPos + i][yPos + j] || Player.isExploded[xPos + i][yPos + j] || player.isDrawed[xPos + i][yPos + j]) return false;
            }
        }
        return true;
    }


    public void renderShip(Graphics g) {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                drawShip(g, i, j);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
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

