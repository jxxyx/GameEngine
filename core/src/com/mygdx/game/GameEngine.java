package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
// import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.JFrame;

public class GameEngine extends ApplicationAdapter {;

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
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
