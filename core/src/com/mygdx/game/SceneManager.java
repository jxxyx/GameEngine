package com.mygdx.game;

import java.awt.Graphics2D;
import Scene.Scene;

public class SceneManager {
	Graphics2D g2;
    GamePanel gp;
    Scene currentScene;

    public SceneManager(GamePanel gp) {
        this.gp = gp;
        // Initialize your scenes here, e.g., currentScene = new MenuScene(gp);
        // Set initial scene based on game state
        // Example: setSceneBasedOnGameState(gp.gameState);
    }

    public void setScene(Scene scene) {
        this.currentScene = scene;
    }

    public void draw(Graphics2D g2) {
        currentScene.draw(g2);
    }
}