package de.almostintelligent.kicker.service;

import de.almostintelligent.kicker.api.dto.TeamDTO;
import de.almostintelligent.kicker.api.request.CreateTeamRequest;
import de.almostintelligent.kicker.api.request.InviteAccountToTeamRequest;
import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.exception.TeamInvitationFailedException;
import de.almostintelligent.kicker.exception.TeamNotFoundException;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.model.TeamInvitation;
import de.almostintelligent.kicker.repository.TeamInvitationRepository;
import de.almostintelligent.kicker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class TeamService {

    private TeamRepository teamRepository;
    private AccountService accountService;
    private TeamInvitationRepository teamInvitationRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository,
                       AccountService accountService,
                       TeamInvitationRepository teamInvitationRepository) {
        this.teamRepository = teamRepository;
        this.accountService = accountService;
        this.teamInvitationRepository = teamInvitationRepository;
    }

    public Team createTeam(CreateTeamRequest createTeamRequest) {
        TeamDTO teamDTO = createTeamRequest.getTeam();
        Team team = new Team();
        team.setName(teamDTO.getName());
        return teamRepository.save(team);
    }

    public Collection<TeamInvitation> getTeamInvitations(Account account) {
        return account.getTeamInvitations();
    }

    public Collection<Account> getMembersFromTeams(Collection<Team> teams) {
        Set<Account> members = new HashSet<>();
        teams.forEach(team -> {
            team.getMembers().forEach((member) -> {
                members.add(member);
            });
        });
        return members;
    }

    public Team getTeam(String id) throws TeamNotFoundException {
        if (id != null) {
            Team team = teamRepository.findOne(id);
            if (team != null) {
                return team;
            }
        }

        throw new TeamNotFoundException();
    }

    private TeamInvitation createInvitation(Account account, Team team) {
        TeamInvitation teamInvitation = new TeamInvitation();
        teamInvitation.setTeam(team);
        teamInvitation.setAccount(account);

        return teamInvitationRepository.save(teamInvitation);
    }

    public TeamInvitation inviteAccountToTeam(InviteAccountToTeamRequest inviteAccountToTeamRequest)
            throws AccountNotFoundException, LoginFailedException, TeamNotFoundException, TeamInvitationFailedException {
        Team team = this.getTeam(inviteAccountToTeamRequest.getTeamInvitation().getTeam());
        Account account = accountService.getAccount(inviteAccountToTeamRequest.getTeamInvitation().getAccount());

        if (!team.getMembers().contains(account)) {
            return createInvitation(account, team);
        }

        throw new TeamInvitationFailedException();
    }
}
