package de.almostintelligent.kicker.repository;

import de.almostintelligent.kicker.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

    Account findOneByEmail(String email);


}
