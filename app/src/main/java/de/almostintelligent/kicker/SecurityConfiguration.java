package de.almostintelligent.kicker;

import de.almostintelligent.kicker.model.Role;
import de.almostintelligent.kicker.service.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String CLIENT_ID = "kicker";
    private static final String RESOURCE_ID = "restservice";
    private static final String SECRET = "secret";

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(accountDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Configuration
    @EnableResourceServer
    @EnableGlobalMethodSecurity(securedEnabled = true)
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources
                    .resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/index.html",
                            "/robots.txt",
                            "/*.ico",
                            "/*.png",
                            "/bower_components/**",
                            "/scripts/**",
                            "/assets/**",
                            "/views/**",
                            "/styles/**").anonymous();
                    //.antMatchers("/api/register").anonymous()
                    //.antMatchers("/api/blogs").anonymous()
                    //.antMatchers("/api/**").hasRole("USER")
                    //.antMatchers("/dev/**").permitAll()
                    //.antMatchers("/admin/**").hasRole("ADMIN");
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

        @Autowired
        @Qualifier("dataSource")
        public DataSource dataSource;

        public DefaultTokenServices tokenServices;

        private TokenStore tokenStore;

        @PostConstruct
        private void setUpTokenStore() {
            tokenStore = new JdbcTokenStore(dataSource);
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setReuseRefreshToken(false);
            tokenServices.setTokenStore(this.tokenStore);
            tokenServices.setAccessTokenValiditySeconds(12 * 60 * 60);
            tokenServices.setRefreshTokenValiditySeconds(60 * 24 * 60 * 60);
            this.tokenServices = tokenServices;
        }

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .tokenStore(tokenStore)
                    .tokenServices(tokenServices)
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient(CLIENT_ID)
                    .authorizedGrantTypes("password", "refresh_token")
                    .authorities(Role.USER, Role.ADMIN)
                    .scopes("read", "write")
                    .secret(SECRET)
                    .resourceIds(RESOURCE_ID);
        }
    }
}
