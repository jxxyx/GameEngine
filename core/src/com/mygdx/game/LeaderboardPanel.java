package com.mygdx.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardPanel extends SceneManager {
	
	GamePanel gp;
	Graphics2D g2;
	public UI ui = new UI(gp);
    Font arial_40, arial_80B;
    public int commandNum = 0;
    String playTime; 

    private List<String> playerRecords;
    
    public LeaderboardPanel(GamePanel gp) {
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
		if(gp.gameState == gp.leaderboardState) {
			
			drawLeaderboard();
		}
		
	}
	
	private void drawLeaderboard() {
	    g2.setColor(new Color(0, 0, 0));
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    // TITLE NAME
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
	    String title = "Leaderboard";
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

	    playerRecords = readPlaytimefromFile();

	    // Extract numeric part and sort
	    playerRecords.sort(Comparator.comparingDouble(this::extractPlaytime));

	    // Display only the top 5 records or less if the list is smaller
	    for (int i = 0; i < Math.min(playerRecords.size(), 5); i++) {
	        String time = playerRecords.get(i);
	        x = getXforCenteredText(time);
	        y += gp.tileSize;
	        g2.drawString(time, x, y);
	    }
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();	
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	private List<String> readPlaytimefromFile() {
		List<String> times = new ArrayList<>(); 
	    try (BufferedReader reader = new BufferedReader(new FileReader("playtime.txt"))) {
	    	String line; 
	        while ((line = reader.readLine()) != null) { 
	            times.add(line); 
	        } 
	    } catch (IOException e) {
	        e.printStackTrace(); // Handle the exception appropriately in your actual application
	    }
	    return times;
	}
	
	private double extractPlaytime(String record) {
	    // Extract numeric part from the record
	    String numericPart = record.replaceAll("[^0-9.]", "");
	    return Double.parseDouble(numericPart);
	}
	
	
}
