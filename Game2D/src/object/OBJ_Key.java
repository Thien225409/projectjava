package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Key";
        type = type_consumable;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        decription = "[" + name + "]\nIt opens a door.";
    }

    public boolean use(Entity entity){

        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Chest");

        if(objIndex != 999){
            gp.ui.currentDialogue = "You use the " + name + " and open the chest";
            gp.obj[objIndex] = null;
            return true;
        }
        else{
            gp.ui.currentDialogue = "What are you doing?";
            return false;
        }

    }
}
