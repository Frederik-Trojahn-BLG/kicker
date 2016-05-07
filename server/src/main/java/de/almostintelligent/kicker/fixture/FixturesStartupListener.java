package de.almostintelligent.kicker.fixture;

import de.almostintelligent.kicker.model.Account;
import de.almostintelligent.kicker.model.Role;
import de.almostintelligent.kicker.repository.AccountRepository;
import de.almostintelligent.kicker.repository.RoleRepository;
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
    private RoleRepository roleRepository;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2ddlAuto;

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.name}")
    private String adminName;
    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    public FixturesStartupListener(AccountRepository accountRepository,
                                   RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        if (hibernateHBM2ddlAuto.equalsIgnoreCase("create")) {
            createAdmin();
            createUser();
        }
    }

    private void createAdmin() {
        Account admin = new Account();
        admin.setEmail(adminEmail);
        admin.setName(adminName);
        admin.setPassword(BCrypt.hashpw(adminPassword, BCrypt.gensalt()));
        admin = accountRepository.save(admin);

        Set<Role> roles = new HashSet<>();

        Role roleAdmin = Role.admin();
        roleAdmin.setAccount(admin);
        roles.add(roleAdmin);

        Role roleUser = Role.user();
        roleUser.setAccount(admin);
        roles.add(roleUser);

        admin.setRoles(roles);
        accountRepository.save(admin);
    }

    private void createUser() {
        Account user = new Account();
        user.setEmail("user@user.com");
        user.setName("User User");
        user.setPassword(BCrypt.hashpw("12345678", BCrypt.gensalt()));
        user = accountRepository.save(user);

        Set<Role> roles = new HashSet<>();

        Role roleUser = Role.user();
        roleUser.setAccount(user);
        roles.add(roleUser);

        user.setRoles(roles);
        accountRepository.save(user);
    }
}
