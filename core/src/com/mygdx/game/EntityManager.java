package com.mygdx.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import entity.Entity;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import entity.Player;

public class EntityManager {
	GamePanel gp;
	AssetSetter aSetter;

    public List<Entity> entityList;

    public EntityManager(GamePanel gp) {
        this.entityList = new ArrayList<>();
        this.gp = gp;
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public void drawAll(Graphics2D g2) {
		// NPC
		for(int i = 0; i < gp.npc.length; i++) {
			if (gp.npc[i] != null) {
				gp.npc[i].draw(g2);
			}
		}

		// Player
		gp.player.draw(g2);
		
    }

    public void updateAll() {
    	
		gp.player.update();
		// NPC
		for (int i = 0; i < gp.npc.length; i++) {
			if (gp.npc[i] != null) {
				gp.npc[i].update();
			}
		}
    }
    
    public void clearAll() {
        for (Entity entity : entityList) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.setDefaultValues();
                player.resetDialogueIndex();
                player.inventory.clear();
                player.hasKey = 0;
            	}
            }
		gp.aSetter.setObject();
		gp.aSetter.setNPC();
    }
    
    public void resetAll() {
        for (Entity entity : entityList) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.setDefaultValues();
                player.resetDialogueIndex();
                player.inventory.clear();
                player.hasKey = 0;
            }
        }
		gp.aSetter.setObject();
		gp.aSetter.setNPC();
    }
}
