package de.almostintelligent.kicker.controller;

import de.almostintelligent.kicker.api.request.CreateLeagueRequest;
import de.almostintelligent.kicker.ember.EmberModel;
import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.media.MediaType;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.League;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.service.AccountService;
import de.almostintelligent.kicker.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeagueController {

    private AccountService accountService;
    private LeagueService leagueService;

    @Autowired
    public LeagueController(AccountService accountService,
                            LeagueService leagueService) {
        this.accountService = accountService;
        this.leagueService = leagueService;
    }

    @RequestMapping(
            value = "/api/leagues",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8
    )
    public EmberModel getLeagues() throws AccountNotFoundException, LoginFailedException {
        Account account = accountService.currentUser();
        return new EmberModel.Builder(League.class, account.getLeagues())
                .sideLoad(Team.class, account.getTeams())
                .build();
    }

    @RequestMapping(
            value = "/api/leagues",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8
    )
    public EmberModel createLeague(@RequestBody CreateLeagueRequest createLeagueRequest) throws AccountNotFoundException, LoginFailedException {
        Account account = accountService.currentUser();
        League league = leagueService.createLeague(createLeagueRequest);

        return new EmberModel.Builder(League.class, league)
                .sideLoad(Team.class, account.getTeams())
                .build();
    }


}
