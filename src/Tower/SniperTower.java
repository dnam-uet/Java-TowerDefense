package Tower;
import Map.Block;

import static util.Constant.*;

public class SniperTower extends Tower {
    public SniperTower(Block block){
        super(block , KEY_SNIPERTOWER,SNIPERTOWER_RANGE);
        this.towerDamage = SNIPER_TOWER_DAME;
        bullet.shotTime = SNIPER_SHOT_TIME;
    }
}
