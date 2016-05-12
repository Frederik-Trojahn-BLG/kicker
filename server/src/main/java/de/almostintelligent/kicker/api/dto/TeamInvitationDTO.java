package de.almostintelligent.kicker.api.dto;

/**
 * Created by frederiktrojahn on 12.05.16.
 */
public class TeamInvitationDTO {

    private String account;
    private String team;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
