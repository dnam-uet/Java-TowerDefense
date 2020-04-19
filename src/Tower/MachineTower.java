package Tower;
import Map.Block;

import static util.Constant.*;

public class MachineTower extends Tower {
    public MachineTower(Block block){
        super(block , KEY_MACHINEGUNTOWER,MACHINETOWER_RANGE);
        this.towerDamage = MACHINE_TOWER_DAME;
        bullet.shotTime = MACHINE_SHOT_TIME;
    }
}
