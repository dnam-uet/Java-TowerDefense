package Map;

import Game.GameField;

import java.awt.*;

import static util.Constant.*;
import static util.Constant.MAP_ITEM;

public class Block extends Rectangle {
    public int groundID , airID ;
    public Rectangle towerSquare; // hình vuông biểu thị phạm vi tháp bắn được
    public int towerSquareSize;
    public Block(){}
    public Block(int x, int y, int width, int height , int groundID , int airID) {
        setBounds(x  , y , width  , height );
        towerSquare = new Rectangle(x - (towerSquareSize / 2),y- (towerSquareSize / 2),width + (towerSquareSize ),height + (towerSquareSize )); // set phạm vi bắn = kích thước 1 ô + 1 phần ngoài tùy ý,k cần hiểu kỹ
        setBounds(x,y,width,height);
        this.groundID = groundID;
        this.airID = airID;
    }
    public void draw(Graphics graphics){
        graphics.drawImage(GameField.tileset_ground[groundID] , x ,y , width, height , null);
        if(airID != KEY_AIR){ //airID # -1 tức là ô đó có đối tượng tháp=> vẽ tháp
            graphics.drawImage(GameField.tileset_air[airID] , x , y  ,width , height,null);
        }

    }
    public void physic(){
    }
    public void fight(Graphics graphics){
    }
}
