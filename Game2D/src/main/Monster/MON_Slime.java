package main.Monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_EnergyDrink;
import object.OBJ_HP;
import object.OBJ_HP_half;

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
        actionLockCounter ++;
        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) + 1;// random a number from 1 to 100

            if(i <= 25 ){
            	if(0<worldY && worldY<70*gp.tileSize) {
            		direction = "up";
            	}
            }
            if(i > 25 && i <= 50){
            	if(0<worldY && worldY<70*gp.tileSize) {
            		direction = "down";
            	}                
            }
            if(i > 50 && i <= 75){
            	if(0<worldX && worldX<50*gp.tileSize) {
            		direction = "left";
            	}
            }
            if(i > 75){
            	if(0<worldX && worldX<50*gp.tileSize) {
            		direction = "right";
            	}
            }

            actionLockCounter = 0;
        }
    }
    public void checkDrop(){
        
        int i = new Random().nextInt(100) + 1;
        //SET THE MONSTER DROP
        if(i < 50){
            dropItem(new OBJ_HP(gp));
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_HP_half(gp));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_EnergyDrink(gp));
        }
    }
}
