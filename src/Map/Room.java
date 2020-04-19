package Map;

import Tower.Tower;

import java.awt.*;

import static util.Constant.*;
import static util.Constant.KEY_AIR;

public class Room {
    public static Block[][] blocks; //object tiles list
    public Room() {
        define();
    }
    // define all block
    private static void define() {
        blocks = new Block[MAX_MAP_HEIGHT][MAX_MAP_WIDTH];

        for (int y = 0; y < blocks.length; y++) {
            for(int x = 0 ; x < blocks[0].length ; x++){
                blocks[y][x] = new Block(x * MAP_ITEM, y * MAP_ITEM, MAP_ITEM, MAP_ITEM, KEY_GRASS, KEY_AIR) {
                };
            }
        }
    }
    public void physic() {
        for(int y = 0 ; y < blocks.length ; y++){
            for(int x = 0 ; x < blocks[0].length ; x++){
                blocks[y][x].physic();
            }
        }
    }
    public void draw(Graphics graphics){
        for (int y = 0; y < blocks.length; y++) {
            for(int x = 0 ; x < blocks[0].length ; x++){
                if(blocks[y][x] != null){
                    blocks[y][x].draw(graphics);
                }
            }
        }
        //ve hinh vuong
        /*
        nếu không vẽ lại các block sẽ in từng hàng và khi in qua vị trí
        ô tháp hình vuông của tháp sẽ bị
        ô sau chèn mất
         */
        for (int y = 0; y < blocks.length; y++) {
            for(int x = 0 ; x < blocks[0].length ; x++){
                blocks[y][x].fight(graphics);
            }
        }

    }
}
