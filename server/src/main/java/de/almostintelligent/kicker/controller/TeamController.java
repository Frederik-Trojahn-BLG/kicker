package de.almostintelligent.kicker.controller;

import de.almostintelligent.kicker.api.request.CreateTeamRequest;
import de.almostintelligent.kicker.ember.EmberModel;
import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.media.MediaType;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.model.TeamInvitation;
import de.almostintelligent.kicker.service.AccountService;
import de.almostintelligent.kicker.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class TeamController {

    private TeamService teamService;
    private AccountService accountService;

    @Autowired
    public TeamController(TeamService teamService, AccountService accountService) {
        this.teamService = teamService;
        this.accountService = accountService;
    }

    @RequestMapping(
            value = "/api/teams",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8)
    public EmberModel getTeams() throws AccountNotFoundException, LoginFailedException {
        Account account = accountService.currentUser();
        Set<Team> teams = account.getTeams();
        Set<Account> members = new HashSet<>();
        members.add(account);
        teams.forEach(team -> {
            team.getMembers().forEach((member) -> {
                members.add(member);
            });
        });
        return new EmberModel.Builder(Team.class, teams)
                .sideLoad(Account.class, members)
                .build();
    }

    @RequestMapping(
            value = "/api/teams",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8,
            produces = MediaType.APPLICATION_JSON_UTF8)
    public EmberModel createTeam(@RequestBody CreateTeamRequest createTeamRequest) throws AccountNotFoundException, LoginFailedException {
        Team team = teamService.createTeam(createTeamRequest);
        team = accountService.addTeamToAccount(team, accountService.currentUser());
        return new EmberModel.Builder(Team.class, team).build();
    }

    @RequestMapping(
            value = "/api/teamInvitations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8
    )
    public EmberModel getTeamInvitations() throws AccountNotFoundException, LoginFailedException {
        Account account = accountService.currentUser();
        return new EmberModel.Builder(TeamInvitation.class, account.getTeamInvitations())
                .sideLoad(Team.class, account.getTeams())
                .build();
    }

}
