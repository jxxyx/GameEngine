package Scene;

import java.awt.Graphics2D;

import com.mygdx.game.GamePanel;

public abstract class Scene {
    protected GamePanel gp;
	Graphics2D g2;

    public Scene(GamePanel gp) {
        this.gp = gp;
    }

    public abstract void draw(Graphics2D g2);

    // Method to calculate x coordinate for centered text
    protected int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
