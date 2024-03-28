package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.mygdx.game.GamePanel;

public class SuperObject{
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public String description = "";

    public int value = 0;

    //Type
    public int type;
    public final int type_all = 0;
    public final int type_number = 1;
    public final int type_object = 2;
    
    
    public interface ObjectFactory {
        SuperObject createObject();
    }
    
    // Getter for type
    public int getType() {
        return type;
    }

    // Getter for type_all
    public int getTypeAll() {
        return type_all;
    }

    // Getter for type_number
    public int getTypeNumber() {
        return type_number;
    }

    // Getter for type_object
    public int getTypeObject() {
        return type_object;
    }


    public void draw(Graphics2D g2, GamePanel gp) {
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY +gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }
}
