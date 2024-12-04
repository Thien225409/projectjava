package entity;

import java.awt.Rectangle;
import java.util.Random;

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
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
    }
    public void setAction(){

        actionLockCounter ++;
        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) + 1;// random a number from 1 to 100

            if(i <= 25 ){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75){
                direction = "right";
            }

            actionLockCounter = 0;
        }

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
        gp.ui.currentDialogue = dialogues [dialogueIndex];
        dialogueIndex ++;

        switch(gp.player.direction){
        case "up":
            direction = "down";
            break;
        case "down":
            direction = "up";
            break;
        case "right":   
            direction = "left";
            break;
        case "left":
            direction = "right";
            break;
        }
    }
}
