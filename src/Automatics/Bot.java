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
    public int x, y;
    public boolean[][] indexAttacked = new boolean[100][100];

    public Bot(PlayerManager playerManager) {
        super(playerManager);
        this.playerManager = playerManager;
        x = rnd.nextInt(0, NUMBER_OF_SQUARE);
        y = rnd.nextInt(0, NUMBER_OF_SQUARE);
    }

    // Chế độ tự bắn cho bot

    public void autoAttack() {
        boolean isHit = playerManager.getPlayer1().shipManager.attackShip(x, y, false);
        if (isHit){
            indexAttacked[x][y] = true;
        }
        else{
            x = rnd.nextInt(0,NUMBER_OF_SQUARE);
            y = rnd.nextInt(0,NUMBER_OF_SQUARE);
            while (indexAttacked[x][y]) {
                x = rnd.nextInt(0,NUMBER_OF_SQUARE);
                y = rnd.nextInt(0,NUMBER_OF_SQUARE);
            }
        }
        while (isHit) {
            if (isIndexValid(x, y + 1)) {
                y++;
            }
            else if (isIndexValid(x, y - 1)){
                y--;
            } else if (isIndexValid(x+1, y)) {
                x++;
            }
            else x--;
            isHit = playerManager.getPlayer1().shipManager.attackShip(x,y, false);
        }
    }

    public boolean isIndexValid(int xPos, int yPos) {
        if (xPos <= NUMBER_OF_SQUARE && yPos <= NUMBER_OF_SQUARE && xPos >= 0 && yPos >= 0) return true;
        return false;
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