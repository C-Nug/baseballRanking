package org.launchcode.baseballPlayerRater.models;

import java.util.Comparator;

public class RankPlayerByRating implements Comparator<Player> {

    public int compare(Player a, Player b) {
        return b.getRating().compareTo(a.getRating());
    }
}
