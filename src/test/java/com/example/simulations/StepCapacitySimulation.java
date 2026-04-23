package com.example.simulations;

import io.gatling.javaapi.core.Simulation;
import com.example.config.AppConfig;
import com.example.scenarios.UserScenarios;

import static io.gatling.javaapi.core.CoreDsl.*;

public class StepCapacitySimulation extends Simulation {
    {
        setUp(
                UserScenarios.bookFlightScenario.injectOpen(
                        incrementUsersPerSec(1.0)
                                .times(10)
                                .eachLevelLasting(120)
                                .startingFrom(0.0)
                )
        ).protocols(AppConfig.HTTP_PROTOCOL);
    }
}