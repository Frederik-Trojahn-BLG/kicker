package de.almostintelligent.kicker.service;

import de.almostintelligent.kicker.api.dto.TeamDTO;
import de.almostintelligent.kicker.api.request.CreateTeamRequest;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
