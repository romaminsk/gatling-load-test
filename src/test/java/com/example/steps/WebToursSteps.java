package com.example.steps;

import io.gatling.javaapi.core.ChainBuilder;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class WebToursSteps {

    public static final ChainBuilder openHomePage = exec(http("1_Main_Page").get("/webtours/")).pause(1);

    public static final ChainBuilder initSession = exec(http("1_1_Welcome").get("/cgi-bin/welcome.pl?signOff=true")).pause(1);

    public static final ChainBuilder getLoginFormAndSession = exec(
            http("1_2_Nav_Home")
                    .get("/cgi-bin/nav.pl?in=home")
                    .check(regex("name=\"userSession\" value=\"([^\"]+)\"").saveAs("userSession"))
    ).pause(1);

    public static final ChainBuilder login = exec(
            http("2_Login")
                    .post("/cgi-bin/login.pl")
                    .formParam("userSession", "#{userSession}")
                    .formParam("username", "testuser1313")
                    .formParam("password", "123")
                    .formParam("login.x", "76")
                    .formParam("login.y", "3")
                    .formParam("JSFormSubmit", "off")
    ).pause(1);

    public static final ChainBuilder openFlightsPage = exec(http("3_Flights_Page").get("/cgi-bin/nav.pl?in=flights")).pause(1);

    public static final ChainBuilder searchFlights = exec(
            http("4_Search_Flights")
                    .post("/cgi-bin/reservations.pl")
                    .formParam("depart", "#{departCity}")
                    .formParam("arrive", "#{arriveCity}")
                    .formParam("departDate", "12/01/2025")
                    .formParam("returnDate", "12/15/2025")
                    .formParam("numPassengers", "1")
                    .formParam("seatPref", "None")
                    .formParam("seatType", "Coach")
                    .formParam("findFlights.x", "50")
                    .formParam("findFlights.y", "10")
                    .check(regex("name=\"outboundFlight\" value=\"([^\"]+)\"").findRandom().saveAs("outboundFlight"))
    ).pause(1);

    public static final ChainBuilder selectFlight = exec(
            http("5_Select_Flight")
                    .post("/cgi-bin/reservations.pl")
                    .formParam("outboundFlight", "#{outboundFlight}")
                    .formParam("reserveFlights.x", "50")
                    .formParam("reserveFlights.y", "10")
    ).pause(1);

    public static final ChainBuilder buyTicket = exec(
            http("6_Buy_Ticket")
                    .post("/cgi-bin/reservations.pl")
                    .formParam("firstName", "Test")
                    .formParam("lastName", "User")
                    .formParam("address1", "123 Street")
                    .formParam("address2", "")
                    .formParam("city", "Denver")
                    .formParam("state", "CO")
                    .formParam("zipCode", "12345")
                    .formParam("creditCard", "1234567890123456")
                    .formParam("expDate", "12/2026")
                    .formParam("buyFlights.x", "50")
                    .formParam("buyFlights.y", "10")
    ).pause(1);

    public static final ChainBuilder returnHome = exec(http("7_Return_Home").get("/webtours/")).pause(1);
}