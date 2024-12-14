package main.Monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Eye_Boom;

public class MON_FlyingEye extends Entity{
	GamePanel gp;
    public MON_FlyingEye(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "FlyingEye";
        speed = 2;
        maxLife = 2;
        projectile = new OBJ_Eye_Boom(gp);

        attack = 2;
        defense = 0;
        
        life = maxLife;
        getImage();
    }

    public void getImage() {
        
        right1 = setup("/monster/flying_eye_R1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/flying_eye_R2", gp.tileSize, gp.tileSize);
        right3 = setup("/monster/flying_eye_R3", gp.tileSize, gp.tileSize);
        right4 = setup("/monster/flying_eye_R4", gp.tileSize, gp.tileSize);
        right5 = setup("/monster/flying_eye_R5", gp.tileSize, gp.tileSize);
        right6 = setup("/monster/flying_eye_R6", gp.tileSize, gp.tileSize);     
    
        left1 = setup("/monster/flying_eye_L1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/flying_eye_L2", gp.tileSize, gp.tileSize);
        left3 = setup("/monster/flying_eye_L3", gp.tileSize, gp.tileSize);
        left4 = setup("/monster/flying_eye_L4", gp.tileSize, gp.tileSize);
        left5 = setup("/monster/flying_eye_L5", gp.tileSize, gp.tileSize);
        left6 = setup("/monster/flying_eye_L6", gp.tileSize, gp.tileSize);
     
        down1 = setup("/monster/flying_eye_R1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/flying_eye_R2", gp.tileSize, gp.tileSize);
        down3 = setup("/monster/flying_eye_R3", gp.tileSize, gp.tileSize);
        down4 = setup("/monster/flying_eye_R4", gp.tileSize, gp.tileSize);
        down5 = setup("/monster/flying_eye_R5", gp.tileSize, gp.tileSize);
        down6 = setup("/monster/flying_eye_R6", gp.tileSize, gp.tileSize);
       
        up1 = setup("/monster/flying_eye_L1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/flying_eye_L2", gp.tileSize, gp.tileSize);
        up3 = setup("/monster/flying_eye_L3", gp.tileSize, gp.tileSize);
        up4 = setup("/monster/flying_eye_L4", gp.tileSize, gp.tileSize);
        up5 = setup("/monster/flying_eye_L5", gp.tileSize, gp.tileSize);
        up6 = setup("/monster/flying_eye_L6", gp.tileSize, gp.tileSize);
       
    }
    
    public void update(){

        setAction();
        checkCollison();
        if(collisionOn == false){
            switch (direction) {
                case "up": 
                    if(18*gp.tileSize < worldY && worldY <= 35*gp.tileSize) {
                    	worldY -= speed ;           		
                    }         	     	
                    break;
                case "down":  
                    if(18*gp.tileSize <= worldY && worldY <= 35*gp.tileSize) {
                    	worldY += speed; 
                    }
                    break;
                case "right":
                    if(50*gp.tileSize <= worldX && worldX <= 68*gp.tileSize) {
                    	worldX += speed; 
                    }
                    break;
                case "left":
                    if(50*gp.tileSize <= worldX && worldX <= 68*gp.tileSize) {
                    	worldX -= speed;
                    }  
                    break;
                default: break;
            }
        }
        
        spriteCounter ++;
        if(spriteCounter > 4){
            if(spriteNum == 1) spriteNum = 2;
            else if(spriteNum == 2) spriteNum = 3;
            else if(spriteNum == 3) spriteNum = 4;
            else if(spriteNum == 4) spriteNum = 5;
            else if(spriteNum == 5) spriteNum = 6;
            else if(spriteNum == 6) spriteNum = 1;           
            spriteCounter = 0;
        }

        if(invincible == true){
            invincibleCounter ++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30) {
        	shotAvailableCounter ++;
        }
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance < 10){
            
            onPath = true;
        }
        if(onPath == true && tileDistance > 20){
            onPath = false;
        }
    }
    
    public void subdraw(Graphics2D g2, int screenX, int screenY){

        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                if(spriteNum == 3) image = up3;
                if(spriteNum == 4) image = up4;
                if(spriteNum == 5) image = up5;
                if(spriteNum == 6) image = up6;              
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                if(spriteNum == 3) image = down3;
                if(spriteNum == 4) image = down4;
                if(spriteNum == 5) image = down5;
                if(spriteNum == 6) image = down6;
                break;   
            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                if(spriteNum == 3) image = right3;
                if(spriteNum == 4) image = right4;
                if(spriteNum == 5) image = right5;
                if(spriteNum == 6) image = right6;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                if(spriteNum == 3) image = left3;
                if(spriteNum == 4) image = left4;
                if(spriteNum == 5) image = left5;
                if(spriteNum == 6) image = left6;
                break;
        }
        // Monster HP bar (Thanh HP của quái)
        if(hpBarOn == true && type == 2){

            double oneScale = (double) gp.tileSize/maxLife;
            double hpBarValue = oneScale*life;

            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX - 1,screenY - 16,gp.tileSize+2,7);
            g2.setColor(new Color(255,0,30));
            g2.fillRect(screenX, screenY-15, (int) hpBarValue, 5);

            hpBarCounter++;
            if(hpBarCounter > 600){
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }
        // Làm mờ khi nhân sát thương
        if(invincible == true){
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.4f);
        }

        if(dying == true){
            //TODO: Khi quái chết thì tải animation die của quái
            dyingCounter ++;

            int jump = 5;
            if(dyingCounter <= jump) changeAlpha(g2, 1f);
            if(dyingCounter > jump && dyingCounter <= jump*2) changeAlpha(g2, 0f);
            if(dyingCounter > jump*2 && dyingCounter <= jump*3) changeAlpha(g2, 1f);
            if(dyingCounter > jump*3 && dyingCounter <= jump*4) changeAlpha(g2, 0f);
            if(dyingCounter > jump*4 && dyingCounter <= jump*5) changeAlpha(g2, 1f);
            if(dyingCounter > jump*5 && dyingCounter <= jump*6) changeAlpha(g2, 0f);
            if(dyingCounter > jump*6 && dyingCounter <= jump*7) changeAlpha(g2, 1f);
            if(dyingCounter > jump*7 && dyingCounter <= jump*8) changeAlpha(g2, 0f);
            if(dyingCounter > jump*8){
                dying = false;
                alive = false;
            }
        }

        g2.drawImage(image, screenX , screenY, gp.tileSize , gp.tileSize , null);
        changeAlpha(g2, 1f);
    }

    public void setAction(){
        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(100) + 1;
            if(i > 90 && projectile.alive == false && shotAvailableCounter == 30){
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
            }
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
