package com.mygdx.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import leaderboard.LeaderboardPanel;

public class PlayerControl implements KeyListener{
	
	GamePanel gp;
	MenuPanel mp;
	LeaderboardPanel lp;
	LevelPanel glp;
	UI ui;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

	
	public PlayerControl(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		//LEADERBOARD STATE
		else if(gp.gameState == gp.leaderboardState) {
			leaderboardState(code);
		} 
		
		else if(gp.gameState == gp.gameLevelState) {
			gameLevelState(code);
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			playState(code);
		} 
		else if (gp.gameState == gp.dialogueState){
			if(code == KeyEvent.VK_ENTER){
				gp.gameState = gp.playState;
			}
		}
		//character state
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		// Options state
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		//game over state
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
			
		}
	}

	public void titleState(int code) {
	    if(code == KeyEvent.VK_W) {
	        gp.mp.commandNum--;
	        if(gp.mp.commandNum < 0) {
	            gp.mp.commandNum = 2;
	        }
	    }
	    
	    if(code == KeyEvent.VK_S) {
	        gp.mp.commandNum++;
	        if(gp.mp.commandNum > 2) {
	            gp.mp.commandNum = 0;
	        }
	    }
	    
	    if(code == KeyEvent.VK_ENTER) {
	        if(gp.mp.commandNum == 0) {
	            gp.gameState = gp.gameLevelState;

	        }
	        if(gp.mp.commandNum == 1) {
	            gp.gameState = gp.leaderboardState;
	        }
	        if(gp.mp.commandNum == 2) {
	            System.exit(0);
	        }
	    }
	}
	
	public void leaderboardState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.titleState;
		}

	}
	
	public void gameLevelState(int code) {
		
	    if(code == KeyEvent.VK_W) {
	        gp.glp.commandNum--;
	        if(gp.glp.commandNum < 0) {
	            gp.glp.commandNum = 3;
	        }
	    }
	    
	    if(code == KeyEvent.VK_S) {
	        gp.glp.commandNum++;
	        if(gp.glp.commandNum > 3) {
	            gp.glp.commandNum = 0;
	        }
	    }
	    
	    if(code == KeyEvent.VK_ENTER) {
	        if(gp.glp.commandNum == 0) {
	            gp.gameState = gp.playState;
	            gp.gameDifficulty = 0;
	            gp.playMusic(0);
	        }
	        if(gp.glp.commandNum == 1) {
	            gp.gameState = gp.playState;
	            gp.gameDifficulty = 1;
	            gp.playMusic(0);
	        }		
	        if(gp.glp.commandNum == 2) {
	            gp.gameState = gp.playState;
	            gp.gameDifficulty = 2;
	            gp.playMusic(0);
	        }
	        
	        if(gp.glp.commandNum == 3) {
	            gp.gameState = gp.titleState;
	        }
	    }

	}

	public void playState(int code) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		// if(code == KeyEvent.VK_P) {
		// 	if(gp.gameState == gp.playState) {
		// 		gp.gameState = gp.pauseState;
		// 	}
		// }
		if(code == KeyEvent.VK_ENTER){
			enterPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.optionsState;
			}
		}
	
	}


	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}

		if(code == KeyEvent.VK_W) {
			if(gp.ui.slotRow != 0){
			gp.ui.slotRow--;
			gp.playSE(5);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.slotCol != 0){
				gp.ui.slotCol--;
				gp.playSE(5);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.slotRow != 3){
			gp.ui.slotRow++;
			gp.playSE(5);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.slotCol != 4){
			gp.ui.slotCol++;
			gp.playSE(5);
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
	}
	
	public void gameOverState(int code) {
	    if(code == KeyEvent.VK_W) {
	        gp.ui.commandNum--;
	        if(gp.ui.commandNum < 0) {
	            gp.ui.commandNum = 1;
	        }
	    }
	    
	    if(code == KeyEvent.VK_S) {
	        gp.ui.commandNum++;
	        if(gp.ui.commandNum > 1) {
	            gp.ui.commandNum = 0;
	        }
	    }
	    
	    if(code == KeyEvent.VK_ENTER) {
	        if(gp.ui.commandNum == 0) {
        		gp.resetGame();
	            gp.gameState = gp.gameLevelState;
	        }
	        if(gp.ui.commandNum == 1) {
	        	gp.quitToMainMenu();
	            gp.gameState = gp.titleState;
	        }		
	    }
	}

	public void optionsState(int code) {
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
			
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		int maxCommandNum = 0;
		switch(gp.ui.subState){
			case 0 : maxCommandNum = 5; break;
			case 3 : maxCommandNum = 1; break;
		}

		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(5);
			if(gp.ui.commandNum < 0){
				gp.ui.commandNum = maxCommandNum;
			}
		}

		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(5);
			if(gp.ui.commandNum > maxCommandNum){
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A){
			if(gp.ui.subState == 0){
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(5);
				}
				if(gp.ui.subState == 0){
					if(gp.ui.commandNum == 2 && gp.SE.volumeScale > 0){
						gp.SE.volumeScale--;
						gp.playSE(5);
					}
				}
			}
		}
		if(code == KeyEvent.VK_D){
			if(gp.ui.subState == 0){
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(5);
				}
				if(gp.ui.commandNum == 2 && gp.SE.volumeScale < 5){
					gp.SE.volumeScale++;
					gp.playSE(5);
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_ENTER){
			enterPressed = false;
		}
	}

}
