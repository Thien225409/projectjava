package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HP_half extends Entity{

    public OBJ_HP_half(GamePanel gp){

        super(gp);

        name = "HP_half";
        down1 = setup("/objects/hp_half", gp.tileSize, gp.tileSize);

    }
}
