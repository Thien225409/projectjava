package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;
import object.OBJ_EnergyDrink;
import object.OBJ_HP;
import object.OBJ_HP_half;
import object.OBJ_Key;

public class Entity {

    GamePanel gp;
    public BufferedImage up1 , up2 ,up3, up4,up5,up6,up7,up8,up9,
    down1 , down2 ,down3 ,down4,down5,down6,down7,down8,down9,
    right1 , right2 ,right3 ,right4,right5,right6,right7,right8,right9,
    left1 , left2 ,left3 ,left4,left5,left6,left7,left8,left9,
    idleL1, idleL2, idleL3, idleL4, idleL5, idleL6,
    idleR1, idleR2, idleR3, idleR4, idleR5, idleR6;

    public BufferedImage die;

    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackUp5,attackUp6,attackUp7, attackUp8, attackUp9,
    attackDown1, attackDown2, attackDown3, attackDown4, attackDown5,attackDown6,attackDown7, attackDown8,attackDown9,
    attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5,attackLeft6,attackLeft7,attackLeft8, attackLeft9,
    attackRight1, attackRight2, attackRight3, attackRight4, attackRight5,attackRight6,attackRight7, attackRight8, attackRight9;

    public BufferedImage defendLeft1, defendLeft2, defendLeft3, defendLeft4, defendLeft5, defendLeft6,
    defendRight1, defendRight2, defendRight3, defendRight4, defendRight5, defendRight6;

    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0 , 0 , 48 , 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues [] = new String[20];

    // STATE (TRẠNG THÁI)
    public int worldX , worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;

    // COUNTER (BIẾN ĐẾM)
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;

    // CHARACTER ATTIBUTES (ĐẶC ĐIỂM NHÂN VẬT)
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int level;// Cấp độ
    public int strength;// Sức mạnh
    public int dexterity;// Khéo léo 
    public int attack;// Tấn công
    public int defense;// Phòng thủ
    public int exp;// Kinh nghiệm
    public int nextLevelExp;

    //Lớp attackMonster
    public Projectile projectile;

    //Kiểm tra khoảng cách tấn công của monster
    public int getXdistance(Entity target) {
    	int xDistance = Math.abs(worldX - target.worldX);
    	return xDistance;
    }
    public int getYdistance(Entity target) {
    	int yDistance = Math.abs(worldY - target.worldY);
    	return yDistance;
    }   

    // ITEM ATTRIBUTES (ĐẶC ĐIỂM VẬT PHẨM)
    public int value;
    public int attackValue;
    public int defenseValue;
    public String decription = "";

