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

    // Mỗi player sẽ lấy dữ liệu map khác nhau
    public void setResource(String pictureName) {
        gameMap = new BufferedImage[NUMBER_OF_SQUARE][NUMBER_OF_SQUARE];
        BufferedImage img = Utility.importImg(pictureName);
        stick = Utility.importImg(Utility.stick).getSubimage(484,933, SQUARE_WIDTH,1);
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                gameMap[i][j] = img.getSubimage(j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT);
            }
        }
    }

    // Vẽ map
    public void drawMap(Graphics g) {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                g.drawImage(stick, j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT, null);
                g.drawImage(gameMap[i][j], j * SQUARE_WIDTH + 2, i * SQUARE_HEIGHT + 2, SQUARE_WIDTH, SQUARE_HEIGHT, null);
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

    public void placedBattleShip(int x, int y){
        if (isAvailble(x,y)){
            System.out.println("T đã đặt");
            isPlaced[x][y] = true;
            HP[x][y] = 5;
        }
        else{
            System.out.println("Đ đặt được nữa");
        }
    }

    public void attackBattleShip(int x,int y){
        if (!isPlaced[x][y]){
            // In ra màn hình là bắn xịt
            System.out.println("Bắn xịt");
        }
        else{
            System.out.println("T đang bắn");
            HP[x][y]--;
            if (HP[x][y] == 0){
                isExploded[x][y] = true;
                // Hiệu ứng thuyền biến mất
            }
        }
    }


    public void renderExplode(){
        BufferedImage image = Utility.importImg(Utility.explodeAni);
    }
    public void setIsPlaced(boolean[][] isPlaced) {
        this.isPlaced = isPlaced;
    }

    public void setIsExploded(boolean[][] isExploded) {
        this.isExploded = isExploded;
    }



    public void render(Graphics g){
        drawMap(g);
    }

}
