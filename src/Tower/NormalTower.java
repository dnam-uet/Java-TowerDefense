package Tower;
import Map.Block;

import static util.Constant.*;

public class NormalTower extends Tower {
    public NormalTower(Block block){
        super(block , KEY_NORMALTOWER,NORMALTOWER_RANGE);
        this.towerDamage = NORMAL_TOWER_DAME;
    }
}
