package com.example.scenarios;

import io.gatling.javaapi.core.ScenarioBuilder;
import com.example.steps.WebToursSteps;
import com.example.feeders.TestData;

import static io.gatling.javaapi.core.CoreDsl.scenario;

public class UserScenarios {

    public static final ScenarioBuilder bookFlightScenario = scenario("Buy Ticket Scenario")
            .exec(WebToursSteps.openHomePage)
            .exec(WebToursSteps.initSession)
            .exec(WebToursSteps.getLoginFormAndSession)
            .exec(WebToursSteps.login)
            .exec(WebToursSteps.openFlightsPage)
            .feed(TestData.CITY_FEEDER) // берём случайные города
            .exec(WebToursSteps.searchFlights)
            .exec(WebToursSteps.selectFlight)
            .exec(WebToursSteps.buyTicket)
            .exec(WebToursSteps.returnHome);
}