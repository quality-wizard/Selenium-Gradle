package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.PaginaPrincipal;

/**
 * FreeRangeSteps class contains step definitions for navigating the Free Range
 * Testers website.
 * It uses the PaginaPrincipal page object to interact with the homepage and
 * perform actions.
 */
public class FreeRangeSteps {

    PaginaPrincipal landingPage = new PaginaPrincipal();

    @Given("I am on the Free Range Testers homepage")
    public void navigateToFreeRangeTesters() {
        landingPage.navigateToFreeRangeTesters();
    }

    @When("I go to the {word} using the navigation bar")
    public void NavigationBarUse(String section) {
        landingPage.goToSectionUsingNavigationBar(section);
    }
}
