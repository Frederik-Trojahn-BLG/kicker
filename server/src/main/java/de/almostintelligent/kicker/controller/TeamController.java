package de.almostintelligent.kicker.controller;

import de.almostintelligent.kicker.ember.EmberModel;
import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.media.MediaType;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.service.AccountService;
import de.almostintelligent.kicker.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamService teamService;
    private AccountService accountService;

    @Autowired
    public TeamController(TeamService teamService, AccountService accountService) {
        this.teamService = teamService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/api/teams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
    public EmberModel getTeams() throws AccountNotFoundException, LoginFailedException {
        return new EmberModel.Builder(Team.class, accountService.currentUser().getTeams()).build();
    }

}
