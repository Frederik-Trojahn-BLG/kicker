package de.almostintelligent.kicker.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "game")
@EqualsAndHashCode(callSuper = true, exclude = {"league", "results", "gamePlanEntry"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Account.class)
public class Game extends BaseEntity {

    public enum GameState {
        OPEN, CLOSE
    }

    @ManyToOne
    private League league;

    @OneToMany(mappedBy = "game")
    private Set<GameResult> results;

    @Enumerated(EnumType.STRING)
    private GameState gameState = GameState.OPEN;

    @OneToOne
    private GamePlanEntry gamePlanEntry;

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

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GamePlanEntry getGamePlanEntry() {
        return gamePlanEntry;
    }

    public void setGamePlanEntry(GamePlanEntry gamePlanEntry) {
        this.gamePlanEntry = gamePlanEntry;
    }
}
