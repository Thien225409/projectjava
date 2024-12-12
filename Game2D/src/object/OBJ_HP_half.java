package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HP_half extends Entity{

    GamePanel gp;
    public OBJ_HP_half(GamePanel gp){

        super(gp);
        this.gp = gp;
        type = type_consumable;
        value = 1;
        name = "HP_half";
        down1 = setup("/objects/hp_half", gp.tileSize, gp.tileSize);
        decription = "[" + name + "]\nDrink to recover " + value + ".";
    }
    public void use(Entity entity){

        gp.gameState = gp.playState;
        gp.ui.addMessage("You drink the " + name);
        gp.ui.addMessage("Your life has been recovered by " + value + " half heart.");
        entity.life += value;
    }
}
