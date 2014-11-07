package GameObject;

import javax.swing.*;

/**
 * Created by danielmacario on 14-11-06.
 */
public class Flame extends StaticObject {

    public Flame(int posX, int posY, boolean visible) {
        this.posX = posX;
        this.posY = posY;
        this.previousX = posX;
        this.visible = visible;
        this.image = new ImageIcon(this.getClass().getResource("../resources/flames.png")).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }
}