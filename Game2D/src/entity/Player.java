package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    // public int hasKey = 0;
    public int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = 30;
        solidArea.width = 30;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;// 1 life = 1/2 heart
    }
    public void getPlayerImage(){
        up1 = setup("/player/player_up1");
        up2 = setup("/player/player_up2");
        down1 = setup("/player/player_down1");
        down2 = setup("/player/player_down2");
        right1 = setup("/player/player_right1");
        right2 = setup("/player/player_right2");
        left1 = setup("/player/player_left1");
        left2 = setup("/player/player_left2");
    }
    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true 
            || keyH.rightPressed == true || keyH.leftPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

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
        }
        else{
            standCounter ++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }
    public void pickUpObject(int i){

        if(i != 999){
           // TODO: Xử lí va chạm với vật thể
        }

    }
    public void interactNPC(int i){

        if(i != 999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }
    public void draw(Graphics2D g2){

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
        g2.drawImage(image , screenX , screenY, null );
    }
}
