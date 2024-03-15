package com.mygdx.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerControl implements KeyListener{
	
	GamePanel gp;
	MenuPanel mp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
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
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			playState(code);
		} 
		//pause state
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		} 

		//character state
		else if(gp.gameState == gp.characterState) {
			characterState(code);
			
		}
	
	}

	public void titleState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.mp.commandNum--;
			if(gp.mp.commandNum < 0) {
				gp.mp.commandNum = 1;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.mp.commandNum++;
			if(gp.mp.commandNum > 1) {
				gp.mp.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.mp.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.playMusic(0);
			}
			if(gp.mp.commandNum == 1) {
				System.exit(0);
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
		if(code == KeyEvent.VK_P) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
		}
	}

	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			if(gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
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
		
	}

}
