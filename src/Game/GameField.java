package Game;

import Enemy.*;
import Map.IngameButton;
import Map.Room;
import Map.Save;
import Map.Store;
import control.KeyHandler;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;

import static util.Constant.*;

public class GameField extends JPanel implements Runnable {
    Thread thread = new Thread(this);
    public static boolean isFirst = true;
    public static int coinage , health = HEALTH_GAME_BEGIN;
    public static boolean isDebug = true;
    public static Room room = new Room();
    public static int killed = KILLED_BEGIN , killsToWin , level = LEVEL_BEGIN;
    public static int maxLevel = MAX_LEVEL;
    public static int winTime = WIN_TIME , winFrame = 0;
    public static boolean isWin = false;
    //mang luu image cua cac doi tuong
    public static Image[] tileset_ground = new Image[5];
    public static Image[] tileset_air = new Image[10];
    public static Image[] tileset_button = new Image[20];
    public static Image[] tileset_enemy = new Image[4];

    public static Image gameOverImage = new  ImageIcon("image/GameOver.png").getImage(); // anh thua tro choi
    public static Image nextLevel = new ImageIcon("image/nextLevel.jpg").getImage(); // anh level up;
    public static Image gameWinImage = new ImageIcon("image/Win.jpg").getImage(); // anh chien thang tro choi;
    public static Image gameBackground = new ImageIcon("image/generalBackground.png").getImage();
    public static Image menuBackground = new ImageIcon("image/MenuBackground.png").getImage();
    public static int gameStatus = START_MENU;
    public static boolean playing = true;
    public static Save save;
    public static Point mse = new Point(0,0 );
    public static Store store;
    public static StartMenu startMenu = new StartMenu();
    public static IngameButton ingameButton = new IngameButton();
    public static Enemy[] enemies = new Enemy[100];
    /*Sound*/
    //public GameSound gameSound = new GameSound();

    public GameField(Frame frame){
        frame.addMouseListener(new KeyHandler());
        frame.addMouseMotionListener(new KeyHandler());
        setFocusable(true);
        requestFocus();
        thread.start();
    }


    public void define() {

        //init everything
        coinage = COIN_BEGIN;
        room = new Room();
        save = new Save();
        store = new Store();

        //set image for array
        for(int i = 0 ; i < tileset_ground.length ; i++){
            tileset_ground[i] = new ImageIcon("image\\tileset_grass.png").getImage();
            tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource() , new CropImageFilter(0 , 50 * i , 50,50)));
        }
        for(int i = 0 ; i < tileset_air.length ; i++){
            tileset_air[i] = new ImageIcon("image\\tileset_air.png").getImage();
            tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource() , new CropImageFilter(0 , 50*i , 50,50)));
        }
        tileset_button[0] = new ImageIcon("image/Button.png").getImage();
        tileset_button[1] = new ImageIcon("image/Health.png").getImage();
        tileset_button[2] = new ImageIcon("image/Coins.png").getImage();
        //enemy
        //tileset_enemy[0] = new ImageIcon("image\\Enemy\\NormalEnemy.png").getImage();
        for(int i = 0 ; i < tileset_enemy.length ; i++){
            tileset_enemy[i] = new ImageIcon("image\\tileset_enemy.png").getImage();
            tileset_enemy[i] = createImage(new FilteredImageSource(tileset_enemy[i].getSource() , new CropImageFilter(0 , 300*i , 300,300)));
        }



        //load map , số kill cần để thắng load luôn trong hàm loadSave
        save.loadSave(new File("src/save/misson"+level+".txt"));



        //create Enemy Object in list
        for(int i = 0 ; i < enemies.length ; i++){
            if(i%1000 == 0 ){
                enemies[i] = new BossEnemy();
            }
            else if (i%5 == 0 && i!=0){
                enemies[i] = new TankerEnemy();
            }
            else if (i%2 == 0 && i!=0){
                enemies[i] = new SmallerEnemy();
            }
            else {
                enemies[i] = new NormalEnemy();
            }
        }


    }
    public void paintComponent(Graphics graphics){
        try{
            if(gameStatus == START_MENU){
                isFirst = true;
                startMenu.draw(graphics);
            } else if( gameStatus == PLAYING){
                if(isFirst){
                    isFirst = false;
                    define();
                }
                // số quái giết >= số quái cần giết => isWin = true , nhảy vào phần rẽ nhánh isWin = true ở run()
                if(killed >= killsToWin){
                    isWin = true;
                }
                //mỗi lần in màn hình mới sẽ xóa màn cũ đi 1 lần
                graphics.clearRect(0,0, getWidth() , getHeight());
                graphics.setColor(Color.orange);
                graphics.drawImage(gameBackground , 0,0,MAP_WIDTH , MAP_HEIGHT , null);
                room.draw(graphics); // drawing the room
                for(int i = 0 ; i < enemies.length ; i++){
                    if(enemies[i].inGame){
                        enemies[i].draw(graphics);
                    }
                }

                //in ảnh khu mua tháp
                store.draw(graphics); //drawing the store
                if(health < 1){
                    gameOver(graphics);
                }
                ingameButton.draw(graphics);
                if(isWin){
                    if(level >= maxLevel){
                        graphics.drawImage(gameWinImage , 0 , 0 , MAP_WIDTH , MAP_HEIGHT , null);
                    } else graphics.drawImage(nextLevel , 0,0,MAP_WIDTH , MAP_HEIGHT , null);
                }
            }
        } catch (Exception ex){
            System.out.println("Error in GameField");
        }


    }

    public void gameOver(Graphics graphics){
        graphics.clearRect(0,0,MAP_WIDTH,MAP_HEIGHT);
        graphics.drawImage(gameOverImage , 0,0,MAP_WIDTH , MAP_HEIGHT , null);
    }
    //do tre tao enemy moi la SPAWN_TIME_ENEMY chu ky,giảm thời gian spawn quái
    public int spawnTime = SPAWN_TIME;
    public int spawnFrame = SPAWN_TIME_FRAME_BEGIN;
    public boolean checkEnemy = false;
    public void enemySpawner(){
        try{
            if (spawnFrame >= spawnTime){
                for(int i = 0 ; i < enemies.length ; i++){
                    if(!enemies[i].inGame){
                        enemies[i].spawnEnemy(enemies[i].getID());
                        spawnFrame = 1;
                        break;
                    }
                }
            }
            else {
                spawnFrame += 1;
            }
        } catch (Exception e){}

    }

    @Override
    public void run() {
                while (true){
                        if(!isFirst && health > 0 && !isWin){
                            if(playing){
                            room.physic();
                            enemySpawner();
                            for(int i = 0 ; i < enemies.length ; i++){

                                if(enemies[i] != null){
                                    enemies[i].physic();
                                }
                            }}

                        }
                        else if(isWin){

                            if(winFrame >= winTime){
                                if(level >= maxLevel){
                                    System.exit(0);
                                }
                                else{
                                    level += 1;
                                    define();
                                    killed = 0;
                                    isWin = false;
                                }
                                winFrame = 0;
                            } else winFrame++;
                        }

                        repaint();

                        try {
                            Thread.sleep(THREAD_SLEEP);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
    }
}
