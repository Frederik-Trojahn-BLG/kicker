package de.almostintelligent.kicker.repository;

import de.almostintelligent.kicker.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, String> {
}
