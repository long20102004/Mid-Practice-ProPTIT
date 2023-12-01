package Main;
import Entities.PlayerManager;

import java.awt.*;

public class Game implements Runnable{
    private final int FPS = 6;
    private final int UPS = 20000;
    private PlayerManager playerManager = new PlayerManager(this);
    public Game(){
        startGameLoop();
    }
    public void startGameLoop(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
    public void update(){
        playerManager.update();
    }
    @Override
    public void run() {
        int frames = 0, updates = 0;
        Double deltaFrameTime = 0.0, deltaUpdateTime = 0.0;
        Double timePerFrame = 1000000000.0 / FPS;
        Double timePerUpdate = 1000000000.0 / UPS;
        long prevTime = System.nanoTime();
        long timeCheck = System.currentTimeMillis();
        while(true){
            long now = System.nanoTime();
            deltaFrameTime += (now - prevTime) / timePerFrame;
            deltaUpdateTime += (now - prevTime) / timePerUpdate;
            prevTime = now;
            if (deltaUpdateTime >= 1){
                update();
                deltaUpdateTime--;
                updates++;
            }
            if (deltaFrameTime >= 1){
                playerManager.getPlayer1().repaint();
                playerManager.getPlayer2().repaint();
                deltaFrameTime--;
                frames++;
            }
            if (System.currentTimeMillis() - timeCheck >= 1000){
                timeCheck = System.currentTimeMillis();
                System.out.println("FPS: " + FPS + " | UPS: " + UPS);
                frames = 0;
                updates = 0;
            }
        }
    }
}
