package Game;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

import static util.Constant.*;
public class StartMenu {
    private static Image[] lightButton = new Image[3];
    private static Image[] darkButton = new Image[3];
    public Rectangle[] button = new Rectangle[3];
    public StartMenu(){
        define();
    }
    public void define(){
        for(int i = 0 ; i < button.length ; i++){
            button[i] = new Rectangle((MAP_WIDTH/2) - (180 / 2), (MAP_HEIGHT/2) - (72/2) + i*72 + (i)*12 , 180,72);
        }
        lightButton[0] = new ImageIcon("image/Button/Play.png").getImage();
        lightButton[1] = new ImageIcon("image/Button/Sound.png").getImage();
        lightButton[2] = new ImageIcon("image/Button/Quit.png").getImage();
        darkButton[0] = new ImageIcon("image/Button/Play_Dark.png").getImage();
        darkButton[1] = new ImageIcon("image/Button/Sound_Dark.png").getImage();
        darkButton[2] = new ImageIcon("image/Button/Quit_Dark.png").getImage();
    }
    public void click(int mouseButton){
        if(mouseButton == 1){
            for(int i = 0 ; i < 3 ; i++){
                if(button[i].contains(GameField.mse)){
                    if(i == 0){
                        GameField.gameStatus = PLAYING;
                        GameField.playing = true;
                    } else if( i == 1){
                        GameSound.musicStatus = !GameSound.musicStatus;
                        GameSound.playMusic();
                    } else if(i == 2){
                        System.exit(0);
                    }
                }
            }
        }
    }
    public void draw(Graphics g){
        g.drawImage(GameField.menuBackground , 0,0, MAP_WIDTH , MAP_HEIGHT , null);
        for(int i = 0 ; i < 3 ; i++){
            int x = button[i].x , y = button[i].y;
            g.drawImage(darkButton[i] , button[i].x , button[i].y, button[i].width , button[i].height , null);
            if(button[i].contains(GameField.mse)){
                if(i == 1 && GameSound.musicStatus == false){
                }
                else g.drawImage(lightButton[i] , button[i].x, button[i].y, button[i].width, button[i].height,null);
            }
        }
    }
}
