package Automatics;

import Entities.Player;
import Entities.PlayerManager;
import Entities.Ship;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static utilz.ConstantVariable.*;

public class Bot extends Player {
    private PlayerManager playerManager;
    private final Random rnd = new Random();
    public int lastXIndex, lastYIndex;

    public Bot(PlayerManager playerManager) {
        super(playerManager);
        this.playerManager = playerManager;
    }

    public boolean isIndexValid(int xPos, int yPos) {
        if (xPos <= IMAGE_WIDTH && yPos <= IMAGE_HEIGHT && xPos >= 0 && yPos >= 0) return true;
        return false;
    }

    public void autoAttack() {
        int x = rnd.nextInt(0, NUMBER_OF_SQUARE);
        int y = rnd.nextInt(0, NUMBER_OF_SQUARE);
        boolean isHit = playerManager.getPlayer1().shipManager.attackShip(x, y);
        while (isHit) {
            if (isIndexValid(lastXIndex, lastYIndex + 1)) y++;
            else if (isIndexValid(lastXIndex + 1, lastYIndex)) x++;
            else if (isIndexValid(lastXIndex - 1, lastYIndex))  x--;
            else  y--;
            isHit = playerManager.getPlayer1().shipManager.attackShip(x,y);
        }
//        if (!isLastShotCorrect) {
//            int x = rnd.nextInt(0, NUMBER_OF_SQUARE);
//            int y = rnd.nextInt(0, NUMBER_OF_SQUARE);
//            playerManager.getPlayer1().shipManager.attackShip(x, y);
//        }
//        if (isLastShotCorrect){
//
//        }
    }


    @Override
    public void render(Graphics g) {
        if (isLost) {
            extraMethods.drawLostScreen(g);
        } else if (isVictory) {
            extraMethods.drawVictoryScreen(g);
        } else {
            map.renderMap(g);
//            shipManager.renderAllShip(g);
            extraMethods.renderExtraMethods(g);
        }
    }
}