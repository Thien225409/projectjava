package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Key";
        type = type_rare;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        decription = "[" + name + "]\nIt opens a door.";
    }

    public void use(Entity entity){

        int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
        if(npcIndex != 999 && gp.player.hasKey != 0){
            gp.gameState = gp.playState;
            gp.player.hasKey --;
            gp.npc[npcIndex] = null;
        }
    }
}
