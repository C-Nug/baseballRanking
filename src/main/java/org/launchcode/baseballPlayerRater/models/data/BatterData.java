package org.launchcode.baseballPlayerRater.models.data;

import org.launchcode.baseballPlayerRater.models.Batter;

import java.util.ArrayList;

/**
 * Created by CNUG on 8/30/17.
 */

// Obsolete
public class BatterData {

    private ArrayList<Batter> batters = new ArrayList<>();

    public void addBatter (Integer id, String name, String team, String positions, int runs, int homeRuns, int rbis, int strikeOuts,
                           int stolenBases, double onBasePercent, double slugging) {
        batters.add(new Batter(id, name, team, positions, runs, homeRuns, rbis,
                    strikeOuts, stolenBases, onBasePercent, slugging));
    }

}