    // TYPE (LOẠI)
    public int type; // 0: player , 1: npc , 2: monster
    public final int type_player  = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_consumable = 3;
    public final int type_obstacle = 5;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public int getLeftX(){
        return worldX + solidArea.x;
    }
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY(){
        return worldY +solidArea.y;
    }
    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol(){
        return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow(){
        return (worldY + solidArea.y)/gp.tileSize;
    }
    public void setAction() {}
    public void damageReaction(){
        actionLockCounter = 0;
        // direction = gp.player.direction;
        onPath = true;
    }
    public void speak() {}
    public void interact(){}
    public boolean use(Entity entity){return false;}
    public void checkDrop(){
        int i = new Random().nextInt(100) + 1;
        //SET THE MONSTER DROP
        if(i < 25){
            dropItem(new OBJ_HP(gp));
        }
        if(i >= 25 && i < 50){
            if(gp.player.hasKey == 6){
                i = 80;
            }
            dropItem(new OBJ_Key(gp));
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_HP_half(gp));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_EnergyDrink(gp));
        }
    }
    public void dropItem(Entity droppedItem){

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] == null){
                gp.obj[i] = droppedItem;
                gp.obj[i].worldX =  worldX;
                gp.obj[i].worldY = worldY;
                break;
            }
        }
    }
    public void checkCollison(){

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer == true){
            damagePlayer(attack);
        }
    }
    public void update(){

        setAction();
        checkCollison();
        if(collisionOn == false){
            switch (direction) {
                case "up": worldY -= speed ; break;
                case "down": worldY += speed; break;
                case "right": worldX += speed; break;
                case "left": worldX -= speed; break;
                default: break;
            }
        }

        spriteCounter ++;
        if(spriteCounter > 10){
            if(spriteNum == 1) spriteNum = 2;
            else if(spriteNum == 2) spriteNum =1;
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
     
     public void checkAttackOrNot(int a, int b) {
    	boolean targetInRange = false;
    	int xDis = getXdistance(gp.player);
    	int yDis = getYdistance(gp.player);
    	
    	switch(direction) {
    	
    	case "left":
    		if(gp.player.worldX <= worldX && xDis < a && yDis < b) {
    			targetInRange = true;
    		}  		
    		break;
    	case "right":
    		
    		if(gp.player.worldX > worldX && xDis < a && yDis < b) {
    			targetInRange = true;
    		}
    		break;
    	}
    	
    	if(targetInRange == true) {
    		attacking = true;
    	}
    }

    public void damagePlayer(int attack) {
    	if(gp.player.invincible == false){
            // NGƯỜI CHƠI CÓ THỂ CHỊU DMGDMG
            int damage = attack - gp.player.defense;
            if(damage < 0) damage = 0;
            gp.player.life -= damage;
            switch(this.direction){
                case "left": gp.player.direction = "right";
                case "right": gp.player.direction = "left";
            }
            gp.player.invincible = true;
        }
    }
    
    public void draw(Graphics2D g2){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        // Stop moving the camere at the edge (Ngừng di chuyển camera khi ở lề)

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
            
                subdraw(g2, screenX, screenY);
        }
        else if(gp.player.screenX > gp.player.worldX || gp.player.screenY > gp.player.worldY ||
            rightOffset > gp.worldWidth - gp.player.worldX ||
            bottomOffset > gp.worldHeight - gp.player.worldY) {
                // WHEN STOP CAMERA (ngừng camera)
                subdraw(g2, screenX, screenY);
        }
    }
    // SUBDRAW (Vẽ phụ)
    public void subdraw(Graphics2D g2, int screenX, int screenY){

        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                break;    
            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                break;
        }

        // Monster HP bar (Thanh HP của quái)
        if(type == type_monster && hpBarOn == true){
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
                if(die == null) dyingCounter = jump*32;
                image = die;
            }
            if(dyingCounter > jump*31){
                alive = false;
            }
        }
        g2.drawImage(image, screenX , screenY, null);
        changeAlpha(g2, 1f);
    }
    

    public void changeAlpha(Graphics2D g2, float alphaValue){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow){

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(gp.pFinder.search() == true){

            // Next worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY   + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX  < nextX + gp.tileSize){
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX  < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                // left or right
                if(enLeftX > nextX) direction = "left";
                if(enLeftX < nextX) direction = "right";
            }
            else if(enTopY > nextY && enLeftX > nextX){
                //up or left
                direction = "up";
                checkCollison();
                if(collisionOn == true) direction = "left";
            }
            else if(enTopY > nextY && enLeftX < nextX){
                //up or right
                direction = "up";
                checkCollison();
                if(collisionOn == true) direction = "right";
            }
            else if(enTopY < nextY && enLeftX > nextX){
                //down or left
                direction = "down";
                checkCollison();
                if(collisionOn == true) direction = "left";
            }
            else if(enTopY < nextY && enLeftX < nextX){
                //down or right
                direction = "down";
                checkCollison();
                if(collisionOn == true) direction = "right";
            }
            // // Nếu tìm được đích thì dừng search()
            // int nextCol = gp.pFinder.pathList.get(0).col;
            // int nextRow = gp.pFinder.pathList.get(0).row;
            // if(nextCol == goalCol && nextRow == goalRow){
            //     onPath = false;
            // }
        }

    }
    public int getDetected(Entity user, Entity target[], String targetName){

        int index = 999;

        // Check the surrounding object 
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch(user.direction){
        case "up": nextWorldY = user.getTopY() - 1; break;
        case "down": nextWorldY = user.getBottomY() + 1; break;
        case "left": nextWorldX = user.getLeftX() - 1; break;
        case "right": nextWorldX = user.getRightX() + 1; break;
        case "up_right": 
            nextWorldY = user.getTopY() - 1;
            nextWorldX = user.getRightX() + 1;
            break;
        case "up_left": 
            nextWorldY = user.getTopY() - 1;
            nextWorldX = user.getLeftX() - 1;
            break;
        case "down_right":
            nextWorldY = user.getBottomY() + 1;
            nextWorldX = user.getRightX() + 1;
            break;
        case "down_left":
            nextWorldY = user.getBottomY() + 1;
            nextWorldX = user.getLeftX() - 1;
            break;
        }

        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for(int  i = 0; i < target.length; i++){
            if(target[i] != null){
                if(target[i].getCol() == col && target[i].getRow() == row
                    && target[i].name.equals(targetName)){

                    index = i;
                    break;

                }
            }
        }
        return index;
    }
}
