package com.mygdx.game;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import Scene.LeaderboardPanel;
import Scene.LevelPanel;
import Scene.MenuPanel;
import Scene.Scene;

public class SceneManager extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private GamePanel gp;
    private Scene currentScene;
    public MenuPanel menuPanel;
    public LevelPanel levelPanel;
    public LeaderboardPanel leaderboardPanel;
    public PlayerControl playerControl;

    public SceneManager(GamePanel gp, PlayerControl playerControl) {
        this.gp = gp;
        this.playerControl = playerControl;
        menuPanel = new MenuPanel(gp);
        levelPanel = new LevelPanel(gp);
        leaderboardPanel = new LeaderboardPanel(gp);
        currentScene = menuPanel; // Set the initial scene to the menu scene
        this.addKeyListener(playerControl);
    }
    
    public Scene getScene() {
    	return currentScene;
    }

    public void draw(Graphics2D g2) {
        currentScene.draw(g2);
    }
    

    public void setMenuScene() {
        currentScene = menuPanel;
    }

    public void setLevelScene() {
        currentScene = levelPanel;
    }

    public void setLeaderboardScene() {
        currentScene = leaderboardPanel;
    }
}

