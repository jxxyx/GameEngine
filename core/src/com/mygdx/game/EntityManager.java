package com.mygdx.game;

import java.util.List;
import javax.swing.text.html.parser.Entity;

public class EntityManager { 

    private List<Entity> entityList;

    public EntityManager(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public void draw() {
        // ... (existing code)
    }

    public void updateAll() {
        // ... (existing code)
    }

    public void moveAll() {
        // ... (existing code)
    }
}