package Main;

import Entities.Player;
import GameState.GameState;
import GameState.Player1State;
import GameState.Player2State;
import Inputs.KeyInputs;
import Inputs.MouseInputs;
import utilz.Utility;

public class Game implements Runnable{
    private Player player1;
    private Player player2;
    private final int FPS = 6;
    private final int UPS = 20000;
    private GameWindow gameWindow1;
    private GameWindow gameWindow2;
    public Player1State player1State;
    public Player2State player2State;
    public Game(){
        initClass();
        startGameLoop();
    }
    private void initClass() {
        player1 = new Player();
        player1.initClass(Utility.getRandomBackGround());
        player2 = new Player();
        player2.initClass(Utility.getRandomBackGround());


        gameWindow1 = new GameWindow(player1, "PLAYER 1", new KeyInputs(player1));
        MouseInputs mouseInputs = new MouseInputs(player1);
        player1.addMouseListener(mouseInputs);
        player1.addMouseMotionListener(mouseInputs);
        player1.addKeyListener(new KeyInputs(player1));
        gameWindow1.focus();
        player1State = new Player1State(player1);


        gameWindow2 = new GameWindow(player2, "PLAYER 2", new KeyInputs(player2));
        MouseInputs mouseInputs2 = new MouseInputs(player2);
        player2.addMouseListener(mouseInputs2);
        player2.addMouseMotionListener(mouseInputs2);
        player2.addKeyListener(new KeyInputs(player2));
        gameWindow2.focus();
        player2State = new Player2State(player2);
    }

    public void startGameLoop(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        if (GameState.state == GameState.PLAYER1){
            player1.isPlaying = true;
            player2.isPlaying = false;
        }
        else {
            player1.isPlaying = false;
            player2.isPlaying = true;
        }
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
                player1.repaint();
                player2.repaint();
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
