package main.Monster;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Ghost extends Entity{

    GamePanel gp;
    public MON_Ghost(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Ghost";
        speed = 1;
        maxLife = 2;
        life = maxLife;
        getImage();
    }

    public void getImage() {
        
        right1 = setup("/monster/ghost_R1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/ghost_R2", gp.tileSize, gp.tileSize);
        right3 = setup("/monster/ghost_R3", gp.tileSize, gp.tileSize);
        right4 = setup("/monster/ghost_R4", gp.tileSize, gp.tileSize);
        right5 = setup("/monster/ghost_R5", gp.tileSize, gp.tileSize);
        right6 = setup("/monster/ghost_R6", gp.tileSize, gp.tileSize);
        right7 = setup("/monster/ghost_R7", gp.tileSize, gp.tileSize);
        right8 = setup("/monster/ghost_R8", gp.tileSize, gp.tileSize);
    
        left1 = setup("/monster/ghost_L1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/ghost_L2", gp.tileSize, gp.tileSize);
        left3 = setup("/monster/ghost_L3", gp.tileSize, gp.tileSize);
        left4 = setup("/monster/ghost_L4", gp.tileSize, gp.tileSize);
        left5 = setup("/monster/ghost_L5", gp.tileSize, gp.tileSize);
        left6 = setup("/monster/ghost_L6", gp.tileSize, gp.tileSize);
        left7 = setup("/monster/ghost_L7", gp.tileSize, gp.tileSize);
        left8 = setup("/monster/ghost_L8", gp.tileSize, gp.tileSize);
        
        down1 = setup("/monster/ghost_R1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/ghost_R2", gp.tileSize, gp.tileSize);
        down3 = setup("/monster/ghost_R3", gp.tileSize, gp.tileSize);
        down4 = setup("/monster/ghost_R4", gp.tileSize, gp.tileSize);
        down5 = setup("/monster/ghost_R5", gp.tileSize, gp.tileSize);
        down6 = setup("/monster/ghost_R6", gp.tileSize, gp.tileSize);
        down7 = setup("/monster/ghost_R7", gp.tileSize, gp.tileSize);
        down8 = setup("/monster/ghost_R8", gp.tileSize, gp.tileSize);
        
        up1 = setup("/monster/ghost_L1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/ghost_L2", gp.tileSize, gp.tileSize);
        up3 = setup("/monster/ghost_L3", gp.tileSize, gp.tileSize);
        up4 = setup("/monster/ghost_L4", gp.tileSize, gp.tileSize);
        up5 = setup("/monster/ghost_L5", gp.tileSize, gp.tileSize);
        up6 = setup("/monster/ghost_L6", gp.tileSize, gp.tileSize);
        up7 = setup("/monster/ghost_L7", gp.tileSize, gp.tileSize);
        up8 = setup("/monster/ghost_L8", gp.tileSize, gp.tileSize);
    }
    
    public void update(){

        setAction();
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(contactPlayer == true){
            if(gp.player.invincible == false){
                // We can give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }

        }
        
        switch (direction) {
        case "up": worldY -= speed ; break;
        case "down": worldY += speed; break;
        case "right": worldX += speed; break;
        case "left": worldX -= speed; break;
        default: break;
    }
        
        spriteCounter ++;
        if(spriteCounter > 5){
            if(spriteNum == 1) spriteNum = 2;
            else if(spriteNum == 2) spriteNum = 3;
            else if(spriteNum == 3) spriteNum = 4;
            else if(spriteNum == 4) spriteNum = 5;
            else if(spriteNum == 5) spriteNum = 6;
            else if(spriteNum == 6) spriteNum = 7;
            else if(spriteNum == 7) spriteNum = 8;
            else if(spriteNum == 8) spriteNum = 1;
            spriteCounter = 0;
        }

        if(invincible == true){
            invincibleCounter ++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        // Stop moving the camere at the edge
        if(gp.player.screenX > gp.player.worldX){
            screenX = worldX;
        }
        if(gp.player.screenY > gp.player.worldY){
            screenY = worldY;
        }
        int rightOffset = gp.screenWidth - gp.player.screenX;
        if(rightOffset > gp.worldWidth - gp.player.worldX){
            screenX = gp.screenWidth - (gp.worldWidth - worldX);
        }

        int bottomOffset = gp.screenHeight - gp.player.screenY;
        if(bottomOffset > gp.worldHeight - gp.player.worldY){
            screenY = gp.screenHeight - (gp.worldHeight - worldY);
        }

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            switch(direction){
                case "up":
                	if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                    if(spriteNum == 3) image = up3;
                    if(spriteNum == 4) image = up4;
                    if(spriteNum == 5) image = up5;
                    if(spriteNum == 6) image = up6;
                    if(spriteNum == 7) image = up7;
                    if(spriteNum == 8) image = up8;
                    break;
                case "down":
                	if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
                    if(spriteNum == 3) image = down3;
                    if(spriteNum == 4) image = down4;
                    if(spriteNum == 5) image = down5;
                    if(spriteNum == 6) image = down6;
                    if(spriteNum == 7) image = down7;
                    if(spriteNum == 8) image = down8;
                    break;    
                case "right":
                	if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                    if(spriteNum == 3) image = right3;
                    if(spriteNum == 4) image = right4;
                    if(spriteNum == 5) image = right5;
                    if(spriteNum == 6) image = right6;
                    if(spriteNum == 7) image = right7;
                    if(spriteNum == 8) image = right8;
                    break;
                case "left":
                	if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
                    if(spriteNum == 3) image = left3;
                    if(spriteNum == 4) image = left4;
                    if(spriteNum == 5) image = left5;
                    if(spriteNum == 6) image = left6;
                    if(spriteNum == 7) image = left7;
                    if(spriteNum == 8) image = left8;
                    break;
            }
            // Làm mờ khi nhân sát thương
            if(invincible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

            if(dying == true){
                dyingAnimation(g2);//TODO: Khi quái chết thì tải animation die của quái
            }

            g2.drawImage(image, screenX , screenY , gp.tileSize , gp.tileSize , null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            
        }
        else if(gp.player.screenX > gp.player.worldX || gp.player.screenY > gp.player.worldY ||
                rightOffset > gp.worldWidth - gp.player.worldX ||
                bottomOffset > gp.worldHeight - gp.player.worldY) {
        	    
        	 switch(direction){
             case "up":
             	if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                if(spriteNum == 3) image = up3;
                if(spriteNum == 4) image = up4;
                if(spriteNum == 5) image = up5;
                if(spriteNum == 6) image = up6;
                if(spriteNum == 7) image = up7;
                if(spriteNum == 8) image = up8;
                break;
             case "down":
             	if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                if(spriteNum == 3) image = down3;
                if(spriteNum == 4) image = down4;
                if(spriteNum == 5) image = down5;
                if(spriteNum == 6) image = down6;
                if(spriteNum == 7) image = down7;
                if(spriteNum == 8) image = down8;
                break;    
             case "right":
             	if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                if(spriteNum == 3) image = right3;
                if(spriteNum == 4) image = right4;
                if(spriteNum == 5) image = right5;
                if(spriteNum == 6) image = right6;
                if(spriteNum == 7) image = right7;
                if(spriteNum == 8) image = right8;
                break;
             case "left":
             	if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                if(spriteNum == 3) image = left3;
                if(spriteNum == 4) image = left4;
                if(spriteNum == 5) image = left5;
                if(spriteNum == 6) image = left6;
                if(spriteNum == 7) image = left7;
                if(spriteNum == 8) image = left8;
                break;
            } 
            // Làm mờ khi nhân sát thương
            if(invincible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

            if(dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX , screenY , gp.tileSize , gp.tileSize , null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); 
        }
        
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
