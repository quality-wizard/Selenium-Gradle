package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.PaginaCursos;
import pages.PaginaPrincipal;

/**
 * FreeRangeSteps class contains step definitions for navigating the Free Range
 * Testers website.
 * It uses the PaginaPrincipal page object to interact with the homepage and
 * perform actions.
 */
public class FreeRangeSteps {

    PaginaPrincipal landingPage = new PaginaPrincipal();
    PaginaCursos cursosPage = new PaginaCursos();

    @Given("I navigate to {string}")
    public void navigateToFreeRangeTesters(String url) {
        landingPage.navigateToFreeRangeTesters(url);
    }

    @When("I go to the {word} using the navigation bar")
    public void navigationBarUse(String section) {
        landingPage.goToSectionUsingNavigationBar(section);
    }

    @And("I select Introducción al Testing")
    public void selectIntroduccionAlTesting() {
        cursosPage.goToFundamentosTestingLink();
    }

}
