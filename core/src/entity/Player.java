package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.mygdx.game.GamePanel;
import com.mygdx.game.PlayerControl;

import object.SuperObject;

public class Player extends Entity{
	
	PlayerControl playerControl;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	private Scanner scanner;
	public ArrayList<SuperObject> inventory = new ArrayList<SuperObject>();
	public final int maxInventorySize = 20;
	public List<SuperObject> currentNumber = new ArrayList<>();
	public String answer = "";

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
		setItems();
//		resetDialogueIndex();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 33;
		worldY = gp.tileSize * 21;
		speed= 10;
		direction = "down";
	}

	//to manually set items in the inventory
	public void setItems() {
		//inventory.add((Object) new Obj_key());
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
		String text;
	
		int worldX = gp.obj[i].worldX / gp.tileSize;
        int worldY = gp.obj[i].worldY / gp.tileSize;

	// CHECK OBJECT NAME
	switch (objectName) {
		case "Key":
		case "Boots":
			// CHECK IF INVENTORY IS FULL
			if (inventory.size() != maxInventorySize) {
				inventory.add(gp.obj[i]);
				text = "Got a " + objectName + "!";
				if ("Key".equals(objectName)) {
					gp.playSE(1);
					hasKey++;
					gp.ui.showMessage("You got a key!");
					gp.gameState=gp.dialogueState;
					if(gp.gameDifficulty == 0) {
						setDialogueEasy();
					} 
					else if(gp.gameDifficulty == 1) {
						setDialogueMedium();
					}
					else if(gp.gameDifficulty == 2) {
						setDialogueHard();
					}

					gp.ui.currentDialogue = dialogues[dialogueIndex];
					dialogueIndex++;												
					 // Call method to prompt math question based on key location
				} else {
					gp.playSE(2);
					speed += 2;
					gp.ui.showMessage("Speed up!");
				}
				gp.obj[i] = null;
			} else {
				text = "You cannot carry anymore. Inventory is full!";
			}
			break;
		case "Door":
			if (hasKey > 0) {
				gp.playSE(3);
				gp.obj[i] = null;
				hasKey--;
				  // Remove a key from the inventory
				  for (int j = 0; j < inventory.size(); j++) {
					if ("Key".equals(inventory.get(j).name)) {
						inventory.remove(j);
						break;
					}
				}
				gp.ui.showMessage("You opened the door!");
			}
			else {
				gp.ui.showMessage("You need a key!");
			}
			break;
		case "Chest":
			gp.ui.gameFinished = true;
			gp.gameState = gp.gameOverState;
			gp.stopMusic();
			gp.playSE(4);
			break;
		case "Answer 3":
		case "Answer 5":
		case "Answer 8":
			// CHECK IF INVENTORY IS FULL
			if (inventory.size() != maxInventorySize) {
				inventory.add(gp.obj[i]);
				gp.playSE(1);
				gp.obj[i] = null;
				text = "Got a " + objectName + "!";
			} else {
				text = "You cannot carry anymore. Inventory is full!";
			}
			break;		
	}
}
	}
	
	private void setDialogueEasy(){
		dialogues[0] = "Q1:What is 1 + 2";
		dialogues[1] = "Q2:What is 2 + 4";
		dialogues[2] = "Q3:What is 3 + 5";
	}
	
	private void setDialogueMedium(){
		dialogues[0] = "Q1:What is 8 x 7";
		dialogues[1] = "Q2:What is 12 - 16";
		dialogues[2] = "Q3:What is 25 ÷ 5";
	}
	
	private void setDialogueHard(){
		dialogues[0] = "Q1:What is 2 raised to the power of 4";
		dialogues[1] = "Q2:What is the squareroot of 64";
		dialogues[2] = "Q3:What is 12 x 35";
	}
	
	public void interactNPC(int i) {
		if (i != 999) {
			System.out.println("You are hitting an npc!");
		gp.gameState=gp.dialogueState;
		gp.npc[i].speak();
			
		}
	}
	
    public void resetDialogueIndex() {
        dialogueIndex = 0;
    }

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
		if (itemIndex < inventory.size()) {
			SuperObject selectedItem = inventory.get(itemIndex);
			if (currentNumber.contains(selectedItem)) {
				currentNumber.remove(selectedItem);
			} else {
				currentNumber.add(selectedItem);
				answer += selectedItem.value;
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
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		
	}


	
}
