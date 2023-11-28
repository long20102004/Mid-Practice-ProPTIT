package Entities;

import utilz.Utility;

import java.awt.*;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class Ship {
    private Player player;
    public Ship(Player player){
        this.player = player;
    }
    public void setShip(){
        player.monster = Utility.importImg(Utility.monsterImage);
    }
    public void drawMonster(Graphics g, int xPos, int yPos){
        if (player.isPlaced[xPos][yPos] && !player.explodedAnimation[xPos][yPos]) g.drawImage(player.monster,xPos * SQUARE_HEIGHT, yPos * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT,null);

    }

    public boolean isAvailble(int x, int y) {
        if (!player.isPlaced[x][y] && !player.isExploded[x][y]) return true;
        return false;
    }

    public void placedBattleShip(int x, int y) {
        if (isAvailble(x, y)) {
            System.out.println("T đã đặt");
            player.isPlaced[x][y] = true;
            player.HP[x][y] = 3;
        } else {
            System.out.println("Đ đặt được nữa");
        }
    }

    public void attackBattleShip(int x, int y) {
        if (!player.isPlaced[x][y] || Player.isExploded[x][y]) {
            System.out.println("Bắn xịt");
        } else {
            System.out.println("T đang bắn");
            player.HP[x][y]--;
            if (player.HP[x][y] <= 0) {
                player.HP[x][y] = 0;
                player.isExploded[x][y] = true;
            }
        }
    }

    public void renderShip(Graphics g) {
        for (int i=0; i<NUMBER_OF_SQUARE; i++){
            for (int j=0; j<NUMBER_OF_SQUARE; j++){
                if (player.isPlaying) drawMonster(g,i,j);
            }
        }
    }
}
