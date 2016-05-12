package de.almostintelligent.kicker.controller;

import de.almostintelligent.kicker.api.request.CreateTeamRequest;
import de.almostintelligent.kicker.api.request.InviteAccountToTeamRequest;
import de.almostintelligent.kicker.ember.EmberModel;
import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.exception.TeamInvitationFailedException;
import de.almostintelligent.kicker.exception.TeamNotFoundException;
import de.almostintelligent.kicker.media.MediaType;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.League;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.model.TeamInvitation;
import de.almostintelligent.kicker.service.AccountService;
import de.almostintelligent.kicker.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Account account = accountService.currentAccount();
        Set<Team> teams = account.getTeams();
        return new EmberModel.Builder(Team.class, teams)
                .sideLoad(Account.class, teamService.getMembersFromTeams(teams))
                .build();
    }

    @RequestMapping(
            value = "/api/teams",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8,
            produces = MediaType.APPLICATION_JSON_UTF8)
    public EmberModel createTeam(@RequestBody CreateTeamRequest createTeamRequest) throws AccountNotFoundException, LoginFailedException {
        Team team = teamService.createTeam(createTeamRequest);
        team = accountService.addTeamToAccount(team, accountService.currentAccount());
        return new EmberModel.Builder(Team.class, team).build();
    }

    @RequestMapping(
            value = "/api/teams/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8)
    public EmberModel getTeam(@PathVariable String id) throws TeamNotFoundException {
        Team team = teamService.getTeam(id);
        return new EmberModel.Builder(Team.class, team)
                .sideLoad(League.class, team.getLeagues())
                .sideLoad(Account.class, team.getMembers())
                .build();
    }

    @RequestMapping(
            value = "/api/teamInvitations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8
    )
    public EmberModel getTeamInvitations() throws AccountNotFoundException, LoginFailedException {
        Account account = accountService.currentAccount();
        return new EmberModel.Builder(TeamInvitation.class, account.getTeamInvitations())
                .sideLoad(Team.class, account.getTeams())
                .build();
    }

    @RequestMapping(
            value = "/api/teamInvitations",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8,
            produces = MediaType.APPLICATION_JSON_UTF8
    )
    public EmberModel inviteAccountToTeam(@RequestBody InviteAccountToTeamRequest inviteAccountToTeamRequest)
            throws AccountNotFoundException, LoginFailedException, TeamNotFoundException, TeamInvitationFailedException {
        TeamInvitation teamInvitation = teamService.inviteAccountToTeam(inviteAccountToTeamRequest);
        return new EmberModel.Builder(TeamInvitation.class, teamInvitation).build();
    }

}
