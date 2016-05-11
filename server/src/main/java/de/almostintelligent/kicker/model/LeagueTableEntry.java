package de.almostintelligent.kicker.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "league_table_entry")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(exclude = {"league", "team"}, callSuper = false)
public class LeagueTableEntry extends BaseEntity {

    @ManyToOne
    private League league;

    @ManyToOne
    private Team team;

    private Integer goalScored;
    private Integer points;
    private Integer goalReceived;
    private Integer position;
    private Integer positionDelta;

    @JsonIdentityReference(alwaysAsId = true)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(Integer goalScored) {
        this.goalScored = goalScored;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGoalReceived() {
        return goalReceived;
    }

    public void setGoalReceived(Integer goalReceived) {
        this.goalReceived = goalReceived;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPositionDelta() {
        return positionDelta;
    }

    public void setPositionDelta(Integer positionDelta) {
        this.positionDelta = positionDelta;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
