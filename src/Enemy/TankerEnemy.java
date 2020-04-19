package Enemy;

import Game.GameField;
import Map.Room;

import static util.Constant.*;

public class TankerEnemy extends Enemy {
    public TankerEnemy(){
        setHEALTH(HEALTH_TANKER_ENEMY);
        setSpeed(SPEED_TANKER_ENEMY);
    }

    @Override
    public int getID() {
        return TANKER_ENEMY_ID;
    }

    @Override
    public void spawnEnemy(int enemeyID){
        direction = right;
        for(int i = 0; i < GameField.room.blocks.length ; i++){
            if(GameField.room.blocks[i][0].groundID == KEY_ROAD){
                setBounds(GameField.room.blocks[i][0].x , GameField.room.blocks[i][0].y , enemeySize , enemeySize);
            }
        }
        this.enemyID = enemeyID;
        health = HEALTH_TANKER_ENEMY;
        inGame = true;
    }

    @Override
    public void deleteEnemy() {
        super.deleteEnemy();
        int i = y/MAP_ITEM;
        int j = x/MAP_ITEM;
        if(Room.blocks[i][j].groundID != 2){
            GameField.coinage += COIN_TANKER_ENEMY;
        }
    }

    @Override
    public void loseHealth() {
        GameField.health -= HEALTH_GAME_TANKER_ENEMY;
    }
}
