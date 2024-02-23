package com.mygdx.game;

import java.awt.Graphics2D;
import java.util.List;
import entity.Entity;

public class EntityManager { 

    private List<Entity> entityList;

    public EntityManager(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public void draw(Graphics2D g2) {
        for (Entity entity : entityList) {
            entity.draw(g2);
        }
    }

    public void updateAll() {
        for (Entity entity : entityList) {
            entity.update();
        }
    }

    public void moveAll() {
        // ... (existing code)
    }
}