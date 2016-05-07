package de.almostintelligent.kicker.service;

import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.Team;
import de.almostintelligent.kicker.repository.AccountRepository;
import de.almostintelligent.kicker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


@Service
public class AccountService {

    public static final String CURRENT_USER = "current-user";

    Logger logger = Logger.getLogger(AccountService.class.getSimpleName());

    private AccountRepository accountRepository;
    private TeamRepository teamRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          TeamRepository teamRepository) {
        this.accountRepository = accountRepository;
        this.teamRepository = teamRepository;
    }

    public Account currentUser() throws AccountNotFoundException, LoginFailedException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            failedAuthentication("no security context found");
        }

        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            failedAuthentication("no authentication found");
        }

        UserDetails principal;
        if (authentication.getPrincipal() instanceof UserDetails) {
            principal = (UserDetails) authentication.getPrincipal();
        } else {
            throw new AccountNotFoundException();
        }

        return accountRepository.findOneByEmail(principal.getUsername());
    }

    private void failedAuthentication(String msg) throws LoginFailedException {
        logger.warning("failed authentication: " + msg);
        throw new LoginFailedException(msg);
    }

    /**
     * Add a Team to the given account
     *
     * @param team    Team to add
     * @param account Account to add to
     * @return Modified Team
     */
    public Team addTeamToAccount(Team team, Account account) {
        Set<Team> teams = account.getTeams();
        if (teams == null) {
            teams = new HashSet<>();
        }
        teams.add(team);
        account.setTeams(teams);

        accountRepository.save(account);
        accountRepository.flush();
        return teamRepository.findOne(team.getId());
    }

    public Account getAccount(String id) {
        if (id != null) {
            return accountRepository.findOne(id);
        }

        return null;
    }
}
