package de.almostintelligent.kicker.service;

import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.League;
import de.almostintelligent.kicker.model.Team;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LeagueService {

    public Set<League> getLeaguesFromUser(Account account) {
        Set<Team> teams = account.getTeams();
        Set<League> leagues = new HashSet<>();

        for (Team team : teams) {
            leagues.addAll(team.getLeagues());
        }
        return leagues;
    }

}
