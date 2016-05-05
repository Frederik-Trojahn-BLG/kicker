package de.almostintelligent.kicker.api.request;

import de.almostintelligent.kicker.api.dto.TeamDTO;

/**
 * Created by frederiktrojahn on 05.05.16.
 */
public class CreateTeamRequest {
    private TeamDTO team;

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
}
