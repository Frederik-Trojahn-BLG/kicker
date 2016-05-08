package de.almostintelligent.kicker.api.request;

import de.almostintelligent.kicker.api.dto.LeagueDTO;

/**
 * Created by frederiktrojahn on 07.05.16.
 */
public class CreateLeagueRequest {

    private LeagueDTO league;

    public LeagueDTO getLeague() {
        return league;
    }

    public void setLeague(LeagueDTO league) {
        this.league = league;
    }
}
