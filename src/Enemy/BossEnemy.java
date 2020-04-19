package Enemy;

import Game.GameField;
import Map.Room;

import static util.Constant.*;

public class BossEnemy extends Enemy{

    public BossEnemy(){
        setHEALTH(HEALTH_BOSS_ENEMY);
        setSpeed(SPEED_BOSS_ENEMY);
    }

    @Override
    public int getID() {
        return BOSS_ENEMY_ID;
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
        health = HEALTH_BOSS_ENEMY;
        inGame = true;
    }

    @Override
    public void deleteEnemy() {
        super.deleteEnemy();
        int i = y/MAP_ITEM;
        int j = x/MAP_ITEM;
        if(Room.blocks[i][j].groundID != 2){
            GameField.coinage += COIN_BOSS_ENEMY;
        }
    }

    @Override
    public void loseHealth() {
        GameField.health -= HEALTH_GAME_BOSS_ENEMY;
    }
}
