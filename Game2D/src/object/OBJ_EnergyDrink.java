package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_EnergyDrink extends Entity{

    public OBJ_EnergyDrink(GamePanel gp){

        super(gp);

        name = "Energy Drink";
        down1 = setup("/objects/buff_attack", gp.tileSize, gp.tileSize);
        decription = "[" + name + "]\nDrink to increase\nattack damage.";
    }
}
