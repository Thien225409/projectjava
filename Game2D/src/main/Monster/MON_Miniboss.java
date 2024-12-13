package main.Monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
public class MON_Miniboss extends Entity{
	
	GamePanel gp;
	public MON_Miniboss(GamePanel gp){
		super(gp);

		this.gp = gp;
		type = type_monster;
		name = "Miniboss";
		speed = 1;
		maxLife = 10;
		life = maxLife;

		attack = 3;
		defense = 0;
		
		solidArea.x = 20;
		solidArea.y = 8;
		solidArea.width = 70;
		solidArea.height = 60;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
		getAttackImage();
	}	 

	public void getImage(){
		
		left1 = setup("/monster/miniboss_idle_L1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/miniboss_idle_L2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/miniboss_idle_L3", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/miniboss_idle_L4", gp.tileSize, gp.tileSize);
		left5 = setup("/monster/miniboss_idle_L5", gp.tileSize, gp.tileSize);
		left6 = setup("/monster/miniboss_idle_L6", gp.tileSize, gp.tileSize);
		left7 = setup("/monster/miniboss_idle_L4", gp.tileSize, gp.tileSize);
		left8 = setup("/monster/miniboss_idle_L5", gp.tileSize, gp.tileSize);
		left9 = setup("/monster/miniboss_idle_L6", gp.tileSize, gp.tileSize);
		
		right1 = setup("/monster/miniboss_idle_R1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/miniboss_idle_R2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/miniboss_idle_R3", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/miniboss_idle_R4", gp.tileSize, gp.tileSize);
		right5 = setup("/monster/miniboss_idle_R5", gp.tileSize, gp.tileSize);
		right6 = setup("/monster/miniboss_idle_R6", gp.tileSize, gp.tileSize);
		right7 = setup("/monster/miniboss_idle_R4", gp.tileSize, gp.tileSize);
		right8 = setup("/monster/miniboss_idle_R5", gp.tileSize, gp.tileSize);
		right9 = setup("/monster/miniboss_idle_R6", gp.tileSize, gp.tileSize);
		
		up1 = setup("/monster/miniboss_idle_L1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/miniboss_idle_L2", gp.tileSize, gp.tileSize);
		up3 = setup("/monster/miniboss_idle_L3", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/miniboss_idle_L4", gp.tileSize, gp.tileSize);
		up5 = setup("/monster/miniboss_idle_L5", gp.tileSize, gp.tileSize);
		up6 = setup("/monster/miniboss_idle_L6", gp.tileSize, gp.tileSize);
		up7 = setup("/monster/miniboss_idle_L4", gp.tileSize, gp.tileSize);
		up8 = setup("/monster/miniboss_idle_L5", gp.tileSize, gp.tileSize);
		up9 = setup("/monster/miniboss_idle_L6", gp.tileSize, gp.tileSize);
		
		down1 = setup("/monster/miniboss_idle_R1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/miniboss_idle_R2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/miniboss_idle_R3", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/miniboss_idle_R4", gp.tileSize, gp.tileSize);
		down5 = setup("/monster/miniboss_idle_R5", gp.tileSize, gp.tileSize);
		down6 = setup("/monster/miniboss_idle_R6", gp.tileSize, gp.tileSize);
		down7 = setup("/monster/miniboss_idle_R4", gp.tileSize, gp.tileSize);
		down8 = setup("/monster/miniboss_idle_R5", gp.tileSize, gp.tileSize);
		down9 = setup("/monster/miniboss_idle_R6", gp.tileSize, gp.tileSize);

		
		die = setup("/monster/miniboss_died",gp.tileSize*2,gp.tileSize);
	}
	public void getAttackImage() {
		attackLeft1 = setup("/monster/miniboss_attack_L1", gp.tileSize, gp.tileSize);
		attackLeft2 = setup("/monster/miniboss_attack_L2", gp.tileSize, gp.tileSize);
		attackLeft3 = setup("/monster/miniboss_attack_L3", gp.tileSize, gp.tileSize);
		attackLeft4 = setup("/monster/miniboss_attack_L4", gp.tileSize, gp.tileSize);
		attackLeft5 = setup("/monster/miniboss_attack_L5", gp.tileSize, gp.tileSize);
		attackLeft6 = setup("/monster/miniboss_attack_L6", gp.tileSize, gp.tileSize);
		attackLeft7 = setup("/monster/miniboss_attack_L7", gp.tileSize, gp.tileSize);
		attackLeft8 = setup("/monster/miniboss_attack_L8", gp.tileSize, gp.tileSize);
		attackLeft9 = setup("/monster/miniboss_attack_L9", gp.tileSize, gp.tileSize);
		
		attackRight1 = setup("/monster/miniboss_attack_R1", gp.tileSize, gp.tileSize);
		attackRight2 = setup("/monster/miniboss_attack_R2", gp.tileSize, gp.tileSize);
		attackRight3 = setup("/monster/miniboss_attack_R3", gp.tileSize, gp.tileSize);
		attackRight4 = setup("/monster/miniboss_attack_R4", gp.tileSize, gp.tileSize);
		attackRight5 = setup("/monster/miniboss_attack_R5", gp.tileSize, gp.tileSize);
		attackRight6 = setup("/monster/miniboss_attack_R6", gp.tileSize, gp.tileSize);
		attackRight7 = setup("/monster/miniboss_attack_R7", gp.tileSize, gp.tileSize);
		attackRight8 = setup("/monster/miniboss_attack_R8", gp.tileSize, gp.tileSize);
		attackRight9 = setup("/monster/miniboss_attack_R9", gp.tileSize, gp.tileSize);
	}
	
	public void update(){

		setAction();
		checkCollison();
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
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 5;
			else if(spriteNum == 5) spriteNum = 6;
			else if(spriteNum == 6) spriteNum = 7;
			else if(spriteNum == 7) spriteNum = 8;
			else if(spriteNum == 8) spriteNum = 9;
			else if(spriteNum == 9) spriteNum = 1;               
			spriteCounter = 0;
		}

		if(invincible == true){
			invincibleCounter ++;
			if(invincibleCounter > 40){
				invincible = false;
				invincibleCounter = 0;
			}
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
				if(attacking == false) {
					if(spriteNum == 1) image = up1;
					if(spriteNum == 2) image = up2;
					if(spriteNum == 3) image = up3;
					if(spriteNum == 4) image = up4;
					if(spriteNum == 5) image = up5;
					if(spriteNum == 6) image = up6;
					if(spriteNum == 7) image = up7;
					if(spriteNum == 8) image = up8;	  
					if(spriteNum == 9) image = up9;	
				}
				if(attacking == true) {
					if(spriteNum == 1) image = attackLeft1;
					if(spriteNum == 2) image = attackLeft2;
					if(spriteNum == 3) image = attackLeft3;
					if(spriteNum == 4) image = attackLeft4;
					if(spriteNum == 5) image = attackLeft5;
					if(spriteNum == 6) image = attackLeft6;
					if(spriteNum == 7) image = attackLeft7;
					if(spriteNum == 8) image = attackLeft8;
					if(spriteNum == 9) image = attackLeft9;	
				}
				break;
			case "down":
				if(attacking == false) {
					if(spriteNum == 1) image = down1;
					if(spriteNum == 2) image = down2;
					if(spriteNum == 3) image = down3;
					if(spriteNum == 4) image = down4;
					if(spriteNum == 5) image = down5;
					if(spriteNum == 6) image = down6;
					if(spriteNum == 7) image = down7;
					if(spriteNum == 8) image = down8;
					if(spriteNum == 9) image = down9;	
				}            	  
				if(attacking == true) {
					if(spriteNum == 1) image = attackRight1;
					if(spriteNum == 2) image = attackRight2;
					if(spriteNum == 3) image = attackRight3;
					if(spriteNum == 4) image = attackRight4;
					if(spriteNum == 5) image = attackRight5;
					if(spriteNum == 6) image = attackRight6;
					if(spriteNum == 7) image = attackRight7;
					if(spriteNum == 8) image = attackRight8;
					if(spriteNum == 9) image = attackRight9;	
				}
				break;    
			case "right":
				if(attacking == false) {
					if(spriteNum == 1) image = right1;
					if(spriteNum == 2) image = right2;
					if(spriteNum == 3) image = right3;
					if(spriteNum == 4) image = right4;
					if(spriteNum == 5) image = right5;
					if(spriteNum == 6) image = right6;
					if(spriteNum == 7) image = right7;
					if(spriteNum == 8) image = right8;
					if(spriteNum == 9) image = right9;
				}	            			                
				if(attacking == true) {
					if(spriteNum == 1) image = attackRight1;
					if(spriteNum == 2) image = attackRight2;
					if(spriteNum == 3) image = attackRight3;
					if(spriteNum == 4) image = attackRight4;
					if(spriteNum == 5) image = attackRight5;
					if(spriteNum == 6) image = attackRight6;
					if(spriteNum == 7) image = attackRight7;
					if(spriteNum == 8) image = attackRight8;
					if(spriteNum == 9) image = attackRight9;	
				}
				break;
			case "left":
				if(attacking == false) {
					if(spriteNum == 1) image = left1;
					if(spriteNum == 2) image = left2;
					if(spriteNum == 3) image = left3;
					if(spriteNum == 4) image = left4;
					if(spriteNum == 5) image = left5;
					if(spriteNum == 6) image = left6;
					if(spriteNum == 7) image = left7;
					if(spriteNum == 8) image = left8;
					if(spriteNum == 9) image = left9;
				}	            		                
				if(attacking == true) {
					if(spriteNum == 1) image = attackLeft1;
					if(spriteNum == 2) image = attackLeft2;
					if(spriteNum == 3) image = attackLeft3;
					if(spriteNum == 4) image = attackLeft4;
					if(spriteNum == 5) image = attackLeft5;
					if(spriteNum == 6) image = attackLeft6;
					if(spriteNum == 7) image = attackLeft7;
					if(spriteNum == 8) image = attackLeft8;
					if(spriteNum == 9) image = attackLeft9;	
				}
				break;
		}

		// Monster HP bar
		if(type == 2 && hpBarOn == true){
			double oneScale = (double) gp.tileSize/maxLife;
			double hpBarValue = oneScale*life;

			g2.setColor(new Color(35,35,35));
			g2.fillRect(screenX +45 ,screenY - 16,gp.tileSize+2,7);
			g2.setColor(new Color(255,0,30));
			g2.fillRect(screenX +46, screenY-15, (int) hpBarValue, 5);

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
			if(dyingCounter > jump*8 && dyingCounter <= jump*31) {        		 
				changeAlpha(g2, 1f);
				hpBarOn = false;
				image = die;
			}
			if(dyingCounter > jump*31){
				dying = false;
				alive = false;
			}
		}
		g2.drawImage(image, screenX , screenY , gp.tileSize*3 , gp.tileSize*3/2 , null);
		changeAlpha(g2, 1f);
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
		
		if(attacking == false) {
			checkAttackOrNot(gp.tileSize*4, gp.tileSize*4);
		}
	}
}
