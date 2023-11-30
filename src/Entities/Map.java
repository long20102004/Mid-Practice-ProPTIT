package Entities;

import utilz.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.ConstantVariable.*;

public class Map {
    private Player player;
    public Map(Player player){
        this.player = player;
    }
    public void setMap(String pictureName) {
        player.gameMap = new BufferedImage[NUMBER_OF_SQUARE][NUMBER_OF_SQUARE];
        BufferedImage img = Utility.importImg(pictureName);
        player.setStick(Utility.importImg(Utility.stick).getSubimage(484, 933, SQUARE_WIDTH, 1));
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                player.gameMap[i][j] = img.getSubimage(j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT);
            }
        }
    }
    public void renderMap(Graphics g) {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARE; j++) {
                g.drawImage(player.getStick(), j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT, null);
                g.drawImage(player.gameMap[i][j], j * SQUARE_WIDTH + 2, i * SQUARE_HEIGHT + 2, SQUARE_WIDTH, SQUARE_HEIGHT, null);
            }
        }
    }

}
