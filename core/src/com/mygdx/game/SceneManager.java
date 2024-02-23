package com.mygdx.game;

import java.awt.Graphics2D;

public abstract class SceneManager {
    protected GamePanel gp;

    public SceneManager(GamePanel gp) {
        this.gp = gp;
    }
    
    public abstract void draw(Graphics2D g2);
    // Other common methods for scene management can go here...
}
