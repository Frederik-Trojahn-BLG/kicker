package de.almostintelligent.kicker.repository;

import de.almostintelligent.kicker.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends JpaRepository<Team, String> {

}
