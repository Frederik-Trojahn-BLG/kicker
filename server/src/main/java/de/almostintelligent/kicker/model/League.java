package de.almostintelligent.kicker.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "league")
@EqualsAndHashCode(callSuper = false)
public class League extends BaseEntity {

    private String name;

    @ManyToMany(targetEntity = Team.class)
    private Set<Team> teams;

    @OneToMany(mappedBy = "league")
    private Set<Game> games;

}
