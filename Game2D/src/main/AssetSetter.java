package main;

import entity.NPC_OldMan;

import object.OBJ_Door;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
        
    }
    public void setObject(){
        
        // gp.obj[0] = new OBJ_Door(gp);
        // gp.obj[0].worldX =  gp.tileSize *3;
        // gp.obj[0].worldY =  gp.tileSize *13;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX =  gp.tileSize *21;
        gp.obj[1].worldY =  gp.tileSize *6;
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

    }

    public void setMonster(){
        // TODO: Set vị trí cho quái
    }
}
