package Entities;

import utilz.Utility;

import java.awt.*;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class Ship {
    private int size = 3;
    private int height = 3;
    private int width = 1;
    private int HP;
    private Player player;
    private boolean isHorizontal = true;

    public Ship(Player player) {
        this.player = player;
        HP = size;
    }

    public void setShip() {
        switch (size) {
            case 1:
                player.battleship = Utility.importImg(Utility.battleship1);  // 1x1
                player.battleshipRotate = Utility.importImg(Utility.battleship1);
                break;
            case 2:
                player.battleship = Utility.importImg(Utility.battleship2); // 1x2
                player.battleshipRotate = Utility.importImg(Utility.battleship2Rotate);
                break;
            case 3:
                player.battleship = Utility.importImg(Utility.battleship3); // 1x3
                player.battleshipRotate = Utility.importImg(Utility.battleship3Rotate);
                break;
            case 4:
                player.battleship = Utility.importImg(Utility.battleship4); // 1x4
                player.battleshipRotate = Utility.importImg(Utility.battleship4Rotate);

        }
    }
    public void attackBattleShip(int x, int y) {
        if (!player.isPlaced[x][y] || Player.isExploded[x][y]) {
            System.out.println("Bắn xịt");
        } else {
            System.out.println("T đang bắn");
            HP--;
            if (HP <= 0) {
                HP = 0;
                for (int i=0; i<width; i++){
                    for (int j=0; j<height; j++){
                        Player.isExploded[x+i][y+j] = true;
                    }
                }
            }
        }
    }


    public void placedBattleShip(int x, int y) {
        if (isAvailbleToPlace(x, y)) {
            System.out.println("T đã đặt");
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    player.isPlaced[x + i][y + j] = true;
                }
            }
        } else {
            System.out.println("Đ đặt được nữa");
        }
    }


    public void drawShip(Graphics g, int xPos, int yPos) {
        if (isAvailbleToDraw(xPos, yPos)){
            if (isHorizontal) g.drawImage(player.battleship, xPos * SQUARE_HEIGHT, yPos * SQUARE_WIDTH, SQUARE_WIDTH * width, SQUARE_HEIGHT * height, null);
            else g.drawImage(player.battleshipRotate, xPos * SQUARE_HEIGHT, yPos * SQUARE_WIDTH, SQUARE_WIDTH * width, SQUARE_HEIGHT * height, null);
        }
    }

    private boolean isAvailbleToDraw(int xPos, int yPos) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!player.isPlaced[xPos + i][yPos + j] || Player.isExploded[xPos + i][yPos + j]) return false;
            }
        }
        for (int i = 1; i < width; i++){
            if (xPos >= width - i && xPos + width <= SQUARE_WIDTH && player.isPlaced[xPos - width + i][yPos] && player.isPlaced[xPos + width][yPos]) return false;
        }
        for (int i=1; i<height; i++) {
            if (yPos >= height - i && yPos + height <= SQUARE_HEIGHT && player.isPlaced[xPos][yPos - height + i] && player.isPlaced[xPos][yPos + height])
                return false;
        }
        return true;
    }

    private boolean isAvailbleToPlace(int xPos, int yPos) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (player.isPlaced[xPos + i][yPos + j] || Player.isExploded[xPos + i][yPos + j]) return false;
            }
        }
        return true;
    }


    public void renderShip(Graphics g) {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                if (player.isPlaying) drawShip(g, i, j);
            }
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
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
}

