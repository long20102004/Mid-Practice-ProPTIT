package Automatics;

import Entities.PlayerManager;

import java.util.Random;

import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class AutoPlace {
    private final PlayerManager playerManager;
    private final Random rnd = new Random();
    public AutoPlace(PlayerManager playerManager){
        this.playerManager = playerManager;
    }
    public void autoAddPlayer1() {
        while (playerManager.getPlayer1().getShipManager().shipsList.size() < 5) {
            int size = rnd.nextInt(1,5);
            int xPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            int yPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            boolean isHorizontal = rnd.nextBoolean();
            playerManager.player1.shipManager.addShip(size,xPosition,yPosition,isHorizontal);
        }
    }
    public void autoAddPlayer2() {
        while (playerManager.getPlayer2().getShipManager().shipsList.size() < 5) {
            int size = rnd.nextInt(1,4);
            int xPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            int yPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            boolean isHorizontal = rnd.nextBoolean();
            playerManager.player2.shipManager.addShip(size,xPosition,yPosition,isHorizontal);
        }
    }
}
