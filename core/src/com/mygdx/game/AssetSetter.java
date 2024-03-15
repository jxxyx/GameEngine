package com.mygdx.game;


import object.Obj_Chest;
import object.Obj_Door;
import object.Obj_key;
import entity.NPC_OldMan;
import object.Obj_Boots;

public class AssetSetter {
    
    GamePanel gp;

    // Constructor
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // Method
    public void setObject(){

        gp.obj[0] = new Obj_key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new Obj_key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new Obj_key();
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new Obj_Boots();
        gp.obj[3].worldX = 37 * gp.tileSize;
        gp.obj[3].worldY = 42 * gp.tileSize;

        gp.obj[4] = new Obj_Door();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 11 * gp.tileSize;

        gp.obj[5] = new Obj_Door();
        gp.obj[5].worldX = 8 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new Obj_Door();
        gp.obj[6].worldX = 12 * gp.tileSize;
        gp.obj[6].worldY = 22 * gp.tileSize;

        gp.obj[7] = new Obj_Chest();
        gp.obj[7].worldX = 10 * gp.tileSize;
        gp.obj[7].worldY = 7 * gp.tileSize;
    }
    public void setNPC() {
    	gp.npc[0] = new NPC_OldMan(gp);
    	gp.npc[0].worldX = gp.tileSize*21;
    	gp.npc[0].worldY = gp.tileSize*21;
    }
}
