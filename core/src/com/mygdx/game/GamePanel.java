package com.mygdx.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol; // 800 pixels
	public final int worldHeight = tileSize * maxWorldRow; // 800 pixels

	// FPS
	int FPS = 60;
	
	// SYSTEM
	TileManager tileM = new TileManager(this);
	PlayerControl playerControl = new PlayerControl();
	customSound music = new customSound();
	customSound SE = new customSound();
	public CollisionManager cChecker = new CollisionManager(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;

	// ENTITY OBJECT
	public Player player = new Player(this, playerControl);
	public SuperObject obj[] = new SuperObject[10];
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(playerControl);
		this.setFocusable(true);
	}

	public void setupGame() {
		
		aSetter.setObject();
		
		playMusic(0);
	}


	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.01666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >=1 ) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		if(gameState == playState) {
			player.update();
		}
		if(gameState == pauseState) {
			//nothing
		}

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		Graphics2D g2 = (Graphics2D)g;
		
		// TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		}
		// OTHERS
		else {
			// This is like a layer
			// Tile
			tileM.draw(g2);

			// Object
			for(int i = 0; i <obj.length; i++){
				if(obj[i] !=null) {
					obj[i].draw(g2, this);
				}
			}

			// Player
			player.draw(g2);
			
			// UI
			ui.draw(g2);
		}
		

		
		g2.dispose();
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		SE.setFile(i);
		SE.play();
	}
}
