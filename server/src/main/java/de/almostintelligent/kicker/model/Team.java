package de.almostintelligent.kicker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "team")
@EqualsAndHashCode(callSuper = false, exclude = {"members", "leagues", "results"})
public class Team extends BaseEntity {

    private String name;

    @ManyToMany(targetEntity = Account.class, mappedBy = "teams")
    private Set<Account> members;

    @ManyToMany(targetEntity = League.class, mappedBy = "teams")
    private Set<League> leagues;

    @OneToMany(mappedBy = "team")
    private Set<GameResult> results;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<Account> getMembers() {
        return members;
    }

    public void setMembers(Set<Account> members) {
        this.members = members;
    }

    @JsonIgnore
    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }

    @JsonIgnore
    public Set<GameResult> getResults() {
        return results;
    }

    public void setResults(Set<GameResult> results) {
        this.results = results;
    }
}
