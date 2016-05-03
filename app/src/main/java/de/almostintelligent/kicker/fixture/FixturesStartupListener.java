package de.almostintelligent.kicker.fixture;

import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.Role;
import de.almostintelligent.kicker.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FixturesStartupListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    Logger logger = LoggerFactory.getLogger("de.almostintelligent.kicker");

    private AccountRepository accountRepository;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2ddlAuto;

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.name}")
    private String adminName;
    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    public FixturesStartupListener(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        if (hibernateHBM2ddlAuto.equalsIgnoreCase("create")) {
            createAdmin();
        }
    }

    private void createAdmin() {
        Account admin = new Account();
        admin.setEmail(adminEmail);
        admin.setName(adminName);
        admin.setPassword(BCrypt.hashpw(adminPassword, BCrypt.gensalt()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.admin());
        roles.add(Role.user());
        admin.setRoles(roles);
        admin = accountRepository.save(admin);
    }
}
