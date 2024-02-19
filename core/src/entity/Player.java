package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mygdx.game.GamePanel;
import com.mygdx.game.PlayerControl;

public class Player extends Entity{
	
	GamePanel gp;
	PlayerControl playerControl;
	
	public Player(GamePanel gp, PlayerControl playerControl) {
		
		this.gp = gp;
		this.playerControl = playerControl;
		

		//Collision part
		solidArea = new Rectangle(8, 16, 32, 32);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		x=100;
		y=100;
		speed= 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
	        up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
	        up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
	        down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
	        down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
	        left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
	        left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png")); 
	        right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
	        right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png")); 
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(playerControl.upPressed == true || playerControl.downPressed == true || playerControl.leftPressed == true || playerControl.rightPressed == true) {
			if(playerControl.upPressed == true) {
				direction = "up";
				y -= speed; // THIS line will be moved to the bottom collision checker function, y will become worldY 
			}
			else if(playerControl.downPressed == true) {
				direction = "down";
				y += speed; // THIS line will be moved to the bottom collision checker function, y will become worldY
			}
			else if(playerControl.leftPressed == true) {
				direction = "left";
				x -= speed; // THIS line will be moved to the bottom collision checker function, x will become worldX
			}
			else if(playerControl.rightPressed == true) {
				direction = "right";
				x += speed; // THIS line will be moved to the bottom collision checker function, x will become worldX
			}
			
//			// CHECK TILE COLLISION
//			collisionOn = false;
//			gp.cChecker.checkTile(this);
//			
//			// IF COLLISION IS FALSE, PLAYER CAN MOVE
//			if(collisionOn == false) {
//				
//				switch(direction) {
//				case "up":
//					y -= speed; // HELP ME MODIFY THIS LIKE IF CAN, THANK YOU (y to worldY)
//					break;
//				case "down":
//					y += speed; // HELP ME MODIFY THIS LIKE IF CAN, THANK YOU (y to worldY)
//					break;
//				case "left":
//					x -= speed; // HELP ME MODIFY THIS LIKE IF CAN, THANK YOU (x to worldX)
//					break;
//				case "right":
//					x += speed;// HELP ME MODIFY THIS LIKE IF CAN, THANK YOU (x to worldX)
//					break;
//				}
//			}
			
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
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
		
	}
}
