package com.mygdx.game;


import object.Obj_Chest;
import object.Obj_Door;
import object.Obj_key;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import object.Obj_16;
import object.Obj_27;
import object.Obj_3;
import object.Obj_420;
import object.Obj_5;
import object.Obj_56;
import object.Obj_8;
import object.Obj_Boots;

public class AssetSetter {
    
    GamePanel gp;

    // Constructor
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // Method
    public void setObject(){

        gp.obj[0] = new Obj_16();
        gp.obj[0].worldX = 33 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        

        gp.obj[1] = new Obj_56();
        gp.obj[1].worldX = 33 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new Obj_27();
        gp.obj[2].worldX = 48 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new Obj_Boots();
        gp.obj[3].worldX = 47 * gp.tileSize;
        gp.obj[3].worldY = 42 * gp.tileSize;

        // gp.obj[4] = new Obj_Door();
        // gp.obj[4].worldX = 20 * gp.tileSize;
        // gp.obj[4].worldY = 11 * gp.tileSize;

        // gp.obj[5] = new Obj_Door();
        // gp.obj[5].worldX = 18 * gp.tileSize;
        // gp.obj[5].worldY = 22 * gp.tileSize;

        // gp.obj[6] = new Obj_Door();
        // gp.obj[6].worldX = 22 * gp.tileSize;
        // gp.obj[6].worldY = 22 * gp.tileSize;

        gp.obj[7] = new Obj_Chest();
        gp.obj[7].worldX = 20 * gp.tileSize;
        gp.obj[7].worldY = 7 * gp.tileSize;

        gp.obj[8] = new Obj_3();
        gp.obj[8].worldX = 57 * gp.tileSize;
        gp.obj[8].worldY = 9 * gp.tileSize;

        gp.obj[9] = new Obj_5();
        gp.obj[9].worldX = 56 * gp.tileSize;
        gp.obj[9].worldY = 20 * gp.tileSize;

        gp.obj[10] = new Obj_8();
        gp.obj[10].worldX = 58 * gp.tileSize;
        gp.obj[10].worldY = 43 * gp.tileSize;

        gp.obj[11] = new Obj_420();
        gp.obj[11].worldX = 65 * gp.tileSize;
        gp.obj[11].worldY = 37 * gp.tileSize;
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
