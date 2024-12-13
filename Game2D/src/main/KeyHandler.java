package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed , downPressed , rightPressed, leftPressed, enterPressed;
    //DEBUG
    boolean showDebugText = false;
    public KeyHandler(GamePanel gp){

        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE (Trạng thái menu game)
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        // PLAY STATE (Trạng thái đang chơi)
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        // OPTIONS STATE (Trạng thái tùy chọn, cài đặt)
        else if(gp.gameState == gp.optionsState){
            optionsState(code);
        }
        // DIALOGUE STATE (Trạng thái đang có thoại)
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        // CHARACTER STATE (Trạng thái mở bảng thuộc tính nhân vật)
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
    }

    public void titleState(int code){
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum --;
            if(gp.ui.commandNum < 0){
                // TODO: Khi thêm option thì cần thay đổi dòng này
                gp.ui.commandNum = 2;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum ++;
            // TODO: Khi thêm option thì cần thay đổi dòng này
            if(gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
        }

        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if(gp.ui.commandNum == 1){
                // ADD LOAD GAME 
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
    }
    public void playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.optionsState;
        }
        if(code == KeyEvent.VK_X){
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        //DEBUG
        if(code == KeyEvent.VK_T){
            if(showDebugText == false) showDebugText = true;
            else if(showDebugText == true) showDebugText = false;
        }
    }
    public void optionsState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum --;
            if(gp.ui.commandNum < 0) gp.ui.commandNum = maxCommandNum;
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum ++;
            if(gp.ui.commandNum > maxCommandNum) gp.ui.commandNum = 0;
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                }
                if(gp.ui.commandNum == 2 && gp.music.volumeScale > 0) {
                    gp.se.volumeScale--;
                }
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.se.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                }
            }
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_X){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
            }
            
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow < 3){
                gp.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_D){
            gp.ui.slotCol++;
            if(gp.ui.slotCol > 4){
                gp.ui.slotCol = 0;
                if(gp.ui.slotRow < 3){
                    gp.ui.slotRow++;
                }
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }

}
