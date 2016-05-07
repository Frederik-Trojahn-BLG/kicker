package de.almostintelligent.kicker.service;

import de.almostintelligent.kicker.api.dto.TeamDTO;
import de.almostintelligent.kicker.api.request.CreateTeamRequest;
import de.almostintelligent.kicker.exception.TeamNotFoundException;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.model.TeamInvitation;
import de.almostintelligent.kicker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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
            return teamRepository.findOne(id);
        }

        throw new TeamNotFoundException();
    }
}
