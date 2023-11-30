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
    public boolean placedDone;
    public boolean[][] markHeadShip = new boolean[100][100];

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
        System.out.println("x: " + x + "y: " + y);
        if (!player.getIsPlaced()[x][y]) player.getIsFailedShot()[x][y] = true;
        if (!player.getIsPlaced()[x][y] || player.getIsExploded()[x][y]) {
            System.out.println("Bắn xịt");
        } else {
            System.out.println("T đang bắn");
            player.getIsBroken()[x][y] = true;
            HP--;
            if (HP <= 0) {
                HP = 0;
                for (int i=xStartPosition; i<xEndPosition; i++){
                    for (int j=yStartPosition; j<yEndPosition; j++){
                        player.getIsExploded()[i][j] = true;
                    }
                }
            }
        }
        if (player.getPlayerManager().isSwitchStatus()) player.getPlayerManager().updatePlayerState();
    }


    public void placedBattleShip(int x, int y) {
        if (isAvailbleToPlace(x, y)) {
            markHeadShip[x][y] = true;
            placedDone = true;
            System.out.println("T đã đặt: "  + size);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    player.getIsPlaced()[x + i][y + j] = true;
                }
            }
            xStartPosition = x;
            xEndPosition = x + width - 1;
            yStartPosition = y;
            yEndPosition = y + height - 1;
            System.out.println(xStartPosition + " " + xEndPosition);
        } else {
            System.out.println("Đ đặt được nữa");
        }
    }


    public void drawShip(Graphics g, int xPos, int yPos) {
        if (isAvailbleToDraw(xPos,yPos) && markHeadShip[xPos][yPos] ){
            g.drawImage(battleship, xPos * SQUARE_HEIGHT, yPos * SQUARE_WIDTH, SQUARE_WIDTH * width, SQUARE_HEIGHT * height, null);
            for (int i=0; i<width; i++){
                for (int j=0; j<height; j++){
                    player.getIsDrawed()[xPos+i][yPos+j] = true;
                }
            }
        }
    }

    private boolean isAvailbleToDraw(int xPos, int yPos) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!player.getIsPlaced()[xPos + i][yPos + j] || player.getIsExploded()[xPos + i][yPos + j] ) return false;
            }
        }
        return true;
    }

    private boolean isAvailbleToPlace(int xPos, int yPos) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (player.getIsPlaced()[xPos + i][yPos + j] || player.getIsExploded()[xPos + i][yPos + j] || player.getIsDrawed()[xPos + i][yPos + j]) return false;
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

