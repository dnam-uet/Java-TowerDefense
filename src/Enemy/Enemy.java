package Enemy;

import Game.GameField;
import Map.Room;

import java.awt.*;

import static util.Constant.*;

public abstract class Enemy extends Rectangle {
    protected int enemeySize = ENEMY_SIZE;
    protected final int upward = 0 , downward = 1 , right = 2 , left = 3;
    protected int direction;
    protected int enemyID = ENEMY_AIR;
    public boolean inGame = false;
    protected int health;
    protected int HEALTH;
    protected int start = 0 , speed;
    public Enemy() {

    }

    public void setHEALTH(int HEALTH) {
        this.HEALTH = HEALTH;
    }

    public abstract int getID();

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract void spawnEnemy(int enemeyID);
    public void deleteEnemy(){
        inGame = false;

    };
    //loose player health
    public abstract void loseHealth();
    //loose enemy health
    public void looseHealth(int towerDamage){
        health-=towerDamage;
        checkDeath();

    }

    protected void checkDeath() {
        if(health <= 0){
            deleteEnemy();
            GameField.killed++;
        }
    }
    public void draw(Graphics g){
        if(inGame){
            if(enemyID == 3){
                g.drawImage(GameField.tileset_enemy[enemyID] , x - 5 , y-5 , width+10 , height+10 , null);
            }
            else g.drawImage(GameField.tileset_enemy[enemyID] , x , y , width , height , null);
            g.setColor(Color.BLUE);
            g.fillRect(x  , y - HEIGHT_BAR_HEALTH, (WIDTH_BAR_HEALTH*health)/HEALTH , HEIGHT_BAR_HEALTH);
        }
    }
    //move enemy
    public void physic(){
        //move
        if(inGame){
            if(start >= speed){
                if(direction == right){
                    x += 1;
                } else if(direction == upward){
                    y -= 1;
                } else if(direction == downward){
                    y += 1;
                } else if(direction == left){
                    x -= 1;
                }
                //xử lý tìm ra trạng thái ô block tiếp theo
                try{
                    if(x %ENEMY_SIZE == 0 && y % ENEMY_SIZE == 0){
                        int j = x/ENEMY_SIZE; int i = y/ENEMY_SIZE;
                        if(GameField.room.blocks[i][j].groundID == TARGET){
                            deleteEnemy();
                            loseHealth();
                            //System.out.println("loose health at" + i + " " + j);
                        }
                        if(GameField.room.blocks[i][j+1].groundID == KEY_ROAD && direction != left){
                            direction = right;
                        } else if(GameField.room.blocks[i+1][j].groundID == KEY_ROAD&& direction != upward){
                            direction = downward;
                        } else if(GameField.room.blocks[i-1][j].groundID == KEY_ROAD&& direction != downward){
                            direction = upward;

                        }else if(GameField.room.blocks[i][j-1].groundID == KEY_ROAD&& direction != right){
                            direction = left;
                        }

                    }

                } catch (Exception e){}
                start = 0;
            } else start++;
        }

    }
}