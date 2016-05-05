package de.almostintelligent.kicker.repository;

import de.almostintelligent.kicker.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findOneByEmail(String email);


}
