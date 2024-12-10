package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HP extends Entity{

    public OBJ_HP(GamePanel gp){

        super(gp);
        
        type = type_consumable;
        name = "HP";
        down1 = setup("/objects/hp", gp.tileSize, gp.tileSize);
        decription = "[" + name + "]\nDrink to recover 1 HP.";
    }
}
