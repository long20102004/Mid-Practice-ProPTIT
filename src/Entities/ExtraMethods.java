package Entities;

import utilz.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;
import static utilz.ConstantVariable.SQUARE_WIDTH;

public class ExtraMethods {
    private Player player;
    private int xDrawPos, yDrawPos;
    private int xFire, yFire;
    private int xSmoke, ySmoke;
    BufferedImage[][] fire = new BufferedImage[100][100];
    public BufferedImage brokenFrame;
    public BufferedImage[][] explodeFrame = new BufferedImage[100][100];
    public boolean[][] explodedAnimation = new boolean[100][100];
    public BufferedImage[][] smokeFrame = new BufferedImage[100][100];
    public BufferedImage lostScreen;
    public BufferedImage victoryScreen;

    public ExtraMethods(Player player) {
        this.player = player;
    }

    public void importBroken() {
        brokenFrame = Utility.importImg(Utility.broken);
    }

    public void importFire() {  // Đọc và tách hiệu ứng ngọn lửa
        BufferedImage img = Utility.importImg(Utility.burnLeft);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                fire[i][j] = img.getSubimage(j * FIRE_SIZE_WIDTH, i * FIRE_SIZE_HEIGHT, FIRE_SIZE_WIDTH, FIRE_SIZE_HEIGHT);
            }
        }
    }

    public void importExplodeAnimation() {  // Đọc và tách hiệu ứng nổ
        BufferedImage img = Utility.importImg(Utility.explodeAni);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                explodeFrame[i][j] = img.getSubimage(j * EXPLODE_SIZE, i * EXPLODE_SIZE, EXPLODE_SIZE, EXPLODE_SIZE);
            }
        }
    }

    public void importSmoke() { // Đọc và tách hiệu ứng khói
        BufferedImage img = Utility.importImg(Utility.smokeAni);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                smokeFrame[i][j] = img.getSubimage(j * 250, i * 170, 250, 170);
            }
        }
    }
    public void importLostScreen(){
        lostScreen = Utility.importImg(Utility.defeated);
    }
    public void importVictoryScreen() {
        victoryScreen = Utility.importImg(Utility.victory);
    }
    public void drawExplode(Graphics g, int i, int j) { // Vẽ hiệu ứng nổ
        if (!explodedAnimation[i][j] && player.isExploded[i][j]) {
            if (xDrawPos < 9 && yDrawPos < 9) {
                drawExplodeAnimation(g, j, i, xDrawPos, yDrawPos);
                player.isBroken[i][j] = false;
            }
            xDrawPos++;
            if (xDrawPos >= 9) yDrawPos++;
            if (yDrawPos >= 9) {
                xDrawPos = 0;
                yDrawPos = 0;
                explodedAnimation[i][j] = true;
            }
        }
    }
    public void drawExplodeAnimation(Graphics g, int xPos, int yPos, int i, int j) {
        g.drawImage(explodeFrame[i][j], yPos * SQUARE_WIDTH, xPos * SQUARE_HEIGHT, EXPLODE_SIZE * 10 / (2 * NUMBER_OF_SQUARE), EXPLODE_SIZE * 10 / (2 * NUMBER_OF_SQUARE), null);
    }




    public void drawFire(Graphics g, int xPos, int yPos) { // Vẽ hiệu ứng cháy
        if (explodedAnimation[xPos][yPos]) {
            if (xFire < 4 && yFire < 2) {
                g.drawImage(fire[xFire][yFire], xPos * SQUARE_HEIGHT + SQUARE_HEIGHT / 4, yPos * SQUARE_WIDTH + SQUARE_WIDTH / 10, SQUARE_WIDTH / 2, SQUARE_HEIGHT - SQUARE_HEIGHT / 9, null); // Chưa xử lý được số liệu theo các hằng số
            }
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

    // Hiệu ứng khói khi bắn xịt



    public void drawSmoke(Graphics g, int i, int j) { // Vẽ hiệu ứng khói
        if (player.isFailedShot[i][j]) {
            if (xSmoke < 3 && ySmoke < 3) {
                g.drawImage(smokeFrame[xSmoke][ySmoke], i * SQUARE_HEIGHT, j * SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_HEIGHT * 170 / 250, null);
            }
            xSmoke++;
            if (xSmoke >= 3) {
                ySmoke++;
                xSmoke = 0;
            }
            if (ySmoke >= 3) {
                xSmoke = 0;
                ySmoke = 0;
            }
        }
    }
    public void drawChangeTurnBackground(Graphics g){ // Vẽ hiệu ứng chờ lượt trong khi đặt thuyền
        BufferedImage img = Utility.importImg(Utility.waitingBackground);
        g.drawImage(img, 0,0, NUMBER_OF_SQUARE * SQUARE_WIDTH, NUMBER_OF_SQUARE * SQUARE_HEIGHT , null);
    }


    public void drawCorrectShot(Graphics g, int xPos, int yPos){ // Vẽ hiệu ứng vỡ khi bắn trúng
        if (player.isBroken[xPos][yPos]) g.drawImage(brokenFrame, xPos * SQUARE_WIDTH, yPos*SQUARE_HEIGHT, SQUARE_WIDTH,SQUARE_HEIGHT, null);

    }

    public void drawLostScreen(Graphics g){
        g.drawImage(lostScreen, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null );
    }
    public void drawVictoryScreen(Graphics g){
        g.drawImage(victoryScreen, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }
    public void renderExtraMethods(Graphics g) {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                drawSmoke(g, i, j);
                drawCorrectShot(g,i,j);
                drawExplode(g, i, j);
                drawFire(g, i, j);
            }
        }
    }


}


