package ua.epam.resthomework.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component

public class HealthCheck implements HealthIndicator {

    private final String appName = "rest-homework";
    private final String detail = "App name";

    @Override
    public Health health() {
        Health.Builder builder;
        if (ControllerAdvisor.isIsAppTerminated()) {
            builder = Health.down();
        }
        else {
            builder = Health.up();
        }
        return builder.withDetail(detail, appName).build();
    }
}
