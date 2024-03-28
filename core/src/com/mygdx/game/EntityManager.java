package com.mygdx.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import entity.Entity;
import entity.Player;

public class EntityManager {

    private List<Entity> entityList;

    public EntityManager() {
        this.entityList = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public void drawAll(Graphics2D g2) {
        for (Entity entity : entityList) {
            entity.draw(g2);
        }
    }

    public void updateAll() {
        for (Entity entity : entityList) {
            entity.update();
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
    }
    // Add other methods as needed
}
