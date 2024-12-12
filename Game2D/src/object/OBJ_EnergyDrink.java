package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_EnergyDrink extends Entity{

    GamePanel gp;
    public OBJ_EnergyDrink(GamePanel gp){

        super(gp);

        this.gp = gp;
        type = type_consumable;
        value = 1;
        name = "Energy Drink";
        down1 = setup("/objects/buff_attack", gp.tileSize, gp.tileSize);
        decription = "[" + name + "]\nDrink to increase " + value + "\nattack damage.";
    }
    public void use(Entity entity){

        gp.gameState = gp.playState;
        gp.ui.addMessage("You drink the " + name);
        gp.ui.addMessage("Your strength has been increased " + value + " damage.");
        entity.attack += value;
    }
}
