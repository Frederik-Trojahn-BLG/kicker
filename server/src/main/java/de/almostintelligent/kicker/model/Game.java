package de.almostintelligent.kicker.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "game")
@EqualsAndHashCode(callSuper = false)
public class Game extends BaseEntity {

    @ManyToOne
    private League league;

    @OneToMany(mappedBy = "game")
    private Set<GameResult> results;

    @JsonIdentityReference(alwaysAsId = true)
    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @JsonIgnore
    public Set<GameResult> getResults() {
        return results;
    }

    public void setResults(Set<GameResult> results) {
        this.results = results;
    }
}
