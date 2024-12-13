package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

import AI.PathFinder;
import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS (Thiết lập màn hình)
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS (Thiết lập thế giới (map))
    public final int maxWorldCol = 71;
    public final int maxWorldRow = 51;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FULL SCREEN (Toàn màn hình)
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //FPS 
    int FPS = 60;

    //SYSTEM (Hệ thống)
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);

    // EVENT HANDLER (Xử lý sự kiện)
    public EventHandler eHandler = new EventHandler(this);

    //ENTITY AND OBJECT (Thực thể và vật thể)
    public Player player = new Player(this , keyH);
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[50];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    //GAME STATE (trạng thái game)
    public int gameState;
    public int titleState = 0;
    public final int playState = 1;
    public final int optionsState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int gameOverState = 5;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth , screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        setFocusable(true);
    }   

    public void setupGame(){

        gameState = titleState;
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn == true){
            setFullScreen();
        }
    }
    public void retry(){

        player.setDefaultPositions();
        player.restoreLife();
        aSetter.setNPC();
        aSetter.setMonster();
    }
    public void restart(){

        player.setDefaultValues();
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
    }
    public void setFullScreen(){

        // GET LOCAL SCREEN DEVICE (lấy thông tin màn hình thiết bị)
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        gd.setFullScreenWindow(Main.window);
        // GET FULL SCREEN WIDTH AND HEIGHT (Lấy chiều rộng, cao toàn màn hình)
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/ FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                drawToTempScreen();// Vẽ mọi thứ cho buffered image
                drawToScreen();// Vẽ buffered image ra màn hình                         
                delta --;
                drawCount++;
            }

            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }

    }
    public void update(){

        if(gameState == playState){
            // PLAYER (Cập nhật player)
            player.update();
            // MONSTER (Cập nhật quái)
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive == true && monster[i].dying == false) monster[i].update();
                    if(monster[i].alive == false) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            //MONSTER ATTACK (Quái tấn công)
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive == true ) projectileList.get(i).update();
                    if(projectileList.get(i).alive == false) projectileList.remove(i);
                }
            }

        }
        if(gameState == optionsState){
            // TODO: Viết code cho trạng thái PAUSE (pauseState)
        }
    }
    public void drawToTempScreen(){

        //DEBUG 
        long drawStart = 0;
        if(keyH.showDebugText == true){
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN (Màn hình tiêu đề)
        if(gameState == titleState){
            ui.draw(g2);
        }
        // OTHERS (Khác)
        else{
            //TILE
            tileM.draw(g2);
            
            // ADD ENTITIES TO THE LIST (Thêm các thực thể vào danh sách)
            entityList.add(player);
            // NPC (Thêm NPC)
            for(int i = 0; i < npc.length ; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            // OBJECTS (Thêm vật thể)
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            // MONSTER (Thêm quái vậtvật)
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
            }

            //MONSTER ATTACK (Thêm đòn tấn công của quái (projectiles))
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }

            // SORT (Sắp xếp)
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
                
            });

            // DRAW ENTITIES (Vẽ các thực thể)
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            // EMPTY ENTITY LIST (Xóa danh sách thực thể)
            entityList.clear();

            //UI (Vẽ giao diện UI)
            ui.draw(g2);
        }

        //DEBUG
        if(keyH.showDebugText == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial",Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("World X: " + player.worldX, x , y);
            y += lineHeight;
            g2.drawString("World Y: " + player.worldY, x , y);
            y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x , y);
            y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x , y);
            y += lineHeight;
            g2.drawString("Draw Time: " + passed, x , y);

        }
    }
    public void drawToScreen(){

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }
    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }

}
