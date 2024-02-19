package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
// import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.JFrame;

public class GameEngine extends ApplicationAdapter {
//    private SpriteBatch batch;
//    private ShapeRenderer shapeRenderer;
//    // private EntityManager entityManager;
//    private GamePanel gamePanel; // Add reference to GamePanel
//    
//    @Override
//    public void create() {
//        batch = new SpriteBatch();
//        shapeRenderer = new ShapeRenderer();
//    }
//    
//    @Override
//    public void render() {
//
//        // Request focus for the GamePanel
//        gamePanel.requestFocus();
//
//        // Render using LibGDX components if needed
//        batch.begin();
//        // Your rendering logic using batch or shapeRenderer here
//        batch.end();
//    }
//    
//    @Override
//    public void dispose() {
//        batch.dispose();
//        shapeRenderer.dispose();
//    }
    
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PokeFight");
        
        GamePanel gamePanel = new GamePanel(); // Initialize GamePanel
        // Add GamePanel to the window's content pane
        window.getContentPane().add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.startGameThread();
    }
}
