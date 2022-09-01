package com.company;

import com.company.entity.Entity;
import com.company.entity.Player;
import com.company.object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTileSize = 16; //16x16 Tiles (Pixels)
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn;  //768
    public final int screenHeigth = tileSize * maxScreenRow; //576

    //World Settings
    public final int maxWorldCol = 69;
    public final int maxWorldRow = 69;


    //FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker checker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public UI ui = new UI(this);

    //Sound
    public Sound music = new Sound();
    public Sound soundEffect = new Sound();

    //Thread
    Thread gameThread;

    //Objects
    public ObjectSetter obSetter = new ObjectSetter(this);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npcs[] = new Entity[10];

    //Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {

        obSetter.setObject();
        obSetter.setNPC();
        playMusic(0);
        stopMusic();
        gameState = playState;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //Game Loop
        double drawInterval = 1000000000 / FPS; //0.016666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null) {

            //Restriction (FPS)

            //1. Update Information (Character position)
            update();
            //2. Draw the Screen with the updated information
            repaint();

            //How much time is left until next draw time
            //sleep until done
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //converting nano seconds in milli seconds

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();

            for (int i = 0; i < npcs.length; i++) {
                if (npcs[i] != null) {
                    npcs[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            //do something -- pause screen
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        //DEBUGG
        long drawStart = 0;
        drawStart = System.nanoTime();


        //Tile back of player
        tileManager.draw(g2);

        //Object
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        //NPCs
        for (int i = 0; i < npcs.length; i++) {
            if (npcs[i] != null) {
                npcs[i].draw(g2);
            }
        }

        player.draw(g2);
        ui.draw(g2);


        g2.dispose();

    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }

    public int getTileSize() {
        return tileSize;
    }
}
