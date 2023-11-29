package Entities;

import utilz.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.SQUARE_WIDTH;

public class ExtraMethods {
    private Player player;
    private int xDrawPos = 0, yDrawPos = 0;
    private int shipHeight, shipWidth;

    public ExtraMethods(Player player) {
        this.player = player;
        shipHeight = player.ship.getHeight();
        shipWidth = player.ship.getWidth();
    }

    public void drawExplode(Graphics g, int i, int j) {
        if (!player.explodedAnimation[i][j] && Player.isExploded[i][j]) {
            if (xDrawPos < 9 && yDrawPos < 9) drawExplodeAnimation(g, j, i, xDrawPos, yDrawPos);
            xDrawPos++;
            if (xDrawPos >= 9) yDrawPos++;
            if (yDrawPos >= 9) {
                xDrawPos = 0;
                yDrawPos = 0;
                player.explodedAnimation[i][j] = true;
            }
        }
    }


    public void importExplodeAnimation() {
        BufferedImage img = Utility.importImg(Utility.explodeAni);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                player.explodeFrame[i][j] = img.getSubimage(j * EXPLODE_SIZE, i * EXPLODE_SIZE, EXPLODE_SIZE, EXPLODE_SIZE);
            }
        }
    }

    public void drawExplodeAnimation(Graphics g, int xPos, int yPos, int i, int j) { // Vẽ nổ
        g.drawImage(player.explodeFrame[i][j], yPos * SQUARE_WIDTH, xPos * SQUARE_HEIGHT, EXPLODE_SIZE / 2, EXPLODE_SIZE / 2, null);
    }

    BufferedImage[][] fire = new BufferedImage[100][100];

    public void importFire() {
        BufferedImage img = Utility.importImg(Utility.burnLeft);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                fire[i][j] = img.getSubimage(j * FIRE_SIZE_WIDTH, i * FIRE_SIZE_HEIGHT, FIRE_SIZE_WIDTH, FIRE_SIZE_HEIGHT);
            }
        }
    }

    private int xFire, yFire;

    public void drawFire(Graphics g, int xPos, int yPos) {
        if (player.explodedAnimation[xPos][yPos]) {
            if (xFire < 4 && yFire < 2)
                g.drawImage(fire[xFire][yFire], xPos * SQUARE_HEIGHT + 13, yPos * SQUARE_WIDTH + 6, 27, 48, null); // Chưa xử lý được số liệu theo các hằng số
            xFire++;
            if (xFire >= 4) {
                yFire++;
                xFire = 0;
            }
            if (yFire >= 2) {
                xFire = 0;
                yFire = 0;
            }
        }
    }

    public void renderExtraMethods(Graphics g) {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                drawExplode(g, i, j);
                drawFire(g, i, j);
            }
        }
    }
}
