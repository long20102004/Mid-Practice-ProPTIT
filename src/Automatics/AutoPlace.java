package Automatics;

import Entities.PlayerManager;

import java.util.Random;

import static utilz.ConstantVariable.NUMBER_OF_SQUARE;

public class AutoPlace {
    private final PlayerManager playerManager;
    private final Random rnd = new Random();


    // Tự động đặt thuyền cho PLAYER1,2 và BOT

    public AutoPlace(PlayerManager playerManager){
        this.playerManager = playerManager;
    }


    public void autoAddPlayer1() {
        playerManager.getPlayer1().isAuto = true;
        while (playerManager.getPlayer1().getShipManager().shipsList.size() < 5) {
            int size = rnd.nextInt(1,5);
            int xPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            int yPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            boolean isHorizontal = rnd.nextBoolean();
            playerManager.getPlayer1().shipManager.addShip(size,xPosition,yPosition,isHorizontal);
        }
    }


    public void autoAddPlayer2() {
        playerManager.getPlayer2().isAuto = true;
        while (playerManager.getPlayer2().getShipManager().shipsList.size() < 5) {
            int size = rnd.nextInt(1,5);
            int xPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            int yPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            boolean isHorizontal = rnd.nextBoolean();
            playerManager.getPlayer2().shipManager.addShip(size,xPosition,yPosition,isHorizontal);
        }
    }

    public void autoAddBot(){
        playerManager.getBot().isAuto = true;
        while (playerManager.getBot().shipManager.shipsList.size() < 5){
            int size = rnd.nextInt(1,5);
            int xPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            int yPosition = rnd.nextInt(0, NUMBER_OF_SQUARE);
            boolean isHorizontal = rnd.nextBoolean();
            playerManager.getBot().shipManager.addShip(size,xPosition,yPosition,isHorizontal);
        }
    }
}
