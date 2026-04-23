package com.example.simulations;

import io.gatling.javaapi.core.Simulation;
import com.example.config.AppConfig;
import com.example.scenarios.UserScenarios;

import static io.gatling.javaapi.core.CoreDsl.*;

public class ReliabilitySimulation extends Simulation {
    {
        setUp(
                UserScenarios.bookFlightScenario.injectOpen(
                        rampUsersPerSec(0).to(6).during(120),
                        constantUsersPerSec(6).during(3600)
                )
        ).protocols(AppConfig.HTTP_PROTOCOL);
    }
}