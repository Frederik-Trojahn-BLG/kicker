package de.almostintelligent.kicker.controller;

import de.almostintelligent.kicker.ember.EmberModel;
import de.almostintelligent.kicker.exception.AccountNotFoundException;
import de.almostintelligent.kicker.exception.LoginFailedException;
import de.almostintelligent.kicker.media.MediaType;
import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(
            value = "/api/currentUsers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8
    )
    public EmberModel getCurrentUser() throws AccountNotFoundException, LoginFailedException {
        return new EmberModel.Builder(AccountService.CURRENT_USER, accountService.currentAccount()).build();
    }

    @RequestMapping(
            value = "api/accounts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8
    )
    public EmberModel getAccounts() {
        return new EmberModel.Builder(Account.class, accountService.getAccounts()).build();
    }

    @RequestMapping(
            value = "api/accounts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8
    )
    public EmberModel getAccount(@PathVariable("id") String id) throws AccountNotFoundException {
        return new EmberModel.Builder(Account.class, accountService.getAccount(id)).build();
    }

}
