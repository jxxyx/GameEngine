package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.mygdx.game.GamePanel;

public class NPC_Merchant extends Entity{

    public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
		
	}
	public void getImage() {
		try {
	        up1 = ImageIO.read(getClass().getResourceAsStream("/npc/merchant_down_1.png"));
	        up2 = ImageIO.read(getClass().getResourceAsStream("/npc/merchant_down_2.png"));
	        down1 = ImageIO.read(getClass().getResourceAsStream("/npc/merchant_down_1.png"));
	        down2 = ImageIO.read(getClass().getResourceAsStream("/npc/merchant_down_2.png"));
	        left1 = ImageIO.read(getClass().getResourceAsStream("/npc/merchant_down_1.png"));
	        left2 = ImageIO.read(getClass().getResourceAsStream("/npc/merchant_down_2.png")); 
	        right1 = ImageIO.read(getClass().getResourceAsStream("/npc/merchant_down_1.png"));
	        right2 = ImageIO.read(getClass().getResourceAsStream("/npc/merchant_down_2.png")); 
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setDialogue(){
		dialogues[0] = "Hello adventurer";
		dialogues[1] = "There are multiple math \nquestions";
		dialogues[2] = "Solve them to obtain keys \nwhich can unlock doors!";
		dialogues[3] = "Goodluck!";
	}
	public void speak() {
		super.speak();
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		gp.gameState = gp.qnaState;
		gp.ui.npc = this;
		dialogueIndex++;
	}

}
