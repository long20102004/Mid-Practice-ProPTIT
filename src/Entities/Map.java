package Entities;

import utilz.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;

public class Map {
    private Player player;
    public BufferedImage[][] gameMap;
    private BufferedImage stick;
    public Map(Player player){
        this.player = player;
        stick = Utility.importImg(Utility.stick).getSubimage(484, 933, SQUARE_WIDTH, 1);
    }
    public void setMap(String pictureName) { // Đọc và tách map
        gameMap = new BufferedImage[NUMBER_OF_SQUARE][NUMBER_OF_SQUARE];
        BufferedImage img = Utility.importImg(pictureName);
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                gameMap[i][j] = img.getSubimage(j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT);
            }
        }
    }
    public void renderMap(Graphics g) { // Vẽ map
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                g.drawImage(stick, j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT, null);
                g.drawImage(gameMap[i][j], j * SQUARE_WIDTH + 2, i * SQUARE_HEIGHT + 2, SQUARE_WIDTH, SQUARE_HEIGHT, null);
            }
        }
    }

}
