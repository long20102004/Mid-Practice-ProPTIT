package Main;

import Entities.Player;
import Inputs.KeyInputs;
import Inputs.KeyInputs2;
import Inputs.MouseInputs;
import Inputs.MouseInputs2;
import utilz.Utility;

public class Game implements Runnable{
    private Player player1;
    private Player player2;
    private final int FPS = 10;
    private final int UPS = 200;
    private GameWindow gameWindow1;
    private GameWindow gameWindow2;
    public Game(){
        initClass();
        startGameLoop();
    }

    private void initClass() {
        player1 = new Player();
        player1.setResource(Utility.backgroundImage1);
        player2 = new Player();
        player2.setResource(Utility.backgroundImage2);

        // Tạo cửa sổ và đọc đầu vào từ bàn phím và chuột
        gameWindow1 = new GameWindow(player1, "PLAYER 1");
        MouseInputs mouseInputs = new MouseInputs(player1, player2);
        KeyInputs keyInputs = new KeyInputs(player1, player2);
        player1.addMouseListener(mouseInputs);
        player1.addKeyListener(keyInputs);


        ;
        // Tạo cửa sổ và đọc đầu vào từ bàn phím và chuột
        gameWindow2 = new GameWindow(player2, "PLAYER 2");
        MouseInputs2 mouseInputs2 = new MouseInputs2(player1, player2);
        KeyInputs2 keyInputs2 = new KeyInputs2(player1, player2);
        player2.addMouseListener(mouseInputs2);
        player2.addKeyListener(keyInputs2);
    }

    public void startGameLoop(){
        Thread gameThread = new Thread(this);
        gameThread.start();
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
            if (deltaFrameTime >= 1){
                player1.repaint();
                player2.repaint();
                deltaFrameTime--;
                frames++;
            }
            if (deltaUpdateTime >= 1){
                deltaUpdateTime--;
                updates++;
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
