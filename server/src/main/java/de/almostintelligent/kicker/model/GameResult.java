package de.almostintelligent.kicker.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "game_result")
@EqualsAndHashCode(callSuper = false)
public class GameResult extends BaseEntity {

    @ManyToOne(optional = false)
    private Team team;

    private Integer score;

    @ManyToOne
    private Game game;

    @JsonIdentityReference(alwaysAsId = true)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
