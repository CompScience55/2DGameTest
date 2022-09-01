package com.company;

import com.company.entity.NPC;
import com.company.object.OBJBoots;
import com.company.object.OBJChest;
import com.company.object.OBJDoor;
import com.company.object.OBJKey;

public class ObjectSetter {

    GamePanel gp;

    public ObjectSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }

    public void setNPC() {
        gp.npcs[0] = new NPC(gp);
        gp.npcs[0].worldX = gp.tileSize * 33;
        gp.npcs[0].worldY = gp.tileSize * 24;

        gp.npcs[1] = new NPC(gp);
        gp.npcs[1].worldX = gp.tileSize * 33;
        gp.npcs[1].worldY = gp.tileSize * 27;

        gp.npcs[2] = new NPC(gp);
        gp.npcs[2].worldX = gp.tileSize * 35;
        gp.npcs[2].worldY = gp.tileSize * 29;

        gp.npcs[3] = new NPC(gp);
        gp.npcs[3].worldX = gp.tileSize * 31;
        gp.npcs[3].worldY = gp.tileSize * 27;
    }
}
