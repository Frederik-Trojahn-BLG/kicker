package de.almostintelligent.kicker.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "game")
@EqualsAndHashCode(callSuper = false)
public class Game extends BaseEntity {

    @ManyToOne
    private League league;

    @ManyToOne
    private Team team1;
    private Integer scoreTeam1;

    @ManyToOne
    private Team team2;
    private Integer scoreTeam2;

}
