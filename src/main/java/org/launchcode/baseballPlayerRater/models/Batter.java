package org.launchcode.baseballPlayerRater.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by CNUG on 8/1/17.
 */

@Entity
@DiscriminatorValue("BATTER")
public class Batter extends Player {

    private Integer runs;

    private Integer homeRuns;

    private Integer rbis;

    private Integer strikeOuts;

    private Integer stolenBases;

    private Double onBasePercent;

    private Double slugging;

    public Batter(Integer id, String name, String team, String positions, Integer runs, Integer homeRuns, Integer rbis, Integer strikeOuts,
                  Integer stolenBases, Double onBasePercent, Double slugging) {
        super(id, name, team, positions);
        this.runs = runs;
        this.homeRuns = homeRuns;
        this.rbis = rbis;
        this.strikeOuts = strikeOuts;
        this.stolenBases = stolenBases;
        this.onBasePercent = onBasePercent;
        this.slugging = slugging;

    }

    public Batter() {}

    public Integer getRuns() {
        return runs;
    }

    public Integer getHomeRuns() {
        return homeRuns;
    }

    public Integer getRbis() {
        return rbis;
    }

    public Integer getStrikeOuts() {
        return strikeOuts;
    }

    public Integer getStolenBases() {
        return stolenBases;
    }

    public Double getOnBasePercent() {
        return onBasePercent;
    }

    public Double getSlugging() {
        return slugging;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public void setHomeRuns(Integer homeRuns) {
        this.homeRuns = homeRuns;
    }

    public void setRbis(Integer rbis) {
        this.rbis = rbis;
    }

    public void setStrikeOuts(Integer strikeOuts) {
        this.strikeOuts = strikeOuts;
    }

    public void setStolenBases(Integer stolenBases) {
        this.stolenBases = stolenBases;
    }

    public void setOnBasePercent(Double onBasePercent) {
        this.onBasePercent = onBasePercent;
    }

    public void setSlugging(Double slugging) {
        this.slugging = slugging;
    }
}
