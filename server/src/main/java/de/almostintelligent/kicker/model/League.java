package de.almostintelligent.kicker.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "league")
@EqualsAndHashCode(callSuper = false, exclude = {"account", "teams", "games"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Account.class)
public class League extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "leagues")
    private Set<Team> teams;

    @OneToMany(mappedBy = "league")
    private Set<Game> games;

    @ManyToOne(optional = false)
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @JsonIgnore
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
