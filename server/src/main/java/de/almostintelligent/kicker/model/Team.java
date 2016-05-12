package de.almostintelligent.kicker.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.almostintelligent.kicker.util.EntityUtils;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity(name = "team")
@EqualsAndHashCode(callSuper = true, exclude = {"members", "leagues", "results", "teamInvitations", "leagueTableEntries"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Account.class)
public class Team extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "teams")
    private Set<Account> members;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "team_in_league",
            joinColumns = @JoinColumn(
                    name = "team_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "league_id",
                    referencedColumnName = "id"))
    private Set<League> leagues;

    @OneToMany(mappedBy = "team")
    private Set<GameResult> results;

    @OneToMany(mappedBy = "team")
    private Set<TeamInvitation> teamInvitations;

    @OneToMany(mappedBy = "team")
    private Set<LeagueTableEntry> leagueTableEntries;

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

    @JsonGetter(value = "members")
    public Collection<String> getMemberIds() {
        return EntityUtils.toIds(getMembers());
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

    public Set<TeamInvitation> getTeamInvitations() {
        return teamInvitations;
    }

    public void setTeamInvitations(Set<TeamInvitation> teamInvitations) {
        this.teamInvitations = teamInvitations;
    }

    public Set<LeagueTableEntry> getLeagueTableEntries() {
        return leagueTableEntries;
    }

    public void setLeagueTableEntries(Set<LeagueTableEntry> leagueTableEntries) {
        this.leagueTableEntries = leagueTableEntries;
    }
}
