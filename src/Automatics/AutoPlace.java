package Automatics;

import Entities.PlayerManager;

import java.util.Random;

public class AutoPlace {
    private PlayerManager playerManager;
    Random rnd = new Random();
    public AutoPlace(PlayerManager playerManager){
        this.playerManager = playerManager;
    }
    public void autoAddPlayer1(){
        while (playerManager.getPlayer1().getShipManager().shipsList.size() < 5){

        }
    }
}
