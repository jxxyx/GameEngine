package com.mygdx.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelPanel extends SceneManager {
	
	Graphics2D g2;
    Font arial_40, arial_80B;
    public int commandNum = 0;

    private List<String> playerRecords;
    
    public LevelPanel(GamePanel gp) {
    	super(gp);
        this.gp = gp;
        
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
    }


	@Override
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.gameLevelState) {
			drawGameLevel();
		}
		
	}
	
	private void drawGameLevel() {
	    g2.setColor(new Color(0, 0, 0));
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    // TITLE NAME
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
	    String title = "Difficulty";
	    int x = getXforCenteredText(title);
	    int y = gp.tileSize * 2;

	    // SHADOW
	    g2.setColor(Color.gray);
	    g2.drawString(title, x + 5, y + 5);

	    // MAIN COLOR
	    g2.setColor(Color.white);
	    g2.drawString(title, x, y);

	    // MENU
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));

		String text = "EASY";
		x = getXforCenteredText(text);
		y += gp.tileSize *3.5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}

		
		text = "MEDIUM";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}

		
		text = "HARD";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "BACK";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if(commandNum == 3) {
			g2.drawString(">", x-gp.tileSize, y);
		}

	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();	
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	
	
}
