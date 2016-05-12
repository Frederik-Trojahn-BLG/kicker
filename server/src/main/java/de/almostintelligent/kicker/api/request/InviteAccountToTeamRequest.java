package de.almostintelligent.kicker.api.request;

import de.almostintelligent.kicker.api.dto.TeamInvitationDTO;

/**
 * Created by frederiktrojahn on 12.05.16.
 */
public class InviteAccountToTeamRequest {

    private TeamInvitationDTO teamInvitation;

    public TeamInvitationDTO getTeamInvitation() {
        return teamInvitation;
    }

    public void setTeamInvitation(TeamInvitationDTO teamInvitation) {
        this.teamInvitation = teamInvitation;
    }
}
