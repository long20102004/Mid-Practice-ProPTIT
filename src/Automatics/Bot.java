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
    private boolean isPotential;
    public static boolean isExploded;
    public int lastXIndex, lastYIndex, x, y;
    public boolean[][] indexAttacked = new boolean[100][100];

    public Bot(PlayerManager playerManager) {
        super(playerManager);
        this.playerManager = playerManager;
        x = rnd.nextInt(0, NUMBER_OF_SQUARE);
        y = rnd.nextInt(0, NUMBER_OF_SQUARE);
    }

    public boolean isIndexValid(int xPos, int yPos) {
        if (xPos <= IMAGE_WIDTH && yPos <= IMAGE_HEIGHT && xPos >= 0 && yPos >= 0) return true;
        return false;
    }

    public void autoAttack() {
        boolean isHit = playerManager.getPlayer1().shipManager.attackShip(x, y);
        if (isHit){
            isPotential = true;
            lastXIndex = x;
            lastYIndex = y;
        }
        if (isHit == false && isPotential){
            x = lastXIndex;
            y = lastYIndex;
        }
        else{
            x = rnd.nextInt(0, NUMBER_OF_SQUARE);
            y = rnd.nextInt(0, NUMBER_OF_SQUARE);
            while (indexAttacked[x][y]) {
                x = rnd.nextInt(0, NUMBER_OF_SQUARE);
                y = rnd.nextInt(0, NUMBER_OF_SQUARE);
            }
        }
        while (isHit) {
            if (isIndexValid(x, y + 1)) {
                y++;
            }
            else if (isIndexValid(x, y- 1)){
                y--;
            } else if (isIndexValid(x+1,y)) {
                x++;
            }
            else x--;
            isHit = playerManager.getPlayer1().shipManager.attackShip(x,y);
            indexAttacked[x][y] = true;
        }
    }

    public void setPotential(boolean potential){
        this.isPotential = potential;
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