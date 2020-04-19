package Bullet;


import static util.Constant.NORMAL_SHOT_TIME;

public class Bullet {
    public boolean shoting;
    public int shotTime , shotFrame;
    public int shotEnemy;
    public Bullet(){
        shoting = false;
        shotTime = NORMAL_SHOT_TIME;
        shotFrame = 0;
        shotEnemy = 0;
    }
}
