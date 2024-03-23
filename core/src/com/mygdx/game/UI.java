package com.mygdx.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;	
import java.text.DecimalFormat;
import java.awt.Image;

// import com.badlogic.gdx.scenes.scene2d.ui.Image;

import object.Obj_key;


public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	private boolean hasWrittenPlaytimeToFile = false;
	public int subState = 0;

	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		Obj_key key = new Obj_key();
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//GameOver State
		if(gp.gameState == gp.gameOverState){
			drawGameOverScene();
//			gp.gameThread = null;
			if(gameFinished == true && !hasWrittenPlaytimeToFile) {
		        writePlaytimeToFile(playTime);
		        hasWrittenPlaytimeToFile = true; // Marking that play time has been written
			}

//			gp.startGameThread();
		}
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 74, 65);
			
			// TIME
			if (gp.gameState != gp.optionsState && gp.gameState != gp.characterState) {
				playTime += (double)1/60;
				g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*14, 65);
			}

			
			// MESSAGE
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
				
				messageCounter++;
				
				if (messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
		
		//character state
		if(gp.gameState == gp.characterState) {
			//drawCharacterScreen();
			drawInventory();
		}

		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState){
			drawDialogueScreen();
		}
		
		// OPTIONS STATE
		if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
	}
	
	public void drawGameOverScene() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
		//Shadow
		String text = "CONGRATULATIONS !!";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 4;
		g2.drawString(text, x, y);
		//Main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		//Time
		text = "Your time is: " + dFormat.format(playTime) + "!";
		int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = getXforCenteredText(text);
		y = gp.tileSize;
		g2.drawString(text, x, y);
		//Retry
		g2.setFont(g2.getFont().deriveFont(30f));
		text = "Replay";
		x = getXforCenteredText(text);
		y = gp.tileSize * 8;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		//Quit
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}

	public void drawDialogueScreen(){
		//window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*5;

		drawSubWindow(x, y, width, height);

		x += gp.tileSize;
		y += gp.tileSize;

		
		for(String line: currentDialogue.split("\n")){
			g2.drawString(line, x, y);
			y += 40;
		}

	}

	public void drawCharacterScreen() {
		final int frameX = gp.tileSize*2;
		final int frameY = gp.tileSize;
		final int framWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, framWidth, frameHeight);
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255,255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}

	public void drawInventory() {
		final int frameX = gp.tileSize*12;
		final int frameY = gp.tileSize;
		final int framWidth = gp.tileSize*6;
		final int frameHeight = gp.tileSize*5;
		drawSubWindow(frameX, frameY, framWidth, frameHeight);

		//slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize+3;
		int imageWidth = slotSize; // specify the width of the image
		int imageHeight = slotSize; // specify the height of the image

		//DRAW PLAYERS ITEMS IN INVENTORY
		for(int i = 0; i < gp.player.inventory.size(); i++) {

			// equip cursor
			
			if (gp.player.currentNumber.contains(gp.player.inventory.get(i))) {
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			

			Image scaledImage = gp.player.inventory.get(i).image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
			g2.drawImage(scaledImage, slotX, slotY, null);

			slotX += slotSize;
			if( i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}

		//cursor
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;

		//draw cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		// description frame
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = framWidth;
		int dFrameHeight = gp.tileSize*3;
		drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

		//draw description text
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(20F));

		int itemIndex = getItemIndexOnSlot();
		int maxWidth = 200; // Maximum width of a line
		
		if(itemIndex < gp.player.inventory.size()) {
			String description = gp.player.inventory.get(itemIndex).description;
			String[] words = description.split(" ");
			String currentLine = words[0];
		
			for (int i = 1; i < words.length; i++) {
				if (g2.getFontMetrics().stringWidth(currentLine + words[i]) < maxWidth) {
					currentLine += " " + words[i];
				} else {
					g2.drawString(currentLine, textX, textY);
					textY += g2.getFontMetrics().getHeight();
					currentLine = words[i];
				}
			}
			g2.drawString(currentLine, textX, textY);
		}

	}
	
	public void drawOptionsScreen() {

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		// Sub window
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch (subState) {
			case 0: options_top(frameX, frameY);break;
			case 1: options_fullScreenNotification(frameX, frameY);break;
			case 2: options_control(frameX, frameY);break;
			case 3: options_endGameConfirmation(frameX, frameY); break;
		}

		gp.playerControl.enterPressed = false;
	}

	public void options_top(int frameX, int frameY){

		int textX;
		int textY;

		// Title
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		// Fullscreen on/off
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Fullscreen", textX, textY);
		if(commandNum == 0){
			g2.drawString(">", textX-25, textY);
			if(gp.playerControl.enterPressed == true){
				if(gp.fullScreenOn == false){
					gp.fullScreenOn = true;
				}
				else if (gp.fullScreenOn == true){
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
			
		}
		
		// Music
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1){
			g2.drawString(">", textX-25, textY);
		}

		// SE
		textY += gp.tileSize;
		g2.drawString("SE", textX, textY);
		if(commandNum == 2){
			g2.drawString(">", textX-25, textY);
		}

		// Control
		textY += gp.tileSize;
		g2.drawString("Control", textX, textY);
		if(commandNum == 3){
			g2.drawString(">", textX-25, textY);
			if(gp.playerControl.enterPressed == true){
				subState = 2;
				commandNum = 0;
			}
		}

		// End Game
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if(commandNum == 4){
			g2.drawString(">", textX-25, textY);
			if(gp.playerControl.enterPressed == true){
				subState = 3;
				commandNum = 0;
			}
		}

		// Back
		textY += gp.tileSize*2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 5){
			g2.drawString(">", textX-25, textY);
			if(gp.playerControl.enterPressed == true){
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}

		// Fullscreen check box
		textX = frameX + (int)(gp.tileSize*4.5);
		textY = frameY + gp.tileSize*2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if (gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}

		// Music volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24); // 120/5 = 24
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);

		// SE volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.SE.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
	}

	public void options_fullScreenNotification(int frameX, int frameY){
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;

		currentDialogue = "The change will take \neffect after restarting \nthe game.";

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}

		// Back
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0){
			g2.drawString(">", textX-25, textY);
			if(gp.playerControl.enterPressed == true){
				subState = 0;
			}
		}
	}

	public void options_control(int frameX, int frameY){
		
		int textX;
		int textY = frameY + gp.tileSize; // Initialize textY

		// Title
		String text = "Control";
		textX = getXforCenteredText(text);
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY); textY += gp.tileSize;
		g2.drawString("Confirm", textX, textY); textY += gp.tileSize;
		g2.drawString("Options", textX, textY); textY += gp.tileSize;
		g2.drawString("Inventory", textX, textY); textY += gp.tileSize;
		
		textX = frameX + gp.tileSize*5;
		textY = frameY + gp.tileSize*2;
		g2.drawString("WASD", textX, textY); textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
		g2.drawString("ESCAPE", textX, textY); textY += gp.tileSize;
		g2.drawString("C", textX, textY); textY += gp.tileSize;

		// Back
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0){
			g2.drawString(">", textX-25, textY);
			if(gp.playerControl.enterPressed == true){
				subState = 0;
				commandNum = 3;
			}
		}

	}


	public void options_endGameConfirmation(int frameX, int frameY){

		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;

		currentDialogue = "Quit the game and \nreturn to title screen?";

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}

		// Yes
		String text = "Yes";	
		textX = getXforCenteredText(text);
		textY += gp.tileSize*2;
		g2.drawString(text, textX, textY);
		if(commandNum == 0){
			g2.drawString(">", textX-25, textY);
			if(gp.playerControl.enterPressed == true){
				subState = 0;
				gp.gameState = gp.titleState;
				gp.resetGame();
				gp.stopMusic();
			}
		}

		// No
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.tileSize*2;
		g2.drawString(text, textX, textY);
		if(commandNum == 1){
			g2.drawString(">", textX-25, textY);
			if(gp.playerControl.enterPressed == true){
				subState = 0;
				commandNum = 4;
			}
		}

	}
	
	public void resetUIVariables() {
	    gameFinished = false;
	    hasWrittenPlaytimeToFile = false;
	    playTime = 0.0;
	}
	
	public int getItemIndexOnSlot(){
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();	
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	private void writePlaytimeToFile(double playTime) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("playtime.txt", true))) {
	        // Append the play time to the file
	        writer.write("Playtime: " + dFormat.format(playTime) + " seconds\n");
	    } catch (IOException e) {
	        e.printStackTrace(); // Handle the exception appropriately in your actual application
	    }
	}
}
