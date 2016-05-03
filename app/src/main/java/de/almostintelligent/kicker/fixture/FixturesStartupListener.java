package de.almostintelligent.kicker.fixture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class FixturesStartupListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    Logger logger = LoggerFactory.getLogger("de.almostintelligent.kicker");


    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2ddlAuto;

    public FixturesStartupListener() {
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        if (hibernateHBM2ddlAuto.equalsIgnoreCase("create")) {
        }
    }
}
