package com.company.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJChest extends SuperObject{

    public OBJChest() {
        name = "Treasure Chest";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
