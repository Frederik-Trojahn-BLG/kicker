package de.almostintelligent.kicker.repository;

import de.almostintelligent.kicker.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, String> {

}
