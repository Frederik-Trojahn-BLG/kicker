package de.almostintelligent.kicker.repository;

import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, String> {

    @Query("SELECT DISTINCT t FROM team t JOIN t.members m WHERE :account in m")
    List<Team> getTeamsOfAccount(@Param("account") Account account);

}
