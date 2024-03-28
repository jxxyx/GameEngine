package com.mygdx.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Scene.LeaderboardPanel;
import Scene.LevelPanel;
import Scene.MenuPanel;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// World Settings
	public final int maxWorldCol = 70;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol; // 800 pixels
	public final int worldHeight = tileSize * maxWorldRow; // 800 pixels

	// For Fullscreen
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;

	// FPS
	int FPS = 60;
	
	// SYSTEM
	TileManager tileM = new TileManager(this);
	PlayerControl playerControl = new PlayerControl(this);
	customSound music = new customSound();
	customSound SE = new customSound();
	public CollisionManager cChecker = new CollisionManager(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public EntityManager entityManager = new EntityManager(this);
	public UI ui = new UI(this);
	public SceneManager sceneManager = new SceneManager(this, playerControl);
	public MenuPanel mp = new MenuPanel(this);
	public LeaderboardPanel lp = new LeaderboardPanel(this);
	public LevelPanel glp = new LevelPanel(this);
	Thread gameThread;

	// ENTITY OBJECT
	public Player player = new Player(this, playerControl);
	public SuperObject obj[] = new SuperObject[9];
	public Entity npc[] = new Entity[10];
	
	// Game state
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int leaderboardState = 2;
	public final int gameLevelState = 3;
	public final int pauseState = 4;
	public final int characterState= 5;
	public final int dialogueState = 6;
	public final int gameOverState = 7;
	public final int qnaState = 8;
	public int gameDifficulty = 0;
	public final int optionsState = 9;


	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(playerControl);
		this.setFocusable(true);
	}

	public TileManager getTileManager() {
		return tileM;
	}

	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		//playMusic(0);
		gameState = titleState;
		
		   // Add player to entity manager
	    entityManager.addEntity(player);
	    
        for (Entity npcEntity : npc) {
            if (npcEntity != null) {
                entityManager.addEntity(npcEntity);
            }
        }
		System.out.println(entityManager.entityList);
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) tempScreen.getGraphics();

		// setFullScreen();
	}
	
	public void resetGame() {
	    // Stop the current game thread if it's running
	    if (gameThread != null) {
	        gameThread.interrupt();
	    }

	    // Reset player variables and state
	    entityManager.resetAll();
	    
//	    player.setDefaultValues();
//	    player.resetDialogueIndex();
//	    player.inventory.clear();
//		aSetter.setObject();
//		aSetter.setNPC();

	    
		ui.resetUIVariables();
	}
	
	public void quitToMainMenu() {
		entityManager.clearAll();
//		aSetter.setObject();
//		aSetter.setNPC();
		
		ui.resetUIVariables();

	}

	
	public void setFullScreen() {
		// Get local screen size
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(GameEngine.window);

		// Get full screen width and height
		screenWidth2 = GameEngine.window.getWidth();
		screenHeight2 = GameEngine.window.getHeight();
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
				drawToTempScreen(); // draw everything to the buffered image
				drawToScreen(); // draw the buffered image to the screen
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
			// PLAYER
			entityManager.updateAll();		
//			player.update();
//			// NPC
//			for (int i = 0; i < npc.length; i++) {
//				if (npc[i] != null) {
//					npc[i].update();
//				}
//			}
}
		
		if(gameState == pauseState) {
			//nothing
		}
	}
	
	public void drawToTempScreen() {
		// TITLE SCREEN
		if(gameState == titleState) {
//			mp.draw(g2);
			sceneManager.setMenuScene();
			sceneManager.draw(g2);
		}
		else if(gameState == leaderboardState) {
//			lp.draw(g2);
			sceneManager.setLeaderboardScene();
			sceneManager.draw(g2);
		}
		else if(gameState == gameLevelState) {
//			glp.draw(g2);
			sceneManager.setLevelScene();
			sceneManager.draw(g2);
		}
		// OTHERS
		else {
			// This is like a layer
			tileM.draw(g2);

			// Object
			for(int i = 0; i <obj.length; i++){
				if(obj[i] !=null) {
					obj[i].draw(g2, this);
				}
			}
			
//			// NPC
//			for(int i = 0; i < npc.length; i++) {
//				if (npc[i] != null) {
//					npc[i].draw(g2);
//				}
//			}
//
//			// Player
//			player.draw(g2);
			// Player and NPC
			
			entityManager.drawAll(g2);
			
			// UI
			ui.draw(g2);
		}
	}

	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
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
