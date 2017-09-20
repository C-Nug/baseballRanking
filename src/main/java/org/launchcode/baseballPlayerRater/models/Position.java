package org.launchcode.baseballPlayerRater.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by CNUG on 9/6/17.
 */
@Entity
public class Position {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String position;

    public Position(String position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void addPosition(Position newPosition) {
        this.position = this.position + "," + newPosition.getPosition();
    }
}
