package main;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public EventHandler(GamePanel gp){

        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent(){

        // Check if the player character is more than 1 tile away from the last event (Kiểm tra xem nếu người chơi đang cách nhiều hơn 1 tile từ sự kiện trước đó)
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance =  Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(hit(21, 6, "any") == true){teleport(gp.dialogueState, 51, 4);}
        // TODO: Vị trí để teleport, cửa sẽ được đặt ở đây
        if(hit(51, 1, "any") == true){teleport(gp.dialogueState,21, 1);}
    }

    public boolean hit(int col, int row, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        
        return hit;
    }
    public void teleport(int gameState, int col_position, int row_position){

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = col_position*gp.tileSize;
        // TODO: Ô (51,4) sẽ có một cửa để teleport ra
        gp.player.worldY = row_position*gp.tileSize;
    }

}
