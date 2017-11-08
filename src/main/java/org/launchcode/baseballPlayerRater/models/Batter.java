package org.launchcode.baseballPlayerRater.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.HashMap;

/**
 * Created by CNUG on 8/1/17.
 */

@Entity
@DiscriminatorValue("BATTER")
public class Batter extends Player {

    private HashMap<String, Integer> intStats = new HashMap<String, Integer>();
    private HashMap<String, Double> dubStats = new HashMap<String, Double>();
    private HashMap<String, Double> ratings = new HashMap<String, Double>();


    public Batter(Integer id, String name, String team, String positions, Integer abs, Integer runs, Integer homeRuns, Integer rbis, Integer strikeOuts,
                  Integer stolenBases, Double onBasePercent, Double slugging) {
        super(id, name, team, positions);
        this.intStats.put("abs", abs);
        this.intStats.put("runs", runs);
        this.intStats.put("homeRuns", homeRuns);
        this.intStats.put("rbis", rbis);
        this.intStats.put("strikeOuts", strikeOuts);
        this.intStats.put("stolenBases", stolenBases);
        this.dubStats.put("onBasePercent", onBasePercent);
        this.dubStats.put("slugging", slugging);

        this.ratings.put("runs", 0.0);
        this.ratings.put("homeRuns", 0.0);
        this.ratings.put("rbis", 0.0);
        this.ratings.put("strikeOuts", 0.0);
        this.ratings.put("stolenBases", 0.0);
        this.ratings.put("onBasePercent", 0.0);
        this.ratings.put("slugging", 0.0);


    }

    public Batter (Integer id) {
        super(id);
    }

    public Batter() {}


    public HashMap<String, Integer> getIntStats() {
        return intStats;
    }

    public HashMap<String, Double> getDubStats() {
        return dubStats;
    }

    public HashMap<String, Double> getRatings() {
        return ratings;
    }

    public void setIntStats(HashMap<String, Integer> intStats) {
        this.intStats = intStats;
    }

    public void setDubStats(HashMap<String, Double> dubStats) {
        this.dubStats = dubStats;
    }

    public void setRatings(HashMap<String, Double> ratings) {
        this.ratings = ratings;
    }
}
