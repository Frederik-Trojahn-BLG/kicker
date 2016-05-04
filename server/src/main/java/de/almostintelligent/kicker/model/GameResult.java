package de.almostintelligent.kicker.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "game_result")
@EqualsAndHashCode(callSuper = false)
public class GameResult extends BaseEntity {

    @ManyToOne(optional = false)
    private Team team;

    private Integer score;

}
