package main.Monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Mushroom_Boom;

public class MON_Mushroom extends Entity{
	GamePanel gp;
    public MON_Mushroom(GamePanel gp){
        super(gp);

        this.gp = gp;
        type = 2;
        name = "Mushroom";
        speed = 1;
        maxLife = 3;
        life = maxLife;

        attack = 2;
        defense = 0;
        projectile = new OBJ_Mushroom_Boom(gp);
        
        solidArea.x = 4;
        solidArea.y = 3;
        solidArea.width = 24;
        solidArea.height = 38;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

    	up1 = setup("/monster/mushroom_walk_R1", gp.tileSize, gp.tileSize);
    	up2 = setup("/monster/mushroom_walk_R2", gp.tileSize, gp.tileSize);
    	up3 = setup("/monster/mushroom_walk_R3", gp.tileSize, gp.tileSize);
    	
        down1 = setup("/monster/mushroom_walk_L1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/mushroom_walk_L2", gp.tileSize, gp.tileSize);
        down3 = setup("/monster/mushroom_walk_L3", gp.tileSize, gp.tileSize);
        
        right1 = setup("/monster/mushroom_walk_R1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/mushroom_walk_R2", gp.tileSize, gp.tileSize);
        right3 = setup("/monster/mushroom_walk_R3", gp.tileSize, gp.tileSize);
       
        
        left1 = setup("/monster/mushroom_walk_L1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/mushroom_walk_L2", gp.tileSize, gp.tileSize);
        left3 = setup("/monster/mushroom_walk_L3", gp.tileSize, gp.tileSize);
       
    }   
    
    public void update(){

        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer == true){
        	damagePlayer(attack);
        }
        
        if(collisionOn == false){
        	switch (direction) {
            case "up": 
            	if(1*gp.tileSize <= worldY && worldY <= 10*gp.tileSize) {
            		worldY -= speed ;           		
            	}         	     	
            	break;
            case "down": 
            	if(1*gp.tileSize <= worldY && worldY <= 10*gp.tileSize) {
            		worldY += speed; 
            	}
            	break;
            case "right": 
            	if(2*gp.tileSize <= worldX && worldX <= 21*gp.tileSize) {
            		worldX += speed; 
            	}
            	break;
            case "left": 
            	if(2*gp.tileSize <= worldX && worldX <= 21*gp.tileSize) {
            		worldX -= speed;
            	}  
            	break;
            default: break;
            }
        }

        spriteCounter ++;
        if(spriteCounter > 5){
            if(spriteNum == 1) spriteNum = 2;
            else if(spriteNum == 2) spriteNum = 3;
            else if(spriteNum == 3) spriteNum = 1;       
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
    }
    
    public void subdraw(Graphics2D g2, int screenX, int screenY){
    	
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                if(spriteNum == 3) image = up3;                              
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                if(spriteNum == 3) image = down3; 
                break;   
            case "right":
            	if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                if(spriteNum == 3) image = right3;  
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                if(spriteNum == 3) image = left3;  
                break;
        }

        // Monster HP bar

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
        
//        if(attacking == false) {
//        	checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize * 2);
//        }
        
          int i = new Random().nextInt(100) + 1;
          if(i > 90 && projectile.alive == false && shotAvailableCounter == 30){
        	  
              projectile.set(worldX, worldY, direction, true, this);
        	  gp.projectileList.add(projectile);
        	  shotAvailableCounter = 0;
          }
    }

    public void damageReaction(){

        actionLockCounter = 0;
        direction = gp.player.direction;
        //TODO: Setup Mushroom AI
    }
}