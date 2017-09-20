package org.launchcode.baseballPlayerRater.models;

import java.util.ArrayList;

/**
 * Created by CNUG on 8/30/17.
 */
public class RankerSystem {

    public int findLowest(ArrayList<Batter> allBatters) {
        int lowest = 999999;
        for (Batter batter: allBatters) {
            if (batter.getRuns() < lowest) {
                lowest = batter.getRuns();
            }
        }
        return lowest;
    }

    // TODO: 8/30/17 repeat for highest and lowest for all stats or refactor for generic use for all stats
}
