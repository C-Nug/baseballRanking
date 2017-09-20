package org.launchcode.baseballPlayerRater.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by CNUG on 9/6/17.
 */
@Entity
public class Team {

    @Id
    @GeneratedValue
    private Integer id;

    private String team;

    public Team(String team) {
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
