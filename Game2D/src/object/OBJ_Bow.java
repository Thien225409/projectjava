package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Bow extends Projectile{
	
	GamePanel gp;

	public OBJ_Bow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "SkeletonBow";
		speed = 5;
		maxLife = 120;
		life = maxLife;
		attack = 2;
		alive = false;
		
		getImage();
	}

	public void getImage() {
	
		left1 = setup("/projectile/skeleton_bow_L1", gp.tileSize/4, gp.tileSize/4);
		left2 = setup("/projectile/skeleton_bow_L2", gp.tileSize/4, gp.tileSize/4);
		
		right1 = setup("/projectile/skeleton_bow_R1", gp.tileSize/4, gp.tileSize/4);
		right2 = setup("/projectile/skeleton_bow_R2", gp.tileSize/4, gp.tileSize/4);
	
	}
	
}
