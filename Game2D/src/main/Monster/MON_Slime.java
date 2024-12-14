package main.Monster;

import java.util.Random;
import entity.Entity;
import main.GamePanel;

public class MON_Slime extends Entity{

    GamePanel gp;
    public MON_Slime(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Smile";
        speed = 1;
        maxLife = 2;
        life = maxLife;
        attack = 1;
        defense = 0;
        exp = 1;
    
        solidArea.x = 4;
        solidArea.y = 11;
        solidArea.width = 27;
        solidArea.height = 15;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }

    public void getImage(){

        up1 = setup("/monster/slime_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/slime_up2", gp.tileSize, gp.tileSize);
      
        down1 = setup("/monster/slime_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/slime_down2", gp.tileSize, gp.tileSize);
        
        right1 = setup("/monster/slime_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/slime_right2", gp.tileSize, gp.tileSize);
    
        left1 = setup("/monster/slime_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/slime_left2", gp.tileSize, gp.tileSize);
    }
    public void setAction(){
        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
        }
        else{
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
    }
}
