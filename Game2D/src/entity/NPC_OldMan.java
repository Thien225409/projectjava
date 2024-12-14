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
        down1 = setup("/npc/01_Demon Door_NPC", gp.tileSize, gp.tileSize);
    }
    public void setDialogue(){
        
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island to find the treasure?";
        dialogues[2] = "I used to be a great wirazd but now... I'm a bit too old for taking an adventure. Chúng tôi là biệt đội giải cứu thế giới.";
        dialogues[3] = "Hãy tiêu diệt thêm quái để nhận được chìa khóa, good luck on you.";
    }
    
    public void speak(){
        if(gp.player.hasKey >= 1){
            dialogues[0] = "Hello, lad.";
            dialogues[1] = "Oh, thanh kiếm trên tay cậu trông cũng sắc bén đấy!";
            dialogues[2] = "Võ công của cậu quả thật rất đáng nể.";
            dialogues[3] = "Giờ hãy đưa tôi chìa khóa mà cậu đang cầm.";
            dialogues[4] = "Trong kia là một con yêu quái đã làm loạn ở vùng này rất lâu, chúc cậu may mắn!.";
        }
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex ++;
    }
}
