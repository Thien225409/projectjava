package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HP extends Entity{

    GamePanel gp;
    public OBJ_HP(GamePanel gp){

        super(gp);
        
        this.gp = gp;
        type = type_consumable;
        value = 2;
        name = "HP";
        down1 = setup("/objects/hp", gp.tileSize, gp.tileSize);
        decription = "[" + name + "]\nDrink to recover " + value + ".";
    }
    public boolean use(Entity entity){

        gp.gameState = gp.playState;
        gp.ui.addMessage("You drink the " + name);
        gp.ui.addMessage("Your life has been recovered by " + value + " half heart.");
        entity.life += value;
        return true;
    }
}
