package com.company.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJBoots extends SuperObject {

    public OBJBoots() {
        name = "Boots";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
