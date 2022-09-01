package com.company.entity;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;
import java.util.Random;


public class NPC extends Entity{


    public NPC(GamePanel gp) {
        super(gp);
        this.gp = gp;

        direction = "down";
        speed = 1;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);

        getNPCImages();
    }

    public void getNPCImages() {

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/npc-up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/npc-up2.png")));

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/npc-down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/npc-down2.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/npc-left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/npc-left2.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/npc-right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/npc-right2.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 180) {
            Random random = new Random();
            int dir = random.nextInt(100) + 1;

            if (dir <= 25) {
                direction = "up";
            } else if (dir > 25 && dir <= 50) {
                direction = "left";
            } else if (dir > 50 && dir <= 75) {
                direction = "down";
            } else if (dir > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
