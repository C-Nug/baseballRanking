package org.launchcode.baseballPlayerRater.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CNUG on 8/30/17.
 */
public class RankerSystem {

    private static final int totalStatCategories = 7;
    private static HashMap<String, Integer> categories = new HashMap<>();

    public RankerSystem () {
        categories.put("runs", 0);
        categories.put("homeRuns", 1);
        categories.put("rbis", 2);
        categories.put("strikeOuts", 3);
        categories.put("stolenBases", 4);
        categories.put("onBasePercent", 5);
        categories.put("slugging", 6);
    }

    public static ArrayList<Integer> findLowest(Iterable<Batter> allBatters) {
        ArrayList<Integer> lowests = new ArrayList<>();
        for (int i = 0; i < totalStatCategories; i++) {
            lowests.add(i, 0);
        }

        for (Batter batter: allBatters) {
            if (batter.getRuns() < lowests.get(categories.get("runs"))) {
                lowests.set(categories.get("runs"), batter.getRuns());
            }
            if (batter.getHomeRuns() < lowests.get(categories.get("homeRuns"))) {
                lowests.set(categories.get("homeRuns"), batter.getHomeRuns());
            }
            if (batter.getRbis() < lowests.get(categories.get("rbis"))) {
                lowests.set(categories.get("rbis"), batter.getRbis());
            }

            // TODO: repeat for rest of stats
            // TODO: consider rewriting this with lamba expressions and method references
        }
        return lowests;
    }

    public static int findHighest(Iterable<Batter> allBatters) {
        int highest = 0;
        for (Batter batter: allBatters) {
            if (batter.getRuns() > highest) {
                highest = batter.getRuns();
            }
        }
        return highest;
    }


}
