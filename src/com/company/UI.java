package com.company;

import com.company.object.OBJKey;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial40;
    Font arial80;
    Graphics2D g2;


    public boolean messageOn = false;
    public String message = "";

    int messageCounter = 0;

    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 20);
        arial80 = new Font("Arial", Font.PLAIN, 80);
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial80);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState) {
            //Do playstate stuff
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeigth / 2;
        g2.drawString(text,x,y);
    }

    public int getXForCenteredText(String s) {
        int x;
        int length = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        x = (gp.screenWidth/2) - (length / 2);
        return x;
    }

}
