package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {

    GamePanel gp;
    public BufferedImage up1 , up2 ,up3, up4,up5,up6,up7,up8,
    down1 , down2 ,down3 ,down4,down5,down6,down7,down8,
    right1 , right2 ,right3 ,right4,right5,right6,right7,right8,
    left1 , left2 ,left3 ,left4,left5,left6,left7,left8;

    public BufferedImage die = null;

    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackUp5,attackUp6,
    attackDown1, attackDown2, attackDown3, attackDown4, attackDown5,attackDown6,
    attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5,attackLeft6,
    attackRight1, attackRight2, attackRight3, attackRight4, attackRight5,attackRight6;

    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0 , 0 , 48 , 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues [] = new String[20];

    // STATE
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

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;

    // CHARACTER ATTIBUTES
    public int type; // 0: player , 1: npc , 2: monster
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
    public int coin;
    public Entity currentWeapon;// Vũ khí hiện tại
    public Entity currentShield;// Lá chắn hiện tại

    // ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;


    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction() {}
    public void damageReaction(){}
    public void speak() {}
    public void update(){

        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                // We can give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }

        }
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
    }
    public void draw(Graphics2D g2){

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
            
                subdraw(g2, screenX, screenY);
        }
        else if(gp.player.screenX > gp.player.worldX || gp.player.screenY > gp.player.worldY ||
            rightOffset > gp.worldWidth - gp.player.worldX ||
            bottomOffset > gp.worldHeight - gp.player.worldY) {
                // WHEN STOP CAMERA
                subdraw(g2, screenX, screenY);
        }
    }
    // SUBDRAW
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

        // Monster HP bar
        if(type == 2 && hpBarOn == true){
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
            
            if(die == null) {
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
            }else {
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
        }
        g2.drawImage(image, screenX , screenY , gp.tileSize , gp.tileSize , null);
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
}
