package org.launchcode.baseballPlayerRater.models;

import org.launchcode.baseballPlayerRater.dataFetcher.BatterCSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by CNUG on 8/30/17.
 */
public class RankerSystem {

    private static final int MINIMUM_AT_BATS = 300;
    private static ArrayList<Batter> allBatters = new ArrayList<>();

    // Abstract batters representing cumulative lowest stats
    private static Batter lowestBatter = new Batter(1);
    private static Batter highestBatter = new Batter(2);


    public RankerSystem () {

    }

    public static ArrayList<Batter> rankBatters () {

        allBatters = BatterCSVReader.loadAllBatters();

        // find lowest stats of all players for each category
        HashMap<String, Integer> lowestIntStats = RankerSystem.minInt();
        HashMap<String, Double> lowestDubStats = RankerSystem.minDub();
        lowestBatter.setIntStats(lowestIntStats);
        lowestBatter.setDubStats(lowestDubStats);

        // find highest Stats of all players for each category
        HashMap<String, Integer> highestIntStats = RankerSystem.maxInt();
        HashMap<String, Double> highestDubStats = RankerSystem.maxDub();
        highestBatter.setIntStats(highestIntStats);
        highestBatter.setDubStats(highestDubStats);

        // Find rating for each stat
        RankerSystem.rateStats();

        // Find average rating of each batter
        RankerSystem.avgRatings();
        RankerSystem.minAvgRatings();
        RankerSystem.maxAvgRatings();

        RankerSystem.giveBattersRatings();

        RankerSystem.rankByRating();

        return allBatters;
    }

    public static HashMap<String, Integer> minInt() {

        HashMap<String, Integer> lowests = new HashMap<>();
        lowests.putAll(allBatters.get(0).getIntStats());

        for (int i = 1; i < allBatters.size(); i++) {
            if (allBatters.get(i).getIntStats().get("abs") > MINIMUM_AT_BATS) {
                for (Map.Entry<String, Integer> stat : allBatters.get(i).getIntStats().entrySet()) {
                    if (allBatters.get(i).getIntStats().get(stat.getKey()).compareTo(lowests.get(stat.getKey())) < 0) {
                        lowests.put(stat.getKey(), allBatters.get(i).getIntStats().get(stat.getKey()));
                    }

                }
            }
        }

        return lowests;

    }

    public static HashMap<String, Double> minDub() {

        HashMap<String, Double> lowests = new HashMap<>();
        lowests.putAll(allBatters.get(0).getDubStats());

        for (int i = 1; i < allBatters.size(); i++) {
            if (allBatters.get(i).getIntStats().get("abs") > MINIMUM_AT_BATS) {
                for (Map.Entry<String, Double> stat : allBatters.get(i).getDubStats().entrySet()) {
                    if (allBatters.get(i).getDubStats().get(stat.getKey()).compareTo(lowests.get(stat.getKey())) < 0) {
                        lowests.put(stat.getKey(), allBatters.get(i).getDubStats().get(stat.getKey()));
                    }

                }
            }
        }

        return lowests;

    }

    public static HashMap<String, Integer> maxInt () {

        HashMap<String, Integer> highests = new HashMap<>();
        highests.putAll(allBatters.get(0).getIntStats());

        for (int i = 1; i < allBatters.size(); i++) {
            if (allBatters.get(i).getIntStats().get("abs") > MINIMUM_AT_BATS) {
                for (Map.Entry<String, Integer> stat : allBatters.get(i).getIntStats().entrySet()) {
                    if (allBatters.get(i).getIntStats().get(stat.getKey()).compareTo(highests.get(stat.getKey())) > 0) {
                        highests.put(stat.getKey(), allBatters.get(i).getIntStats().get(stat.getKey()));
                    }

                }
            }
        }

        return highests;

    }

