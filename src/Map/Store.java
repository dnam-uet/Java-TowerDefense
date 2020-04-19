package Map;

import Game.GameField;
import Game.GameSound;
import Tower.Tower;

import java.awt.*;

import static util.Constant.*;
import static util.Constant.KEY_AIR;
import Tower.*;

public class Store {
    private static int shopWidth = ELEMENT_OF_STORE; // ELEMENT_OF_STORE ô trong shop
    public static int buttonSize = STORE_ITEM;
    public static int cellSpace = BUTTON_SPACE; // khoang cach giua cac nut , nếu k có các nút sẽ dính vào nhau,k đẹp
    public static int iconSize = COIN_HEALTH_ITEM; // kích thước icon health và coin
    public Rectangle[] button = new Rectangle[shopWidth]; //mảng ô tháp
    public Rectangle buttonHealth;
    public Rectangle buttonCoins;
    public static int[] buttonID = {KEY_NORMALTOWER,KEY_SNIPERTOWER,KEY_MACHINEGUNTOWER,KEY_TRASHCAN , KEY_HAMMER};
    public static int[] buttonPrice ={PRICE_NORMAL_TOWER,PRICE_SNIPER_TOWER,PRICE_MACHINE_TOWER,0,0}; //phần tử thứ i có giá trị = giá tiền tháp thứ i trong mảng button phía trên
    public static int heldID = -1; //vị trí ô trong mảng buttong mà trỏ chuột tới,khi click vào ô nào heldID sẽ đổi thành số của ô đấy
    public static boolean holdsItem = false; // nếu giữ chuột ở ô tháp holdItem sẽ thành true
    public Store() {
        define();
    }
    //khoi tao vi tri cac nut
    public void define(){
        for(int i = 0 ; i < button.length ; i++){
            button[i] = new Rectangle(150 + i*(buttonSize + cellSpace) , MAP_HEIGHT - 3*MAP_ITEM + MAP_ITEM/8  , buttonSize , buttonSize);
        }
        buttonCoins = new Rectangle(GameField.room.blocks[0][0].x +10 , button[0].y + button[0].height - iconSize , iconSize,iconSize);

        buttonHealth = new Rectangle(GameField.room.blocks[0][0].x + 10 , button[0].y , iconSize,iconSize);
    }
    //xu ly nut
    public void click(int mouseButton){
        if(GameField.playing){
            if(mouseButton == 1){
                for(int i = 0 ; i< button.length ; i++){
                    if(button[i].contains(GameField.mse)){
                        if(buttonID[i] == KEY_TRASHCAN){ //delete button
                            heldID = KEY_AIR;
                            holdsItem = false;
                        } else{
                            heldID = buttonID[i];
                            holdsItem = true;
                        }
                    }
                    //dat thap,holdItem = true tức là tháp đó đang được chọn > trừ tiền > đổi airID của ô được đặt từ -1 thành id của tháp
                    if(holdsItem){
                        int y = (int)(GameField.mse.getY() / MAP_ITEM) ;
                        int x = (int)(GameField.mse.getX() / MAP_ITEM);
                        if(heldID != KEY_HAMMER){
                            try{
                                if(GameField.coinage >= buttonPrice[heldID]){

                                    if(GameField.room.blocks[y][x].airID == KEY_PLACE_TOWER){
                                        GameField.room.blocks[y][x].airID = heldID;
                                        Block b = GameField.room.blocks[y][x];
                                        if(heldID == 0){
                                            GameField.room.blocks[y][x] = new NormalTower(b);
                                        } else if(heldID == 1){
                                            GameField.room.blocks[y][x] = new SniperTower(b);
                                        } else if(heldID == 2){
                                            GameField.room.blocks[y][x] = new MachineTower(b);
                                        }
                                        GameField.coinage -= buttonPrice[heldID];
                                    }
                                }
                            }catch (Exception e){}
                        } else{
                            //xóa tháp + tiền
                            try{
                                if(GameField.room.blocks[y][x].airID >= KEY_NORMALTOWER && GameField.room.blocks[y][x].airID <= KEY_MACHINEGUNTOWER){
                                    GameField.coinage += buttonPrice[GameField.room.blocks[y][x].airID];
                                    GameField.room.blocks[y][x].airID = KEY_PLACE_TOWER;
                                }
                            } catch (Exception e){}

                        }


                    }
                }
            }
        }

    }
    //ve cac nut
    public void draw(Graphics graphics){
        //ve tat ca nut thap
        for(int i = 0 ; i < button.length ; i++){
            graphics.setColor(Color.ORANGE);
            //khi trỏ chuột đến ô nào ô đó sẽ sáng lên
            if(button[i].contains(GameField.mse)){
                graphics.fillRect(button[i].x,button[i].y,button[i].width,button[i].height);
            }
            graphics.drawImage(GameField.tileset_button[0],button[i].x,button[i].y,button[i].width,button[i].height,null);
            graphics.drawImage(GameField.tileset_air[buttonID[i]], button[i].x , button[i].y , button[i].width , button[i].height , null);
            graphics.setColor(Color.GREEN);
            if(buttonPrice[i] != 0){ // in phần giá bên cạnh các ô tháp
                graphics.setFont(new Font("Courier New" , Font.BOLD , 14));
                graphics.drawString(buttonPrice[i] + "$",button[i].x +10 , button[i].y + buttonSize  );
            }
            graphics.setColor(Color.YELLOW);
        }
        //ve o health , coins
        drawHealthCoins(graphics);


    }
    //in 2 thông số coin và health
    public void drawHealthCoins(Graphics graphics){

        if(buttonHealth.contains(GameField.mse)){
            graphics.fillRect(buttonHealth.x,buttonHealth.y,buttonHealth.width,buttonHealth.height);
        }
        if(buttonCoins.contains(GameField.mse)){
            graphics.fillRect(buttonCoins.x , buttonCoins.y , buttonCoins.width , buttonCoins.height );
        }
        graphics.drawImage(GameField.tileset_button[1],buttonHealth.x ,buttonHealth.y ,buttonHealth.width ,buttonHealth.height ,null);
        graphics.drawImage(GameField.tileset_button[2],buttonCoins.x ,buttonCoins.y ,buttonCoins.width ,buttonCoins.height ,null);
        graphics.setFont(new Font("Courier New" , Font.BOLD , 14));
        graphics.drawString(""+GameField.health , buttonHealth.x + iconSize + 3, buttonHealth.y + 14);
        graphics.drawString(""+GameField.coinage , buttonCoins.x + iconSize + 3, buttonCoins.y + 14);
        graphics.drawString("LEVEL : "+ GameField.level , buttonCoins.x + 3, buttonCoins.y - 60);
        if(holdsItem){
            graphics.drawImage(GameField.tileset_air[heldID] , GameField.mse.x - (button[0].width  - (4*2)/2) + 16 ,GameField.mse.y - (button[0].width  - (4*2)/2) + 16,button[0].width - 8 ,button[0].height - 8,null);

        }


    }
}
