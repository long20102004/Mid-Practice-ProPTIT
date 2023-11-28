package Entities;

import Main.GameWindow;
import utilz.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;

public class Player extends JPanel {
    // Xử lý map cho mỗi người chơi
    private BufferedImage[][] gameMap;
    private BufferedImage stick;
    private boolean[][] explodedAnimation = new boolean[100][100];
    private boolean isPlaying;

    // Mỗi player sẽ lấy dữ liệu map khác nhau
    public void setResource(String pictureName) {
        gameMap = new BufferedImage[NUMBER_OF_SQUARE][NUMBER_OF_SQUARE];
        BufferedImage img = Utility.importImg(pictureName);
        stick = Utility.importImg(Utility.stick).getSubimage(484, 933, SQUARE_WIDTH, 1);
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                gameMap[i][j] = img.getSubimage(j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT);
            }
        }
        explodeAnimation();
        monster = Utility.importImg(Utility.monsterImage);
        importFire();
    }

    // Vẽ map
    private int xDrawPos = 0, yDrawPos = 0;

    public void drawMap(Graphics g) {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                g.drawImage(stick, j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT, null);
                g.drawImage(gameMap[i][j], j * SQUARE_WIDTH + 2, i * SQUARE_HEIGHT + 2, SQUARE_WIDTH, SQUARE_HEIGHT, null);
                if (isPlaying) drawMonster(g,i,j);
                drawExplode(g,i,j);
                drawFire(g,i,j);
            }
        }
    }


    // Vẽ hiệu ứng nổ
    public void drawExplode(Graphics g, int i, int j){
        if (!explodedAnimation[i][j] && isExploded[i][j]) {
            if (xDrawPos < 9 && yDrawPos < 9) drawExplodeAnimation(g, j, i, xDrawPos, yDrawPos);
            xDrawPos++;
            if (xDrawPos >= 9) yDrawPos++;
            if (yDrawPos >= 9) {
                xDrawPos = 0;
                yDrawPos = 0;
                explodedAnimation[i][j] = true;
            }
        }
    }

    // Xử lý Window cho mỗi người chơi
    // Hàm kế thừa để repaint sau mỗi lần sửa thông tin
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    // Thay đổi khi nhận được lệnh từ chuột hoặc bàn phím
    private boolean[][] isPlaced = new boolean[100][100]; // Kiểm tra xem vị trí đó đã được đặt tàu hay chưa
    private boolean[][] isExploded = new boolean[100][100]; // Kiểm tra xem vị trí đã bị bắn hỏng hay chưa
    private int[][] HP = new int[100][100];

    public boolean isAvailble(int x, int y) { // Lưu ý x,y truyền vào là số thứ tự của ô, không truyền tọa độ
        if (!isPlaced[x][y] && !isExploded[x][y]) return true;
        return false;
    }

    public void placedBattleShip(int x, int y) {
        if (isAvailble(x, y)) {
            System.out.println("T đã đặt");
            isPlaced[x][y] = true;
            HP[x][y] = 3;
        } else {
            System.out.println("Đ đặt được nữa");
        }
    }

    public void attackBattleShip(int x, int y) {
        if (!isPlaced[x][y] || isExploded[x][y]) {
            // In ra màn hình là bắn xịt
            System.out.println("Bắn xịt");
        } else {
            System.out.println("T đang bắn");
            HP[x][y]--;
            if (HP[x][y] <= 0) {
                HP[x][y] = 0;
                isExploded[x][y] = true;
                // Hiệu ứng thuyền biến mất
            }
        }
    }


    // Cài đặt hiệu ứng nổ:

    BufferedImage[][] explodeFrame = new BufferedImage[100][100];

    public void explodeAnimation() { // Tách ảnh hiệu ứng nổ thành các khung nhỏ 100x100;
        BufferedImage img = Utility.importImg(Utility.explodeAni);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                explodeFrame[i][j] = img.getSubimage(j * EXPLODE_SIZE, i * EXPLODE_SIZE, EXPLODE_SIZE, EXPLODE_SIZE);
            }
        }
    }

    public void drawExplodeAnimation(Graphics g, int xPos, int yPos, int i, int j) { // Vẽ nổ
        g.drawImage(explodeFrame[i][j], yPos * SQUARE_WIDTH, xPos * SQUARE_HEIGHT, EXPLODE_SIZE / 2, EXPLODE_SIZE / 2, null);
    }

    // Vẽ hiệu ứng ngọn lửa sau cháy
    BufferedImage[][] fire = new BufferedImage[100][100];
    public void importFire(){
        BufferedImage img = Utility.importImg(Utility.burnLeft);
        for (int i=0; i<2; i++){
            for (int j=0; j<4; j++){
                fire[i][j] = img.getSubimage(j*FIRE_SIZE_WIDTH, i*FIRE_SIZE_HEIGHT, FIRE_SIZE_WIDTH, FIRE_SIZE_HEIGHT);
            }
        }
    }

    private int xFire, yFire;
    public void drawFire(Graphics g, int xPos, int yPos){
        if (explodedAnimation[xPos][yPos]){
            if (xFire < 4 && yFire < 2) g.drawImage(fire[xFire][yFire], xPos * SQUARE_HEIGHT + 13, yPos * SQUARE_WIDTH + 6,27,48,null ); // Chưa xử lý được số liệu theo các hằng số
            xFire++;
            if (xFire >= 4){
                yFire++;
                xFire = 0;
            }
            if (yFire >= 2){
                xFire = 0;
                yFire = 0;
            }

        }
    }

    // Vẽ quái vật (tàu chiến)
    private BufferedImage monster;
    public void drawMonster(Graphics g, int xPos, int yPos){
        if (isPlaced[xPos][yPos] && !explodedAnimation[xPos][yPos]) g.drawImage(monster,xPos * SQUARE_HEIGHT, yPos * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT,null);

    }
    public void render(Graphics g) {
        drawMap(g);
    }


    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
