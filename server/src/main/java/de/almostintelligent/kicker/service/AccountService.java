package de.almostintelligent.kicker.service;

import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class AccountService {

    Logger logger = Logger.getLogger(AccountService.class.getSimpleName());

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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

        return accountRepository.findOne(principal.getUsername());
    }

    private void failedAuthentication(String msg) throws LoginFailedException {
        logger.warning("failed authentication: " + msg);
        throw new LoginFailedException(msg);
    }
}
