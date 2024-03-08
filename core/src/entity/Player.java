package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mygdx.game.GamePanel;
import com.mygdx.game.PlayerControl;

public class Player extends Entity{
	
	PlayerControl playerControl;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	private Scanner scanner;

	
	public Player(GamePanel gp, PlayerControl playerControl) {
		super(gp);
		
		this.playerControl = playerControl;
		this.scanner = new Scanner(System.in);
		//return the halfway point of the screen
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		//Collision part
		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed= 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
	        up1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_up_1.png"));
	        up2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_up_2.png"));
	        down1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_down_1.png"));
	        down2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_down_2.png"));
	        left1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_left_1.png"));
	        left2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_left_2.png")); 
	        right1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_right_1.png"));
	        right2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_right_2.png")); 
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(playerControl.upPressed == true || playerControl.downPressed == true || playerControl.leftPressed == true || playerControl.rightPressed == true) {
			if(playerControl.upPressed == true) {
				direction = "up";
			}
			else if(playerControl.downPressed == true) {
				direction = "down";
			}
			else if(playerControl.leftPressed == true) {
				direction = "left";
			}
			else if(playerControl.rightPressed == true) {
				direction = "right";
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this,  true);
			pickUpObject(objIndex);
			
			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed; 
					break;
				case "down":
					worldY += speed; 
					break;
				case "left":
					worldX -= speed; 
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}

	}
	
	public void pickUpObject (int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;
			
		int worldX = gp.obj[i].worldX / gp.tileSize;
        int worldY = gp.obj[i].worldY / gp.tileSize;

        switch (objectName) {
            case "Key":
                gp.playSE(1);
                hasKey++;
                gp.obj[i] = null;
                gp.ui.showMessage("You got a key!");
                promptMathQuestionForKey(worldX, worldY); // Call method to prompt math question based on key location
                break;

			case "Door":
				if (hasKey > 0) {
					gp.playSE(3);
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("You opened the door!");
				}
				else {
					gp.ui.showMessage("You need a key!");
				}
				break;
			case "Boots":
				gp.playSE(2);
				speed += 2;
				gp.obj[i] = null;
				gp.ui.showMessage("Speed up!");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
		}
	}
	
	public void promptMathQuestionForKey(int worldX, int worldY) {
		// Display the math question in a dialog box based on key location
		int num1, num2;
		String question;
		switch (worldX) {
			case 23:
				if (worldY == 7) {
					// Key at position (23, 7)
					num1 = 5;
					num2 = 12;
					question = "What is " + num1 + " + " + num2 + "?";
					break;
				} else if (worldY == 40) {
					// Key at position (23, 40)
					num1 = 10;
					num2 = 8;
					question = "What is " + num1 + " + " + num2 + "?";
					break;
				}
				// Other cases for position (23, Y)
			case 38:
				if (worldY == 8) {
					// Key at position (38, 8)
					num1 = 20;
					num2 = 5;
					question = "What is " + num1 + " + " + num2 + "?";
					break;
				}
				// Other cases for position (38, Y)
			default:
				// Default question
				num1 = 0;
				num2 = 0;
				question = "Default math question.";
				break;
		}
	
		int correctAnswer = num1 + num2;
	
		JFrame frame = new JFrame();
		String userInput = JOptionPane.showInputDialog(frame, question);
	
		// Check if the player's input matches the correct answer
		try {
			int userAnswer = Integer.parseInt(userInput);
			if (userAnswer == correctAnswer) {
				gp.ui.showMessage("Correct!");
				// Additional actions if the answer is correct
			} else {
				gp.ui.showMessage("Incorrect. Try again!");
				// Additional actions if the answer is incorrect
			}
		} catch (NumberFormatException e) {
			gp.ui.showMessage("Invalid input. Please enter a number.");
		}
	}

	
	public void interactNPC(int i) {
		if (i != 999) {
			System.out.println("YOu are hitting an npc!");
		}
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		
	}
}
