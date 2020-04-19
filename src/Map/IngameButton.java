package Map;

import Game.GameField;
import Game.GameSound;

import javax.swing.*;
import java.awt.*;
import static util.Constant.*;
public class IngameButton {
    public Rectangle[] button = new Rectangle[5];
    private int buttonSize = 80;
    public Image[] buttonImage = new Image[5];
    public IngameButton(){
        define();
    }
    public void define(){
        for(int i = 0 ; i < 4 ; i++){
            button[i] = new Rectangle(MAP_WIDTH - 150 , 10 + i*buttonSize + i*10 , buttonSize , buttonSize);
        }
        button[4] = new Rectangle(MAP_WIDTH - 150 , 10 + 2*buttonSize + 2*10 , buttonSize , buttonSize);
        buttonImage[0] = new ImageIcon("image/Button/Round_Home.png").getImage();
        buttonImage[1] = new ImageIcon("image/Button/Round_Pause.png").getImage();
        buttonImage[2] = new ImageIcon("image/Button/Round_Sound.png").getImage();
        buttonImage[3] = new ImageIcon("image/Button/Round_Exit.png").getImage();
        buttonImage[4] = new ImageIcon("image/Button/Round_Sound_Mute.png").getImage();
    }
    public void draw(Graphics g){
        //vẽ nút , game over chỉ vẽ nút home
        if(GameField.health > 0){
            for(int i = 0 ; i < 4 ; i++){
                g.drawImage(buttonImage[i] , button[i].x , button[i].y, button[i].width, button[i].height , null);
            }
            if(GameSound.musicStatus == false){
                g.drawImage(buttonImage[4] , button[4].x , button[4].y , button[4].width , button[4].height , null);
            }
        } else g.drawImage(buttonImage[0] , button[0].x, button[0].y, button[0].width, button[0].height , null);

    }
    public void click(int mouseButton){
        //xử lý bấm nút trong màn hình chơi,nếu game over chỉ xử lý nút home
        if(mouseButton == 1){
            if(GameField.health > 0){
                for(int i = 0 ; i < 4 ; i++){
                    if(button[i].contains(GameField.mse)){
                        if(i == 0){
                            GameField.gameStatus = START_MENU;
                        } else if( i == 1){
                            //game pause
                            GameField.playing = !GameField.playing;
                        } else if(i == 2){
                            //game sound
                            GameSound.musicStatus = !GameSound.musicStatus;
                            GameSound.playMusic();
                        } else if(i == 3){
                            System.exit(0);
                        }
                    }
                }
            } else {
                if(button[0].contains(GameField.mse)){
                    GameField.gameStatus = START_MENU;
                }
            }

        }
    }
}
