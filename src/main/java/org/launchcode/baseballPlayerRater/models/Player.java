package org.launchcode.baseballPlayerRater.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by CNUG on 8/1/17.
 */

@Entity
@Inheritance
@DiscriminatorColumn(name = "PLAYER_TYPE")
public abstract class Player {

    @Id
    protected Integer id;

    @NotNull
    protected String name;

    @NotNull
    protected String team;

    protected Integer rank;

    @NotNull
    protected String positions;

    protected Double dollarAmount;

    public Player(Integer id, String name, String team, String positions) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.positions = positions;
        this.rank = 1;
        this.dollarAmount = 0.0;
    }

    public Player() { }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Integer getRank() {
        return rank;
    }

    public String getPositions() {
        return positions;
    }

    public Double getDollarAmount() {
        return dollarAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public void setDollarAmount(Double dollarAmount) {
        this.dollarAmount = dollarAmount;
    }
}
