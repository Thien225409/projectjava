package main.Monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Skeleton extends Entity{

    public MON_Skeleton(GamePanel gp){
        super(gp);

        name = "Smile";
        speed = 2;
        maxLife = 4;
        life = maxLife;
    }

    public void getImage(){

        up1 = setup("/monster/skeleton_up1");
        up2 = setup("/monster/skeleton_up2");
        up3 = setup("/monster/skeleton_1");
        up4 = setup("/monster/skeleton_1");

        down1 = setup("/monster/skeleton_down1");
        down2 = setup("/monster/skeleton_down2");
        down3 = setup("/monster/skeleton_2");
        down4 = setup("/monster/skeleton_2");
        
        right1 = setup("/monster/skeleton_right1");
        right2 = setup("/monster/skeleton_right2");
        right3 = setup("/monster/skeleton_1");
        right4 = setup("/monster/skeleton_1");
    
        left1 = setup("/monster/skeleton_left1");
        left2 = setup("/monster/skeleton_left2");
        left3 = setup("/monster/skeleton_2");
        left4 = setup("/monster/skeleton_2");
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
}
