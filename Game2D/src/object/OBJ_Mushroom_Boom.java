package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Mushroom_Boom extends Projectile{
	GamePanel gp;

	public OBJ_Mushroom_Boom(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "MushroomBoom";
		speed = 4;
		maxLife = 120;
		life = maxLife;
		attack = 2;
		alive = false;
		
		getImage();
     }
	
	public void getImage() {		
		left1 = setup("/projectile/mushroom_bom1", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/mushroom_bom2", gp.tileSize, gp.tileSize);
		left3 = setup("/projectile/mushroom_bom3", gp.tileSize, gp.tileSize);
		left4 = setup("/projectile/mushroom_bom4", gp.tileSize, gp.tileSize);
		left5 = setup("/projectile/mushroom_bom5", gp.tileSize, gp.tileSize);
		left6 = setup("/projectile/mushroom_bom6", gp.tileSize, gp.tileSize);
		left7 = setup("/projectile/mushroom_bom7", gp.tileSize, gp.tileSize);
		left8 = setup("/projectile/mushroom_bom8", gp.tileSize, gp.tileSize);
		
		right1 = setup("/projectile/mushroom_bom1", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/mushroom_bom2", gp.tileSize, gp.tileSize);
		right3 = setup("/projectile/mushroom_bom3", gp.tileSize, gp.tileSize);
		right4 = setup("/projectile/mushroom_bom4", gp.tileSize, gp.tileSize);
		right5 = setup("/projectile/mushroom_bom5", gp.tileSize, gp.tileSize);
		right6 = setup("/projectile/mushroom_bom6", gp.tileSize, gp.tileSize);
		right7 = setup("/projectile/mushroom_bom7", gp.tileSize, gp.tileSize);
		right8 = setup("/projectile/mushroom_bom8", gp.tileSize, gp.tileSize);		
		
	}
	
    public void update() {
		
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if(gp.player.invincible == false && contactPlayer == true) {
			damagePlayer(attack);
			alive = false;
		}
		
		spriteCounter ++;
        if(spriteCounter > 14){
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
		
		switch(direction) {
		case "left": 
			worldX -= speed; 				
			break;
		case "right": 
			worldX += speed; 
			break;
		}
		
		life--;
		if(life <= 0) {
			alive = false;
		}
		
	}
	
	// SUBDRAW
    public void subdraw(Graphics2D g2, int screenX, int screenY){

        BufferedImage image = null;
        switch(direction){
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
        g2.drawImage(image, screenX , screenY , gp.tileSize , gp.tileSize , null);      
    }
}
