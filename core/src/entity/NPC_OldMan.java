package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.mygdx.game.AI_controller;
import com.mygdx.game.GamePanel;

public class NPC_OldMan extends Entity implements AI_controller{

	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
		
	}
	public void getImage() {
		try {
	        up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
	        up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
	        down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
	        down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
	        left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
	        left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png")); 
	        right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
	        right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png")); 
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setDialogue(){
		dialogues[0][0] = "Hello adventurer";
		dialogues[1][0] = "There are multiple math \nquestions";
		dialogues[2][0] = "Solve them to obtain keys \nwhich can unlock doors!";
		dialogues[3][0] = "Goodluck!";
	}

	@Override
	public void performAIAction() {
		actionLockCounter ++;
		if (actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
	}
	public void speak() {
	    if (dialogueIndex < 4) {
	        gp.ui.currentDialogue = dialogues[dialogueIndex][0];
	        gp.gameState = gp.dialogueState; // Keep the dialogue state active
	        dialogueIndex++;
	    } else {
	        dialogueIndex = 0; 
	        gp.ui.currentDialogue = dialogues[dialogueIndex][0]; 
	        gp.gameState = gp.dialogueState; 
	        dialogueIndex++;
	    }
	}
}
