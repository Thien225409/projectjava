package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp){
        this.gp = gp;
    }
    public void saveConfig(){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Game2D\\config.txt"));

            // FULL SCREEN (Toàn màn hình)
            if(gp.fullScreenOn == true){
                bw.write("On");
            }
            if(gp.fullScreenOn == false){
                bw.write("Off");
            }
            bw.newLine();

            // Music volume (Âm lượng nhạc)
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            // SE volume (Âm lượng hiệu ứng âm thanh)
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadConfig(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("Game2D\\config.txt"));

            String s = br.readLine();

            // FULL SCREEN (Toàn màn hình)
            if(s.equals("On")){
                gp.fullScreenOn = true;
            }
            if(s.equals("Off")){
                gp.fullScreenOn = false;
            }

            // Music volume (Âm lượng nhạc nền)
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            // SE volume (Âm lượng hiệu ứng âm thanh)
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
