package com.mygdx.game;

import entity.NPC_Merchant;
import entity.NPC_OldMan;

public class AssetSetter {
    
    GamePanel gp;

    // Constructor
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
 // Method to set object items using factory pattern
    public void setObject() {
        for (int i = 0; i < gp.obj.length; i++) {
            ObjectFactory factory = new ObjectItemFactory(i, gp);
            gp.obj[i] = factory.createObject();
            // Set world coordinates for the object item
            gp.obj[i].worldX = getObjectWorldX(i);
            gp.obj[i].worldY = getObjectWorldY(i);
        }
    }

    private int getObjectWorldX(int index) {
        switch (index) {
            case 0:
                return 33 * gp.tileSize;
            case 1:
                return 33 * gp.tileSize;
            case 2:
                return 48 * gp.tileSize;
            case 3:
                return 47 * gp.tileSize;
            case 4:
                return 20 * gp.tileSize;
            case 5:
                return 57 * gp.tileSize;
            case 6:
                return 56 * gp.tileSize;
            case 7:
                return 58 * gp.tileSize;
            case 8:
                return 65 * gp.tileSize;
            default:
                return 0; 
        }
    }
    // Method to get world Y coordinate for object items
    private int getObjectWorldY(int index) {
        switch (index) {
            case 0:
                return 7 * gp.tileSize;
            case 1:
                return 40 * gp.tileSize;
            case 2:
                return 8 * gp.tileSize;
            case 3:
                return 42 * gp.tileSize;
            case 4:
                return 7 * gp.tileSize;
            case 5:
                return 9 * gp.tileSize;
            case 6:
                return 20 * gp.tileSize;
            case 7:
                return 43 * gp.tileSize;
            case 8:
                return 37 * gp.tileSize;
            default:
                return 0; // Handle unknown index
        }
    }
    
    public void setNPC() {
    	gp.npc[0] = new NPC_OldMan(gp);
    	gp.npc[0].worldX = gp.tileSize*31;
    	gp.npc[0].worldY = gp.tileSize*21;

        gp.npc[1] = new NPC_Merchant(gp);
        gp.npc[1].worldX = 20 * gp.tileSize;
        gp.npc[1].worldY = 11 * gp.tileSize;
    
        gp.npc[2] = new NPC_Merchant(gp);
        gp.npc[2].worldX = 18 * gp.tileSize;
        gp.npc[2].worldY = 22 * gp.tileSize;

        gp.npc[3] = new NPC_Merchant(gp);
        gp.npc[3].worldX = 22 * gp.tileSize;
        gp.npc[3].worldY = 22 * gp.tileSize;

    }
}
