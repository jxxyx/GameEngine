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
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
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
			
			if(code == KeyEvent.VK_P) {
				if(gp.gameState == gp.playState) {
					gp.gameState = gp.pauseState;
				}

			}
		} else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				if(gp.gameState == gp.pauseState) {
					gp.gameState = gp.playState;
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
		
	}

}