    public static HashMap<String, Double> maxDub() {

        HashMap<String, Double> highests = new HashMap<>();
        highests.putAll(allBatters.get(0).getDubStats());

        for (int i = 1; i < allBatters.size(); i++) {
            if (allBatters.get(i).getIntStats().get("abs") > MINIMUM_AT_BATS) {
                for (Map.Entry<String, Double> stat : allBatters.get(i).getDubStats().entrySet()) {
                    if (allBatters.get(i).getDubStats().get(stat.getKey()).compareTo(highests.get(stat.getKey())) > 0) {
                        highests.put(stat.getKey(), allBatters.get(i).getDubStats().get(stat.getKey()));
                    }

                }
            }
        }

        return highests;

    }

    // Gives each stat for each player a rating on a scale of 100 - 0, 100 being the best, 0 being the worst
    // but impossible with a filter
    public static void rateStats() {

        for (Batter batter : allBatters) {
            if (batter.getIntStats().get("abs") > MINIMUM_AT_BATS) {
                HashMap<String, Double> statRatings = new HashMap<>();
                for (Map.Entry<String, Integer> stat: batter.getIntStats().entrySet()) {
                    Double statValue = stat.getValue().doubleValue();
                    Double lowestStat = lowestBatter.getIntStats().get(stat.getKey()).doubleValue();
                    Double highestStat = highestBatter.getIntStats().get(stat.getKey()).doubleValue();
                    Double statRating = 100 * ( (statValue - lowestStat) / (highestStat - lowestStat) );
                    statRatings.put(stat.getKey(), statRating);
                }
                for (Map.Entry<String, Double> stat: batter.getDubStats().entrySet()) {
                    Double statValue = stat.getValue();
                    Double lowestStat = lowestBatter.getDubStats().get(stat.getKey());
                    Double highestStat = highestBatter.getDubStats().get(stat.getKey());
                    Double statRating = 100 * ( (statValue - lowestStat) / (highestStat - lowestStat) );
                    statRatings.put(stat.getKey(), statRating);
                }
                batter.setRatings(statRatings);
            }
        }
    }

    public static void avgRatings() {

        for (Batter batter : allBatters) {
            Double sumOfRatings = 0.0;
            Double numOfStats = 0.0;
            for (Map.Entry<String, Double> statRating : batter.getRatings().entrySet()) {
                sumOfRatings += statRating.getValue();
                numOfStats += 1;
            }
            batter.setAverageOfRatings(sumOfRatings / numOfStats);
        }
    }

    public static void maxAvgRatings() {

        highestBatter.setAverageOfRatings(allBatters.get(0).getAverageOfRatings());

        for (int i = 1; i < allBatters.size(); i++) {
            if (allBatters.get(i).getIntStats().get("abs") > MINIMUM_AT_BATS &&
                    allBatters.get(i).getAverageOfRatings().compareTo(highestBatter.getAverageOfRatings()) > 0) {
                highestBatter.setAverageOfRatings(allBatters.get(i).getAverageOfRatings());
            }
        }
    }

    public static void minAvgRatings() {

        lowestBatter.setAverageOfRatings(allBatters.get(0).getAverageOfRatings());

        for (int i = 1; i < allBatters.size(); i++) {
            if (allBatters.get(i).getIntStats().get("abs") > MINIMUM_AT_BATS &&
                    allBatters.get(i).getAverageOfRatings().compareTo(lowestBatter.getAverageOfRatings()) < 0) {
                lowestBatter.setAverageOfRatings(allBatters.get(i).getAverageOfRatings());
            }
        }
    }

    public static void giveBattersRatings() {

        for(Batter batter : allBatters) {
            if (batter.getIntStats().get("abs") > MINIMUM_AT_BATS) {
                Double lowestAvgRating = lowestBatter.getAverageOfRatings();
                Double highestAvgRating = highestBatter.getAverageOfRatings();
                batter.setRating(100 * ((batter.getAverageOfRatings() - lowestAvgRating) /
                        (highestAvgRating - lowestAvgRating)));
            }
        }

    }

    public static void rankByRating() {

        allBatters.sort(new RankPlayerByRating());

        // Set all players to 1 in case of multiple updates for debugging purposes
        for (Integer i = 0; i < allBatters.size(); i++) {
            allBatters.get(i).setRank(1);
        }

        for (Integer i = 0; i < allBatters.size(); i++) {
            allBatters.get(i).setRank(i + 1);   // +1 because baseball players don't want to be a 0
        }

    }


}

