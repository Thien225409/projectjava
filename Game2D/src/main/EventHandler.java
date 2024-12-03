package main;

import java.awt.Rectangle;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){

        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){

        // if(hit()){damagePit(gp.dialogueState);}// TODO: Các địa điểm bị dính sát thương
        // if(hit()){healingPool(gp.dialogueState);}// TODO: Các địa điểm để hồi máu (hồi nửa trái tim)
        if(hit(20, 10, "any") == true){teleport(gp.dialogueState);}// TODO: Vị trí để teleport

    }

    public void damagePit(int gameState){

        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit";
        gp.player.life -= 1;

    }

    public void healingPool(int gameState){

        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "";// TODO: Viết code Khi hồi máu thì hiển thị dialogue
            if(gp.player.life < gp.player.maxLife){
                gp.player.life += 1;
            }
        }
    }

    public void teleport(int gameState){

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = 60*gp.tileSize;
        gp.player.worldY = 5*gp.tileSize;
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        
        return hit;
    }
}
