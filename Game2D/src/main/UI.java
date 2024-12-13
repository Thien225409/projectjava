package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import object.OBJ_Heart;
import entity.Entity;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    FontMetrics metrics;
    Font arial_40, arial_80, arial_28;

    // BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        // arial_80B = new Font("Arial", Font.BOLD, 80);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        arial_28 = new Font("Arial", Font.PLAIN, 28);

        // OBJ_Key key = new OBJ_Key(gp);
        // keyImage = key.image;

        // CREATE HUD OBJECT (Tạo vật thể "HUD"???)
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }
    public void addMessage(String text){

        message.add(text);
        messageCounter.add(0);
    }
    // public void drawCenterTextUI(Font font, Color color, String text, int y){
    //     g2.setFont(font);
    //     g2.setColor(color);
    //     int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    //     int xCenter = gp.screenWidth/2 - textLength/2;
    //     g2.drawString(text, xCenter , y);
    // }
    //TODO: Hàm in ra Congratulation khi chiến thắng
    /* public void draw(Graphics2D g2){
        if(gameFinished == true){

            String text;
            
            text = "You found the treasure!";
            drawTextUI(g2, arial_40, Color.WHITE, text, gp.screenHeight/2 - gp.tileSize *3);

            text = "Your Time is :" + dFormat.format(playTime) + "!";
            drawTextUI(g2, arial_40, Color.WHITE, text, gp.screenHeight/2 + gp.tileSize *3);

            text = "Congratulation!";
            drawTextUI(g2, arial_80B, Color.YELLOW, text, gp.screenHeight/2 + gp.tileSize *2);

            gp.gameThread = null;
        }
        else{
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize , gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 62);
            //TIME
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 62);

            //MESSAGE
            if(messageOn){

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter ++;
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){

        }
        if(gp.gameState == gp.OptionsState){

            drawOptionsScreen();

        }
    }
    */
    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // TITLE STATE (Trạng thái màn hình menu)
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // PLAY STATE (Trạng thái đang chơi)
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        // OPTIONS STATE (Trạng thái tùy chọn cài đặt)
        if(gp.gameState == gp.optionsState){
            drawPlayerLife();
            drawOptionsScreen();
        }
        // DIALOGUE STATE (Trạng thái đang có thoại)
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        // CHARACTER STATE (Trạng thái đang mở bảng thông số nhân vật)
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
    }
    public void drawMessage(){
        
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));

        for(int i = 0; i < message.size(); i++){

            if(message.get(i) != null){

                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 30;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DRAW MAX LIFE (Vẽ full HP nhân vật)
        while(i < gp.player.maxLife/2){

            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        
        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        // DRAW CURRENT LIFE (Vẽ thanh HP hiện tại)
        while(i < gp.player.life){
            
            g2.drawImage(heart_half,x , y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public int getXforCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public void drawTitleScreen(){

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME (Tên tiêu đề)
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String text = "Name Game Here";
        int x = getXforCenterText(text);
        int y = gp.tileSize*3;

        // SHADOW (Bóng)
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);
        // MAIN COLOR (Màu chính)
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // (MAIN CHARACTER) IMAGE ((Nhân vật chính) Hình ảnh)
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        // MENU 
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

        text = "NEW GAME";
        x = getXforCenterText(text);
        y += gp.tileSize*3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
    public void drawOptionsScreen(){

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));
        // CREATE A FRAME (Tạo 1 khung hình)
        final int frameX = gp.tileSize*6;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*8;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX , frameY); break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }

        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY){
        
        int textX;
        int textY;

        // TITLE (Tiêu đề)
        String text = "Options";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF (Bật/Tắt toàn màn hình)
        text = "Full Screen";
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 28, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                } else if(gp.fullScreenOn == true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }
        // MUSIC (Nhạc)
        text = "Music";
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 28, textY);
        }
        // SE (Hiệu ứng âm thanh)
        text = "SE";
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX - 28, textY);
        }
        // CONTROL (Điều khiển)
        text = "Control";
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX - 28, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum = 0;
            }
        }
        // END GAME (Kết thúc trò chơi)
        text = "End Game";
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX - 28, textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commandNum = 0;
            }
        }
        // BACK (Quay lại)
        text = "Back";
        textY += gp.tileSize*2;
        g2.drawString(text, textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX - 28, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX (Check toàn màn hình)
        textX = frameX + gp.tileSize*5;
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24, 24);;
        }

        // MUSIC VOLUME (Âm lượng nhạc)
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);// 120/5 = 24
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE (Âm lượng hiệu ứng âm thanh)
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX , int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        currentDialogue = "The change will take \neffect after restarting \nthe game.";

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK (Quay lại)
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    public void drawDialogueScreen(){

        // WINDOW (Cửa sổ)
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - gp.tileSize*4;
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(arial_28);
        x += gp.tileSize/2;
        y += gp.tileSize;

        metrics = g2.getFontMetrics();
        int lineWidth = 0;
        int maxlineWidth = width - gp.tileSize;
        int xfordrawString = x;
        for(String line : currentDialogue.split(" ")){
            lineWidth += metrics.stringWidth(line + " ");
            
            if(lineWidth <= maxlineWidth){
                g2.drawString(line + " " , xfordrawString, y);
                xfordrawString += metrics.stringWidth(line + " ");
            }
            else{
                y += 40;
                xfordrawString = x;
                lineWidth = 0;
                g2.drawString(line + " ", xfordrawString , y);

                xfordrawString += metrics.stringWidth(line + " ");
                lineWidth += metrics.stringWidth(line + " ");
            }
        }

    }
    public void options_control(int frameX, int frameY){
        int textX;
        int textY;

        //TTITLE (Tiêu đề)
        String text = "Control";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Di chuyển", textX, textY); textY += gp.tileSize;
        g2.drawString("Tấn công", textX, textY); textY += gp.tileSize;
        g2.drawString("Kho đồ", textX, textY); textY += gp.tileSize;
        g2.drawString("Lựa chọn", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize*5;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("Enter", textX, textY); textY += gp.tileSize;
        g2.drawString("X", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;

        // BACK (Quay lại)
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        currentDialogue = "Quit the game and\nreturn to the title screen?";
        for(String line : currentDialogue.split("\n")){
            textX = getXforCenterText(line);
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXforCenterText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }
        // NO
        text = "No";
        textX = getXforCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 4;
            }
        }

    }
    public void drawCharacterScreen(){

        // CREATE A FRAME (Tạo 1 khung hình)
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*8;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT 
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(30F));;

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 38;
        
        // NAMES
        g2.drawString("Level", textX, textY);

        textY += lineHeight;
        g2.drawString("Life", textX, textY);

        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);

        textY += lineHeight;
        g2.drawString("Attack", textX, textY);

        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);

        textY += lineHeight;
        g2.drawString("NextLevel", textX, textY);

        // VALUES
        int tailX = (frameWidth + frameX) - 20;
        // Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
                
        textY += lineHeight;
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        
        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        
        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        
        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        
        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        
    }
    public int getXforAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    public void drawInventory(){

        // FRAME (Khung hình túi đồ)
        int frameX = gp.tileSize*12;
        int frameY = gp.tileSize;
        int frameHeight = gp.tileSize*5;
        int frameWidth = gp.tileSize*6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT (Slot đồ)
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        // CURSOR (Con trỏ)
        int cursorX = slotXstart + (slotSize* slotCol);
        int cursorY = slotYstart + (slotSize* slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        g2.setColor(new Color(240,190,90));
        g2.fillRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        // DRAW PLAYER'S ITEMS (Vẽ vật phẩm)
        for(int i = 0; i < gp.player.inventory.size(); i++){

            g2.drawImage(gp.player.inventory.get(i).down1, slotX,slotY,null);
            
            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        // DRAW CURSOR (Vẽ con trỏ)
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // DECRIPTION FRAME (Khung mô tả)
        int dframeX = frameX;
        int dframeY = frameY + frameHeight;
        int dframeHeight = gp.tileSize*3;
        int dframeWidth = frameWidth;

        // DRAW DECRIPTION TEXT (Mô tả)
        int textX = dframeX + 20;
        int textY = dframeY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()){

            drawSubWindow(dframeX, dframeY, dframeWidth, dframeHeight);
            for(String line : gp.player.inventory.get(itemIndex).decription.split("\n")){
                g2.drawString(line, textX , textY);
                textY += 32;
            }

        }
    }
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0 , 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x + 3, y + 3, width - 6, height - 6, 27, 27);

    }
}
