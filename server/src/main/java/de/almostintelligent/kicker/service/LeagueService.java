package de.almostintelligent.kicker.service;

import de.almostintelligent.kicker.api.request.CreateLeagueRequest;
import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.League;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LeagueService {

    private LeagueRepository leagueRepository;
    private AccountService accountService;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository,
                         AccountService accountService) {
        this.leagueRepository = leagueRepository;
        this.accountService = accountService;
    }

    public Set<League> getLeaguesFromUser(Account account) {
        Set<Team> teams = account.getTeams();
        Set<League> leagues = new HashSet<>();

        for (Team team : teams) {
            leagues.addAll(team.getLeagues());
        }
        return leagues;
    }

    public League createLeague(CreateLeagueRequest createLeagueRequest) throws AccountNotFoundException, LoginFailedException {

        Account account = accountService.currentAccount();

        League league = new League();
        league.setName(createLeagueRequest.getLeague().getName());
        league.setAccount(account);

        return leagueRepository.save(league);
    }
}
