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
        speed = 3;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;// 1 life = 1/2 heart
    }
    public void getPlayerImage(){
        up1 = setup("/player/runsau1");
        up2 = setup("/player/runsau2");
        up3 = setup("/player/runsau3");
        up4 = setup("/player/runsau4");

        down1 = setup("/player/runtruoc1");
        down2 = setup("/player/runtruoc2");
        down3 = setup("/player/runtruoc3");
        down4 = setup("/player/runtruoc4");

        right1 = setup("/player/runright1");
        right2 = setup("/player/runright2");
        right3 = setup("/player/runright3");
        right4 = setup("/player/runright4");

        left1 = setup("/player/runleft1");
        left2 = setup("/player/runleft2");
        left3 = setup("/player/runleft3");
        left4 = setup("/player/runleft4");
    }
    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true 
            || keyH.rightPressed == true || keyH.leftPressed == true){
            if(keyH.upPressed == true && keyH.rightPressed == true){
                direction = "up_right";
            }
            else if(keyH.upPressed == true && keyH.leftPressed == true){
                direction = "up_left";
            }
            else if(keyH.downPressed == true && keyH.rightPressed == true){
                direction = "down_right";
            }
            else if(keyH.downPressed == true && keyH.leftPressed == true){
                direction = "down_left";
            }

            else if(keyH.upPressed == true){
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

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

            if(collisionOn == false){
                switch (direction) {
                    case "up": worldY -= speed ; break;
                    case "down": worldY += speed; break;
                    case "right": worldX += speed; break;
                    case "left": worldX -= speed; break;
                    case "up_right":
                        worldY -= (int) (speed / Math.sqrt(2));
                        worldX += (int) (speed / Math.sqrt(2));
                        break;
                    case "up_left":
                        worldY -= (int) (speed / Math.sqrt(2));
                        worldX -= (int) (speed / Math.sqrt(2));
                        break;
                    case "down_right":
                        worldY += (int) (speed / Math.sqrt(2));
                        worldX += (int) (speed / Math.sqrt(2));
                        break;
                    case "down_left":
                        worldY += (int) (speed / Math.sqrt(2));
                        worldX -= (int) (speed / Math.sqrt(2));
                        break;
                }
            }
            spriteCounter ++;
            if(spriteCounter > 10){
                if(spriteNum == 1) spriteNum = 2;
                else if(spriteNum == 2) spriteNum = 3;
                else if(spriteNum == 3) spriteNum = 4;
                else if(spriteNum == 4) spriteNum = 1;
                spriteCounter = 0;
            }
        }
        else{
            standCounter ++;
            if(standCounter == 20){
                spriteNum = 4;
                standCounter = 0;
            }
        }

        // INVINCIBLE COUNTER
        if(invincible == true){
            invincibleCounter ++;
            if(invincibleCounter > 60){
                invincible = false;

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
    }

    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                life -= 1;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                if(spriteNum == 3) image = up3;
                if(spriteNum == 4) image = up4;
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                if(spriteNum == 3) image = down3;
                if(spriteNum == 4) image = down4;
                break;    
            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                if(spriteNum == 3) image = right3;
                if(spriteNum == 4) image = right4;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                if(spriteNum == 3) image = left3;
                if(spriteNum == 4) image = left4;
                break;
            case "up_right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                if(spriteNum == 3) image = right3;
                if(spriteNum == 4) image = right4;
                break;
            case "up_left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                if(spriteNum == 3) image = left3;
                if(spriteNum == 4) image = left4;
                break;
            case "down_right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                if(spriteNum == 3) image = right3;
                if(spriteNum == 4) image = right4;
                break;
            case "down_left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                if(spriteNum == 3) image = left3;
                if(spriteNum == 4) image = left4;
                break;
    
        }

        int x = screenX;
        int y = screenY;

        if(screenX > worldX){
            x = worldX;
        }
        if(screenY > worldY){
            y = worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.worldWidth - worldX){
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }

        int bottomOffset = gp.screenHeight - screenY;
        if(bottomOffset > gp.worldHeight - worldY){
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }
        
        g2.drawImage(image , x , y, null );
    }
}
