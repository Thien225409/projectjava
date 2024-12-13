package entity;

import java.awt.Rectangle;
import main.GamePanel;


public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp){

        super(gp);
        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = 30;
        solidArea.width = 30;
        
        getImage();
        setDialogue();
    }
    public void getImage(){
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
    }
    public void setDialogue(){

        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island to find the treasure?";
        dialogues[2] = "I used to be a great wirazd but now... I'm a bit too old for taking an adventure. Chúng tôi là biệt đội giải cứu thế giới.";
        dialogues[3] = "Well, good luck on you.";
        
    }
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex ++;
    }
}
