package com.mygdx.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardPanel extends SceneManager {
	
	GamePanel gp;
	Graphics2D g2;
	public UI ui = new UI(gp);
    Font arial_40, arial_80B;
    public int commandNum = 0;

    private ArrayList<PlayerRecord> playerRecords;
    
    public LeaderboardPanel(GamePanel gp) {
    	super(gp);
        this.gp = gp;
        
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void addPlayerRecord(PlayerRecord record) {
        playerRecords.add(record);
        sortLeaderboard();
    }

    public ArrayList<PlayerRecord> getPlayerRecords() {
        return playerRecords;
    }

    private void sortLeaderboard() {
        // Sort the leaderboard based on play time or any other relevant metric
        Collections.sort(playerRecords, Comparator.comparingDouble(PlayerRecord::getPlayTime));
    }

	@Override
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawLeaderboard();
		}
		
	}
	
	private void drawLeaderboard() {
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Dino Adventure";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		
		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// DINO IMAGE
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		
		text = String.valueOf(ui.playTime);
		x = getXforCenteredText(text);
		y += gp.tileSize *3.5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();	
		int x = gp.screenWidth - length/2;
		return x;
	}
}
