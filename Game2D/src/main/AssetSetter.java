package main;

import entity.NPC_OldMan;
import main.Monster.MON_Ghost;
import main.Monster.MON_Skeleton;
import main.Monster.MON_Slime;
import object.OBJ_Door;
import object.OBJ_HP;
import object.OBJ_HP_half;

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

        gp.obj[2] = new OBJ_HP(gp);
        gp.obj[2].worldX =  gp.tileSize *29;
        gp.obj[2].worldY =  gp.tileSize *20;

        gp.obj[3] = new OBJ_HP_half(gp);
        gp.obj[3].worldX =  gp.tileSize *29;
        gp.obj[3].worldY =  gp.tileSize *23;
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 3;
        gp.npc[0].worldY = gp.tileSize * 13;

    }

    public void setMonster(){
        // TODO: Set vị trí cho quái
        gp.monster[0] = new MON_Skeleton(gp);
        gp.monster[0].worldX = gp.tileSize *3;
        gp.monster[0].worldY = gp.tileSize *22;

        gp.monster[1] = new MON_Skeleton(gp);
        gp.monster[1].worldX = gp.tileSize * 2;
        gp.monster[1].worldY = gp.tileSize *24;

        gp.monster[2] = new MON_Skeleton(gp);
        gp.monster[2].worldX = gp.tileSize * 9;
        gp.monster[2].worldY = gp.tileSize * 47;
        
        gp.monster[3] = new MON_Skeleton(gp);
        gp.monster[3].worldX = gp.tileSize * 3;
        gp.monster[3].worldY = gp.tileSize * 43;
        
        gp.monster[4] = new MON_Skeleton(gp);
        gp.monster[4].worldX = gp.tileSize * 23;
        gp.monster[4].worldY = gp.tileSize * 47;
        
        gp.monster[5] = new MON_Skeleton(gp);
        gp.monster[5].worldX = gp.tileSize * 28;
        gp.monster[5].worldY = gp.tileSize * 47;
        
        gp.monster[6] = new MON_Skeleton(gp);
        gp.monster[6].worldX = gp.tileSize * 35;
        gp.monster[6].worldY = gp.tileSize * 47;
                
        gp.monster[7] = new MON_Skeleton(gp);
        gp.monster[7].worldX = gp.tileSize * 51;
        gp.monster[7].worldY = gp.tileSize * 43;
                
        gp.monster[8] = new MON_Skeleton(gp);
        gp.monster[8].worldX = gp.tileSize * 66;
        gp.monster[8].worldY = gp.tileSize * 46;
                
        gp.monster[9] = new MON_Skeleton(gp);
        gp.monster[9].worldX = gp.tileSize * 20;
        gp.monster[9].worldY = gp.tileSize * 34;
                
        gp.monster[10] = new MON_Skeleton(gp);
        gp.monster[10].worldX = gp.tileSize * 27;
        gp.monster[10].worldY = gp.tileSize * 39;
                
        gp.monster[11] = new MON_Skeleton(gp);
        gp.monster[11].worldX = gp.tileSize * 37;
        gp.monster[11].worldY = gp.tileSize * 41;
                
        gp.monster[12] = new MON_Skeleton(gp);
        gp.monster[12].worldX = gp.tileSize * 45;
        gp.monster[12].worldY = gp.tileSize * 38;
                
        gp.monster[13] = new MON_Skeleton(gp);
        gp.monster[13].worldX = gp.tileSize * 55;
        gp.monster[13].worldY = gp.tileSize * 38;
                
        gp.monster[14] = new MON_Skeleton(gp);
        gp.monster[14].worldX = gp.tileSize * 67;
        gp.monster[14].worldY = gp.tileSize * 39;

        gp.monster[15] = new MON_Ghost(gp);
        gp.monster[15].worldX = gp.tileSize * 8;
        gp.monster[15].worldY = gp.tileSize * 8;
        
        gp.monster[16] = new MON_Ghost(gp);
        gp.monster[16].worldX = gp.tileSize * 23;
        gp.monster[16].worldY = gp.tileSize * 8;
        
        gp.monster[17] = new MON_Ghost(gp);
        gp.monster[17].worldX = gp.tileSize * 38;
        gp.monster[17].worldY = gp.tileSize * 8;
        
        gp.monster[18] = new MON_Ghost(gp);
        gp.monster[18].worldX = gp.tileSize * 13;
        gp.monster[18].worldY = gp.tileSize * 23;
        
        gp.monster[19] = new MON_Ghost(gp);
        gp.monster[19].worldX = gp.tileSize * 28;
        gp.monster[19].worldY = gp.tileSize * 23;
        
        gp.monster[20] = new MON_Ghost(gp);
        gp.monster[20].worldX = gp.tileSize * 18;
        gp.monster[20].worldY = gp.tileSize * 38;
        
        gp.monster[21] = new MON_Ghost(gp);
        gp.monster[21].worldX = gp.tileSize * 33;
        gp.monster[21].worldY = gp.tileSize * 38;
        
        gp.monster[22] = new MON_Ghost(gp);
        gp.monster[22].worldX = gp.tileSize * 48;
        gp.monster[22].worldY = gp.tileSize * 38;

        gp.monster[23] = new MON_Slime(gp);
        gp.monster[23].worldX = gp.tileSize * 4;
        gp.monster[23].worldY = gp.tileSize * 15;
        
        gp.monster[24] = new MON_Slime(gp);
        gp.monster[24].worldX = gp.tileSize * 15;
        gp.monster[24].worldY = gp.tileSize * 15;
        
        gp.monster[25] = new MON_Slime(gp);
        gp.monster[25].worldX = gp.tileSize * 25;
        gp.monster[25].worldY = gp.tileSize * 15;
        
        gp.monster[26] = new MON_Slime(gp);
        gp.monster[26].worldX = gp.tileSize * 48;
        gp.monster[26].worldY = gp.tileSize * 20;
        
        gp.monster[27] = new MON_Slime(gp);
        gp.monster[27].worldX = gp.tileSize * 50;
        gp.monster[27].worldY = gp.tileSize * 25;
        
        gp.monster[28] = new MON_Slime(gp);
        gp.monster[28].worldX = gp.tileSize * 50;
        gp.monster[28].worldY = gp.tileSize * 35;
        
        gp.monster[29] = new MON_Slime(gp);
        gp.monster[29].worldX = gp.tileSize * 10;
        gp.monster[29].worldY = gp.tileSize * 48;
        
        gp.monster[30] = new MON_Slime(gp);
        gp.monster[30].worldX = gp.tileSize * 4;
        gp.monster[30].worldY = gp.tileSize * 43;
        
        gp.monster[31] = new MON_Slime(gp);
        gp.monster[31].worldX = gp.tileSize * 25;
        gp.monster[31].worldY = gp.tileSize * 48;
        
        gp.monster[32] = new MON_Slime(gp);
        gp.monster[32].worldX = gp.tileSize * 22;
        gp.monster[32].worldY = gp.tileSize * 36;
        
        gp.monster[33] = new MON_Slime(gp);
        gp.monster[33].worldX = gp.tileSize * 66;
        gp.monster[33].worldY = gp.tileSize * 39;
        
        gp.monster[34] = new MON_Slime(gp);
        gp.monster[34].worldX = gp.tileSize * 35;
        gp.monster[34].worldY = gp.tileSize * 15;
        

    }
}
