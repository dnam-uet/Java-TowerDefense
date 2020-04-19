package Enemy;

import Game.GameField;
import Map.Room;

import static util.Constant.*;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy(){
        setHEALTH(HEALTH_SMALLER_ENEMY);
        setSpeed(SPEED_SMALLER_ENEMY);
    }
    @Override
    public int getID() {
        return SMALLER_ENEMY_ID;
    }

    @Override
    public void spawnEnemy(int enemeyID) {
        direction = right;
        for(int i = 0; i < GameField.room.blocks.length ; i++){
            if(GameField.room.blocks[i][0].groundID == KEY_ROAD){
                setBounds(GameField.room.blocks[i][0].x , GameField.room.blocks[i][0].y , enemeySize , enemeySize);
            }
        }
        this.enemyID = enemeyID;
        health = HEALTH_SMALLER_ENEMY;
        inGame = true;
    }

    @Override
    public void deleteEnemy() {
//        GameField.coinage += COIN_SMALLER_ENEMY;
//        inGame = false;
        super.deleteEnemy();
        int i = y/MAP_ITEM;
        int j = x/MAP_ITEM;
        if(Room.blocks[i][j].groundID != 2){
            GameField.coinage += COIN_SMALLER_ENEMY;
        }
    }

    @Override
    public void loseHealth() {
        GameField.health -= HEALTH_GAME_SMALLER_ENEMY;
    }
}
