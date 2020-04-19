package Tower;

import Bullet.Bullet;
import Game.GameField;
import Map.Block;

import java.awt.*;

import static util.Constant.*;


public abstract class Tower extends Block {
    protected int towerID;
    protected int towerDamage;
    protected Bullet bullet = new Bullet();

    public Tower(Block block ,int towerID, int towerRange){
        super();
        super.towerSquareSize = towerRange;
        setBounds(block.x , block.y,block.width,block.height);
        towerSquare = new Rectangle(x - (towerSquareSize / 2),y- (towerSquareSize / 2),width + (towerSquareSize ),height + (towerSquareSize )); // set phạm vi bắn = kích thước 1 ô + 1 phần ngoài tùy ý,k cần hiểu kỹ
        this.towerID = towerID;
        airID = towerID;
    }
    public void physic(){
        bullet.shoting = false;
        //check enemy có trùng với phạm vi bắn của tháp không,có thì shoting = true,con quái bị bắn vị trí thứ shotEnemy trong mảng
        if(airID == KEY_NORMALTOWER || airID == KEY_MACHINEGUNTOWER || airID == KEY_SNIPERTOWER){
            for(int i = 0; i < GameField.enemies.length ; i++){
                try{
                    if(GameField.enemies[i].inGame){
                        if(towerSquare.intersects(GameField.enemies[i])){
                            bullet.shoting = true;
                            bullet.shotEnemy = i;

                        }
                    }
                } catch (Exception e){}

            }
        }
        if(bullet.shotFrame > bullet.shotTime) {
            if(bullet.shoting){
                GameField.enemies[bullet.shotEnemy].looseHealth(towerDamage);
            }
            bullet.shotFrame = 0;
        } else bullet.shotFrame+= 1;
    }
    public void fight(Graphics graphics){
        //ban quai , làm chậm thời gian hiện đạn , nếu k có nó sẽ bắn liên tục và quái rất nhanh chết
        if(bullet.shotFrame >= bullet.shotTime){
            if(bullet.shoting){
                graphics.drawLine(x + (MAP_ITEM / 2) , y +(MAP_ITEM / 2) , GameField.enemies[bullet.shotEnemy].x+(MAP_ITEM / 2) ,GameField.enemies[bullet.shotEnemy].y+(MAP_ITEM / 2));
            }
        }


    }
}
