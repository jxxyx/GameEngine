package com.mygdx.game;

import java.awt.Graphics2D;

import Scene.Scene;

public class SceneManager{
    private GamePanel gp;
    private Scene currentScene;
    public PlayerControl playerControl;

    public SceneManager(GamePanel gp, PlayerControl playerControl) {
        this.gp = gp;
        this.playerControl = playerControl;
        currentScene = gp.mp; // Set the initial scene to the menu scene
    }
    
    public Scene getScene() {
    	return currentScene;
    }

    public void draw(Graphics2D g2) {
        currentScene.draw(g2);
    }
    

    public void setMenuScene() {
        currentScene = gp.mp;
    }

    public void setLevelScene() {
        currentScene = gp.glp;
    }

    public void setLeaderboardScene() {
        currentScene = gp.lp;
    }
}

